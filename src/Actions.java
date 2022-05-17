import java.util.HashMap;
import java.util.List;

public class Actions extends Player{

    Game game;
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
        }
    }

    public void checkActionable() {
        switch (actionName) {
            case "examine" :

        }
    }

    //Action Functions

    public void displayHelp() {
        System.out.println(game.assetHash.get("actions").get("help"));
        System.out.println(game.assetHash.get("actions").get("take"));
        System.out.println(game.assetHash.get("actions").get("run"));
        System.out.println(game.assetHash.get("actions").get("drop"));
        System.out.println(game.assetHash.get("actions").get("persuade"));
        System.out.println(game.assetHash.get("actions").get("north"));
        System.out.println(game.assetHash.get("actions").get("south"));
        System.out.println(game.assetHash.get("actions").get("east"));
        System.out.println(game.assetHash.get("actions").get("west"));
        System.out.println(game.assetHash.get("actions").get("again"));
        System.out.println(game.assetHash.get("actions").get("attack"));
        System.out.println(game.assetHash.get("actions").get("inventory"));
        System.out.println(game.assetHash.get("actions").get("examine"));
        System.out.println(game.assetHash.get("actions").get("look"));
        System.out.println(game.assetHash.get("actions").get("use"));
        System.out.println(game.assetHash.get("actions").get("give"));
    }
}
