import java.lang.Math;
import java.util.HashMap;

public class Characters {

    private String name;
    private String desc;

    private int maxCharHealth;
    private int maxCharDamage;
    private int resistance;
    private boolean isAlive;

    Characters() {

    }

    /**
     *
     * @param name
     * @param desc
     */
    Characters(String name, HashMap<String, String> desc, int maxCharHealth, int maxCharDamage, int resistance) {
        this.name = name;
        this.desc = desc.get(name);
        this.maxCharHealth = maxCharHealth;
        this.maxCharDamage = maxCharDamage;
        this.resistance = resistance;
    }

    /**
     * Takes the maxCharDamage and multiplies it randomly by 0.0 - 1.0;
     * @return attack output
     */
    public double charAttack() {
        return maxCharDamage * Math.random();
    }
}
