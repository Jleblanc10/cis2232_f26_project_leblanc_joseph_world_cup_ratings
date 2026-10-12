package ca.hccis.wc;

import ca.hccis.wc.bo.EffectivenessBO;
import ca.hccis.wc.entity.Player;
import ca.hccis.wc.util.CisUtility;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Updated controller so that it uses a second thread to run the ui.
 * CIS2232_Assignment_3
 *
 * @author Joseph LeBlanc
 * @since 20261010
 */

public class Controller {

    public static final String ADD = "A";
    public static final String VIEW = "V";
    public static final String EXIT = "X";

    public static final String MENU =
                      "A) Add" + System.lineSeparator()
                    + "V) View" + System.lineSeparator()
                    + "X) eXit" + System.lineSeparator();

    public static final String PATH = "c:\\cis2232\\";
    public static final String FILE_NAME = "data_LeBlanc_Joseph.json";

    private static Path journalPath;
    private static FileWriter journalWriter;

    private static boolean consoleFinished = false;
    private static boolean paneFinished = false;

    private static final Gson GSON = new Gson();

    public static void main(String[] args) {

        try {
            Files.createDirectories(Paths.get(PATH));

            journalPath = Paths.get(PATH + FILE_NAME);

            if (Files.notExists(journalPath)) {
                Files.createFile(journalPath);
            }

            journalWriter = new FileWriter(journalPath.toFile(), true);

            // Starts the JOptionPane class on a second thread.
            Thread paneThread = new Thread(new PaneOption());
            paneThread.start();

            // The main thread runs the original console menu.
            boolean running = true;

            while (running) {
                String option =
                        CisUtility.getInputString(MENU).toUpperCase();

                switch (option) {
                    case ADD:
                        processAdd();
                        break;

                    case VIEW:
                        processShow();
                        break;

                    case EXIT:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());

        } finally {
            finishConsole();
        }
    }

    // Adds a player and saves the player to the JSON file, modified due to sharing with Pane.

    private static void processAdd() {

        try {
            int id = CisUtility.getInputInt("Enter player ID: ");
            String playerName =
                    CisUtility.getInputString("Enter player name: ");
            String country =
                    CisUtility.getInputString("Enter country: ");
            String position =
                    CisUtility.getInputString("Enter position: ");
            int minutesPlayed =
                    CisUtility.getInputInt("Enter minutes played: ");
            int goals = CisUtility.getInputInt("Enter goals: ");
            int assists = CisUtility.getInputInt("Enter assists: ");
            int defensiveActions =
                    CisUtility.getInputInt("Enter defensive actions: ");
            int yellowCards =
                    CisUtility.getInputInt("Enter yellow cards: ");
            int redCards =
                    CisUtility.getInputInt("Enter red cards: ");

            Player player = new Player(
                    id, playerName, country, position,
                    minutesPlayed, goals, assists,
                    defensiveActions, yellowCards, redCards
            );

            savePlayer(player);

            System.out.println("Player saved successfully.");
            System.out.println(player);

        } catch (IOException e) {
            System.out.println("Unable to save player: " + e.getMessage());
        }
    }

    // Displays all players stored in the JSON file.


    private static void processShow() {

        try {
            List<String> lines = Files.readAllLines(journalPath);

            EffectivenessBO effectivenessBO = new EffectivenessBO();

            for (String line : lines) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                Player player = GSON.fromJson(line, Player.class);

                if (player == null) {
                    continue;
                }

                double effectiveness =
                        effectivenessBO.getPlayerEffectiveness(
                                player.getMinutesPlayed(),
                                player.getGoals(),
                                player.getAssists(),
                                player.getDefensiveActions(),
                                player.getYellowCards(),
                                player.getRedCards()
                        );

                System.out.println(player);
                System.out.printf("Effectiveness: %.3f%n", effectiveness);
                System.out.println("----------------------------------------");
            }

        } catch (IOException e) {
            System.out.println(
                    "Unable to read players: " + e.getMessage());
        }
    }

    // Saves the player to a file. Shared with PaneOption.

    public static synchronized void savePlayer(Player player)
            throws IOException {

        String jsonValue = player.toJson();

        System.out.println(jsonValue);

        journalWriter.write(jsonValue + System.lineSeparator());
        journalWriter.flush();
    }


    // Checks if the console is shutdown.

    public static synchronized void finishConsole() {
        consoleFinished = true;
        closeWriterIfFinished();
    }

    // Checks if the Pane is finished/closed.

    public static synchronized void finishPane() {
        paneFinished = true;
        closeWriterIfFinished();
    }

    // Due to sharing the writer, this method keeps the writer open until both are closed.

    private static void closeWriterIfFinished() {

        if (consoleFinished && paneFinished && journalWriter != null) {
            try {
                journalWriter.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            } finally {
                journalWriter = null;
            }
        }
    }
}
