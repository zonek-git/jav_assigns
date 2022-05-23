import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Actions extends Player {

    Game game;
    Player player;
    Items item;
    private String actionDescription;
    private String actionName;

    //TODO instead of doing it all this way, I think it would be a good idea to take a parser for TXT file, remember?

    /**
     * Default constructor
     */
    Actions() {

    }

    /**
     * Primary constructor for actions.
     *
     * @param player dependency injection for the player object inside of Game
     * @param game   dependency injection for the main Game class object newGame
     * @param name   pushing in the name of the particular action
     * @param desc   pushing in the description of the particular action using itemHash in Game.loadAssets()
     */
    public Actions(Player player, Game game, String name, HashMap<String, String> desc) {
        super();
        this.game = game;
        this.player = player;
        this.actionName = name;
        this.actionDescription = desc.get(name);
    }

    /**
     * Used to take the command that is passed in through Control and sends it to do the proper Action sequence
     */
    public void checkActionName() {
        switch (actionName) {
            case "help":
                displayHelp();
                break;
            case "look":
                displayLook();
                break;
            case "inventory":
                displayInventory();
                break;
        }
    }

    public void checkActionName(String object) {
        switch (actionName) {
            case "take":
                actionableTake(object);
                break;
            case "drop":
                actionableDrop(object);
                break;
            case "examine":
                actionableExamine(object);
                break;
            case "use":
                actionableUse(object);
                break;
        }
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
        System.out.println();
    }

    /**
     * Displays the current player's inventory.
     */
    public void displayInventory() {
        player.viewInventory();
    }

    /**
     * Displays the current location description, and if there are any items, it displays the items in the locations
     * as well.
     */
    public void displayLook() {
        player.getCurrentLocationDescription();
        if (player.getCurrentLocationObject().getLocationItems().size() == 1) {
            System.out.println();
            System.out.println("It appears there is an item in this room as well: ");
            player.getCurrentLocationObject().getLocationItemNames();
            System.out.println();
        } else if (player.getCurrentLocationObject().getLocationItems().size() > 1) {
            System.out.println();
            System.out.println("It appears there are some items in this room as well: ");
            player.getCurrentLocationObject().getLocationItemNames();
            System.out.println();
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
                if (locationItems.get(i).getItemName().equals(itemName)) {
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
                System.out.println("The " + itemName + " item indicated doesn't exist in the area you're currently in. Try 'look' to see items available.");
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
                if (playerItems.get(i).getItemName().equals(itemName)) {
                    properName = playerItems.get(i).getProperItemName();
                    player.getCurrentLocationObject().addItem(playerItems.get(i));
                    player.removeInventoryItem(playerItems.get(i));
                    System.out.println(properName + " has been dropped in the " + player.getCurrentLocationObject().getLocationProperName());
                    System.out.println();
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
                if (totalItems.get(i).getItemName().equals(itemName)) {
                    properName = totalItems.get(i).getProperItemName();
                    System.out.println(properName + ": " + game.itemHash.get(itemName));
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
                if (playerItems.get(i).getItemName().equals(itemName)) {
                    String nameObject = playerItems.get(i).getItemName();
                    Items itemObject = playerItems.get(i);
                    if (nameObject.equals("rose") || nameObject.equals("jam") || nameObject.equals("oyster")) {
                        player.setAddCurrentHealth((itemObject.getHealingModifier()));
                    } else if (nameObject.equals("gasMask")) {
                        player.setWearingGasMask(true);
                    } else if (nameObject.equals("key")) {
                        //todo
                    } else if (nameObject.equals("umbrella")) {
                        //todo
                    }
                }
            }
        }
        if (properName == null) {
            System.out.println("The " + itemName + " item indicated doesn't exist in your inventory. Try something else.");
        }
    }

    public void actionableOpen(String itemName) throws NullPointerException {
        ArrayList<Items> locationItems = getCurrentLocationObject().getLocationItems();
        String properName = null;

    }

}
