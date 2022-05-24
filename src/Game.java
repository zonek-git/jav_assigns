import java.io.*;
import java.util.*;

public class Game {
    public Control registry;

    Player player;

    Items baton, rose, watch, drinkMeBottle, eatMeBox, key, oyster, match, hookah, teapot,
            teacup, unbirthdayCake, mallet, jam, gasMask, umbrella, playingCard, squareRoomCabinet;

    Rooms squareRoomMain, rabbitsHouseLivingRoom, rabbitsHouseHallway, rabbitsHouseBackRoom;

    Locations squareRoom, forestZone, rabbitsHouse, mushForest, courtRoom, unbirthdayParty, safeHaven, theVoid;

    Actions take, run, drop, persuade, north, south, east, west, again, attack, inventory, examine,
            look, use, help, give, open, test;

    Characters madHatter, redQueen, alice, soldier, caterpillar, whiteRabbit;


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

        actionHash = assetHash.get("actions");
        itemHash = assetHash.get("items");
        characterHash = assetHash.get("characters");
        locationHash = assetHash.get("locations");
        roomHash = assetHash.get("rooms");

        // Characters //

        madHatter = new Characters(this, "madHatter", characterHash);
        redQueen = new Characters(this, "redQueen", characterHash);
        alice = new Characters(this, "alice", characterHash);
        whiteRabbit = new Characters(this, "whiteRabbit", characterHash);
        soldier = new Characters(this, "soldier", characterHash);
        caterpillar = new Characters(this, "caterpillar", characterHash);

        // Actions //

        test = new Actions(game, "test", actionHash);
        look = new Actions(game,"look", actionHash);
        inventory = new Actions(game, "inventory", actionHash);
        help = new Actions(game,"help", actionHash);
        take = new Actions(game, "take", actionHash);
        run = new Actions(game,"run", actionHash);
        drop = new Actions(game, "drop", actionHash);
        persuade = new Actions(game, "persuade", actionHash);
        north = new Actions(game, "north", actionHash);
        south = new Actions(game, "south", actionHash);
        east = new Actions(game, "east", actionHash);
        west = new Actions(game, "west", actionHash);
        again = new Actions(game, "again", actionHash);
        attack = new Actions(game, "attack", actionHash);
        examine = new Actions( game, "examine", actionHash);
        use = new Actions(game, "use", actionHash);
        give = new Actions(game, "give", actionHash);
        open = new Actions(game, "open", actionHash);

        //Items

        squareRoomCabinet = new Items(game, "squareRoomCabinet", itemHash);
        squareRoomCabinet.setIsOpenable(true);


        baton = new Items(game, "baton", itemHash);
        baton.setIsTakeable(true);
        baton.setIsDroppable(true);
        baton.setIsUsable(true);

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

        squareRoomMain = new Rooms("squareRoomMain", roomHash);
        squareRoomMain.addItem(baton);
        squareRoomMain.addItem(rose);
        squareRoomMain.addItem(squareRoomCabinet);
        squareRoomMain.addDirection("south", rabbitsHouse);

        rabbitsHouseLivingRoom = new Rooms("rabbitsHouseLivingRoom", roomHash);
        rabbitsHouseLivingRoom.addItem(match);
        rabbitsHouseLivingRoom.addDirection("south", rabbitsHouseHallway);
        rabbitsHouseHallway = new Rooms("rabbitsHouseHallway", roomHash);
        //todo - add character "whiteRabbit
        rabbitsHouseHallway.addDirection("south", rabbitsHouseBackRoom);
        rabbitsHouseHallway.addDirection("north", rabbitsHouseLivingRoom);

        //Locations

        squareRoom = new Locations(game, "squareRoom", locationHash);
        squareRoom.setContainedRooms(squareRoomMain);
        squareRoom.setInitialRoom(squareRoomMain);

        rabbitsHouse = new Locations(game, "rabbitsHouse", locationHash);
        squareRoom.setContainedRooms(rabbitsHouseLivingRoom);
        squareRoom.setContainedRooms(rabbitsHouseHallway);
        squareRoom.setContainedRooms(rabbitsHouseBackRoom);

        forestZone = new Locations(game, "forestZone", locationHash);
        unbirthdayParty = new Locations(game, "unbirthdayParty", locationHash);
        mushForest = new Locations(game, "mushForest", locationHash);
        courtRoom = new Locations(game, "courtRoom", locationHash);
        safeHaven = new Locations(game, "safeHaven", locationHash);
        theVoid = new Locations(game, "theVoid", locationHash);

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
