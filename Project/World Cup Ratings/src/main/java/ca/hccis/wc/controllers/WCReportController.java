
package ca.hccis.wc.controllers;

import ca.hccis.wc.bo.WCRatingsBO;
import ca.hccis.wc.entity.WCReportRatings;
import ca.hccis.wc.entity.Player;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class WCReportController {

    private final WCRatingsBO wcRatingsBO;

    public WCReportController(WCRatingsBO wcRatingsBO) {
        this.wcRatingsBO = wcRatingsBO;
    }

    private static final Path REPORT_DIRECTORY =
            Path.of("reports");

    private static final String REPORT_FILE =
            "WorldCupPlayerEffectivenessRatings.txt";

    @GetMapping("/report/reportWCRatings")
    public String showReport(Model model) {

        model.addAttribute("wcReportRatings", new WCReportRatings());

        return "report/reportWCRatings";
    }

    /**
     * Handles the report form submission by validating the requested player limit,
     * retrieving the top players through the business object,
     * exporting the results to a text file,
     * and returning the completed report to the Thymeleaf view.
     */
    @PostMapping("/report/reportWCRatings")
    public String generateReport(
            @ModelAttribute("wcReportRatings")
            WCReportRatings wcReportRatings,
            Model model) {

        int limit = wcReportRatings.getLimit();

        if (limit < 1) {
            limit = 1;
        } else if (limit > 50) {
            limit = 50;
        }

        wcReportRatings.setLimit(limit);

        wcReportRatings.setPlayers(
                wcRatingsBO.processSelectTopPlayers(limit)
        );

        try {
            writeReportToFile(wcReportRatings);

            model.addAttribute(
                    "exportMessage",
                    "Report successfully saved to reports/"
                            + REPORT_FILE
            );

        } catch (IOException e) {

            model.addAttribute(
                    "exportMessage",
                    "Unable to save the report: " + e.getMessage()
            );
        }

        model.addAttribute("wcReportRatings", wcReportRatings);

        return "report/reportWCRatings";
    }

    /**
     * Writes the generated report to a file.
     */
    private void writeReportToFile(
            WCReportRatings wcReportRatings) throws IOException {

        Files.createDirectories(REPORT_DIRECTORY);

        Path filePath = REPORT_DIRECTORY.resolve(REPORT_FILE);

        System.out.println("Working directory: "
                + System.getProperty("user.dir"));

        System.out.println("Report location: "
                + filePath.toAbsolutePath());

        StringBuilder report = new StringBuilder();

        report.append("WORLD CUP PLAYER EFFECTIVENESS RATINGS\n");
        report.append("=======================================\n\n");

        report.append("Report Generated: ");
        report.append(LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        ));
        report.append("\n");

        report.append("Players Displayed: ");
        report.append(wcReportRatings.getPlayers().size());
        report.append("\n\n");

        report.append(String.format(
                "%-25s %-20s %-10s %15s%n",
                "Player Name",
                "Country",
                "Position",
                "Effectiveness"
        ));

        report.append(
                "--------------------------------------------------------------------------\n"
        );

        for (Player player : wcReportRatings.getPlayers()) {

            report.append(String.format(
                    "%-25s %-20s %-10s %15.2f%n",
                    player.getPlayerName(),
                    player.getCountry(),
                    player.getPosition(),
                    player.getPlayerEffectiveness()
            ));
        }

        Files.writeString(
                filePath,
                report.toString(),
                StandardCharsets.UTF_8
        );

        System.out.println("Report successfully written.");
    }
}
