import java.io.*;
import java.util.*;

public class Game {
    public Control registry = new Control(this);

    Player player;

    HashMap<String, HashMap<String, String>> assetHash = new HashMap<>();

    FileHandlingClass importAssetDescriptions = new FileHandlingClass();

    public static void main(String[] args) throws IOException {
        Game newGame = new Game();
        newGame.startGame();

    }

    public void loadAssets() throws IOException {

        assetHash.put("characters", importAssetDescriptions.assetImport("characters"));
        assetHash.put("items", importAssetDescriptions.assetImport("items"));
        assetHash.put("locations", importAssetDescriptions.assetImport("locations"));
        assetHash.put("actions", importAssetDescriptions.assetImport("actions"));

        Actions look = new Actions(this,"look", assetHash.get("actions"));
        Actions inventory = new Actions(this, "inventory", assetHash.get("actions"));
        Actions help = new Actions(this,"help", assetHash.get("actions"));

        Items baton = new Items("baton", assetHash.get("items"));
        Items rose = new Items("rose", assetHash.get("items"));

        Locations squareRoom = new Locations(this, "squareRoom", assetHash.get("locations"));
        squareRoom.addItem(baton);
        squareRoom.addItem(rose);

        player = new Player(this, squareRoom, 0);

    }

    public void startGame() throws IOException {
        Scanner userInput = new Scanner(System.in);
        String command;
        loadAssets();

        do {
            player.displayHealth();
            System.out.print(">> ");
            command = userInput.nextLine();
            if (command.equals("")) {
                System.out.println("Hmm. I wonder what I should do?");
            } else {
                actionRegistry(command);
            }
        } while (!command.equals("quit"));
    }

    public void actionRegistry(String command) {
        String cmdSentence = command.toLowerCase();
        String[] cmdArray = cmdSentence.split(" ");
        String verb = cmdArray[0];
        registry.outputCommand(verb);
        if (cmdArray.length > 1) {
            String noun = cmdArray[1];
            registry.outputCommand(verb, noun);
        }
    }
}
