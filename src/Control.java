import java.io.*;
import java.util.HashMap;

public class Control extends Game{
    Game game;
    Player player;

    HashMap<String, String> actions;

    public Control() {

    }

    public Control(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.actions = game.assetHash.get("actions");
    }

    public void outputCommand(String w1) {
        String verb = w1.toLowerCase();
        switch (verb) {
            case "test":
                test.displayCurrentLoc();
            case "help":
            case "h":
            case "commands":
                help.displayHelp();
                break;
            case "look":
                look.displayLook();
                break;
            case "inventory":
            case "i":
            case "in":
            case "invent":
                inventory.displayInventory();
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
                south.actionableMovement("south");
            case "north":
            case "go north":
            case "n":
            case "go n":
            case "head north":
            case "head n":
                north.actionableMovement("north");
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
                take.actionableTake(noun);
                break;
            case "drop":
                drop.actionableDrop(noun);
                break;
            case "examine":
                examine.actionableExamine(noun);
                break;
            case "use":
                use.actionableDrop(noun);
                break;
            case "open":
                open.actionableOpen(noun);
        }
    }

    public void outputCommand(String verb, String noun, String add1) {

    }

    public void outputCommand(String verb, String noun, String add1, String add2) {

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
