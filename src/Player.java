import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    //Constant vars
    private String name;
    private int modifierSelector = 1; //1 = charisma, 2 = fortitude, 3 = combat

    //Maximum, constants. Able to go up and down
    private int maxHealth = 100;
    private int currentHealth = 100;
    private boolean isAlive;

    //Combat/Interaction modifiers:
    private int baseCharisma = 50;
    private int baseFortitude = 50;
    private int baseCombatAbility = 50;

    //Location holder
    Game game;
    private Locations currentLocation;
    private Locations moveFromTo;
    private ArrayList<Items> currentInventory = new ArrayList<>();
    private int numberOfItems;

    public Player() {

    }

    public Player(Game game, Locations currentLocation, int numberOfItems){
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
        if(currentInventory.size() == 0) {
            System.out.println("You currently have nothing in your inventory");
        } else {
            for(int i = 0 ; i < currentInventory.size() ; i++) {
                System.out.println("#" + i + " " + currentInventory.get(i));
            }
        }
        System.out.println();
    }

    // Getters and Setters //

    public String getName() {
        return name;
    }

    public void addInventoryItem(Items item) {
        currentInventory.add(item);
        numberOfItems++;
    }

    public void checkInventoryExist(String name) {
        if (currentInventory.contains(name)) {

        }
    }

    public void removeInventoryItem(Items item) {
        if (currentInventory.isEmpty()) {
            System.out.println("There aren't any items in your inventory!");
        } else if (currentInventory.contains(item)) {
            currentInventory.remove(item);
            System.out.println("The item indicated has been removed and dropped.");
            currentLocation.addItem(item);
            numberOfItems--;
        }
    }

    public String getCurrentLocationDescription() {
        return game.assetHash.get("locations").get(currentLocation);
    }

    public void setCurrentLocation(Locations currentLocation) {
        this.currentLocation = currentLocation;
    }

    /**
     * //TODO restrict the ability of changing the health beyond 100 max level
     * @param effect
     */
    public void setCurrentHealth(int effect) {
        this.currentHealth = this.currentHealth + effect;
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

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void displayHealth() {
        System.out.println("** Current Health: " + currentHealth);
    }



}
