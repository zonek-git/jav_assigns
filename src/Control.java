import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Control {
    Game game;
    Player player;

    HashMap<String, String> actionsDesc;
    HashMap<String, Actions> actions;

    public Control() {

    }

    public Control(Game game, Player player, HashMap<String, Actions> actions) {
        this.game = game;
        this.player = player;
        this.actionsDesc = game.assetHash.get("actions");
        this.actions = actions;
    }

    public void outputCommand(String w1) {
        String verb = w1.toLowerCase();
        switch (verb) {
            case "help":
            case "h":
            case "commands":
                actions.get("help").displayHelp();
                break;
            case "look":
                actions.get("look").displayLook();
                break;
            case "inventory":
            case "i":
            case "in":
            case "invent":
                actions.get("inventory").displayInventory();
                break;
            case "take":
            case "grab":
                System.out.println("Please indicate what item you want to grab.");
                break;
            case "south":
            case "go south":
            case "s":
            case "go s":
            case "head south":
            case "head s":
                actions.get("south").actionableMovement("south");
            case "north":
            case "go north":
            case "n":
            case "go n":
            case "head north":
            case "head n":
                actions.get("north").actionableMovement("north");
            case "up":
                actions.get("up").actionableMovement("up");
            default:
                System.out.println("You don't think you can do that. You should try again or ask (help).");
        }
    }

    public void outputCommand(String w1, String w2) {
        String verb = w1.toLowerCase();
        String noun = w2.toLowerCase();
        switch (verb) {
            case "take":
            case "grab":
                actions.get("take").actionableTake(noun);
                break;
            case "drop":
                actions.get("drop").actionableDrop(noun);
                break;
            case "examine":
                actions.get("examine").actionableExamine(noun);
                break;
            case "use":
                actions.get("use").actionableUse(noun);
                break;
            case "open":
                actions.get("open").actionableOpen(noun);
        }
    }

    public void outputCommand(String verb, String noun, String add1) {

    }

    public void outputCommand(String w1, String w2, String w3, String w4) {
        String add1 = w1.toLowerCase();
        String add2 = w2.toLowerCase();
        String add3 = w3.toLowerCase();
        String add4 = w4.toLowerCase();

        switch(add1) {
            case "attack":
            case "atk":
                actions.get("attack").actionableAttack(add2, add3, add4);
        }
    }

    /**
     * System that allows user to be prompted for an ENTER key to continue the story prompt
     */
    public void pressEnterToContinue() {
        System.out.println("Press the <ENTER> key to continue...");
        try {
            System.in.read();
        } catch (IOException ignored) {
        }
    }


}
