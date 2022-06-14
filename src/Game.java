/**
 * title: Game.java
 * description: The main GAME class file for Assignment 4
 * date: June 6, 2022
 * @author Joshua P.J. Vilcsak
 * @version 1.0
 * @copyright 2022 Joshua P.J.Vilcsak
 */

/**
 * DOCUMENTATION...
 */

/**
 *
 *<H1>Alice In Sane - Main GAME</H1>
 *
 *<H3>Purpose and Description</H3>
 *
 *<P>
 * An application that runs the Alice In Sane Game. This class is the main class for this.
 *</P>
 *<P>
 * This game takes user input from the user and allows the player to make actions in-game based on
 * that input. There is a short story to follow where the player has to react appropriately.
 *</P>
 *
 *<DL>
 *<DT> Compiling and running instructions</DT>
 *<DT> Program requires the latest stable Java 17.0.2 SDK or better. Does not require external APIs</DT>
 *<DT> Change to the directory containing the source code.</DT>
 *<DD> Compile:    javac Game.java, javac Characters.java, javac Control.java, javac Actions.java, javac Items.java
 * javac Player.java, javac FileHandlingClass.java, java Locations.java</DD>
 *<DD> Run:        java Game</DD>
 *<DD> Document:   javadoc Game.java</DD>
 *</DL>
 */

