import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Control extends Player{
    Game game;

    public Control(Game game) {
        this.game = game;
    }

    public void outputCommand(String verb){
        switch(verb) {
            case "help" :
            case "h" :
            case "commands" :
                System.out.println(game.assetHash.get("actions").get("help"));
                System.out.println(game.assetHash.get("actions").get("take"));
                System.out.println(game.assetHash.get("actions").get("run"));
                System.out.println(game.assetHash.get("actions").get("drop"));
                System.out.println(game.assetHash.get("actions").get("persuade"));
                System.out.println(game.assetHash.get("actions").get("north"));
                System.out.println(game.assetHash.get("actions").get("south"));
                System.out.println(game.assetHash.get("actions").get("east"));
                System.out.println(game.assetHash.get("actions").get("west"));
                System.out.println(game.assetHash.get("actions").get("again"));
                System.out.println(game.assetHash.get("actions").get("attack"));
                System.out.println(game.assetHash.get("actions").get("inventory"));
                System.out.println(game.assetHash.get("actions").get("examine"));
                System.out.println(game.assetHash.get("actions").get("look"));
                System.out.println(game.assetHash.get("actions").get("use"));
                System.out.println(game.assetHash.get("actions").get("give"));
                break;
            case "look" :
                System.out.println(getCurrentLocationDescription());
                break;
            case "inventory" :
            case "i" :
            case "in":
            case "invent" :
                viewInventory();
                break;
            case "take" :
            case "grab" :
                System.out.println("Please specify the item you want to take.");
            default :
                System.out.println("You don't think you can do that. You should try again or ask (help).");
        }
    }

    public void outputCommand(String verb, String noun) {
        String nounName = noun;

        switch(verb) {
            case "examine" :

        }

        switch(verb + noun) {
            case "list commands" :
                System.out.println();
                break;
            case "drop" :
        }
    }

    public void outputCommand(String verb, String noun, String character) {

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
