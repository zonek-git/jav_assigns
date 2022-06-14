/**
 * title: Player.java
 * description: Player class for Assignment 4
 * date: June 6, 2022
 * @author Joshua P.J. Vilcsak
 * @version 1.0
 * @copyright 2022 Joshua P.J.Vilcsak
 */

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Player {
    //Global variables for player
    //Constant vars
    private double currentHealth = 0;
    private int maxInventory = 5;

    //Combat/Interaction modifiers:
    private boolean isShrunk = false;
    private boolean isAlive = true;
    private int baseDamage = 20;

    //Location holder
    private Locations previousLocation;
    private Locations currentLocation;
    private ArrayList<Items> currentInventory = new ArrayList<>();
    private int numberOfItems;

    public Player() {

    }

    public Player(int numberOfItems) {
        super();
        this.numberOfItems = numberOfItems;
    }

    /**
     * View the current inventory of the Player.
     */
    public void viewInventory() {
        System.out.println("Your current inventory: ");
        System.out.println();
        if (currentInventory.size() == 0) {
            System.out.println("You currently have nothing in your inventory");
        } else {
            for (int i = 0; i < currentInventory.size(); i++) {
                System.out.println("#" + (i + 1) + " " + currentInventory.get(i).getProperItemName());
            }
        }
        System.out.println();
    }

    // Functions //

    /**
     * Add Items object into inventory
     * @param itemObject Items item specified
     */
    public void addInventoryItem(Items itemObject) {
        currentInventory.add(itemObject);
        numberOfItems++;
    }

    /**
     * Remove Items objet from inventory
     * @param itemObject Items item specified
     */
    public void removeInventoryItem(Items itemObject) {
        currentInventory.remove(itemObject);
        numberOfItems--;
    }

    // Getters //

    /**
     * Is the player shrunk to fit through to theVoid?
     * @return boolean
     */
    public boolean getIsShrunk() {
        return isShrunk;
    }

    /**
     * Get the immediately previous location of the player
     * @return Locations previousLocation
     */
    public Locations getPreviousLocation() {
        return previousLocation;
    }

    /**
     * Get the base damage output of the player
     * @return int baseDamage
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    /**
     * Get the max inventory capacity of the player
     * @return int maxInventory
     */
    public int getMaxInventory() {
        return maxInventory;
    }

    /**
     * Get the number of items in inventory
     * @return int numberOfItems
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Get the name of the current Locations object
     * @return String currentLocationName
     */
    public String getCurrentLocationName() {
        return currentLocation.getLocationName();
    }

    /**
     * Returns the actual object of the current location
     * @return Locations currentLocation
     */
    public Locations getCurrentLocationObject() {
        return currentLocation;
    }

    /**
     * Returns the ArrayList of items currently in the inventory of the player
     * @return ArrayList currentInventory
     */
    public ArrayList<Items> getCurrentInventory() {
        return currentInventory;
    }

    /**
     * Return if the player is alive or not
     * @return boolean isAlive
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Returns the player's current health stats
     * @return double currentHealth
     */
    public double getCurrentHealth() {
        return currentHealth;
    }

    // Setters //

    /**
     * Sets whether the player is shrunk or not
     * @param set boolean
     */
    public void setIsShrunk(boolean set) {
        this.isShrunk = set;
    }

    /**
     * Sets the previous location of the player, before moving to the next location object
     * @param previousLocation Locations location object
     */
    public void setPreviousLocation(Locations previousLocation) {
        this.previousLocation = previousLocation;
    }

    /**
     * Sets if the player is alive or not
     * @param set boolean
     */
    public void setIsAlive(boolean set) {
        this.isAlive = set;
    }

    /**
     * Sets the object of the current location
     * @param currentLocation Locations location object
     */
    public void setCurrentLocation(Locations currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * Sets the starting health of the player
     * @param health double health value
     */
    public void setStartingPlayerHealth(double health) {
        this.currentHealth = health;
    }

    /**
     * Adds to the current health of the
     * @param effect
     * @return
     */
    public void setAddCurrentHealth(int effect) {
        //Maximum, constants. Able to go up and down
        int maxHealth = 100;
        if (currentHealth == maxHealth) {
            System.out.println("You're already at full health. No need to use this!");
        } else if (currentHealth <= maxHealth) {
            currentHealth = Math.min(currentHealth + effect, maxHealth);
        }
    }

    public void setSubtractCurrentHealth(double effect) {
        currentHealth = currentHealth - effect;
        if (currentHealth <= 0) {
            isAlive = false;
        }
    }

    public void displayHealth() {
        DecimalFormat formatHealth = new DecimalFormat("#.0");
        System.out.println("** Current Health: " + formatHealth.format(currentHealth));
    }


}
