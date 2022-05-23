import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    Game game;

    //Constant vars
    private String name;
    private int modifierSelector = 1; //1 = charisma, 2 = fortitude, 3 = combat

    //Maximum, constants. Able to go up and down
    private int maxHealth = 100;
    private int currentHealth = 100;
    private boolean isAlive;
    private int maxInventory = 5;

    //Combat/Interaction modifiers:
    private int baseCharisma = 50;
    private int baseFortitude = 50;
    private int baseCombatAbility = 50;

    //Location holder
    private Locations currentLocation;
    private Locations moveFromTo;
    private ArrayList<Items> currentInventory = new ArrayList<>();
    private int numberOfItems;

    //Special modifiers
    private boolean wearingGasMask = false;

    public Player() {

    }

    public Player(Game game, Locations currentLocation, int numberOfItems) {
        super();
        this.game = game;
        this.currentLocation = currentLocation;
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
                System.out.println("#" + i + " " + currentInventory.get(i).getItemName());
            }
        }
        System.out.println();
    }

    // Functions //

    public void addInventoryItem(Items itemObject) {
        currentInventory.add(itemObject);
        numberOfItems++;
    }

    public void removeInventoryItem(Items item) {
        currentInventory.remove(item);
        numberOfItems--;
    }


    // Getters //


    public String getName() {
        return name;
    }

    public int getMaxInventory() {
        return maxInventory;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public String getCurrentLocationName() {
        return currentLocation.getLocationName();
    }

    public Locations getCurrentLocationObject() {
        return currentLocation;
    }

    public ArrayList<Items> getCurrentInventory() {
        return currentInventory;
    }

    public void getCurrentLocationDescription() {
        System.out.println(currentLocation.getLocationDescription());
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public boolean getWearingGasMask() {
        return wearingGasMask;
    }


    // Setters //


    public void setMaxInventory(int inValue) {
        this.maxInventory = inValue;
    }

    public void setWearingGasMask(boolean wearing) {
        this.wearingGasMask = wearing;
    }

    public void setCurrentLocation(Locations currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setStartingPlayerHealth(int health) {
        this.currentHealth = health;
    }

    public boolean setAddCurrentHealth(int effect) {
        boolean used = false;
        if (currentHealth == maxHealth) {
            System.out.println("You're already at full health. No need to use this!");
            used = false;
        } else if (currentHealth < maxHealth) {
            currentHealth = Math.min(currentHealth + effect, maxHealth);
            used = true;
        }
        return used;
    }

    public void setSubtractCurrentHealth(int effect) {
        currentHealth = currentHealth - effect;
        if (currentHealth <= 0) {
            isAlive = false;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCharismaModifier(boolean modifier) {
        if (modifier) {
            baseCharisma = 100;
        }
    }

    public void setFortitudeModifier(boolean modifier) {
        if (modifier) {
            baseFortitude = 100;
        }
    }

    public void setCombatAbilityModifier(boolean modifier) {
        if (modifier) {
            baseCombatAbility = 100;
        }
    }

    public void displayHealth() {
        System.out.println("** Current Health: " + currentHealth);
    }


}
