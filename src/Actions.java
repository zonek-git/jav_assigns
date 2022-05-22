import java.util.HashMap;
import java.util.List;

public class Actions extends Player{

    Game game;
    Player player;
    Items item;
    private String actionDescription;
    private String actionName;

    //TODO instead of doing it all this way, I think it would be a good idea to take a parser for TXT file, remember?

    Actions() {

    }

    public Actions(Player player, Game game, String name, HashMap<String, String> desc) {
        super();
        this.game = game;
        this.player = player;
        this.actionName = name;
        this.actionDescription = desc.get(name);
    }

    public void checkActionName() {
        switch (actionName) {
            case "help" :
                displayHelp();
                break;
            case "look" :
                displayLook();
                break;
            case "inventory" :
                displayInventory();
                break;
        }
    }

    //Action Functions

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
    }

    public void displayInventory() {
        player.viewInventory();
    }

    public void displayLook() {
        player.getCurrentLocationDescription();
        if(player.getCurrentLocationObject().getLocationItems().size() == 1) {
            System.out.println("It appears there is an item in this room as well: ");
            player.getCurrentLocationObject().getLocationItemNames();
        } else if(player.getCurrentLocationObject().getLocationItems().size() > 1) {
            System.out.println("It appears there are some items in this room as well: ");
            player.getCurrentLocationObject().getLocationItemNames();
        }

    }

    public void actionableTake(String itemName, Items itemObject) {
        if (player.getCurrentLocationObject().locationContainsItem(itemName)) {
            System.out.println("yes");
            player.addInventoryItem(itemObject);
            System.out.println(player.getCurrentLocationName());
            game.player.getCurrentLocationObject().removeItem(itemObject);
        } else {
            System.out.println("The item indicated doesn't exist in the area you're currently in. Try 'look' to see items available.");
        }
    }
}
