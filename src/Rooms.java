import java.util.ArrayList;
import java.util.HashMap;

public class Rooms extends Locations{

    private String name;
    private String roomDescription;
    private ArrayList<Items> containedItems = new ArrayList<>();

    public Rooms() {

    }

    public Rooms(String name, HashMap<String, String> desc) {
        this.name = name;
        this.roomDescription = desc.get(name);
    }


    // Setters //



    // Getters //


}
