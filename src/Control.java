import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Control extends Actions{
    Game game;

    public Control(Game game) {
        this.game = game;
    }

    public void outputCommand(String verb){
        switch(verb) {
            case "help" :
            case "h" :
            case "commands" :

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
