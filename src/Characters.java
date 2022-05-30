import java.lang.Math;
import java.util.HashMap;

public class Characters {

    Game game;
    private String name;
    private String desc;

    private int currentHealth;
    private int maxCharHealth;
    private int maxCharDamage;
    private int resistance;
    private double charAttack;
    private boolean isAlive;
    private Locations charLocation;

    Characters() {

    }

    /**
     * @param name
     * @param desc
     */
    Characters(Game game, String name, HashMap<String, String> desc) {
        this.name = name;
        this.desc = desc.get(name);
    }

    /**
     * Takes the maxCharDamage and multiplies it randomly by 0.0 - 1.0;
     *
     * @return attack output
     */
    public void charAttackRoll() {
        charAttack = maxCharDamage * Math.random();
    }

    //Getters

    public String getCharacterDescription() {
        return desc;
    }

    public String getCharacterName() {
        return name;
    }

    public int getMaxCharacterHealth() {
        return maxCharHealth;
    }

    public int getMaxCharacterDamage() {
        return maxCharDamage;
    }

    public int getCharacterResistance() {
        return resistance;
    }

    public int getCharacterCurrentHealth() {
        return currentHealth;
    }

    public String getProperCharacterName() {
        String properName = null;
        switch (name) {
            case "madHatter" :
                properName = "The Mad Hatter";
                break;
            case "redQueen" :
                properName = "The Queen of Hearts";
                break;
            case "alice" :
                properName = "Alice";
                break;
            case "whiteRabbit" :
                properName = "The White Rabbit";
                break;
            case "soldier" :
                properName = "The Card Soldier";
                break;
            case "caterpillar" :
                properName = "The Caterpillar";
                break;
        }
        return properName;
    }

    //Setters

    public void setCurrentHealth(int healthSet) {
        this.currentHealth = healthSet;
    }

    public void setmaxCharHealth(int maxCharHealth) {
        this.maxCharHealth = maxCharHealth;
    }

    public void setMaxCharDamage(int maxCharDamage) {
        this.maxCharDamage = maxCharDamage;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setCharLocation(Locations charLocation) {
        this.charLocation = charLocation;
    }
}
