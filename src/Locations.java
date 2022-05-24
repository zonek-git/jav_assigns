import java.util.ArrayList;
import java.util.HashMap;

public class Locations {

    private String name;
    private String locationDescription;
    private String entryDescription;
    private String exitDescription;
    private ArrayList<Rooms> containedRooms = new ArrayList<>();
    private Rooms initRoom;
    private ArrayList<Characters> containedCharacters = new ArrayList<>();
    private ArrayList<Items> containedItems = new ArrayList<>();
    private HashMap<String, Locations> directions = new HashMap<>();
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
        this.directions.put(dir, location);
    }


    // Functions //

    public String getLocationProperName() {
        String properName = null;
        switch(name) {
            case "squareRoom" :
                properName = "Square Room";
                break;
            case "squareRoomMain" :
                properName = "Square Room main area";
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

    public void setInitialRoom(Rooms roomName) {
        Rooms initRoom = null;
        for(int i = 0 ; i < containedRooms.size() ; i++) {
            if(containedRooms.get(i).getLocationName().equals(roomName.getLocationName())){
                initRoom = containedRooms.get(i);
            }else {
                System.out.println("Error_setInitialRoom_Locations.java: this location doesn't contain this room");
            }
        }
        this.initRoom = initRoom;
    }

    public void setContainedRooms(Rooms room) {
        containedRooms.add(room);
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

    public void setLocationInitDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public void setLocationName(String locationName) {
        this.name = locationName;
    }

    // Getters //

    public ArrayList<Rooms> getContainedRooms() {
        return containedRooms;
    }

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
