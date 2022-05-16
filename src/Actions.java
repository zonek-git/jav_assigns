import java.util.HashMap;
import java.util.List;

public class Actions extends Player{

    Game game;
    private String actionDescription;
    private String actionName;

    //TODO instead of doing it all this way, I think it would be a good idea to take a parser for TXT file, remember?

    public Actions(Game game, String name, HashMap<String, String> desc) {
        super();
        this.game = game;
        this.actionName = name;
        this.actionDescription = desc.get(name);
        checkActionable();
    }

    public void checkActionable() {
        switch (actionName) {
            case "examine" :

        }
    }
}
