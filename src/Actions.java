import java.util.HashMap;
import java.util.List;

public class Actions extends Player{


    private String actionDescription;
    private String actionName;

    //TODO instead of doing it all this way, I think it would be a good idea to take a parser for TXT file, remember?

    public Actions(Game game, String name, String desc) {
        super();
        this.actionName = name;
        this.actionDescription = desc;
        checkActionable();
    }

    public void checkActionable() {
        switch (actionName) {
            case "examine" :

        }
    }
}
