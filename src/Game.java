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

        HashMap<String, String> actionHash = assetHash.get("actions");
        HashMap<String, String> itemHash = assetHash.get("items");
        HashMap<String, String> characterHash = assetHash.get("characters");
        HashMap<String, String> locationHash = assetHash.get("locations");

        Characters madHatter = new Characters(this, "madHatter", characterHash);
        Characters redQueen = new Characters(this, "redQueen", characterHash);
        Characters alice = new Characters(this, "alice", characterHash);
        Characters whiteRabbit = new Characters(this, "whiteRabbit", characterHash);
        Characters soldier = new Characters(this, "soldier", characterHash);
        Characters caterpillar = new Characters(this, "caterpillar", characterHash);

        Actions look = new Actions(this,"look", actionHash);
        Actions inventory = new Actions(this, "inventory", actionHash);
        Actions help = new Actions(this,"help", actionHash);
        Actions take = new Actions(this, "take", actionHash);
        Actions run = new Actions(this, "run", actionHash);
        Actions drop = new Actions(this, "drop", actionHash);
        Actions persuade = new Actions(this, "persuade", actionHash);
        Actions north = new Actions(this, "north", actionHash);
        Actions south = new Actions(this, "south", actionHash);
        Actions east = new Actions(this, "east", actionHash);
        Actions west = new Actions(this, "west", actionHash);
        Actions again = new Actions(this, "again", actionHash);
        Actions attack = new Actions(this, "attack", actionHash);
        Actions examine = new Actions(this, "examine", actionHash);
        Actions use = new Actions(this, "use", actionHash);
        Actions give = new Actions(this, "give", actionHash);

        Items baton = new Items(this, "baton", itemHash);
        Items rose = new Items(this, "rose", itemHash);
        Items watch = new Items(this, "watch", itemHash);
        Items drinkMeBottle = new Items(this, "drinkMeBottle", itemHash);
        Items eatMeBox = new Items(this, "eatMebox", itemHash);
        Items key = new Items(this, "key", itemHash);
        Items oyster = new Items(this, "oyster", itemHash);
        Items match = new Items(this, "match", itemHash);
        Items hookah = new Items(this, "hookah", itemHash);
        Items teapot = new Items(this, "teapot", itemHash);
        Items teacup = new Items(this, "teacup", itemHash);
        Items unbirthdayCake = new Items(this, "unbirthdayCake", itemHash);
        Items mallet = new Items(this, "mallet", itemHash);
        Items jam = new Items(this, "jam", itemHash);
        Items gasMask = new Items(this, "gasMask", itemHash);
        Items umbrella = new Items(this, "umbrella", itemHash);
        Items playingCard = new Items(this, "playingCard", itemHash);

        Locations squareRoom = new Locations(this, "squareRoom", locationHash);
        squareRoom.addItem(baton);
        squareRoom.addItem(rose);
        Locations forestZone = new Locations(this, "forestZone", locationHash);
        Locations rabbitsHouse = new Locations(this, "rabbitsHouse", locationHash);
        Locations mushForest = new Locations(this, "mushForest", locationHash);
        Locations courtRoom = new Locations(this, "courtRoom", locationHash);
        Locations safeHaven = new Locations(this, "safeHaven", locationHash);
        Locations theVoid = new Locations(this, "theVoid", locationHash);

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
        if (cmdArray.length == 2) {
            String noun = cmdArray[1];
            registry.outputCommand(verb, noun);
        } else if (cmdArray.length == 3) {
            String noun = cmdArray[1];
            String add1 = cmdArray[2];
            registry.outputCommand(verb, noun, add1);
        } else if (cmdArray.length == 4) {
            String noun = cmdArray[1];
            String add1 = cmdArray[2];
            String add2 = cmdArray[3];
            registry.outputCommand(verb, noun, add1, add2);
        }
    }
}
