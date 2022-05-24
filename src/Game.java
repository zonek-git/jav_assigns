import java.io.*;
import java.util.*;

public class Game {
    public Control registry;

    Player player;

    HashMap<String, Items> itemHash = new HashMap<>();
    HashMap<String, Characters> characterHash = new HashMap<>();
    HashMap<String, Locations> locationHash = new HashMap<>();
    HashMap<String, Rooms> roomHash = new HashMap<>();
    HashMap<String, Actions> actionHash = new HashMap<>();

    HashMap<String, HashMap<String, String>> assetHash = new HashMap<>();
    HashMap<String, String> actionDescHash;
    HashMap<String, String> itemDescHash;
    HashMap<String, String> characterDescHash;
    HashMap<String, String> locationDescHash;
    HashMap<String, String> roomDescHash;

    FileHandlingClass importAssetDescriptions = new FileHandlingClass();

    public static void main(String[] args) throws IOException {
        Game newGame = new Game();
        newGame.startGame(newGame);
    }

    public void startGame(Game game) throws IOException {
        Scanner userInput = new Scanner(System.in);
        String command;
        loadAssets(game);
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

    public void loadAssets(Game game) throws IOException {

        assetHash.put("characters", importAssetDescriptions.assetImport("characters"));
        assetHash.put("items", importAssetDescriptions.assetImport("items"));
        assetHash.put("locations", importAssetDescriptions.assetImport("locations"));
        assetHash.put("actions", importAssetDescriptions.assetImport("actions"));
        assetHash.put("rooms", importAssetDescriptions.assetImport("rooms"));

        actionDescHash = assetHash.get("actions");
        itemDescHash = assetHash.get("items");
        characterDescHash = assetHash.get("characters");
        locationDescHash = assetHash.get("locations");
        roomDescHash = assetHash.get("rooms");

        // Characters //

        Characters madHatter = new Characters(this, "madHatter", characterDescHash);
        Characters redQueen = new Characters(this, "redQueen", characterDescHash);
        Characters alice = new Characters(this, "alice", characterDescHash);
        Characters whiteRabbit = new Characters(this, "whiteRabbit", characterDescHash);
        Characters soldier = new Characters(this, "soldier", characterDescHash);
        Characters caterpillar = new Characters(this, "caterpillar", characterDescHash);

        characterHash.put("madHatter", madHatter);
        characterHash.put("redQueen", redQueen);
        characterHash.put("alice", alice);
        characterHash.put("whiteRabbit", whiteRabbit);
        characterHash.put("soldier", soldier);
        characterHash.put("caterpillar", caterpillar);

        // Actions //

        Actions test = new Actions(game, "test", actionDescHash);
        Actions look = new Actions(game,"look", actionDescHash);
        Actions inventory = new Actions(game, "inventory", actionDescHash);
        Actions help = new Actions(game,"help", actionDescHash);
        Actions take = new Actions(game, "take", actionDescHash);
        Actions run = new Actions(game,"run", actionDescHash);
        Actions drop = new Actions(game, "drop", actionDescHash);
        Actions persuade = new Actions(game, "persuade", actionDescHash);
        Actions north = new Actions(game, "north", actionDescHash);
        Actions south = new Actions(game, "south", actionDescHash);
        Actions east = new Actions(game, "east", actionDescHash);
        Actions west = new Actions(game, "west", actionDescHash);
        Actions again = new Actions(game, "again", actionDescHash);
        Actions attack = new Actions(game, "attack", actionDescHash);
        Actions examine = new Actions( game, "examine", actionDescHash);
        Actions use = new Actions(game, "use", actionDescHash);
        Actions give = new Actions(game, "give", actionDescHash);
        Actions open = new Actions(game, "open", actionDescHash);

        actionHash.put("test", test);
        actionHash.put("look", look);
        actionHash.put("inventory", inventory);
        actionHash.put("help", help);
        actionHash.put("take", take);
        actionHash.put("run", run);
        actionHash.put("drop", drop);
        actionHash.put("persuade", persuade);
        actionHash.put("north", north);
        actionHash.put("south", south);
        actionHash.put("east", east);
        actionHash.put("west", west);
        actionHash.put("again", again);
        actionHash.put("attack", attack);
        actionHash.put("examine", examine);
        actionHash.put("use", use);
        actionHash.put("give", give);
        actionHash.put("open", open);

        //Items

        Items squareRoomCabinet = new Items(game, "squareRoomCabinet", itemDescHash);
        squareRoomCabinet.setIsOpenable(true);

        Items baton = new Items(game, "baton", itemDescHash);
        baton.setIsTakeable(true);
        baton.setIsDroppable(true);
        baton.setIsUsable(true);

        Items rose = new Items(game, "rose", itemDescHash);
        rose.setHealingModifier(25);

        Items watch = new Items(game, "watch", itemDescHash);
        Items drinkMeBottle = new Items(game, "drinkMeBottle", itemDescHash);
        Items eatMeBox = new Items(game, "eatMebox", itemDescHash);
        Items key = new Items(game, "key", itemDescHash);
        Items oyster = new Items(game, "oyster", itemDescHash);
        oyster.setHealingModifier(40);

        Items match = new Items(game, "match", itemDescHash);
        Items hookah = new Items(game, "hookah", itemDescHash);
        hookah.setHealingModifier(-10);

        Items teapot = new Items(game, "teapot", itemDescHash);
        Items teacup = new Items(game, "teacup", itemDescHash);
        Items unbirthdayCake = new Items(game, "unbirthdayCake", itemDescHash);
        Items mallet = new Items(game, "mallet", itemDescHash);
        Items jam = new Items(game, "jam", itemDescHash);
        jam.setHealingModifier(30);

        Items gasMask = new Items(game, "gasMask", itemDescHash);
        Items umbrella = new Items(game, "umbrella", itemDescHash);
        Items playingCard = new Items(game, "playingCard", itemDescHash);

        itemHash.put("squareRoomCabinet", squareRoomCabinet);
        itemHash.put("baton", baton);
        itemHash.put("rose", rose);
        itemHash.put("watch", watch);
        itemHash.put("drinkMeBottle", drinkMeBottle);
        itemHash.put("eatMeBox", eatMeBox);
        itemHash.put("key", key);
        itemHash.put("match", match);
        itemHash.put("hookah", hookah);
        itemHash.put("teapot", teapot);
        itemHash.put("teacup", teacup);
        itemHash.put("unbirthdayCake", unbirthdayCake);
        itemHash.put("mallet", mallet);
        itemHash.put("jam", jam);
        itemHash.put("gasMask", gasMask);
        itemHash.put("umbrella", umbrella);
        itemHash.put("playingCard", playingCard);

        //Locations + Rooms

        Locations forestZone = new Locations(game, "forestZone", locationDescHash);
        Locations unbirthdayParty = new Locations(game, "unbirthdayParty", locationDescHash);
        Locations mushForest = new Locations(game, "mushForest", locationDescHash);
        Locations courtRoom = new Locations(game, "courtRoom", locationDescHash);
        Locations safeHaven = new Locations(game, "safeHaven", locationDescHash);
        Locations theVoid = new Locations(game, "theVoid", locationDescHash);
        Locations squareRoom = new Locations(game, "squareRoom", locationDescHash);
        Locations rabbitsHouse = new Locations(game, "rabbitsHouse", locationDescHash);

        Rooms squareRoomMain = new Rooms("squareRoomMain", roomDescHash);
        squareRoomMain.addItem(baton);
        squareRoomMain.addItem(rose);
        squareRoomMain.addItem(squareRoomCabinet);
        Rooms rabbitsHouseBackRoom = new Rooms("rabbitsHouseBackRoom", roomDescHash);
        Rooms rabbitsHouseLivingRoom = new Rooms("rabbitsHouseLivingRoom", roomDescHash);
        rabbitsHouseLivingRoom.addItem(match);
        Rooms rabbitsHouseHallway = new Rooms("rabbitsHouseHallway", roomDescHash);

        locationHash.put("forestZone", forestZone);
        locationHash.put("unbirthdayParty", unbirthdayParty);
        locationHash.put("mushForest", mushForest);
        locationHash.put("courtRoom", courtRoom);
        locationHash.put("safeHaven", safeHaven);
        locationHash.put("theVoid", theVoid);
        locationHash.put("squareRoom", squareRoom);
        locationHash.put("rabbitsHouse", rabbitsHouse);

        roomHash.put("squareRoomMain", squareRoomMain);
        roomHash.put("rabbitsHouseBackRoom", rabbitsHouseBackRoom);
        roomHash.put("rabbitsHouseLivingRoom", rabbitsHouseLivingRoom);
        roomHash.put("rabbitsHouseHallway", rabbitsHouseHallway);

        //Add Directions

        squareRoom.setContainedRooms(squareRoomMain);
        squareRoom.setInitialRoom(squareRoomMain);
        squareRoom.setContainedRooms(rabbitsHouseLivingRoom);
        squareRoom.setContainedRooms(rabbitsHouseHallway);
        squareRoom.setContainedRooms(rabbitsHouseBackRoom);
        squareRoomMain.addDirection("south", rabbitsHouse);
        rabbitsHouseLivingRoom.addDirection("south", rabbitsHouseHallway);
        rabbitsHouseHallway.addDirection("south", rabbitsHouseBackRoom);
        rabbitsHouseHallway.addDirection("north", rabbitsHouseLivingRoom);

        player = new Player(game, squareRoom, 0);

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
