import java.util.ArrayList;
import java.util.HashMap;

public class Locations {

    private String name;
    private String locationDescription;
    private String entryDescription;
    private String exitDescription;
    private ArrayList<Items> containedItems = new ArrayList<>();
    private HashMap<String, Locations> directory;
    private Game game;

    Locations() {

    }

    /**
     * When locations are created, itemsAvailable is created, which passes that to local variable, which passes that to
     * the class file of Items
     * @param name
     * @param locationDescription
     */
    public Locations(Game game, String name, HashMap<String, String> desc) {
        super();
        this.game = game;
        this.locationDescription = desc.get(name);
    }

    //SETTERS

    public void addItem(Items item) {
        containedItems.add(item);
    }

    public void setLocationInitDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    //GETTERS

    public void getLocationItems() {

    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getLocationName(String name) {
        return name;
    }

}
