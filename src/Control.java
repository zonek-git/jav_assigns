import java.io.*;
import java.util.HashMap;

public class Control {
    Game game;
    Player player;
    Actions action;

    HashMap<String, String> actions;

    public Control() {

    }

    public Control(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.actions = game.assetHash.get("actions");
    }

    public void outputCommand(String w1){
        String verb = w1.toLowerCase();
        switch(verb) {
            case "help":
            case "h" :
            case "commands" :
                action = new Actions(this.player, this.game, "help", game.actionHash);
                action.checkActionName();
                break;
            case "look" :
                action = new Actions(this.player, this.game, "look", game.actionHash);
                action.checkActionName();
                break;
            case "inventory" :
            case "i" :
            case "in":
            case "invent" :
                action = new Actions(player, game, "inventory", game.actionHash);
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

    public void outputCommand(String w1, String w2) {
        String verb = w1.toLowerCase();
        String noun = w2.toLowerCase();
        switch(verb) {
            case "take" :
            case "grab" :
                action = new Actions(player, game, "take", game.actionHash);
                Items item = new Items(game, noun, game.itemHash);
                action.actionableTake(noun, item);
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
