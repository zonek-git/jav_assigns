import java.lang.Math;
import java.util.HashMap;

public class Characters {

    Game game;
    private String name;
    private String desc;

    private int maxCharHealth;
    private int maxCharDamage;
    private int resistance;
    private double charAttack;
    private boolean isAlive;
    private Locations charLocation;

    Characters() {

    }

    /**
     *
     * @param name
     * @param desc
     */
    Characters(Game game, String name, HashMap<String, String> desc) {
        this.name = name;
        this.desc = desc.get(name);
    }

    /**
     * Takes the maxCharDamage and multiplies it randomly by 0.0 - 1.0;
     * @return attack output
     */
    public void charAttackRoll() {
        charAttack = maxCharDamage * Math.random();
    }

    //Getters

    public int getMaxCharHealth() {
        return maxCharHealth;
    }

    public int getMaxCharDamage() {
        return maxCharDamage;
    }

    public int getResistance() {
        return resistance;
    }

    //Setters

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
