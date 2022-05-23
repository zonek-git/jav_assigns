import java.util.HashMap;

public class Items {

    Game game;
    private String name;
    private String itemDescription;
    private String examineDescription;
    private boolean isUsable;
    private boolean isWeapon;
    private boolean isDroppable;
    private boolean isTakeable;
    private boolean isInArea;

    Items() {

    }

    Items(Game game, String name, HashMap<String, String> desc) {
        this.game = game;
        this.name = name;
        this.itemDescription = desc.get(name);
    }

    //GETTERS//

    public String getItemName() {
        return name;
    }

    public String getProperItemName() {
        String properName = null;
        switch(name) {
            case "baton" :
                properName = "Baton";
                break;
            case "rose" :
                properName = "Rose";
                break;
            case "watch" :
                properName = "Watch";
                break;
            case "drinkMeBottle" :
                properName = "Drink Me Bottle";
                break;
            case "eatMeBox" :
                properName = "Eat Me Box";
                break;
            case "key" :
                properName = "Key";
                break;
            case "oyster" :
                properName = "Oyster";
                break;
            case "match" :
                properName = "Matchstick";
                break;
            case "hookah" :
                properName = "Hookah";
                break;
            case "teapot" :
                properName = "Teapot";
                break;
            case "teacup" :
                properName = "Teacup";
                break;
            case "unbirthdayCake" :
                properName = "Un-Birthday Cake";
                break;
            case "mallet" :
                properName = "Mallet";
                break;
            case "jam" :
                properName = "Jam Jar";
                break;
            case "gasMask" :
                properName = "Gas Mask";
                break;
            case "umbrella" :
                properName = "Umbrella";
                break;
            case "playingCard" :
                properName = "Playing Card";
                break;
        }
        return properName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getExamineDescription() {
        return examineDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    //SETTERS//

    public void setName(String name) {
        this.name = name;
    }

    public void setExamineDescription(String examineDescription) {
        this.examineDescription = examineDescription;
    }

    /**
     * Method to assign attributes to the specific Item
     */
    public void assignAttributes() {

    }

}
