import java.util.ArrayList;
import java.util.HashMap;

public class Locations {

    private String name;
    private String locationDescription;
    private String entryDescription;
    private String exitDescription;
    private ArrayList<Characters> locationCharacters = new ArrayList<>();
    private ArrayList<Items> locationItems = new ArrayList<>();
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
        this.name = name;
        this.locationDescription = desc.get(name);
    }

    public void addDirection(String dir, Locations location) {
        directory.put(dir, location);
    }

    // Functions //

    public String getLocationProperName() {
        String properName = null;
        switch(name) {
            case "squareRoom" :
                properName = "Square Room";
                break;
            case "forestZone" :
                properName = "Forest Zone";
                break;
            case "rabbitsHouse" :
                properName = "Rabbit's House";
                break;
            case "courtRoom" :
                properName = "CourtRoom";
                break;
            case "unbirthdayParty" :
                properName = "Un-Birthday Party";
                break;
            case "safeHaven" :
                properName = "Safe Room";
                break;
            case "theVoid" :
                properName = "Empty Void";
                break;
        }
        return properName;
    }


    // Setters //

    public void setLocationCharacters(Characters character) {
        locationCharacters.add(character);
    }

    public void setDirectory(HashMap<String, Locations> directory) {
        this.directory = directory;
    }

    public void addItem(Items item) {
        locationItems.add(item);
    }

    public void removeItem(Items item) {
        locationItems.remove(item);
    }

    public void setLocationInitDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    // Getters //

    public HashMap<String, Locations> getDirection() {
        return directory;
    }

    public ArrayList<Characters> getLocationCharacters() {
        return locationCharacters;
    }

    public ArrayList<Items> getLocationItems() {
        return locationItems;
    }

    public void getLocationItemNames() {
        for(int i = 0 ; i < locationItems.size() ; i++ ) {
            System.out.println(locationItems.get(i).getProperItemName());
        }
    }

    public boolean locationContainsItem(String itemName) {
        String itemTitle;
        boolean contained = false;
        for(int i = 0 ; i < locationItems.size() ; i++) {
            itemTitle = locationItems.get(i).getItemName();
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
