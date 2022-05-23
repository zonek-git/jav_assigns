import java.io.*;
import java.util.*;

public class Game {
    public Control registry;

    Player player;

    Items baton, rose, watch, drinkMeBottle, eatMeBox, key, oyster, match, hookah, teapot, teacup, unbirthdayCake, mallet, jam, gasMask, umbrella, playingCard, squareRoomCabinet;

    Rooms squareRoomMain, rabbitsHouseLivingRoom, rabbitsHouseHallway, rabbitsHouseBackRoom;

    Locations squareRoom, forestZone, rabbitsHouse, mushForest, courtRoom, unbirthdayParty, safeHaven, theVoid;

    ArrayList<Items> itemsArrayList = new ArrayList<>();


    HashMap<String, HashMap<String, String>> assetHash = new HashMap<>();
    HashMap<String, String> actionHash;
    HashMap<String, String> itemHash;
    HashMap<String, String> characterHash;
    HashMap<String, String> locationHash;
    HashMap<String, String> roomHash;

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
        assetHash.put("rooms", importAssetDescriptions.assetImport("rooms"));

        actionHash = assetHash.get("actions");
        itemHash = assetHash.get("items");
        characterHash = assetHash.get("characters");
        locationHash = assetHash.get("locations");
        roomHash = assetHash.get("rooms");

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
        //Items

        squareRoomCabinet = new Items(game, "squareRoomCabinet", itemHash);
        squareRoomCabinet.setIsOpenable(true);


        baton = new Items(game, "baton", itemHash);
        rose = new Items(game, "rose", itemHash);
        rose.setHealingModifier(25);

        watch = new Items(game, "watch", itemHash);
        drinkMeBottle = new Items(game, "drinkMeBottle", itemHash);
        eatMeBox = new Items(game, "eatMebox", itemHash);
        key = new Items(game, "key", itemHash);

        oyster = new Items(game, "oyster", itemHash);
        oyster.setHealingModifier(40);

        match = new Items(game, "match", itemHash);
        hookah = new Items(game, "hookah", itemHash);
        hookah.setHealingModifier(-10);

        teapot = new Items(game, "teapot", itemHash);
        teacup = new Items(game, "teacup", itemHash);
        unbirthdayCake = new Items(game, "unbirthdayCake", itemHash);
        mallet = new Items(game, "mallet", itemHash);
        jam = new Items(game, "jam", itemHash);
        jam.setHealingModifier(30);

        gasMask = new Items(game, "gasMask", itemHash);
        umbrella = new Items(game, "umbrella", itemHash);
        playingCard = new Items(game, "playingCard", itemHash);

        //Rooms

        squareRoomMain = new Rooms();

        //Locations

        squareRoom = new Locations(game, "squareRoom", locationHash);
        squareRoom.addItem(baton);
        squareRoom.addItem(rose);
        squareRoom.setDirection("south", rabbitsHouse);

        forestZone = new Locations(game, "forestZone", locationHash);
        unbirthdayParty = new Locations(game, "unbirthdayParty", locationHash);

        rabbitsHouse = new Locations(game, "rabbitsHouse", locationHash);
        rabbitsHouse.addItem(jam);
        rabbitsHouse.addItem(match);

        mushForest = new Locations(game, "mushForest", locationHash);
        courtRoom = new Locations(game, "courtRoom", locationHash);
        safeHaven = new Locations(game, "safeHaven", locationHash);
        theVoid = new Locations(game, "theVoid", locationHash);

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
        } while ((!command.equals("quit") && !player.getIsAlive()));
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
