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

    public String getDescription() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public String getProperCharacterName() {
        String properName = null;
        switch (name) {
            case "madHatter" :
                properName = "Hatter";
                break;
            case "redQueen" :
                properName = "Queen";
                break;
            case "alice" :
                properName = "Alice";
                break;
            case "whiteRabbit" :
                properName = "Rabbit";
                break;
            case "soldier" :
                properName = "Soldier";
                break;
            case "caterpillar" :
                properName = "Caterpillar";
                break;
        }
        return properName;
    }

    public int getMaxHealth() {
        return maxCharHealth;
    }

    public int getMaxDamage() {
        return maxCharDamage;
    }

    public int getResistance() {
        return resistance;
    }

    public int getHealth() {
        return currentHealth;
    }


    //Setters

    public void setHealth(int healthSet) {
        this.currentHealth = healthSet;
    }

    public void setMaxHealth(int maxCharHealth) {
        this.maxCharHealth = maxCharHealth;
    }

    public void setMaxDamage(int maxCharDamage) {
        this.maxCharDamage = maxCharDamage;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public void setLocation(Locations charLocation) {
        this.charLocation = charLocation;
    }
}
