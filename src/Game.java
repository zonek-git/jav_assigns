import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {


    private Control registry;
    private Player player;

    public boolean gameOver = false;
    public boolean victoryConditionsItems = false;
    public boolean victoryConditionsGrow = false;
    public boolean gameVictory = false;

    //Hashmap that stores all the object keys and their corresponding values, assigned in loadAssets
    public HashMap<String, Items> itemHash = new HashMap<>();
    public HashMap<String, Characters> characterHash = new HashMap<>();
    public HashMap<String, Locations> locationHash = new HashMap<>();
    public HashMap<String, Actions> actionHash = new HashMap<>();

    //Hashmap that stores all the keys and description values of the objects instantiated in loadAssets
    public HashMap<String, HashMap<String, String>> assetHash = new HashMap<>();
    public HashMap<String, String> actionDescHash;
    public HashMap<String, String> itemDescHash;
    public HashMap<String, String> characterDescHash;
    public HashMap<String, String> locationDescHash;
    public HashMap<String, String> roomDescHash;

    //Hashmap that stores the secondary descriptions for the locations, after being visited
    public HashMap<String, String> staleLocationDescHash;

    //Importing the story files, and description files for all objects
    private FileHandlingClass importAssetDescriptions = new FileHandlingClass();
    private FileHandlingClass importStoryDescriptions = new FileHandlingClass();

    /**
     * Main method that begins the game. Creates new Game object, and then starts the game using startGame Game method
     * @param args CMD args
     * @throws IOException File not found exception used by FileHandlingClass
     * @throws InterruptedException Possible error thrown by sleep() method
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Game newGame = new Game();
        newGame.startGame(newGame);
    }

    /**
     * startgame method - handles loading all the required items, (via loadAssets) and instantiates player
     * object. Story is displayed, and initial stats are created. (Starting locations, etc). Input from
     * user is also sent to parser in Control.
     *
     * @param game dependency injection of Game object
     * @throws IOException Potential error passed when using FileHandlingCLass
     * @throws InterruptedException Potential error passed when using sleep() functions
     */
    public void startGame(Game game) throws IOException, InterruptedException {
        storySequence();
        Scanner userInput = new Scanner(System.in);
        String command;

        player = new Player(0);
        loadAssets(game);
        Locations safeHaven = locationHash.get("safeHaven");
        player.setCurrentLocation(locationHash.get("squareRoom"));
        player.setStartingPlayerHealth(100);
        registry = new Control(game, player, actionHash);

        do {
            Locations prevLoc = player.getCurrentLocationObject();
            gameOver = player.getIsAlive();
            if (gameOver) {
                player.displayHealth();
                System.out.print(">> ");
                command = userInput.nextLine();
                if (command.equals("")) {
                    System.out.println("Hmm. I wonder what I should do?");
                } else if (!command.equals("quit")) {
                    actionRegistry(command);
                    if (player.getCurrentLocationObject() != prevLoc) {
                        actionHash.get("look").displayLook();
                    }
                } else {
                    break;
                }
            }

            //Every loop checks for victory conditions for the items inside safeHaven
            if (safeHaven.locationContainsItem("watch") && safeHaven.locationContainsItem("unbirthdayCake")
                    && safeHaven.locationContainsItem("hookah") && safeHaven.locationContainsItem("teapot")
                    && safeHaven.locationContainsItem("playingCard")) {
                victoryConditionsItems = true;
            }

            //If both the items are inside safeHaven and you have "unlocked" the exit in safeHaven via the eatmebox, you
            //can go "up" inside safeHaven which will trigger victory
            if (gameVictory) {

                System.out.println("Alice has successfully been reunited with her sister. Thank Goodness...");
                System.out.println("Victory!");
                System.out.println();

                if (player.getCurrentHealth() >= 80) {
                    System.out.println("50 Points for finishing with over 80 points of health.");
                }
                if (player.getCurrentHealth() > 40 && player.getCurrentHealth() < 80) {
                    System.out.println("20 Points for finishing with over 40 points of health.");
                }
                if (player.getCurrentHealth() > 20 && player.getCurrentHealth() < 40) {
                    System.out.println("0 points for finishing with less than 40 points of health.");
                }

                break;
            }

        } while ((gameOver));
        userInput.close();
    }

    /**
     * Displays the intro text at the beginning of the game, uses FileHandlingClass
     * @throws InterruptedException Potential thrown error when using sleep()
     */
    public void storySequence() throws InterruptedException {
        importStoryDescriptions.fileOutput("introPrompt");
        TimeUnit.SECONDS.sleep(3);
        importStoryDescriptions.fileOutput("introPrompt2");
    }


    /**
     * Load all game assets required for game start.
     * Items, Characters, Locations, Actions
     * @param game Dependency injection for Game object
     * @throws IOException
     */
    private void loadAssets(Game game) throws IOException {

        //Initial Description Location Import

        assetHash.put("characters", importAssetDescriptions.assetImport("characters"));
        assetHash.put("items", importAssetDescriptions.assetImport("items"));
        assetHash.put("locations", importAssetDescriptions.assetImport("locations"));
        assetHash.put("actions", importAssetDescriptions.assetImport("actions"));

        //All objects reference for *object* description hashmap

        actionDescHash = assetHash.get("actions");
        itemDescHash = assetHash.get("items");
        characterDescHash = assetHash.get("characters");
        locationDescHash = assetHash.get("locations");

        //Stale Description Location Import

        assetHash.put("locationsStale", importAssetDescriptions.assetImport("locationsStale"));
        staleLocationDescHash = assetHash.get("locationsStale");

        //Character object instantiation

        Characters madHatter = new Characters("madHatter", characterDescHash);
        madHatter.setMaxHealth(125);
        madHatter.setHealth(madHatter.getMaxHealth());
        madHatter.setMaxDamage(20);
        Characters redQueen = new Characters("redQueen", characterDescHash);
        redQueen.setMaxHealth(150);
        redQueen.setHealth(redQueen.getMaxHealth());
        redQueen.setMaxDamage(25);
        Characters alice = new Characters("alice", characterDescHash);
        alice.setMaxHealth(1000);
        alice.setHealth(alice.getMaxHealth());
        alice.setMaxDamage(40);
        Characters whiteRabbit = new Characters("whiteRabbit", characterDescHash);
        whiteRabbit.setMaxHealth(75);
        whiteRabbit.setHealth(whiteRabbit.getMaxHealth());
        whiteRabbit.setMaxDamage(20);
        Characters soldier = new Characters("soldier", characterDescHash);
        soldier.setMaxHealth(50);
        soldier.setHealth(soldier.getMaxHealth());
        soldier.setMaxDamage(15);
        Characters caterpillar = new Characters("caterpillar", characterDescHash);
        caterpillar.setMaxHealth(100);
        caterpillar.setHealth(caterpillar.getMaxHealth());
        caterpillar.setMaxDamage(22);

        characterHash.put("madHatter", madHatter);
        characterHash.put("hatter", madHatter);
        characterHash.put("redQueen", redQueen);
        characterHash.put("queen", redQueen);
        characterHash.put("alice", alice);
        characterHash.put("whiteRabbit", whiteRabbit);
        characterHash.put("rabbit", whiteRabbit);
        characterHash.put("whiterabbit", whiteRabbit);
        characterHash.put("soldier", soldier);
        characterHash.put("caterpillar", caterpillar);

        //Action object instantiation

        Actions look = new Actions(player, game, "look", actionDescHash);
        Actions inventory = new Actions(player, game, "inventory", actionDescHash);
        Actions help = new Actions(player, game, "help", actionDescHash);
        Actions take = new Actions(player, game, "take", actionDescHash);
        Actions run = new Actions(player, game, "run", actionDescHash);
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

        //Add items to game.actionHash() for retrieval

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
        actionHash.put("hit", attack);
        actionHash.put("burn", attack);
        actionHash.put("examine", examine);
        actionHash.put("use", use);
        actionHash.put("give", give);
        actionHash.put("open", open);
        actionHash.put("up", up);
        actionHash.put("climb", up);
        actionHash.put("upwards", up);
        actionHash.put("down", down);
        actionHash.put("downwards", down);

        //Item object instantiation

        Items baton = new Items("baton", itemDescHash);
        baton.setIsTakeable(true);
        baton.setIsUsable(true);
        baton.setIsWeapon(true);
        baton.setWeaponDamage(10);
        Items rose = new Items("rose", itemDescHash);
        rose.setIsTakeable(true);
        rose.setIsDroppable(true);
        rose.setHealingModifier(75);
        rose.setIsUsable(true);
        //Key Item
        Items watch = new Items("watch", itemDescHash);
        watch.setIsTakeable(true);
        watch.setIsSafeDroppable(true);
        Items drinkMeBottle = new Items("drinkMeBottle", itemDescHash);
        drinkMeBottle.setIsTakeable(true);
        drinkMeBottle.setIsSafeDroppable(true);
        drinkMeBottle.setIsUsable(true);
        Items eatMeBox = new Items("eatMeBox", itemDescHash);
        eatMeBox.setIsTakeable(true);
        eatMeBox.setIsSafeDroppable(true);
        eatMeBox.setIsUsable(true);
        Items key = new Items("key", itemDescHash);
        key.setIsTakeable(true);
        key.setIsSafeDroppable(true);
        key.setIsUsable(true);
        Items oyster = new Items("oyster", itemDescHash);
        oyster.setIsTakeable(true);
        oyster.setIsDroppable(true);
        oyster.setHealingModifier(40);
        Items match = new Items("match", itemDescHash);
        match.setIsTakeable(true);
        match.setIsSafeDroppable(true);
        match.setIsUsable(true);
        match.setIsBurn(true);
        //Key Item
        Items hookah = new Items("hookah", itemDescHash);
        hookah.setIsTakeable(true);
        hookah.setIsSafeDroppable(true);
        hookah.setHealingModifier(-10);
        Items squareRoomCabinet = new Items("squareRoomCabinet", itemDescHash);
        squareRoomCabinet.setIsOpenable(true, match);
        //Key Item
        Items teapot = new Items("teapot", itemDescHash);
        teapot.setIsTakeable(true);
        teapot.setIsSafeDroppable(true);
        Items teacup = new Items("teacup", itemDescHash);
        teacup.setIsTakeable(true);
        teacup.setIsUsable(true);
        teacup.setHealingModifier(50);
        //Key Item
        Items unbirthdayCake = new Items("unbirthdayCake", itemDescHash);
        unbirthdayCake.setIsTakeable(true);
        unbirthdayCake.setIsSafeDroppable(true);
        Items mallet = new Items("mallet", itemDescHash);
        mallet.setIsTakeable(true);
        mallet.setIsWeapon(true);
        mallet.setWeaponDamage(28);
        Items jam = new Items("jam", itemDescHash);
        jam.setIsTakeable(true);
        jam.setHealingModifier(30);
        jam.setIsUsable(true);
        jam.setIsDroppable(true);
        //Key Item
        Items playingCard = new Items("playingCard", itemDescHash);
        playingCard.setIsTakeable(true);
        playingCard.setIsSafeDroppable(true);
        Items squareRoomLock = new Items("squareRoomLock", itemDescHash);
        squareRoomLock.setIsTakeable(false);
        squareRoomLock.setIsDestroyable(true);
        Items haystack = new Items("haystack", itemDescHash);
        haystack.setIsDestroyable(true);
        Items sword = new Items("sword", itemDescHash);
        sword.setIsWeapon(true);
        sword.setIsTakeable(true);
        sword.setWeaponDamage(20);
        Items rabbitsHouseBackdoor = new Items("rabbitsHouseBackdoor", itemDescHash);
        rabbitsHouseBackdoor.setIsUsable(true);
        Items forestZoneChest = new Items("forestZoneChest", itemDescHash);
        forestZoneChest.setIsOpenable(true, teapot);

        //Add items to game.itemHash() for retrieval

        itemHash.put("backdoor", rabbitsHouseBackdoor);
        itemHash.put("squareRoomCabinet", squareRoomCabinet);
        itemHash.put("cabinet", squareRoomCabinet);
        itemHash.put("baton", baton);
        itemHash.put("rose", rose);
        itemHash.put("watch", watch);
        itemHash.put("oyster", oyster);
        itemHash.put("drinkMeBottle", drinkMeBottle);
        itemHash.put("bottle", drinkMeBottle);
        itemHash.put("eatMeBox", eatMeBox);
        itemHash.put("box", eatMeBox);
        itemHash.put("key", key);
        itemHash.put("match", match);
        itemHash.put("matchstick", match);
        itemHash.put("hookah", hookah);
        itemHash.put("teapot", teapot);
        itemHash.put("teacup", teacup);
        itemHash.put("unbirthdayCake", unbirthdayCake);
        itemHash.put("cake", unbirthdayCake);
        itemHash.put("mallet", mallet);
        itemHash.put("jam", jam);
        itemHash.put("playingCard", playingCard);
        itemHash.put("card", playingCard);
        itemHash.put("squareRoomLock", squareRoomLock);
        itemHash.put("padlock", squareRoomLock);
        itemHash.put("lock", squareRoomLock);
        itemHash.put("haystack", haystack);
        itemHash.put("sword", sword);
        itemHash.put("forestZoneChest", forestZoneChest);
        itemHash.put("chest", forestZoneChest);

        //Location Instantiation

        Locations forestZone = new Locations("forestZone", locationDescHash);
        forestZone.addItem(oyster);
        forestZone.setLocationCharacters(soldier);
        forestZone.addItem(forestZoneChest);
        Locations unbirthdayParty = new Locations("unbirthdayParty", locationDescHash);
        unbirthdayParty.addItem(teacup);
        unbirthdayParty.addItem(rose);
        unbirthdayParty.addItem(drinkMeBottle);
        unbirthdayParty.addItem(playingCard);
        unbirthdayParty.setLocationCharacters(madHatter);
        Locations mushForest = new Locations("mushForest", locationDescHash);
        mushForest.addItem(unbirthdayCake);
        mushForest.addItem(mallet);
        mushForest.addItem(hookah);
        mushForest.setLocationCharacters(caterpillar);
        Locations courtRoom = new Locations("courtRoom", locationDescHash);
        courtRoom.addItem(watch);
        courtRoom.setLocationCharacters(redQueen);
        Locations safeHaven = new Locations("safeHaven", locationDescHash);
        safeHaven.addItem(key);
        safeHaven.addItem(sword);
        Locations theVoid = new Locations("theVoid", locationDescHash);
        theVoid.setLocationCharacters(alice);
        theVoid.addItem(eatMeBox);
        Locations squareRoom = new Locations("squareRoom", locationDescHash);
        squareRoom.addItem(rose);
        squareRoom.addItem(squareRoomLock);
        squareRoom.addItem(squareRoomCabinet);
        squareRoom.addItem(baton);
        Locations rabbitsHouseLivingRoom = new Locations("rabbitsHouseLivingRoom", locationDescHash);
        rabbitsHouseLivingRoom.addItem(jam);
        Locations rabbitsHouseHallway = new Locations("rabbitsHouseHallway", locationDescHash);
        rabbitsHouseHallway.setLocationCharacters(whiteRabbit);
        Locations rabbitsHouseBackroom = new Locations("rabbitsHouseBackroom", locationDescHash);
        rabbitsHouseBackroom.addItem(haystack);
        rabbitsHouseBackroom.addItem(rose);
        rabbitsHouseBackroom.addItem(rabbitsHouseBackdoor);

        //Add locations to game.locationHash() for retrieval

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

        //Secondary Descriptions for "revisit" condition

        squareRoom.setStaleStateDesc(staleLocationDescHash);
        safeHaven.setStaleStateDesc(staleLocationDescHash);
        forestZone.setStaleStateDesc(staleLocationDescHash);
        rabbitsHouseLivingRoom.setStaleStateDesc(staleLocationDescHash);
        rabbitsHouseHallway.setStaleStateDesc(staleLocationDescHash);
        rabbitsHouseBackroom.setStaleStateDesc(staleLocationDescHash);
        mushForest.setStaleStateDesc(staleLocationDescHash);
        unbirthdayParty.setStaleStateDesc(staleLocationDescHash);
        courtRoom.setStaleStateDesc(staleLocationDescHash);
        theVoid.setStaleStateDesc(staleLocationDescHash);

        //Add Directions for locations

        rabbitsHouseLivingRoom.addDirection("south", rabbitsHouseHallway);
        rabbitsHouseLivingRoom.addDirection("north", squareRoom);
        rabbitsHouseHallway.addDirection("north", rabbitsHouseLivingRoom);
        rabbitsHouseHallway.addDirection("south", rabbitsHouseBackroom);
        rabbitsHouseBackroom.addDirection("north", rabbitsHouseHallway);
        forestZone.addDirection("north", rabbitsHouseBackroom);
        forestZone.addDirection("west", mushForest);
        forestZone.addDirection("east", unbirthdayParty);
        mushForest.addDirection("east", forestZone);
        unbirthdayParty.addDirection("west", forestZone);
        unbirthdayParty.addDirection("east", courtRoom);
        courtRoom.addDirection("west", unbirthdayParty);
        safeHaven.addDirection("up", rabbitsHouseBackroom);

    }

    /**
     * Action registry which registers all the commands inputted into console. This is then sent off to the command
     * parser method in Control via the registry object.
     * @param command input retrieved from the console input
     * @throws InterruptedException
     */
    public void actionRegistry(String command) throws InterruptedException {
        String cmdSentence = command.toLowerCase();
        String[] cmdArray = cmdSentence.split(" ");
        ArrayList<String> cmdArrayList = new ArrayList<>(Arrays.asList(cmdArray));

        registry.cmdParser(cmdArrayList);
    }
}
