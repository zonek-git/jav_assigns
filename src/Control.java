import java.io.*;
import java.util.HashMap;

public class Control extends Actions {
    Game game;
    Actions action;

    HashMap<String, String> actions;

    public Control(Game game) {
        this.game = game;
        this.actions = game.assetHash.get("actions");
    }

    public void outputCommand(String verb){
        switch(verb) {
            case "help":
            case "h" :
            case "commands" :
                action = new Actions(this.game, "help", game.actionHash);
                action.checkActionName();
                break;
            case "look" :
                action = new Actions(this.game, "look", game.actionHash);
                action.checkActionName();
                break;
            case "inventory" :
            case "i" :
            case "in":
            case "invent" :
                action = new Actions(this.game, "inventory", game.actionHash);
                action.checkActionName();
                break;
            case "take" :
            case "grab" :
                System.out.println("Please indicate what item you want to grab.");
                break;
            default :
                System.out.println("You don't think you can do that. You should try again or ask (help).");
        }
    }

    public void outputCommand(String verb, String noun) {
        switch(verb) {
            case "take" :
            case "grab" :
                action = new Actions(this.game, "take", game.actionHash);
                action.actionableTake(noun);
                break;
        }

        switch(verb + noun) {
            case "drop" :

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
        try {System.in.read();}
        catch(IOException ignored) {}
    }


}
