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
     * @param
     */
    public Locations(Game game, String name, HashMap<String, String> desc) {
        super();
        this.game = game;
        this.locationDescription = desc.get(name);
    }

    public void addDirection(String dir, Locations location) {
        directory.put(dir, location);
    }


    //SETTERS
    public void setDirectory(HashMap<String, Locations> directory) {
        this.directory = directory;
    }

    public void addItem(Items item) {
        containedItems.add(item);
    }

    public void removeItem(Items item) {
        containedItems.remove(item);
    }

    public void setLocationInitDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    //GETTERS

    public HashMap<String, Locations> getDirection() {
        return directory;
    }

    public ArrayList<Items> getLocationItems() {
        return containedItems;
    }

    public void getLocationItemNames() {
        for(int i = 0 ; i < containedItems.size() ; i++ ) {
            System.out.println(containedItems.get(i).getItemName());
        }
    }

    public boolean locationContainsItem(String itemName) {
        String itemTitle;
        boolean contained = false;
        for(int i = 0 ; i < containedItems.size() ; i++) {
            itemTitle = containedItems.get(i).getItemName();
            if(itemTitle.equals(itemName)){
                contained = true;
            }
        }
        return contained;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getLocationName() {
        return name;
    }

}
