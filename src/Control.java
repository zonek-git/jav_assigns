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
    int item1Pos;
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

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.actionHash.containsKey(cmdArray.get(i))) {
                action = game.actionHash.get(cmdArray.get(i));

                //todo TESTING
                //System.out.println("registry: " + action.getActionName());
            }
        }

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.itemHash.containsKey(cmdArray.get(i))) {
                item = game.itemHash.get(cmdArray.get(i));
                item1Pos = i;

                //todo TESTING
                //System.out.println("registry item1: " + item.getName());
            }
        }
        if (item != null) {
            cmdArray.remove(cmdArray.get(item1Pos));
        }

        System.out.println(cmdArray);

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.itemHash.containsKey(cmdArray.get(i))) {
                item2 = game.itemHash.get(cmdArray.get(i));

                //todo TESTING
                //System.out.println("registry item2: " + item2.getName());
            }
        }

        for (int i = 0; i < cmdArray.size(); i++) {
            if (game.characterHash.containsKey(cmdArray.get(i))) {
                character = game.characterHash.get(cmdArray.get(i));

                //todo TESTING
                //System.out.println("registry: " + character.getName());
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

                    //todo TESTING
                    //System.out.println(true + " 'atk' item1= " + item.getName());
                    //System.out.println(true + " 'atk' item2= " + item2.getName());
                }
                if (item != null && character != null) {
                    action.actionableAttack(item.getName(), character.getName());

                    //todo TESTING
                    //System.out.println(true + " 'atk' item1= " + item.getName());
                    //System.out.println(true + " 'atk' char= " + character.getName());
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