/**
 *
 * <H3>Classes</H3>
 *
 *<P>
 *     public class Game {<BR>
 *         Contains the main() executable method for the application. Also handles the loading of assets, and registering
 *         user input and sends that to the parser. This class also handles certain gameOver and gameVictory conditions.
 *         The class instantiates ALL objects possible within the game, which are handled elsewhere.
 *</P>
 * <P>
 *     public class Control {<BR>
 *         Control receives input from Game and parses the input into usable actions, items, and characters, based
 *         on the action that was inputted. It then determines what Actions to take with what items and characters.
 *         Control determines what is to be done, and with what action method.
 * </P>
 * <P>
 *     public class Actions {<BR>
 *         The Actions class contains all the actionable actions that are available to the player. The methods are
 *         engaged by passing conditions inside of Control.
 * </P>
 * <P>
 *     public class Items {<BR>
 *         Contains the attributes and values of individual Items that have been instantiated inside of Game.
 *         The class itself contains private variables, with getters and setters to change values.
 * </P>
 * <P>
 *     public class Characters {<BR>
 *         Contains the attributes and values of individual Characters that have been instantiated inside of Game.
 *         The class itself contains private variables, with getters and setters to change values.
 * </P>
 * <P>
 *     public class Player {<BR>
 *         Contains the attributes and values of the Player that was instantiated inside of Game.
 *         The class itself contains private variables, with getters and setters to change values.
 * </P>
 * <P>
 *     public class Locations {<BR>
 *         Contains the attributes and characters of the Locations that have been instantiated inside of Game.
 *         The class itself contains private variables, with getters and setters to change values.1
 * </P>
 * <P>
 *     public class FileHandlingClass {<BR>
 *         This particular class handles the input and retrieval of txt files. The txt files of the game contain
 *         the names and attributes of the individual objects within the game.
 *         It retrieves the description files that pertain to each individual object.
 *         It retrieves the txt files that pertain to the story.
 * </P>
 *
 *
 * <H3>Game Methods</H3>
 *
 * <P>
 *     public static void main(String[] args) throws IOException, InterruptedException {<BR>
 *         Starts the main game Object.
 *         Uses the Game object method startGame()
 * </P>
 * <P>
 *     public void startGame(Game game) throws IOException, InterruptedException {<BR>
 *         Displays the beginning of the story, using FileHandlingClass object
 *         Begins registering user input within for loop that detects gameOver and gameVictory conditions. Uses the
 *         actionRegistry() method
 *         Sends the user input to the Control class to parse important key words
 *         Instantiates the Player object
 *         Instantiates the Game's required txt description files and the required Objects via loadAssets()
 *         Each user input will be within a loop that checks if the game is over. Either victory or game over.
 *         Closes user input registry when conditions reach false, killing process, therefore killing main()
 * </P>
 * <P>
 *     public void storySequence() throws InterruptedException {<BR>
 *         Displays the story sequence at the beginning of the game
 * </P>
 * <P>
 *      private void loadAssets(Game game) throws IOException {<BR>
 *          Loads the Characters Objects
 *          Loads the Items Objects
 *          Loads the Locations Objects
 *          Loads the Actions objects
 *
 *          Loads the descriptions of Characters, Items, Locations, Actions. Including the 'stale' Locations descriptions.
 *          Sets the inventory of the Locations
 *          Assigns the "names" of the objects that are actionable, putting them into a HashMap accessible by Control
 *          Assigns the attributes of the Objects via setters in each respective class
 * </P>
 * <P>
 *     public void actionRegistry(String command) throws InterruptedException {<BR>
 *         Passed String input from the Scanner is placed into actionRegistry.
 *         The Strings inputted are then placed into an ArrayList to be parsed by registry.cmdParser() method in
 *         Control class.
 * </P>
 *
 * <H3>Control Methods</H3>
 *
 * <P>
 *     public Control() {<BR>
 *         Default constructor for Control.
 * </P>
 * <P>
 *     public Control(Game game, Player player) {<BR>
 *         Primary constructor for Player class
 *         Imports Game dependency injection of Game object
 *         Imports Player dependency injection of Player object
 * </P>
 * <P>
 *     public void cmdParser(ArrayList<String> cmd) throws InterruptedException {<BR>
 *         Accepts the ArrayList of Strings labelled cmd from the inputRegistry() method from Game object
 *         Isolates Items objects, Characters objects and Actions objects and then passes it into outputCommand()
 *         method, which then passes that into the proper Actions method that uses the Items, Characters (if required)
 *         The Strings inside of the ArrayList are compared to the actionsHash, itemsHash, and charactersHash from
 *         Game object instantiated inside of the loadAssets() method in the Game class.
 * </P>
 * <P>
 *     public void outputCommand(Actions action) throws InterruptedException {<BR>
 *         Receives the Actions object from the cmdParser() method and then retrieves the appropriate amount of
 *         Items and Characters objects (if required for that action).
 *         Switch-case statement is used to isolate the name of the Actions object. The name of the Actions object
 *         is determined by the getActionName() method inside of Actions
 * </P>
 *
 * <H3>Actions Methods</H3>
 *
 * <P>
 *     Note: Actions class getters and setters are descriptive in their names. Special cases will be specified.
 * </P>
 * <P>
 *     public Actions() {<BR>
 *         Default constructor for Actions class
 * </P>
 * <P>
 *     public Actions(Player player, Game game, String name, HashMap<String, String> desc) {<BR>
 *         Primary constructor for Actions class
 *         Receives the dependency injection of Player object
 *         Receives the dependency injection of Game object
 *         Receives a name in the form of String
 *         Receives the description file from Game class instantiation of Actions, via the form of HashMap (key "name",
 *         value "description").
 * </P>
 * <P>
 *     public double calcEncounter() {<BR>
 *         Calculates a random number from 0-1 to be used by random encounter chance calculation.
 *         Returns a double.
 * </P>
 * <P>
 *     public void spawnEncounter() throws InterruptedException {<BR>
 *         If an action specifically calls on this, displays an encounter message
 *         Waits a couple seconds
 *         "Spawns" or references the soldier enemy. Adds it to Locations object the player is currently in.
 *         Sets the current health of the soldier object, in case it's been killed before.
 * </P>
 * <P>
 *     public void enemyAttack(Characters enemyChar) {<BR>
 *         Prompts user that character object is attacking back
 *         Retrieves attack characteristics of the character object
 *         Subtracts the player object's health based on the character attack damage
 * </P>
 *<P>
 *     public void checkSpecialEvents(Items targetObject, String targetName, String targetProperName, Items weaponObject,
 *     String weaponName, String weaponProperName, String burnName, String burnProperName)  {<BR>
 *         Imports all the required objects from the actionableAttack() method, and then checks if the attack creates
 *         a special event. Handles:
 *         Burning the haystack in the backroom
 *         Breaking the lock with a baton attack in squareRoom
 * </P>
 * <P>
 *     public void displayHelp() {<BR>
 *         Displays the descriptions of all the possible action files.
 * </P>
 * <P>
 *     public void displayInventory() {<BR>
 *         Uses the viewInventory() method inside of the player to display the items in the player object's inventory
 * </P>
 * <P>
 *     public void displayLook() {<BR>
 *         Displays the relevant locations object's description file
 *         Checks if there are 1 or more items in the room
 *         If there are none, display nothing. If there is one, prompt, if there are more than one, different prompt
 *         Checks if there are characters objects with the player in current location
 *         If there is, prompt and display description file of the characters object
 * </P>
 * <P>
 *     public void actionableTake(String itemName) throws NullPointerException {<BR>
 *         Receives the name of the Items Object in question, checks whether it's take-able or not.
 *         If it is, adds the Items object to the inventory, as long as the inventory isn't full.
 * </P>
 * <P>
 *     public void actionableDrop(String itemName) throws NullPointerException <BR>
 *         Receives the name of the items object in question, checks whether it's droppable in that location or not
 *         Checks whether you actually have that item in your inventory
 *         Adds item reference to the current player location if passes
 *         Removes item reference from the player if passes
 * </P>
 * <P>
 *     public void actionableExamine(String itemName) throws NullPointerException {<BR>
 *         Receives the relevant Item object's name from Control
 *         Checks whether or not player has the object's name in inventory
 *         Displays the description file of the Items Object
 * </P>
 * <P>
 *     public void actionableUse(String itemName) throws NullPointerException {<BR>
 *         Receives the relevant Items object's name from Control
 *         Checks whether the player has the object's name in inventory
 *         If the name equals a certain item, then do a certain number of actions, depending on that name
 * </P>
 * <P>
 *     public void actionableOpen(String itemName) throws NullPointerException {<BR>
 *         Receives the name relevant Items object's name from Control
 *         Checks whether the current location player is in contains object
 *         If openable, open the container
 *         If inventory has free space, add to player inventory
 *         Remove from item inventory
 *         If inventory is full, display error
 * </P>
 * <P>
 *     public void actionableMovement(String movementDirection) throws NullPointerException, InterruptedException {<BR>
 *         Receives the name of the Actions object's name
 *         Checks location if movement is possible, then executes relevant code to that direction
 * </P>
 * <P>
 *     public void actionableRun() {<BR>
 *         Sends the player to the previous location they were at, as long as there was a previous location stored.
 * </P>
 * <P>
 *     public void actionableAttack(String target1, String target2) throws NullPointerException, InterruptedException {<BR>
 *         A series of loops checking items and characters relevant to attacking another character or item target.
 *         Receives two objects, 2 items, or 1 item weapon and 1 item target, or 1 item weapon and 1 characters target
 * </P>
 *
 * <H3>Items Methods</H3>
 *
 * <P>
 *     Note: Getters and setters are self explanatory. No special cases.
 * </P>
 * <P>
 *     public Items() {<BR>
 *         Default constructor for Items class
 * </P>
 * <P>
 *     public Items(String name, HashMap<String, String> desc) {<BR>
 *         Primary constructor for Items class
 *         Assigns the String value name
 *         Assigns the description to the item via HashMap passed in via constructor
 * </P>
 * <P>
 *     public String getProperItemName() {<BR>
 *         Takes the name of the Items object based on the constructor input and assigns a proper "displayable" name
 *         for the object, when it's being sent to be viewed by the user.
 * </P>
 *
 * <H3>Characters Methods</H3>
 *
 * <P>
 *     Note: Getters and setters are self explanatory. No special cases.
 * </P>
 * <P>
 *     public Characters {<BR>
 *         Default constructor for the Characters class
 * </P>
 * <P>
 *     public Characters(String name, HashMap<String, String> desc) {<BR>
 *         Primary constructor for the Characters class
 *         Assigns the String value name
 *         Assigns the description to the character via HashMap passed in via constructor
 * </P>
 * <P>
 *     public double charAttackRoll() {<BR>
 *         Takes the pre-assigned max character damage and multiplies it by Math.random() to get value
 *         Returns total attack value of double
 * </P>
 * <P>
 *      public String getProperCharacterName() {<BR>
 *          Takes the name of the Characters object based on the constructor input and assigns a proper "displayable"
 *          name for the object, when its being sent to be viewed by the user.
 * </P>
 *
 * <H3>Player Methods</H3>
 *
 * <P>
 *     public Player() {<BR>
 *         Default constructor for the Player class
 * </P>
 * <P>
 *     public Player(int numberOfItems) {<BR>
 *         Receives the initialized number of player items in inventory. Set at 0
 * </P>
 * <P>
 *     public void viewInventory() {<BR>
 *         Displays the current inventory of the player. If there is nothing in inventory, a prompt will be displayed.
 * </P>
 * <P>
 *     public void setAddCurrentHealth(int effect) {<BR>
 *         Is used by actionableUse() method in Actions to add health to the current player. It does
 *         display an error message if the player is already at full health, according to player var maxHealth.
 * </P>
 * <P>
 *     public void displayHealth() {<BR>
 *         Displays the current health of the player, used every loop by the startGame() method in Game.
 * </P>
 *
 * <H3>Locations Methods</H3>
 *
 * <P>
 *     public Locations() {<BR>
 *         Default constructor for the Locations class
 * </P>
 * <P>
 *     public Locations(String name, HashMap<String, String> desc) {<BR>
 *         Receives the String value of name for object
 *         Receives the String locations description via HashMap file sent by constructor, via the "name" value of
 *         object as the key.
 * </P>
 * <P>
 *     public String getLocationProperName() {<BR>
 *         Returns the proper "displayable" name of the object when it's being presented to the user.
 * </P>
 * <P>
 *     public void addDirection(String dir, Locations location) {<BR>
 *         Adds a direction available when player is in the location object
 *         Receives a String value of the proper direction (relates to the actionsHash in Game)
 *         Receives the corresponding location available in that direction to the player
 * </P>
 * <P>
 *     public void removeDirection(String dir, Locations location) {<BR>
 *         Removes the indicated direction available to the player via input of the String "name" of direction, and
 *         the corresponding Locations object that the String dir relates to.
 * </P>
 * <P>
 *     public boolean locationContainsItem(String itemName) {<BR>
 *         Checks whether a location contains a specified item passed in via parameter
 * </P>
 * <P>
 *     public String getLocationDescription() {<BR>
 *         Retrieves the location description based on the state of the Locations object (whether it's been visited
 *         before by the player or not)
 * </P>
 *
 * <H3>FileHandlingClass Methods</H3>
 *
 * <P>
 *     public void fileOutput(String filename) {<BR>
 *         Requires the filename of a .txt file to work
 *         Searches for filename based on path
 *         Reads ever line by line
 *         Displays every line, as long as there is a nextLine in the txt file
 * </P>
 * <P>
 *     public HashMap<String, String> assetImport(String filename) throws IOException {<BR>
 *         Requires the filename of a .txt file to work
 *         Uses a delimiter to determine between the key value of a has and the following description of the key value.
 *         Key is prefacing the :, Value is after the : delimiter. Applied to a Array of 2 elements
 *         Applies both of these to a HashMap.
 *         Limits the length displayed on console so it's more readable.
 * </P>
 *
 */

