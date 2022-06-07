import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Control {
    private Game game;
    private Player player;
    private Actions action;
    private Items item;
    private Items item2;
    private int item1Pos;
    private Characters character;
    ArrayList<String> cmdArray;

    /**
     * Default constructor for Control class
     */
    public Control() {

    }

    /**
     * Primary constructor for Control class
     * @param game dependency injection from Game
     * @param player dependency injection from Player
     * @param actions dependency injection from Actions
     */
    public Control(Game game, Player player, HashMap<String, Actions> actions) {
        this.game = game;
        this.player = player;
    }

    /**
     * Takes input sent by actionRegistry in Game and attempts to parse out what each "important" word means. Assigns
     * each "important" word determined -> into global variable usable by further methods
     * @param cmd ArrayList of commands sent by actionRegistry
     * @throws InterruptedException Possible error with sleep() method
     */
    public void cmdParser(ArrayList<String> cmd) throws InterruptedException {
        cmdArray = cmd;

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.actionHash.containsKey(cmdArray.get(i))) {
                action = game.actionHash.get(cmdArray.get(i));
            }
        }

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.itemHash.containsKey(cmdArray.get(i))) {
                item = game.itemHash.get(cmdArray.get(i));
                item1Pos = i;
            }
        }
        if (item != null) {
            cmdArray.remove(cmdArray.get(item1Pos));
        }

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.itemHash.containsKey(cmdArray.get(i))) {
                item2 = game.itemHash.get(cmdArray.get(i));
            }
        }

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.characterHash.containsKey(cmdArray.get(i))) {
                character = game.characterHash.get(cmdArray.get(i));
            }
        }

        if (action != null) {
            outputCommand(action);
            action = null;
            item = null;
            item2 = null;
            character = null;
        } else {
            System.out.println("Please specify an action to take.");
        }
    }

    /**
     * Takes the parsed action by cmdParser and assigns appropriate items and characters to specified action, then starts
     * the corresponding Actions method.
     * @param action Action object detected by cmdParser
     * @throws InterruptedException Possible error caused by sleep() method
     */
    public void outputCommand(Actions action) throws InterruptedException {
        switch (action.getActionName()) {
            case "take":
                if (item == null) {
                    System.out.println("You need to specify an item to take in your current location");
                } else {
                    action.actionableTake(item.getName());
                }
                break;
            case "drop":
                if (item == null) {
                    System.out.println("You need to specify what item to drop from your inventory, if possible. Some items are only droppable into the safe room.");
                } else {
                    action.actionableDrop(item.getName());
                }
                break;
            case "north":
            case "south":
            case "east":
            case "west":
            case "up":
            case "down":
                if (player.getCurrentLocationObject().getLocationCharacters() != null) {
                    action.actionableMovement(action.getActionName());
                } else {
                    System.out.println("You can't run away from this! Face your fears... Or you could try 'run'.");
                }
                break;
            case "attack":
            case "hit":
                if (item != null && item2 != null) {
                    action.actionableAttack(item.getName(), item2.getName());
                    break;
                } else if (item != null && character != null) {
                    action.actionableAttack(item.getName(), character.getName());
                    break;
                } else {
                    System.out.println("Hint: use 'attack' like... 'attack + target + with + weapon'");
                    System.out.println("Or, use 'attack + target + weapon' or 'hit'");
                }

            case "burn":
                if (item == game.itemHash.get("match") && item2 != null) {
                    action.actionableAttack(item.getName(), item2.getName());
                } else if (item2 == game.itemHash.get("match") && item != null) {
                    action.actionableAttack(item2.getName(), item.getName());
                } else {
                    System.out.println("Hint: use 'burn' like... 'burn target with item'");
                }
                break;
            case "inventory":
                action.displayInventory();
                break;
            case "examine":
                if (item == null) {
                    System.out.println("Please specify the item to examine.");
                } else {
                    action.actionableExamine(item.getName());
                }
                break;
            case "look":
                action.displayLook();
                break;
            case "use":
                if (item == null) {
                    System.out.println("Please specify an item to use.");
                } else {
                    action.actionableUse(item.getName());
                }
                break;
            case "give":
                if (item == null && character == null) {
                    System.out.println("Please specify both an item and a character to give the item to.");
                }
                if (item == null) {
                    System.out.println("Please specify an item to give to " + character.getProperCharacterName());
                }
                if (character == null) {
                    System.out.println("Please specify a character to give the " + item.getProperItemName());
                }
                break;
            case "open":
                if (item == null) {
                    System.out.println("Please specify an object to open, that is openable.");
                } else {
                    action.actionableOpen(item.getName());
                }
                break;
            case "help":
                action.displayHelp();
                break;
            case "run":
                action.actionableRun();
        }
    }
}
