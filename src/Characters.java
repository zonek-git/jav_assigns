import java.lang.Math;
import java.util.HashMap;

public class Characters {

    //Global variables for Characters
    private String name;
    private String desc;

    private double currentHealth;
    private double maxCharHealth;
    private double maxCharDamage;

    Characters() {

    }

    /**
     * Primary constructor for building Characters items
     * @param name String input for name
     * @param desc HashMap input for description
     */
    Characters(String name, HashMap<String, String> desc) {
        this.name = name;
        this.desc = desc.get(name);
    }

    /**
     * Takes the maxCharDamage and multiplies it randomly by 0.0 - 1.0;
     *
     * @return attack output
     */
    public double charAttackRoll() {
        double charAttack = maxCharDamage * Math.random();
        return charAttack;
    }

    //Getters

    /**
     * Retrieves the character description
     * @return String desc
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Retrieves the character name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the "displayable" character name
     * @return String properName
     */
    public String getProperCharacterName() {
        String properName = null;
        switch (name) {
            case "madHatter" :
                properName = "Mad Hatter";
                break;
            case "redQueen" :
                properName = "Queen of Hearts";
                break;
            case "alice" :
                properName = "Alice";
                break;
            case "whiteRabbit" :
                properName = "White Rabbit";
                break;
            case "soldier" :
                properName = "Card Soldier";
                break;
            case "caterpillar" :
                properName = "Caterpillar";
                break;
        }
        return properName;
    }

    /**
     * retrieves the maximum health of a character
     * @return double maxCharHealth
     */
    public double getMaxHealth() {
        return maxCharHealth;
    }

    /**
     * Retrieves the health of the character
     * @return double currentHealth
     */
    public double getHealth() {
        return currentHealth;
    }

    //Setters

    public void setIsAlive(boolean set) {
    }
    /**
     * Set the health of the character
     * @param healthSet double value
     */
    public void setHealth(double healthSet) {
        this.currentHealth = healthSet;
    }

    /**
     * Set the maximum health of the character
     * @param maxCharHealth double value
     */
    public void setMaxHealth(double maxCharHealth) {
        this.maxCharHealth = maxCharHealth;
    }

    /**
     * Set the maximum damage output of a character
     * @param maxCharDamage double value
     */
    public void setMaxDamage(double maxCharDamage) {
        this.maxCharDamage = maxCharDamage;
    }
}
