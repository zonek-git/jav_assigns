/**
 * title: Locations.java
 * description: Locations class for Assignment 4
 * date: June 6, 2022
 * @author Joshua P.J. Vilcsak
 * @version 1.0
 * @copyright 2022 Joshua P.J.Vilcsak
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Locations {

    private String name;
    protected String locationDescription;
    private String staleDescription;
    private ArrayList<Characters> containedCharacters = new ArrayList<>();
    private ArrayList<Items> containedItems = new ArrayList<>();
    private HashMap<String, Locations> directions = new HashMap<>();
    protected Game game;

    private int locationState = 0;

    public Locations() {

    }

    /**
     * When locations are created, itemsAvailable is created, which passes that to local variable, which passes that to
     * the class file of Items
     * @param name
     * @param
     */
    public Locations(String name, HashMap<String, String> desc) {
        this.name = name;
        this.locationDescription = desc.get(name);
    }

    // Functions //

    /**
     * Retrieves the proper "displayable" location name
     * @return String properName
     */
    public String getLocationProperName() {
        String properName = null;
        switch(name) {
            case "mushForest":
                properName = "Mushroom Forest";
                break;
            case "squareRoom" :
                properName = "Square Room";
                break;
            case "forestZone" :
                properName = "Forest Zone";
                break;
            case "rabbitsHouseLivingRoom" :
                properName = "Rabbit's Living Room";
                break;
            case "rabbitsHouseHallway":
                properName = "Rabbit's Hallway";
                break;
            case "rabbitsHouseBackroom":
                properName = "Rabbit's Backroom";
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

    /**
     * Add a direction available to the indicated location. Adds it to a HashMap
     * @param dir String direction
     * @param location Locations location object
     */
    public void addDirection(String dir, Locations location) {
        this.directions.put(dir, location);
    }

    /**
     * Removes direction
     * @param dir String direction
     * @param location Locations location object
     */
    public void removeDirection(String dir, Locations location) {
        this.directions.remove(dir, location);
    }

    /**
     * Set the characters that are contained in a location
     * @param character Characters character object
     */
    public void setLocationCharacters(Characters character) {
        containedCharacters.add(character);
    }

    /**
     * Add Items object into location
     * @param item Items item object
     */
    public void addItem(Items item) {
        containedItems.add(item);
    }

    /**
     * Remove Items object from location
     * @param item Items item object
     */
    public void removeItem(Items item) {
        containedItems.remove(item);
    }

    /**
     * Sets the state of the location so that the game knows whether the player has visited this location before
     * @param set int 1 or 0
     */
    public void setLocationState(int set) {
        this.locationState = set;
    }

    /**
     * Sets the description of the location if it has already been visited
     * @param stale HashMap String1 = key, String2 = description
     */
    public void setStaleStateDesc(HashMap<String,String> stale) {
        this.staleDescription = stale.get(name);
    }

    // Getters //

    /**
     * Get the direction
     * @param dir String direction which correlates to a "key" in the hashmap
     * @return return the direction Location value that corresponds to the key inputted
     */
    public Locations getDirection(String dir) {
        return directions.get(dir);
    }

    /**
     * Gets the ArrayList of Characters objects contained in location
     * @return ArrayList containedCharacters
     */
    public ArrayList<Characters> getLocationCharacters() {
        return containedCharacters;
    }

    /**
     * Gets the ArrayList of Items objects contained in location
     * @return ArrayList containedItems
     */
    public ArrayList<Items> getLocationItems() {
        return containedItems;
    }

    /**
     * Prints out the names of items in the location
     */
    public void getLocationItemNames() {
        for(int i = 0 ; i < containedItems.size() ; i++ ) {
            System.out.println(containedItems.get(i).getProperItemName());
        }
    }

    /**
     * Checks whether Items name is contained in location
     * @param itemName String itemName
     * @return boolean true if contained
     */
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

    /**
     * Retrieves the location's description
     * @return String description
     */
    public String getLocationDescription() {
        String locDesc = null;
        if (locationState == 0) {
            locDesc = locationDescription;
        } else if (locationState == 1) {
            locDesc = staleDescription;
        }
        return locDesc;
    }

    /**
     * Retrieves the name of the Locations object
     * @return String name
     */
    public String getLocationName() {
        return name;
    }
}
