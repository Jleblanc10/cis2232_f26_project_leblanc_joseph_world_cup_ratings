package ca.hccis.io;

import ca.hccis.io.entity.Player;
import ca.hccis.io.util.CisUtility;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * CIS2232_Assignment_1
 * @author Joseph LeBlanc
 * @since 2026-09-2026
 */

public class Controller {

    public static final String ADD = "A";
    public static final String VIEW = "V";
    public static final String EXIT = "X";

    public static final String MENU =
            "A) Add" + System.lineSeparator()
                    + "V) View" + System.lineSeparator()
                    + "X) eXit" + System.lineSeparator();

    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_EXIT = "Goodbye";
    public static final String MESSAGE_SUCCESS = "Success";

    public static final String PATH = "c:\\cis2232\\";
    public static final String FILE_NAME = "data_LeBlanc_Joseph.json";

    private static Path journalPath = null;
    private static FileWriter journalWriter = null;

    public static void main(String[] args) {

        // Creates a directory if it doesn't already exist

        try {
            Files.createDirectories(Paths.get(PATH));
        } catch (IOException e) {
            System.out.println("Error creating directory");
            throw new RuntimeException(e);
        }

        // Creates a new file if it doesn't already exist.

        journalPath = Paths.get(PATH + FILE_NAME);

        if (!Files.exists(journalPath)) {
            File journalFile = new File(journalPath.toString());
        }

        try {
            journalWriter = new FileWriter(PATH + FILE_NAME, true);
        } catch (IOException e) {
            System.out.println("Error creating file writer");
            throw new RuntimeException(e);
        }

        String menuOption;

        do {

            menuOption =
                    CisUtility.getInputString(MENU).toUpperCase();

            switch (menuOption) {

                case EXIT:
                    System.out.println(MESSAGE_EXIT);
                    break;

                case ADD:
                    processAdd();
                    break;

                case VIEW:
                    processShow();
                    break;

                default:
                    System.out.println(MESSAGE_ERROR);
                    break;
            }

        } while (!menuOption.equals(EXIT));

        try {
            journalWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

     // Adds a player and saves the player to the JSON file.

    public static void processAdd() {

        System.out.println("Add player");

        int id =
                CisUtility.getInputInt("Enter player ID: ");

        String playerName =
                CisUtility.getInputString("Enter player name: ");

        String country =
                CisUtility.getInputString("Enter country: ");

        String position =
                CisUtility.getInputString("Enter position: ");

        int minutesPlayed =
                CisUtility.getInputInt("Enter minutes played: ");

        int goals =
                CisUtility.getInputInt("Enter goals: ");

        int assists =
                CisUtility.getInputInt("Enter assists: ");

        int defensiveActions =
                CisUtility.getInputInt("Enter defensive actions: ");

        int yellowCards =
                CisUtility.getInputInt("Enter yellow cards: ");

        int redCards =
                CisUtility.getInputInt("Enter red cards: ");

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

        try {

            String jsonValue = player.toJson();

            System.out.println(jsonValue);

            journalWriter.write(
                    jsonValue + System.lineSeparator()
            );

            journalWriter.flush();

            System.out.println();
            System.out.println("Player added successfully.");
            System.out.println(
                    "Effectiveness: "
                            + player.getPlayerEffectiveness()
            );

        } catch (IOException e) {

            System.out.println("Error saving player");
            throw new RuntimeException(e);
        }
    }


    // Displays all players stored in the JSON file.

    public static void processShow() {

        System.out.println();

        Gson gson = new Gson();

        try {

            List<String> lines =
                    Files.readAllLines(Paths.get(PATH + FILE_NAME));

            if (lines.isEmpty()) {

                System.out.println("No players found");

            } else {

                System.out.println("Here are the players found");

                for (String current : lines) {

                    Player player =
                            gson.fromJson(current, Player.class);

                    System.out.println(player);
                    System.out.println();
                }
            }

        } catch (IOException e) {

            System.out.println("Error reading file");
            throw new RuntimeException(e);
        }
    }
}