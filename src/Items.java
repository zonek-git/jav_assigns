import java.util.HashMap;

public class Items {

    Game game;
    private String name;
    private String itemDescription;
    private String examineDescription;

    private int healingModifier = 0;

    private boolean isUsable = false;
    private boolean isWeapon = false;
    private boolean isDroppable = false;
    private boolean isTakeable = false;
    private boolean isOpenable = false;
    private boolean isDestroyable = false;

    Items() {

    }

    Items(Game game, String name, HashMap<String, String> desc) {
        this.game = game;
        this.name = name;
        this.itemDescription = desc.get(name);
    }

    // Functions //


    // Getters //

    public String getItemName() {
        return name;
    }

    public boolean getIsWeapon() {
        return isWeapon;
    }

    public boolean getIsOpenable() {
        return isOpenable;
    }

    public boolean getIsUsable() {
        return isUsable;
    }

    public boolean getIsTakeable() {
        return isTakeable;
    }

    public boolean getIsDroppable() {
        return isDroppable;
    }

    public boolean getIsDestroyable() {
        return isDestroyable;
    }

    public String getProperItemName() {
        String properName = null;
        switch (name) {
            case "baton":
                properName = "Baton";
                break;
            case "rose":
                properName = "Rose";
                break;
            case "watch":
                properName = "Watch";
                break;
            case "drinkMeBottle":
                properName = "Drink Me Bottle";
                break;
            case "eatMeBox":
                properName = "Eat Me Box";
                break;
            case "key":
                properName = "Key";
                break;
            case "oyster":
                properName = "Oyster";
                break;
            case "match":
                properName = "Matchstick";
                break;
            case "hookah":
                properName = "Hookah";
                break;
            case "teapot":
                properName = "Teapot";
                break;
            case "teacup":
                properName = "Teacup";
                break;
            case "unbirthdayCake":
                properName = "Un-Birthday Cake";
                break;
            case "mallet":
                properName = "Mallet";
                break;
            case "jam":
                properName = "Jam Jar";
                break;
            case "gasMask":
                properName = "Gas Mask";
                break;
            case "umbrella":
                properName = "Umbrella";
                break;
            case "playingCard":
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

    public int getHealingModifier() {
        return healingModifier;
    }


    //SETTERS//


    public void setIsWeapon(boolean set) {
        this.isWeapon = set;
    }

    public void setIsDroppable(boolean set) {
        this.isDroppable = set;
    }

    public void setIsTakeable(boolean set) {
        this.isTakeable = set;
    }

    public void setIsUsable(boolean set) {
        this.isUsable = set;
    }

    public void setIsDestroyable(boolean set) {
        this.isDestroyable = set;
    }

    public void setIsOpenable(boolean set) {
        this.isOpenable = set;
    }

    public void setHealingModifier(int effect) {
        this.healingModifier = effect;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Method to assign attributes to the specific Item
     */
    public void assignAttributes() {

    }

}
