import java.io.*;
import java.util.*;

public class Game {
    public Control registry;

    public Player player;

    protected HashMap<String, Items> itemHash = new HashMap<>();
    protected HashMap<String, Characters> characterHash = new HashMap<>();
    protected HashMap<String, Locations> locationHash = new HashMap<>();
    protected HashMap<String, Actions> actionHash = new HashMap<>();

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
        player = new Player(game, 0);
        loadAssets(game);
        player.setCurrentLocation(locationHash.get("squareRoom"));
        player.setStartingPlayerHealth(100);
        registry = new Control(game, player, actionHash);

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

    private void loadAssets(Game game) throws IOException {

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
        madHatter.setMaxHealth(150);
        madHatter.setHealth(madHatter.getMaxHealth());
        Characters redQueen = new Characters(this, "redQueen", characterDescHash);
        redQueen.setMaxHealth(200);
        redQueen.setHealth(redQueen.getMaxHealth());
        Characters alice = new Characters(this, "alice", characterDescHash);
        alice.setMaxHealth(1000);
        alice.setHealth(redQueen.getMaxHealth());
        Characters whiteRabbit = new Characters(this, "whiteRabbit", characterDescHash);
        whiteRabbit.setMaxHealth(100);
        whiteRabbit.setHealth(redQueen.getMaxHealth());
        Characters soldier = new Characters(this, "soldier", characterDescHash);
        soldier.setMaxHealth(75);
        soldier.setHealth(redQueen.getMaxHealth());
        Characters caterpillar = new Characters(this, "caterpillar", characterDescHash);
        caterpillar.setMaxHealth(150);
        caterpillar.setHealth(redQueen.getMaxHealth());

        characterHash.put("madHatter", madHatter);
        characterHash.put("hatter", madHatter);
        characterHash.put("redQueen", redQueen);
        characterHash.put("queen", redQueen);
        characterHash.put("alice", alice);
        characterHash.put("whiteRabbit", whiteRabbit);
        characterHash.put("rabbit", whiteRabbit);
        characterHash.put("soldier", soldier);
        characterHash.put("caterpillar", caterpillar);

        // Actions //

        Actions look = new Actions(player,game,"look", actionDescHash);
        Actions inventory = new Actions(player, game, "inventory", actionDescHash);
        Actions help = new Actions(player, game,"help", actionDescHash);
        Actions take = new Actions(player, game, "take", actionDescHash);
        Actions run = new Actions(player, game,"run", actionDescHash);
        Actions drop = new Actions(player, game, "drop", actionDescHash);
        Actions persuade = new Actions(player, game, "persuade", actionDescHash);
        Actions north = new Actions(player, game, "north", actionDescHash);
        Actions south = new Actions(player, game, "south", actionDescHash);
        Actions east = new Actions(player, game, "east", actionDescHash);
        Actions west = new Actions(player, game, "west", actionDescHash);
        Actions again = new Actions(player, game, "again", actionDescHash);
        Actions attack = new Actions(player, game, "attack", actionDescHash);
        Actions examine = new Actions(player, game, "examine", actionDescHash);
        Actions use = new Actions(player, game, "use", actionDescHash);
        Actions give = new Actions(player, game, "give", actionDescHash);
        Actions open = new Actions(player, game, "open", actionDescHash);
        Actions down = new Actions(player, game, "down", actionDescHash);
        Actions up = new Actions(player, game, "up", actionDescHash);

        actionHash.put("look", look);
        actionHash.put("inventory", inventory);
        actionHash.put("invent", inventory);
        actionHash.put("in", inventory);
        actionHash.put("i", inventory);
        actionHash.put("help", help);
        actionHash.put("take", take);
        actionHash.put("run", run);
        actionHash.put("drop", drop);
        actionHash.put("persuade", persuade);
        actionHash.put("north", north);
        actionHash.put("n", north);
        actionHash.put("south", south);
        actionHash.put("s", south);
        actionHash.put("east", east);
        actionHash.put("e", east);
        actionHash.put("west", west);
        actionHash.put("w", west);
        actionHash.put("again", again);
        actionHash.put("attack", attack);
        actionHash.put("atk", attack);
        actionHash.put("examine", examine);
        actionHash.put("use", use);
        actionHash.put("give", give);
        actionHash.put("open", open);
        actionHash.put("up", up);
        actionHash.put("upwards", up);
        actionHash.put("down", down);
        actionHash.put("downwards", down);

        //Items
        //Important Attributes:
        //  isUsable
        //  isWeapon
        //  isDroppable
        //  isSafeDrop
        //  isTakeable
        //  isOpenable
        //  isDestroyable


        Items baton = new Items(game, "baton", itemDescHash);
        baton.setIsTakeable(true);
        baton.setIsUsable(true);
        baton.setIsWeapon(true);

        Items rose = new Items(game, "rose", itemDescHash);
        rose.setIsTakeable(true);
        rose.setIsDroppable(true);
        rose.setHealingModifier(25);
        rose.setIsUsable(true);

        Items watch = new Items(game, "watch", itemDescHash);
        watch.setIsTakeable(true);
        watch.setIsSafeDroppable(true);

        Items drinkMeBottle = new Items(game, "drinkMeBottle", itemDescHash);
        drinkMeBottle.setIsTakeable(true);
        drinkMeBottle.setIsSafeDroppable(true);
        drinkMeBottle.setIsUsable(true);

        Items eatMeBox = new Items(game, "eatMeBox", itemDescHash);
        eatMeBox.setIsTakeable(true);
        eatMeBox.setIsSafeDroppable(true);
        eatMeBox.setIsUsable(true);

        Items key = new Items(game, "key", itemDescHash);
        key.setIsTakeable(true);
        key.setIsSafeDroppable(true);
        key.setIsUsable(true);

        Items oyster = new Items(game, "oyster", itemDescHash);
        oyster.setIsTakeable(true);
        oyster.setIsDroppable(true);
        oyster.setHealingModifier(40);

        Items match = new Items(game, "match", itemDescHash);
        match.setIsTakeable(true);
        match.setIsSafeDroppable(true);
        match.setIsUsable(true);

        Items hookah = new Items(game, "hookah", itemDescHash);
        hookah.setIsTakeable(true);
        hookah.setIsSafeDroppable(true);
        hookah.setHealingModifier(-10);

        Items squareRoomCabinet = new Items(game, "squareRoomCabinet", itemDescHash);
        squareRoomCabinet.setIsOpenable(true, match);

        Items teapot = new Items(game, "teapot", itemDescHash);
        teapot.setIsTakeable(true);
        teapot.setIsSafeDroppable(true);

        Items teacup = new Items(game, "teacup", itemDescHash);
        teacup.setIsTakeable(true);

        Items unbirthdayCake = new Items(game, "unbirthdayCake", itemDescHash);
        unbirthdayCake.setIsTakeable(true);

        Items mallet = new Items(game, "mallet", itemDescHash);
        mallet.setIsTakeable(true);

        Items jam = new Items(game, "jam", itemDescHash);
        jam.setIsTakeable(true);
        jam.setHealingModifier(30);
        jam.setIsUsable(true);

        Items gasMask = new Items(game, "gasMask", itemDescHash);
        gasMask.setIsUsable(true);
        gasMask.setIsTakeable(true);

        Items umbrella = new Items(game, "umbrella", itemDescHash);
        umbrella.setIsTakeable(true);
        umbrella.setIsUsable(true);

        Items playingCard = new Items(game, "playingCard", itemDescHash);
        playingCard.setIsTakeable(true);

        Items squareRoomLock = new Items(game, "squareRoomLock", itemDescHash);
        squareRoomLock.setIsTakeable(false);
        squareRoomLock.setIsDestroyable(true);

        Items haystack = new Items(game, "haystack", itemDescHash);

        itemHash.put("squareRoomCabinet", squareRoomCabinet);
        itemHash.put("cabinet", squareRoomCabinet);
        itemHash.put("baton", baton);
        itemHash.put("rose", rose);
        itemHash.put("watch", watch);
        itemHash.put("drinkMeBottle", drinkMeBottle);
        itemHash.put("bottle", drinkMeBottle);
        itemHash.put("eatMeBox", eatMeBox);
        itemHash.put("box", eatMeBox);
        itemHash.put("key", key);
        itemHash.put("match", match);
        itemHash.put("hookah", hookah);
        itemHash.put("teapot", teapot);
        itemHash.put("teacup", teacup);
        itemHash.put("unbirthdayCake", unbirthdayCake);
        itemHash.put("cake", unbirthdayCake);
        itemHash.put("mallet", mallet);
        itemHash.put("jam", jam);
        itemHash.put("gasMask", gasMask);
        itemHash.put("mask", gasMask);
        itemHash.put("umbrella", umbrella);
        itemHash.put("playingCard", playingCard);
        itemHash.put("card", playingCard);
        itemHash.put("squareRoomLock", squareRoomLock);
        itemHash.put("padlock", squareRoomLock);
        itemHash.put("lock", squareRoomLock);
        itemHash.put("haystack", haystack);

        //Locations + Rooms

        Locations forestZone = new Locations(game, "forestZone", locationDescHash);
        Locations unbirthdayParty = new Locations(game, "unbirthdayParty", locationDescHash);
        Locations mushForest = new Locations(game, "mushForest", locationDescHash);
        Locations courtRoom = new Locations(game, "courtRoom", locationDescHash);
        Locations safeHaven = new Locations(game, "safeHaven", locationDescHash);
        Locations theVoid = new Locations(game, "theVoid", locationDescHash);
        Locations squareRoom = new Locations(game, "squareRoom", locationDescHash);
        squareRoom.addItem(rose);
        squareRoom.addItem(squareRoomLock);
        squareRoom.addItem(squareRoomCabinet);
        squareRoom.addItem(baton);

        Locations rabbitsHouseLivingRoom = new Locations(game, "rabbitsHouseLivingRoom", locationDescHash);
        rabbitsHouseLivingRoom.addItem(jam);

        Locations rabbitsHouseHallway = new Locations(game, "rabbitsHouseHallway", locationDescHash);
        rabbitsHouseHallway.setLocationCharacters(whiteRabbit);

        Locations rabbitsHouseBackroom = new Locations(game, "rabbitsHouseBackroom", locationDescHash);
        rabbitsHouseBackroom.addItem(haystack);


        locationHash.put("forestZone", forestZone);
        locationHash.put("unbirthdayParty", unbirthdayParty);
        locationHash.put("mushForest", mushForest);
        locationHash.put("courtRoom", courtRoom);
        locationHash.put("safeHaven", safeHaven);
        locationHash.put("theVoid", theVoid);
        locationHash.put("squareRoom", squareRoom);
        locationHash.put("rabbitsHouseLivingRoom", rabbitsHouseLivingRoom);
        locationHash.put("rabbitsHouseHallway", rabbitsHouseHallway);
        locationHash.put("rabbitsHouseBackroom", rabbitsHouseBackroom);

        //Add Directions

        squareRoom.addDirection("south", rabbitsHouseLivingRoom);
        rabbitsHouseLivingRoom.addDirection("south", rabbitsHouseHallway);
        rabbitsHouseLivingRoom.addDirection("north", squareRoom);
        rabbitsHouseHallway.addDirection("north", rabbitsHouseLivingRoom);
        rabbitsHouseHallway.addDirection("south", rabbitsHouseBackroom);
        rabbitsHouseBackroom.addDirection("north", rabbitsHouseHallway);
        safeHaven.addDirection("up", rabbitsHouseBackroom);
        //Add direction of SOUTH in backroom when "key" is in the inventory
        //Add direction of UP to the saferoom when the haypile has been burned away

    }



    public void actionRegistry(String command) {
        String cmdSentence = command.toLowerCase();
        String[] cmdArray = cmdSentence.split(" ");
        ArrayList<String> cmdArrayList = new ArrayList<>(Arrays.asList(cmdArray));

        registry.cmdParser(cmdArrayList);
    }
}
