import java.io.*;
import java.util.*;

public class Game {
    public Control registry;

    Player player;
    Items baton, rose, watch, drinkMeBottle, eatMeBox, key, oyster, match, hookah, teapot, teacup,
    unbirthdayCake, mallet, jam, gasMask, umbrella, playingCard;
    Locations squareRoom, forestZone, rabbitsHouse, mushForest, courtRoom, unbirthdayParty, safeHaven,
    theVoid;


    HashMap<String, HashMap<String, String>> assetHash = new HashMap<>();
    HashMap<String, String> actionHash;
    HashMap<String, String> itemHash;
    HashMap<String, String> characterHash;
    HashMap<String, String> locationHash;

    FileHandlingClass importAssetDescriptions = new FileHandlingClass();

    public static void main(String[] args) throws IOException {
        Game newGame = new Game();
        newGame.startGame(newGame);
    }

    public void loadAssets(Game game) throws IOException {

        assetHash.put("characters", importAssetDescriptions.assetImport("characters"));
        assetHash.put("items", importAssetDescriptions.assetImport("items"));
        assetHash.put("locations", importAssetDescriptions.assetImport("locations"));
        assetHash.put("actions", importAssetDescriptions.assetImport("actions"));

        actionHash = assetHash.get("actions");
        itemHash = assetHash.get("items");
        characterHash = assetHash.get("characters");
        locationHash = assetHash.get("locations");

        //Characters madHatter = new Characters(this, "madHatter", characterHash);
        //Characters redQueen = new Characters(this, "redQueen", characterHash);
        //Characters alice = new Characters(this, "alice", characterHash);
        //Characters whiteRabbit = new Characters(this, "whiteRabbit", characterHash);
        //Characters soldier = new Characters(this, "soldier", characterHash);
        //Characters caterpillar = new Characters(this, "caterpillar", characterHash);
//
        //Actions look = new Actions(this,"look", actionHash);
        //Actions inventory = new Actions(this, "inventory", actionHash);
        //Actions help = new Actions(this,"help", actionHash);
        //Actions take = new Actions(this, "take", actionHash);
        //Actions run = new Actions(this, "run", actionHash);
        //Actions drop = new Actions(this, "drop", actionHash);
        //Actions persuade = new Actions(this, "persuade", actionHash);
        //Actions north = new Actions(this, "north", actionHash);
        //Actions south = new Actions(this, "south", actionHash);
        //Actions east = new Actions(this, "east", actionHash);
        //Actions west = new Actions(this, "west", actionHash);
        //Actions again = new Actions(this, "again", actionHash);
        //Actions attack = new Actions(this, "attack", actionHash);
        //Actions examine = new Actions(this, "examine", actionHash);
        //Actions use = new Actions(this, "use", actionHash);
        //Actions give = new Actions(this, "give", actionHash);
//
        baton = new Items(game, "baton", itemHash);
        rose = new Items(game, "rose", itemHash);
        watch = new Items(this, "watch", itemHash);
        drinkMeBottle = new Items(this, "drinkMeBottle", itemHash);
        eatMeBox = new Items(this, "eatMebox", itemHash);
        key = new Items(this, "key", itemHash);
        oyster = new Items(this, "oyster", itemHash);
        match = new Items(this, "match", itemHash);
        hookah = new Items(this, "hookah", itemHash);
        teapot = new Items(this, "teapot", itemHash);
        teacup = new Items(this, "teacup", itemHash);
        unbirthdayCake = new Items(this, "unbirthdayCake", itemHash);
        mallet = new Items(this, "mallet", itemHash);
        jam = new Items(this, "jam", itemHash);
        gasMask = new Items(this, "gasMask", itemHash);
        umbrella = new Items(this, "umbrella", itemHash);
        playingCard = new Items(this, "playingCard", itemHash);

        squareRoom = new Locations(game, "squareRoom", locationHash);
        squareRoom.addItem(baton);
        squareRoom.addItem(rose);
        forestZone = new Locations(this, "forestZone", locationHash);
        unbirthdayParty = new Locations(game, "unbirthdayParty", locationHash);
        rabbitsHouse = new Locations(this, "rabbitsHouse", locationHash);
        mushForest = new Locations(this, "mushForest", locationHash);
        courtRoom = new Locations(this, "courtRoom", locationHash);
        safeHaven = new Locations(this, "safeHaven", locationHash);
        theVoid = new Locations(this, "theVoid", locationHash);

    }

    public void startGame(Game game) throws IOException {
        Scanner userInput = new Scanner(System.in);
        String command;
        loadAssets(game);
        player = new Player(game, squareRoom, 0);
        registry = new Control(game, player);

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
        if (cmdArray.length == 1) {
            String verb = cmdArray[0];
            registry.outputCommand(verb);
        } else if (cmdArray.length == 2) {
            String verb = cmdArray[0];
            String noun = cmdArray[1];
            registry.outputCommand(verb, noun);
        } else if (cmdArray.length == 3) {
            String verb = cmdArray[0];
            String noun = cmdArray[1];
            String add1 = cmdArray[2];
            registry.outputCommand(verb, noun, add1);
        } else if (cmdArray.length == 4) {
            String verb = cmdArray[0];
            String noun = cmdArray[1];
            String add1 = cmdArray[2];
            String add2 = cmdArray[3];
            registry.outputCommand(verb, noun, add1, add2);
        }
    }
}
