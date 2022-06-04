import java.util.ArrayList;
import java.util.HashMap;

public class Actions {

    Game game;
    Player player;
    private String actionDescription;
    private String actionName;
    private ArrayList<String> actionNames;
    private boolean encounter = false;

    //TODO instead of doing it all this way, I think it would be a good idea to take a parser for TXT file, remember?

    /**
     * Default constructor
     */
    Actions() {

    }

    /**
     * Primary constructor for actions.
     *
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

    public void delegator(Actions action) {

    }


    //Getters

    public String getActionName() {
        return actionName;
    }


    //Setters


    //Combat Calculation


    /**
     * Chance calculation to meet a random soldier.
     */
    public double calcEncounter() {
        return Math.random();
    }

    public void spawnEncounter() {
        Characters spawnSoldier = game.characterHash.get("soldier");
        spawnSoldier.setHealth(spawnSoldier.getMaxHealth());
    }


    //Display Functions - Simple functions to display something, nothing else.


    /**
     * Used by the "help" command to display all the descriptions of the individual commands available to the player.
     */
    public void displayHelp() {
        System.out.println(game.actionHash.get("help"));
        System.out.println(game.actionHash.get("take"));
        System.out.println(game.actionHash.get("run"));
        System.out.println(game.actionHash.get("drop"));
        System.out.println(game.actionHash.get("persuade"));
        System.out.println(game.actionHash.get("north"));
        System.out.println(game.actionHash.get("south"));
        System.out.println(game.actionHash.get("east"));
        System.out.println(game.actionHash.get("west"));
        System.out.println(game.actionHash.get("again"));
        System.out.println(game.actionHash.get("attack"));
        System.out.println(game.actionHash.get("inventory"));
        System.out.println(game.actionHash.get("examine"));
        System.out.println(game.actionHash.get("look"));
        System.out.println(game.actionHash.get("use"));
        System.out.println(game.actionHash.get("give"));
        System.out.println(game.actionHash.get("open"));
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

        if (currentLoc.getLocationState() == 0) {
            System.out.println(currentLoc.getLocationDescription());
        } else if (currentLoc.getLocationState() == 1) {
            System.out.println(currentLoc.getStaleDescription());
        }
        //System.out.println(currentLoc.getLocationDescription());
        Characters char1 = null;
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
                char1 = currentLoc.getLocationCharacters().get(i);
            }
            if (char1 != null) {
                System.out.println("There also appears to be someone in here with you! It looks like... " + char1.getProperCharacterName());
                System.out.println(char1.getDescription());
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
     * @throws NullPointerException
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
                    } else {
                        System.out.println("You can't drop this particular item.");
                    }
                    if (playerItems.get(i).getIsSafeDroppable() && player.getCurrentLocationObject().getLocationName().equals("safeRoom")) {
                        properName = playerItems.get(i).getProperItemName();
                        player.getCurrentLocationObject().addItem(playerItems.get(i));
                        player.removeInventoryItem(playerItems.get(i));
                        System.out.println(properName + " has been dropped in the " + player.getCurrentLocationObject().getLocationProperName());
                        System.out.println();
                        break;
                    } else {
                        System.out.println("This item is droppable.. but you should probably put it in the safe room to aid in Alice's recovery.");
                    }

                }
            }
            if (properName == null) {
                System.out.println("The " + itemName + " item indicated doesn't exist in your inventory. Please use 'inventory' to check your inventory.");
                System.out.println();
            }
        }
    }

    /**
     * @param itemName
     * @throws NullPointerException
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

    public void actionableUse(String itemName) throws NullPointerException {
        ArrayList<Items> playerItems = player.getCurrentInventory();
        String properName = null;

        if (playerItems.isEmpty()) {
            System.out.println("There isn't anything in your inventory. Pick something up first.");
        } else {
            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(itemName)) {
                    String nameObject = playerItems.get(i).getName();
                    Items itemObject = playerItems.get(i);
                    if (nameObject.equals("rose") || nameObject.equals("jam") || nameObject.equals("oyster")) {
                        player.setAddCurrentHealth((itemObject.getHealingModifier()));

                    //GasMask
                    } else if (nameObject.equals("gasMask")) {
                        player.setWearingGasMask(true);

                    //Key
                    } else if (nameObject.equals("key") && player.getCurrentLocationObject().getLocationName().equals("rabbitsHouseBackroom")) {
                        System.out.println("Hmm. You use the key on the back door. It makes an audible click as the door is now open. You can now go south.");

                        if (!player.getCurrentLocationObject().getLocationName().equals("rabbitsHouseBackroom")) {
                            System.out.println("You can't use the key here... Try somewhere else.");
                        }

                    //Umbrella
                    } else if (nameObject.equals("umbrella")) {
                        //todo
                    }
                } else {
                    System.out.println("You don't currently have that item in your inventory...");
                }
            }
        }
    }

    /**
     * Allows the player to open openable objects contained in the location.
     *
     * @param itemName passes in the String name value of the object in question, derived from the grammar parser
     * @throws NullPointerException
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
     * @throws NullPointerException
     */
    public void actionableMovement(String movementDirection) throws NullPointerException {
        if (player.getCurrentLocationObject().getDirection(movementDirection) != null) {
            player.setCurrentLocation(player.getCurrentLocationObject().getDirection(movementDirection));
            if (calcEncounter() > 0.6) {
                spawnEncounter();
            }
        } else {
            System.out.println("You can't go that way.");
        }

        if (player.getCurrentLocationObject().getLocationName().equals("squareRoom") && movementDirection.equals("north")) {
            System.out.println("Can't go back up the way you came... You'll have to continue on forward.");
        }
    }

    /**
     * Allows the player to attack a certain target.
     *
     * @param target1
     * @param target2
     * @throws NullPointerException
     */
    public void actionableAttack(String target1, String target2) throws NullPointerException {
        ArrayList<Characters> locationCharacters = player.getCurrentLocationObject().getLocationCharacters();
        ArrayList<Items> playerItems = player.getCurrentInventory();
        ArrayList<Items> locationItems = player.getCurrentLocationObject().getLocationItems();
        Characters targetChar = null;
        Items targetObject = null;
        String targetName = null;
        String targetProperName = null;
        Items weaponObject = null;
        String weaponName = null;
        String weaponProperName = null;
        boolean pass = true;

        do {
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

            for (int i = 0; i < locationItems.size(); i++) {
                if (locationItems.get(i).getName().equals(target1) && locationItems.get(i).getIsDestroyable()) {
                    targetObject = locationItems.get(i);
                    targetName = locationItems.get(i).getName();
                    targetProperName = locationItems.get(i).getProperItemName();

                    //todo TESTING
                    //System.out.println(true + " target " + targetName);
                }
                if (locationItems.get(i).getName().equals(target2) && locationItems.get(i).getIsDestroyable()) {
                    targetObject = locationItems.get(i);
                    targetName = locationItems.get(i).getName();
                    targetProperName = locationItems.get(i).getProperItemName();

                    //todo TESTING
                    //System.out.println(true + " target " + targetName);
                }
            }

            if (targetName == null) {
                System.out.println("Invalid target, or not nearby");
                break;
            }

            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(target1) && playerItems.get(i).getIsWeapon()) {
                    weaponName = playerItems.get(i).getName();
                    weaponProperName = playerItems.get(i).getProperItemName();
                    weaponObject = playerItems.get(i);

                    //todo TESTING
                    //System.out.println(true + " weapon " + weaponName);
                }
            }

            for (int i = 0; i < playerItems.size(); i++) {
                if (playerItems.get(i).getName().equals(target2) && playerItems.get(i).getIsWeapon()) {
                    weaponName = playerItems.get(i).getName();
                    weaponProperName = playerItems.get(i).getProperItemName();
                    weaponObject = playerItems.get(i);

                    //todo TESTING
                    //System.out.println(true + " weapon " + weaponName);
                }
            }

            if (weaponObject == null) {
                System.out.println("You don't have a weapon!");
                break;
            }

            if (weaponName == null) {
                System.out.println("You don't have that weapon in your inventory.");
                break;
            }

            if (targetChar != null) {
                System.out.println("You attack " + targetProperName + " with your " + weaponProperName);
                System.out.println(targetChar.getHealth());
                double health = targetChar.getHealth();
                double attackpl = 0;
                int attackwp = weaponObject.getWeaponDamage();

                //Critical hit chance
                if (player.getCurrentHealth() == 100) {
                    attackpl = player.getBaseDamage();
                } else if (player.getCurrentHealth() < 80) {
                    double chance = Math.random();
                    if (chance > 0.80) {
                        attackpl = player.getBaseDamage() * 1.2;
                    }
                } else if (player.getCurrentHealth() < 50) {
                    double chance = Math.random();
                    if (chance > 0.50) {
                        attackpl = player.getBaseDamage() * 1.5;
                    }
                } else if (player.getCurrentHealth() < 30) {
                    double chance = Math.random();
                    if (chance > 0.50) {
                        attackpl = player.getBaseDamage() * 1.9;
                    }
                }

                double attackttl = attackpl + attackwp;

                health = health - attackttl;
                targetChar.setHealth(health);
                System.out.println(targetChar.getHealth());
            }



            if (targetObject != null) {
                System.out.println("You attack the " + targetProperName + " with your " + weaponProperName);
                if (targetObject.getIsDestroyable()) {
                    player.getCurrentLocationObject().removeItem(targetObject);

                    //SquareRoom Event (Lock Destroyed)
                    if (player.getCurrentLocationName().equals("squareRoom") && targetName.equals("squareRoomLock") && weaponObject.getIsWeapon()) {
                        System.out.println("The lock drops to the ground, destroyed. Took a couple hits, but it finally gave way.");
                        game.locationHash.get("squareRoom").addDirection("south", game.locationHash.get("rabbitsHouseLivingRoom"));
                        game.locationHash.get("squareRoom").setLocationState(1);
                        break;
                    }

                    //rabbitsHouseBackroom Event (Haystack Burnt)
                    if (player.getCurrentLocationName().equals("rabbitsHouseBackroom") && targetName.equals("haystack") && weaponName.equals("match")) {
                        Locations loc = game.locationHash.get("rabbitsHouseBackroom");
                        loc.addDirection("down", game.locationHash.get("safeHaven"));
                        player.setCurrentLocation(game.locationHash.get("safeHaven"));
                        System.out.println
                                ("""
                                        As the hay burns, you feel the ground giving out below you. Before you fall, you're able to grab onto\s
                                        a nearby rope tied to the upper rafters. As you descend quickly, burning your hands on the tough texture of the rope,\s
                                        you observe what appears to be a small shrine in a tornado shelter. Someone had inhabited this place at one time. You\s
                                        could feel the desperation in the atmosphere. 'Had Alice lived here?' you thought. On the pedestal of the shrine, are five\s
                                        slots where items could be placed. Perhaps you'd find answers to this later... But this would be a great spot to keep\s
                                        items if you become encumbered.\s
                                        """);
                        break;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }

        } while (pass);

    }

}
