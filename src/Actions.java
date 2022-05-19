import java.util.HashMap;
import java.util.List;

public class Actions extends Player{

    Game game;
    Items item;
    private String actionDescription;
    private String actionName;

    //TODO instead of doing it all this way, I think it would be a good idea to take a parser for TXT file, remember?

    Actions() {

    }

    public Actions(Game game, String name, HashMap<String, String> desc) {
        super();
        this.game = game;
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

    public void checkActionable() {
        switch (actionName) {
            case "examine" :

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
        game.player.viewInventory();
    }

    public void displayLook() {
        game.player.getCurrentLocationDescription();
        game.player.getCurrentLocationObject().getLocationItemNames();
    }

    public void actionableTake(String itemName) {
        item = new Items(this.game, itemName, game.itemHash);
        System.out.println(item.getItemDescription());
        System.out.println(game.player.getCurrentLocationName());
        if (game.player.getCurrentLocationObject().getLocationItems().contains(item)) {
            game.player.addInventoryItem(item);
            game.player.getCurrentLocationObject().removeItem(item);
        } else {
            System.out.println("The item indicated doesn't exist in the area you're currently in. Try 'look' to see items available.");
        }
    }
}
