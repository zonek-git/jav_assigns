import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Control {
    Game game;
    Player player;
    Actions action;
    Items item;
    Items item2;
    Characters character;

    HashMap<String, String> actionsDesc;
    HashMap<String, Actions> actions;

    ArrayList<String> cmdArray;
    ArrayList<String> itemArrayS = new ArrayList<>();
    ArrayList<String> charactersArrayS = new ArrayList<>();
    ArrayList<String> actionArrayS = new ArrayList<>();
    ArrayList<String> cmdOutput = new ArrayList<>();

    public Control() {

    }

    public Control(Game game, Player player, HashMap<String, Actions> actions) {
        this.game = game;
        this.player = player;
        this.actionsDesc = game.assetHash.get("actions");
        this.actions = actions;
    }


    public void cmdParser(ArrayList<String> cmd) {
        cmdArray = cmd;

        for (int i = 0; i < cmd.size(); i++) {
            if (game.actionHash.containsKey(cmd.get(i))) {
                action = game.actionHash.get(cmd.get(i));
            }
        }

        for (int i = 0; i < cmd.size(); i++) {
            if (game.itemHash.containsKey(cmd.get(i))) {
                item = game.itemHash.get(cmd.get(i));
                cmd.remove(item.getName());
            }
        }

        for (int i = 0; i < cmd.size(); i++) {
            if (game.itemHash.containsKey(cmd.get(i))) {
                item2 = game.itemHash.get(cmd.get(i));
            }
        }
            for (int i = 0; i < cmd.size(); i++) {
                if (game.characterHash.containsKey(cmd.get(i))) {
                    character = game.characterHash.get(cmd.get(i));
                }
            }

        if (action != null) {
            outputCommand(action);
            action = null;
            item = null;
            character = null;
        } else {
            System.out.println("Please specify an action to take.");
        }
    }


    public void outputCommand(Actions action) {
        switch (action.getActionName()) {
            case "take":
                if (item == null) {
                    System.out.println("You need to specify an item to take in your current location");
                } else {
                    action.actionableTake(item.getName());
                }
                //case "run":
                //action.actionableRun(); //todo
                break;
            case "drop":
                if (item == null) {
                    System.out.println("You need to specify what item to drop from your inventory, if possible. Some items are only droppable into the safe room.");
                } else {
                    action.actionableDrop(item.getName());
                }
                break;
            case "persuade":
                if (character == null) {
                    System.out.println("You need to specify a character to persuade... sometimes it won't work, though.");
                } //else {
                //action.actionablePersuade(character); //todo
                //}
                break;
            case "north":
            case "south":
            case "east":
            case "west":
            case "up":
            case "down":
                action.actionableMovement(action.getActionName());
                break;
            case "attack":
                if (item != null && item2 != null) {
                    action.actionableAttack(item.getName(), item2.getName());
                }
                if (item != null && character != null) {
                    action.actionableAttack(item.getName(), character.getName());
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
                //if(character != null && item != null) {
                //    action.actionableGive();
                //} //todo
                break;
            case "open":
                if (item == null) {
                    System.out.println("Please specify an object to open, that is openable.");
                } else {
                    action.actionableOpen(item.getName());
                }
                break;
        }
    }

    public void pressEnterToContinue() {
        System.out.println("Press the <ENTER> key to continue...");
        try {
            System.in.read();
        } catch (IOException ignored) {
        }
    }


}
