import java.util.ArrayList;
import java.util.HashMap;

public class Locations {

    private String name;
    protected String locationDescription;
    private String entryDescription;
    private String exitDescription;
    private ArrayList<Characters> containedCharacters = new ArrayList<>();
    private ArrayList<Items> containedItems = new ArrayList<>();
    private HashMap<String, Locations> directions = new HashMap<>();
    protected Game game;

    Locations() {

    }

    /**
     * When locations are created, itemsAvailable is created, which passes that to local variable, which passes that to
     * the class file of Items
     * @param name
     * @param
     */
    public Locations(Game game, String name, HashMap<String, String> desc) {
        this.game = game;
        this.name = name;
        this.locationDescription = desc.get(name);
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

    public void addDirection(String dir, Locations location) {
        this.directions.put(dir, location);
    }


    public void setLocationCharacters(Characters character) {
        containedCharacters.add(character);
    }

    public void addItem(Items item) {
        containedItems.add(item);
    }

    public void removeItem(Items item) {
        containedItems.remove(item);
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;

    }

    public void setLocationName(String locationName) {
        this.name = locationName;
    }

    // Getters //


    public Locations getDirection(String dir) {
        return directions.get(dir);
    }

    public ArrayList<Characters> getLocationCharacters() {
        return containedCharacters;
    }

    public ArrayList<Items> getLocationItems() {
        return containedItems;
    }

    public void getLocationItemNames() {
        for(int i = 0 ; i < containedItems.size() ; i++ ) {
            System.out.println(containedItems.get(i).getProperItemName());
        }
    }

    public boolean locationContainsItem(String itemName) {
        String itemTitle;
        boolean contained = false;
        for(int i = 0 ; i < containedItems.size() ; i++) {
            itemTitle = containedItems.get(i).getName();
            if(itemTitle.equals(itemName)){
                contained = true;
            }
        }
        return contained;
    }

    public String getLocationDescription() {
        return this.locationDescription;
    }

    public String getLocationName() {
        return name;
    }

}