/**
 *
 * <H3>Test Plan</H3>
 *
 *<P>
 *
 * 1. Run the application.
 * EXPECTED:
 *    Primary Game Structure:
 *    - Story is displayed.
 *    - User takes items from square Room
 *    - User breaks lock on door leading south
 *    - User goes south to living room
 *    - User takes items from living room
 *    - User goes south
 *    - User enters combat with white rabbit
 *    - User kills white rabbit and heads south
 *    - User encounters locked door in back room
 *    - Burn haystack with match - engages random event where user falls into safe haven
 *    - User takes items as needed
 *    - Goes back up, opens back room door
 *    - head south
 *    - Engages soldier in forest zone, takes items as needed
 *    - heads east or west to
 *     - West encounter caterpillar, engages, takes items
 *     - East encounter mad hatter, engages, takes items
 *    - Goes further east to encounter queen, encounters and engages, takes items as needed
 *    - User uses drink me bottle to become small to get into the void
 *    - Encounters alice who is too hard to attack
 *    - Takes the eatMeBox in the void, avoiding encounter with alice
 *    * User periodically visits the safe haven to drop off items that are necessary to save alice from insanity
 *    * Once user has all the items, the user can then use the eatMeBox to allow them to become tall enough to reach
 *    the surface, where game is concluded.
 *    * Random encounters with card soldiers are generated, only if the location is empty of any other characters
 *    - Player is rated on a scale for score depending on the amount of health they finish the game with
 * ACTUAL:
 *    Program NOW runs as expected. Troubleshooting and error fixing below
 *</P>
 *<P>
 *
 * 2. Good data cases:
 * Full Testing Spectrum:
 *    *** = Marks that it is an important feature that needed to be heavily tested, in multiple scenarios
 *    - Confirm that all actions are working in regularly needed cases.
 *    - Confirm that all objects that are needed to be dropped inside of the safe room work, and are detected for game victory
 *    - Confirm that player death results in termination of the game
 *    - Confirm that all characters do die, releasing the ability to move from location to location
 *    - Confirm that all locations are locked from movement when engaged with a player UNLESS:
 *    - Confirm that player can 'run' away from confrontation successfully
 *    - Confirm that using the eatMeBox does allow player to escape, but only if the necessary items are dropped into the safe room
 *    - Confirm that Characters attacking player CAN cause player death
 *    - Confirm that multiple variants of actions are accessible, such as 'i' and 'in' for inventory, or 'hit' and 'atk' for attack
 *    - Confirm that special events work as intended, burning the haystack and breaking the lock on the door
 *    - Confirm that some locations are inescapable or un-leave-able unless conditions (special events) are met
 *    - Confirm that examining all items does display a description
 *    - Confirm that looking in every room appropriately displays the content there
 *    - Confirm that all weapons have the correct hit-points associated
 *    - Confirm that all characters have the correct amount of health associated with the instantiated Characters object
 *    - *** Confirm that all invalid entries do not cause crashes or errors
 *    - *** Confirm that every location has the appropriate movement directions available, and that no errors are caused
 *    when choosing an invalid direction.
 *    - Confirm when looking into Location description with 'look', after character dies, the character is no longer listed.
 *    - Confirm that it is impossible to kill alice, or at least nearly impossible.. Her health value is too high to ever
 *    kill her before you've used every health item in the game to heal yourself, even if she's only hitting you at a low
 *    rate. Increased her health to 1000 to make double sure.
 *    - Confirm that no other prompts are displayed when the user chooses 'up' when in the safeHaven location, while all
 *    victory conditions are met: the game terminates after displaying all the victory stats.
 *
 * EXPECTED:
 *    Inputting a command, which then results in an error code because you're missing a item name or character name, should
 *    NOT cause issues during the next command input sequence
 * ACTUAL:
 *    Problem: Causing ArrayList index out of bounds error: The variables that were holding onto the compared names inside of Control
 *    would retain the previous entries, therefore causing an out of bounds error.
 *    Solution: Clearing (or making null) the variables that store action, item1, item2, and character after every attempt
 *    at inputting solved this problem.
 * EXPECTED:
 *    Character should be able to attack and kill player
 * ACTUAL:
 *    Problem: Characters are not able to kill the player.
 *    Solution: The "isAlive" boolean was not being properly passed into the game.startGame() while loop, therefore the game
 *    could continue as normal. Properly attaching the getIsAlive() method to the while-loop allows for the game to terminate
 *    itself when the false condition of isAlive is reached.
 * EXPECTED:
 *    Inputting movement data should move character, unless special condition is met. If condition is not met, then a prompt
 *    should prompt the user.
 * ACTUAL:
 *    Problem: Location is void according to NullPointerException
 *    Solution: Many hours of troubleshooting later, it is discovered that I had instantiated the objects inside of loadAssets
 *    improperly, and therefore, the Location wasn't available to the player object. By moving the instantiations into the
 *    correct order, this error is fixed.
 * EXPECTED:
 *
 * ACTUAL:
 *
 *</P>
 *<P>
 *
 * 3. Bad data cases:

 * EXPECTED:
 *    Player should not be able to enter into the safeHaven location without burning the Haystack item first with match
 * ACTUAL:
 *    Problem: Moving character out of the squareRoom location after triggering the special event of breaking lock would
 *    accidentally generate a new movement direction of "down" inside of rabbitsHouseBackroom. Incorrectly nested if-else
 *    statement system.
 *    Solution: Changing around the if-else hierarchy fixed this problem.
 * EXPECTED:
 *    Player should not be able to go south from the back room of rabbit's house without using the key on the door
 * ACTUAL:
 *    Works as expected
 * EXPECTED:
 *    Player should not be able to go back to the back room of rabbit's house when in safeHaven location when all
 *    victory conditions are met, game should end when the player goes back 'up'.
 * ACTUAL:
 *    Problem: The system does both. It prompts the user that the player has gone back to back room, but prompts the
 *    user that victory conditions are achieved. The game doesn't end either.
 *    Solution: Was missing condition at the end of the main game input loop in startGame() that would detect to break
 *    out of the while loop when victory conditions are achieved. Added the conditions fixed this problem, but didn't
 *    remove the prompt that the user went to the backroom.
 *    Solution: Made sure that the user could only go back to the main room when victory conditions WEREN'T achieved.
 *    Fixed
 * EXPECTED:
 * ACTUAL:
 *</P>
 */

/**
 * CODE...
 */

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
        registry = new Control(game, player);

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
