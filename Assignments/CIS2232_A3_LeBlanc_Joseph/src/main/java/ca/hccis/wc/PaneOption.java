package ca.hccis.wc;

import ca.hccis.wc.bo.EffectivenessBO;
import ca.hccis.wc.entity.Player;

import javax.swing.JOptionPane;

/**
 * Provides a JOptionPane interface for adding and viewing players.
 * CIS2232_Assignment_3
 *
 * @author Joseph LeBlanc
 * @since 20261010
 */

public class PaneOption implements Runnable {

    // Displays the menu and checks if the Pane is running.

    @Override
    public void run() {

        boolean running = true;

        while (running) {

            String menu = JOptionPane.showInputDialog(
                    null,
                    "A) Add Player\n"
                            + "V) View Players\n"
                            + "X) Exit",
                    "Player Menu",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (menu == null || menu.equalsIgnoreCase("X")) {
                running = false;
                continue;
            }

            switch (menu.toUpperCase()) {
                case "A":
                    processAdd();
                    break;

                case "V":
                    processView();
                    break;

                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Invalid menu option.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
            }
        }
    }

    // Simple value collecting with JOptionPane.

    private void processAdd() {

        try {
            String value = JOptionPane.showInputDialog("Enter player ID:");
            if (value == null) return;
            int id = Integer.parseInt(value);

            String playerName = JOptionPane.showInputDialog("Enter player name:");
            if (playerName == null) return;

            String country = JOptionPane.showInputDialog("Enter country:");
            if (country == null) return;

            String position = JOptionPane.showInputDialog("Enter position:");
            if (position == null) return;

            value = JOptionPane.showInputDialog("Enter minutes played:");
            if (value == null) return;
            int minutesPlayed = Integer.parseInt(value);

            value = JOptionPane.showInputDialog("Enter goals:");
            if (value == null) return;
            int goals = Integer.parseInt(value);

            value = JOptionPane.showInputDialog("Enter assists:");
            if (value == null) return;
            int assists = Integer.parseInt(value);

            value = JOptionPane.showInputDialog("Enter defensive actions:");
            if (value == null) return;
            int defensiveActions = Integer.parseInt(value);

            value = JOptionPane.showInputDialog("Enter yellow cards:");
            if (value == null) return;
            int yellowCards = Integer.parseInt(value);

            value = JOptionPane.showInputDialog("Enter red cards:");
            if (value == null) return;
            int redCards = Integer.parseInt(value);

            Player player = new Player(
                    id,
                    playerName,
                    country,
                    position,
                    minutesPlayed,
                    goals,
                    assists,
                    defensiveActions,
                    yellowCards,
                    redCards
            );

            // Save using the shared savePlayer method, located in Controller.
            Controller.savePlayer(player);

            EffectivenessBO effectivenessBO = new EffectivenessBO();

            double effectiveness = effectivenessBO.getPlayerEffectiveness(
                    minutesPlayed,
                    goals,
                    assists,
                    defensiveActions,
                    yellowCards,
                    redCards
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Player saved successfully!\n\n"
                            + player
                            + "\nEffectiveness: "
                            + String.format("%.3f", effectiveness),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Please enter valid numbers for numeric fields.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to save player: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Simply displays all players entered.
    // Needed to add window with scroll bar as the file was getting too large.

    private void processView() {

        try {
            java.nio.file.Path filePath = java.nio.file.Paths.get(
                    Controller.PATH + Controller.FILE_NAME);

            java.util.List<String> lines =
                    java.nio.file.Files.readAllLines(filePath);

            com.google.gson.Gson gson = new com.google.gson.Gson();
            EffectivenessBO effectivenessBO = new EffectivenessBO();

            StringBuilder players = new StringBuilder();

            for (String line : lines) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                Player player = gson.fromJson(line, Player.class);

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

                players.append(player)
                        .append(System.lineSeparator())
                        .append(String.format(
                                "Effectiveness: %.3f",
                                effectiveness))
                        .append(System.lineSeparator())
                        .append("----------------------------------------")
                        .append(System.lineSeparator());
            }

            if (players.length() == 0) {
                players.append("No players found.");
            }

            javax.swing.JTextArea textArea =
                    new javax.swing.JTextArea(players.toString(), 20, 50);

            textArea.setEditable(false);
            textArea.setLineWrap(false);
            textArea.setCaretPosition(0);

            javax.swing.JScrollPane scrollPane =
                    new javax.swing.JScrollPane(textArea);

            scrollPane.setVerticalScrollBarPolicy(
                    javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            scrollPane.setHorizontalScrollBarPolicy(
                    javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    scrollPane,
                    "All Players and Effectiveness",
                    javax.swing.JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "Unable to display players: " + e.getMessage(),
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
            );
        }
    }
}