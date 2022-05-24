import java.util.ArrayList;
import java.util.HashMap;

public class Rooms extends Locations{

    private String name;
    private String roomDescription;

    public Rooms() {

    }

    public Rooms(String name, HashMap<String, String> desc) {
        setLocationName(name);
        setLocationInitDescription(getLocationDescription() + desc.get(name));
    }


    // Setters //


    // Getters //


}
