import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Actions {

    //Global variables stored for Actions
    private Game game;
    private Player player;
    private String actionDescription;
    private String actionName;

    /**
     * Default constructor
     */
    Actions() {

    }

    /**
     * Primary constructor for actions.
     * @param game dependency injection for the main Game class object newGame
     * @param name pushing in the name of the particular action
     * @param desc pushing in the description of the particular action using itemHash in Game.loadAssets()
     */
    public Actions(Player player, Game game, String name, HashMap<String, String> desc) {
        this.game = game;
        this.actionName = name;
        this.player = player;
        this.actionDescription = desc.get(name);
    }

    //Getters

    public String getActionName() {
        return actionName;
    }

    //Combat Calculation

    /**
     * Chance calculation to meet a random soldier.
     */
    public double calcEncounter() {
        return Math.random();
    }

    /**
     * Spawns enemy soldier if calcEncounter
     * @throws InterruptedException Possible error with sleep() method
     */
    public void spawnEncounter() throws InterruptedException {
        System.out.println("Out of nowhere, a card soldier makes an appearance!");
        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        player.getCurrentLocationObject().setLocationCharacters(game.characterHash.get("soldier"));
    }

    /**
     * This method allows the target character to attack the player.
     * @param enemyChar enemy character detected inside actionableAttack
     */
    public void enemyAttack(Characters enemyChar) {
        System.out.println(" -> Your target is attacking back!");
        double enemyAtk = enemyChar.charAttackRoll();
        DecimalFormat atkFormat = new DecimalFormat("#.0");
        System.out.println(" -> Your target attacks you with " + atkFormat.format(enemyAtk) + " worth of damage.");
        player.setSubtractCurrentHealth(enemyAtk);
    }

    /**
     * Calculation of special events that can occur
     * @param targetObject Object of target
     * @param targetName Name of Object target
     * @param targetProperName Proper "displayable" name of target object
     * @param weaponObject Object of weapon
     * @param weaponName Name of weapon object
     * @param weaponProperName Proper "displayable" name of weapon object
     * @param burnName Name of burn weapon object
     * @param burnProperName Proper "displayable" name of burn weapon object
     */
    public void checkSpecialEvents(Items targetObject, String targetName, String targetProperName, Items weaponObject, String weaponName, String weaponProperName, String burnName, String burnProperName) {
        //Special commands - attack or burn
        if (targetObject != null) {
            if (weaponName != null) {
                if (targetObject.getIsDestroyable()) {

                    //SquareRoom Event (Lock Destroyed) - check for right location, weapon, target
                    if (player.getCurrentLocationName().equals("squareRoom") && targetName.equals("squareRoomLock") && weaponObject.getIsWeapon()) {
                        System.out.println("You attack the " + targetProperName + " with your " + weaponProperName);
                        System.out.println("The lock drops to the ground, destroyed. Took a couple hits, but it finally gave way.");
                        player.getCurrentLocationObject().removeItem(targetObject);
                        game.locationHash.get("squareRoom").addDirection("south", game.locationHash.get("rabbitsHouseLivingRoom"));
                        game.locationHash.get("squareRoom").setLocationState(1);
                    } else {
                        System.out.println("Invalid target.");
                    }
                } else {
                    System.out.println("Target isn't destroyable");
                }
            } else if (burnName != null) {
                if (targetObject.getIsDestroyable()) {

                    //rabbitsHouseBackroom Event (Haystack Burnt) - check for right location, burnItem and target
                    if (player.getCurrentLocationName().equals("rabbitsHouseBackroom") && targetName.equals("haystack") && burnName.equals("match")) {
                        System.out.println("You burn the " + targetProperName + " with your " + burnProperName);
                        player.removeInventoryItem(game.itemHash.get("match"));
                        Locations prevLoc = player.getCurrentLocationObject();
                        prevLoc.setLocationState(1);
                        player.setPreviousLocation(prevLoc);

                        game.locationHash.get("rabbitsHouseBackroom").addDirection("down", game.locationHash.get("safeHaven"));
                        player.getCurrentLocationObject().removeItem(targetObject);
                        player.setCurrentLocation(game.locationHash.get("safeHaven"));
                    } else {
                        System.out.println("Invalid target.");
                    }
                } else {
                    System.out.println("Target isn't burnable");
                }
            } else {
                System.out.println("You can't do that...");
            }
        } else {
            System.out.println("No target...");
        }
    }

    //Display Functions - Simple functions to display something, nothing else.

    /**
     * Used by the "help" command to display all the descriptions of the individual commands available to the player.
     */
    public void displayHelp() {
        System.out.println(game.actionHash.get("help").actionDescription);
        System.out.println(game.actionHash.get("take").actionDescription);
        System.out.println(game.actionHash.get("run").actionDescription);
        System.out.println(game.actionHash.get("drop").actionDescription);
        System.out.println(game.actionHash.get("persuade").actionDescription);
        System.out.println(game.actionHash.get("north").actionDescription);
        System.out.println(game.actionHash.get("south").actionDescription);
        System.out.println(game.actionHash.get("east").actionDescription);
        System.out.println(game.actionHash.get("west").actionDescription);
        System.out.println(game.actionHash.get("again").actionDescription);
        System.out.println(game.actionHash.get("attack").actionDescription);
        System.out.println(game.actionHash.get("inventory").actionDescription);
        System.out.println(game.actionHash.get("examine").actionDescription);
        System.out.println(game.actionHash.get("look").actionDescription);
        System.out.println(game.actionHash.get("use").actionDescription);
        System.out.println(game.actionHash.get("give").actionDescription);
        System.out.println(game.actionHash.get("open").actionDescription);
        System.out.println();
    }


    /**
     * Displays the current player's inventory.
     */
    public void displayInventory() {
        player.viewInventory();
        System.out.println();
    }

    /**
     * Displays the current location description, and if there are any items, it displays the items in the locations
     * as well.
     */
    public void displayLook() {
        Locations currentLoc = player.getCurrentLocationObject();
        System.out.println(player.getCurrentLocationObject().getLocationDescription());
        //System.out.println(currentLoc.getLocationDescription());
        if (currentLoc.getLocationItems().size() == 1) {
            System.out.println();
            System.out.println("It appears there is an item in this room as well: ");
            currentLoc.getLocationItemNames();
            System.out.println();
        } else if (currentLoc.getLocationItems().size() > 1) {
            System.out.println();
            System.out.println("It appears there are some items in this room as well: ");
            currentLoc.getLocationItemNames();
            System.out.println();
        }
        if (currentLoc.getLocationCharacters().size() == 0) {
            System.out.println("There doesn't seem to be anyone else in here with you...");
        } else if (currentLoc.getLocationCharacters().size() > 0) {
            for (int i = 0; i < currentLoc.getLocationCharacters().size(); i++) {
                System.out.println("There also appears to be someone in here with you! It looks like... " + currentLoc.getLocationCharacters().get(i).getProperCharacterName());
                System.out.println();
                System.out.println(currentLoc.getLocationCharacters().get(i).getDescription());
            }
        }
    }

    //Actionable Functions - Methods that perform a particular set of actions.

    /**
     * Starts up the "Take" method, which removes an item from the inventory of the current player location, into the
     * inventory of the player.
     *
     * @param itemName The String name of the item that is passed in through console commands. This is translated to
     *                 an Items name.
     * @throws NullPointerException Catches the possible NullPointerException sent out by properName variable.
     */
    public void actionableTake(String itemName) throws NullPointerException {
        ArrayList<Items> locationItems = player.getCurrentLocationObject().getLocationItems();
        String properName = null;


        if (locationItems.isEmpty()) {
            System.out.println("There doesn't seem to be anything here to take at the moment.");
            System.out.println();
        } else {
            for (int i = 0; i < locationItems.size(); i++) {
                if (locationItems.get(i).getName().equals(itemName)) {
                    properName = locationItems.get(i).getProperItemName();
                    if (locationItems.get(i).getIsTakeable() && (player.getNumberOfItems() < player.getMaxInventory())) {
                        player.addInventoryItem(locationItems.get(i));
                        player.getCurrentLocationObject().removeItem(locationItems.get(i));
                        System.out.println(properName + " has been added to your inventory.");
                        System.out.println();
                    } else if (!locationItems.get(i).getIsTakeable()) {
                        System.out.println("You cannot take this particular item into your inventory.");
                    } else if (player.getNumberOfItems() >= player.getMaxInventory()) {
                        System.out.println("Your inventory is currently full. Drop something off in the safe room!");
                    }
                }
            }
            if (properName == null) {
                System.out.println("The " + properName + " item indicated doesn't exist in the area you're currently in. Try 'look' to see items available.");
                System.out.println();
            }
        }
    }

    /**
     * Starts up the "Drop" method, which removes an item from the inventory of the current player, and into the inventory
     * of the player's current location.
     *
     * @param itemName The String name of the item that is passed in through console commands. This is translated to an
     *                 Items name.
     * @throws NullPointerException Possible null name, or properName
     */
    public void actionableDrop(String itemName) throws NullPointerException {
        ArrayList<Items> playerItems = player.getCurrentInventory();
        String properName = null;

        if (playerItems.isEmpty()) {
            System.out.println("Hmmm. Your inventory is empty. Nothing to remove.");
            System.out.println();
        } else {
            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(itemName)) {
                    if (playerItems.get(i).getIsDroppable()) {
                        properName = playerItems.get(i).getProperItemName();
                        player.getCurrentLocationObject().addItem(playerItems.get(i));
                        player.removeInventoryItem(playerItems.get(i));
                        System.out.println(properName + " has been dropped in the " + player.getCurrentLocationObject().getLocationProperName());
                        System.out.println();
                        break;
                    } else if (playerItems.get(i).getIsSafeDroppable() && player.getCurrentLocationObject().getLocationName().equals("safeHaven")) {
                        properName = playerItems.get(i).getProperItemName();
                        player.getCurrentLocationObject().addItem(playerItems.get(i));
                        player.removeInventoryItem(playerItems.get(i));
                        System.out.println(properName + " has been dropped in the " + player.getCurrentLocationObject().getLocationProperName());
                        System.out.println();
                        break;
                    } else {
                        System.out.println("You can't do that.");
                    }
                }
            }
        }
    }

    /**
     * @param itemName Name of object to examine (displays description)
     * @throws NullPointerException Possible null
     */
    public void actionableExamine(String itemName) throws NullPointerException {
        ArrayList<Items> playerItems = player.getCurrentInventory();
        ArrayList<Items> locationItems = player.getCurrentLocationObject().getLocationItems();
        ArrayList<Items> totalItems = new ArrayList<>();
        String properName = null;

        if (!playerItems.isEmpty()) {
            totalItems.addAll(playerItems);
        }

        if (!locationItems.isEmpty()) {
            totalItems.addAll(locationItems);
        }

        if (totalItems.size() == 0) {
            System.out.println("There isn't anything in this area, and neither is there anything in your inventory.");
        } else {
            for (int i = 0; i < totalItems.size(); i++) {
                if (totalItems.get(i).getName().equals(itemName)) {
                    properName = totalItems.get(i).getProperItemName();
                    System.out.println(properName + ": " + game.itemHash.get(itemName).getItemDescription());
                    System.out.println();
                }
            }
        }
        if (properName == null) {
            System.out.println("The " + itemName + " item indicated doesn't exist in the area or in your inventory.");
            System.out.println();
        }

    }

    /**
     * On trigger, allows the player to use item as per indicated, if possible.
     * @param itemName Name of item object to be used
     * @throws NullPointerException Possible null
     */
    public void actionableUse(String itemName) throws NullPointerException {
        ArrayList<Items> playerItems = player.getCurrentInventory();

        if (playerItems.isEmpty()) {
            System.out.println("There isn't anything in your inventory. Pick something up first.");
        } else {
            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(itemName)) {
                    String nameObject = playerItems.get(i).getName();
                    Items itemObject = playerItems.get(i);
                    if (nameObject.equals("rose") || nameObject.equals("jam") || nameObject.equals("oyster") || nameObject.equals("teacup")) {
                        System.out.println("You use " + nameObject + " to restore up to " + itemObject.getHealingModifier());
                        player.setAddCurrentHealth((itemObject.getHealingModifier()));
                        player.removeInventoryItem(itemObject);
                        break;
                    }
                    if (nameObject.equals("key") && player.getCurrentLocationObject().getLocationName().equals("rabbitsHouseBackroom")) {
                        System.out.println("Hmm. You use the key on the back door. It makes an audible click as the door is now open. You can now go south.");
                        player.getCurrentLocationObject().addDirection("south", game.locationHash.get("rabbitsHouseBackroom"));
                        player.removeInventoryItem(game.itemHash.get("key"));
                        if (!player.getCurrentLocationObject().getLocationName().equals("rabbitsHouseBackroom")) {
                            System.out.println("You can't use the key here... Try somewhere else.");
                        } else {
                            System.out.println("You use the key on the backroom door. It opens and gives way into the forest zone.");
                            game.locationHash.get("rabbitsHouseBackroom").addDirection("south", game.locationHash.get("forestZone"));
                            game.locationHash.get("rabbitsHouseBackroom").removeItem(game.itemHash.get("rabbitsHouseBackdoor"));
                        }
                        break;
                    }
                    if (nameObject.equals("eatMeBox")) {
                        System.out.println("You grow large enough to get through the hole! (Hole up above is reachable)");
                        game.victoryConditionsGrow = true;
                    }
                    if (nameObject.equals("drinkMeBottle")) {
                        player.getCurrentLocationObject().addDirection("east", game.locationHash.get("theVoid"));
                        player.setIsShrunk(true);
                        System.out.println("You shrank down to to fit through the door! (Door to the void is unlocked)");
                    } else {
                        System.out.println("You don't currently have that item in your inventory...");
                    }
                }
            }
        }
    }

    /**
     * Allows the player to open openable objects contained in the location.
     *
     * @param itemName passes in the String name value of the object in question, derived from the grammar parser
     * @throws NullPointerException Possible null
     */
    public void actionableOpen(String itemName) throws NullPointerException {
        ArrayList<Items> locationItems = player.getCurrentLocationObject().getLocationItems();
        Items itemObject = null;

        if (locationItems.isEmpty()) {
            System.out.println("There isn't anything in here...");
        } else {
            for (int i = 0; i < locationItems.size(); i++) {
                if (locationItems.get(i).getName().equals(itemName)) {
                    itemObject = locationItems.get(i);
                    if (itemObject.getIsOpenable() && itemObject.getContainedItem() != null) {
                        System.out.println("There appears to be a " + itemObject.getContainedItem().getProperItemName() + " in this container. You put it in your inventory.");
                        Items containedItem = itemObject.getContainedItem();
                        player.addInventoryItem(containedItem);
                        itemObject.removeContainedItem();
                        break;
                    }
                    if (itemObject.getContainedItem() == null) {
                        System.out.println("There isn't anything in here.");
                        break;
                    }
                }
            }
        }
    }

    /**
     * Allows the player to move from location to location.
     *
     * @param movementDirection the direction of which is parsed via the Control class.. "north" or "south", etc.
     * @throws NullPointerException Possible null
     */
    public void actionableMovement(String movementDirection) throws NullPointerException, InterruptedException {
        if (player.getCurrentLocationObject().getLocationCharacters().size() > 0) {
            System.out.println("You can't go that way! Your current enemy has all the attention.. You can try to 'run' to your last location, though.");
        } else if (player.getCurrentLocationObject().getLocationName().equals("safeHaven") && game.victoryConditionsItems && game.victoryConditionsGrow && movementDirection.equals("up")) {
            player.getCurrentLocationObject().removeDirection("up", game.locationHash.get("rabbitsHouseBackroom"));
            game.gameVictory = true;
        } else if (player.getCurrentLocationObject().getLocationName().equals("courtRoom") && !player.getIsShrunk() && movementDirection.equals("east")) {
            System.out.println("I need to shrink down to size to use this doorway.");
        } else if (player.getCurrentLocationObject().getDirection(movementDirection) != null) {
            if (player.getCurrentLocationObject().getLocationName().equals("safeHaven") && !game.victoryConditionsItems && movementDirection.equals("up")) {
                System.out.println("Reminder: You haven't found all the items to escape.");
            }
            if (player.getCurrentLocationObject().getLocationName().equals("safeHaven") && !game.victoryConditionsGrow && movementDirection.equals("up")) {
                System.out.println("Reminder: You haven't been able to grow to reach the exit.");
            }
            Locations prevLoc = player.getCurrentLocationObject();
            player.setPreviousLocation(prevLoc);
            player.getPreviousLocation().setLocationState(1);
            player.setCurrentLocation(player.getCurrentLocationObject().getDirection(movementDirection));
            if (player.getCurrentLocationObject().getLocationCharacters() == null && calcEncounter() > 0.6) {
                spawnEncounter();
            }
        } else if (!player.getCurrentLocationObject().getLocationName().equals("squareRoom")) {
            System.out.println("You can't go that way. There's nowhere to go, or there's something blocking the way.");
        } else if (player.getCurrentLocationObject().getLocationName().equals("squareRoom") && movementDirection.equals("north")) {
            System.out.println("Can't go back up the way you came... You'll have to continue on forward.");
        }
    }

    /**
     * Allows the player to run to previously visited location
     */
    public void actionableRun() {
        if (player.getPreviousLocation() == null) {
            System.out.println("You haven't been anywhere before... nowhere to run!");
        } else {
            System.out.println("You run away... To the previous location you were at.");
            player.setCurrentLocation(player.getPreviousLocation());
        }
    }

    /**
     * Allows the player to attack a certain target.
     *
     * @param target1 character, object or item inputted. This method will parse to detect the proper Object
     * @param target2 character, object or item inputted. This method will parse to detect the proper Object
     * @throws NullPointerException Possible null
     */
    public void actionableAttack(String target1, String target2) throws NullPointerException, InterruptedException {
        ArrayList<Characters> locationCharacters = player.getCurrentLocationObject().getLocationCharacters();
        ArrayList<Items> playerItems = player.getCurrentInventory();
        ArrayList<Items> locationItems = player.getCurrentLocationObject().getLocationItems();
        Characters targetChar = null;
        Items targetObject = null;
        String targetName = null;
        String targetProperName = null;
        Items burnObject = null;
        String burnName = null;
        String burnProperName = null;
        Items weaponObject = null;
        String weaponName = null;
        String weaponProperName = null;
        boolean pass = false;

        do {

            //Test for valid character for parameters 1 and 2
            for (int i = 0; i < locationCharacters.size(); i++) {
                if (locationCharacters.get(i).getName().equals(target1)) {
                    targetChar = locationCharacters.get(i);
                    targetName = locationCharacters.get(i).getName();
                    targetProperName = locationCharacters.get(i).getProperCharacterName();
                }
                if (locationCharacters.get(i).getName().equals(target2)) {
                    targetChar = locationCharacters.get(i);
                    targetName = locationCharacters.get(i).getName();
                    targetProperName = locationCharacters.get(i).getProperCharacterName();
                }
            }

            //Test for valid item target for parameters 1 and 2
            for (int i = 0; i < locationItems.size(); i++) {
                if (locationItems.get(i).getName().equals(target1) && locationItems.get(i).getIsDestroyable()) {
                    targetObject = locationItems.get(i);
                    targetName = locationItems.get(i).getName();
                    targetProperName = locationItems.get(i).getProperItemName();
                }
                if (locationItems.get(i).getName().equals(target2) && locationItems.get(i).getIsDestroyable()) {
                    targetObject = locationItems.get(i);
                    targetName = locationItems.get(i).getName();
                    targetProperName = locationItems.get(i).getProperItemName();
                }
            }

            //Test for a valid target
            if (targetName == null) {
                System.out.println("Invalid target, or not nearby");
                break;
            }

            //Test for valid item weapon of parameter 1 and 2
            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(target1) && playerItems.get(i).getIsWeapon()) {
                    weaponName = playerItems.get(i).getName();
                    weaponProperName = playerItems.get(i).getProperItemName();
                    weaponObject = playerItems.get(i);
                }
            }

            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(target2) && playerItems.get(i).getIsWeapon()) {
                    weaponName = playerItems.get(i).getName();
                    weaponProperName = playerItems.get(i).getProperItemName();
                    weaponObject = playerItems.get(i);
                }
            }

            //Test for valid burn weapon on parameter 1 and 2
            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(target1) && playerItems.get(i).getIsBurn()) {
                    burnName = playerItems.get(i).getName();
                    burnProperName = playerItems.get(i).getProperItemName();
                    burnObject = playerItems.get(i);
                }
            }

            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(target2) && playerItems.get(i).getIsBurn()) {
                    burnName = playerItems.get(i).getName();
                    burnProperName = playerItems.get(i).getProperItemName();
                    burnObject = playerItems.get(i);
                }
            }

            //If you don't have either a weapon or a burn weapon, throw error.
            if (weaponObject == null && burnObject == null) {
                System.out.println("You don't the proper weapon or tool for that.");
                break;
            }

            if (targetChar == null && targetObject == null) {
                System.out.println("You need to specify what target you want to attack");
                break;
            }

            //Character attack
            if (targetChar != null) {
                System.out.println(" -> You attack " + targetProperName + " with your " + weaponProperName);
                double health = targetChar.getHealth();
                double attackpl = 0;
                int attackwp = weaponObject.getWeaponDamage();

                //Critical hit chance
                if (player.getCurrentHealth() == 100) {
                    double chance = Math.random();
                    if (chance > 0.80) {
                        attackpl = player.getBaseDamage() * 1.2;
                    } else {
                        attackpl = player.getBaseDamage();
                    }
                } else if (player.getCurrentHealth() < 80) {
                    double chance = Math.random();
                    if (chance > 0.80) {
                        attackpl = player.getBaseDamage() * 1.4;
                    } else {
                        attackpl = player.getBaseDamage();
                    }
                } else if (player.getCurrentHealth() < 50) {
                    double chance = Math.random();
                    if (chance > 0.50) {
                        attackpl = player.getBaseDamage() * 1.6;
                    } else {
                        attackpl = player.getBaseDamage();
                    }
                } else if (player.getCurrentHealth() < 30) {
                    double chance = Math.random();
                    if (chance > 0.50) {
                        attackpl = player.getBaseDamage() * 1.9;
                    } else {
                        attackpl = player.getBaseDamage();
                    }
                }

                double attackttl = attackpl + attackwp;
                health = health - attackttl;
                targetChar.setHealth(health);
                System.out.println(" -> You attack " + targetProperName + " with " + attackttl + " worth of damage.");
                System.out.println("** Current Enemy Health: " + targetChar.getHealth());
                TimeUnit.SECONDS.sleep(1);
                if (targetChar.getHealth() > 0) {
                    enemyAttack(targetChar);
                    if (player.getCurrentHealth() <= 0) {
                        System.out.println(" -> Game over. You have perished. Alice is forever trapped.");
                        player.setIsAlive(false);
                    }
                }

                if (targetChar.getHealth() <= 0) {
                    targetChar.setIsAlive(false);
                    System.out.println(" -> The " + targetProperName + " has perished. Falling to the ground, dead.");
                    player.getCurrentLocationObject().getLocationCharacters().remove(targetChar);
                }
                break;
            }

            checkSpecialEvents(targetObject, targetName, targetProperName, weaponObject, weaponName, weaponProperName, burnName, burnProperName);


        } while (pass);
    }
}
