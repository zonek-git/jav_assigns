import java.lang.Math;
import java.util.HashMap;

public class Characters {

    Game game;
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
    Characters(Game game, String name, HashMap<String, String> desc) {
        this.name = name;
        this.desc = desc.get(name);
    }

    /**
     * Takes the maxCharDamage and multiplies it randomly by 0.0 - 1.0;
     * @return attack output
     */
    public double charAttack() {
        return maxCharDamage * Math.random();
    }
}
