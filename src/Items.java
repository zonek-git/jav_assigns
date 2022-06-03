import java.util.ArrayList;
import java.util.HashMap;

public class Items {

    Game game;
    private String name;
    private String itemDescription;
    private String examineDescription;
    private Items containedItem;
    private ArrayList<String> itemProperNames = new ArrayList<>();

    private int healingModifier = 0;
    private int weaponDamage = 0;
    private int health = 0;

    private boolean isUsable = false;
    private boolean isWeapon = false;
    private boolean isDroppable = false;
    private boolean isSafeDrop = false;
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

    public String getName() {
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
            case "squareRoomLock":
                properName = "Padlock";
                break;
            case "squareRoomCabinet":
                properName = "Cabinet";
                break;
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
                properName = "Bottle";
                break;
            case "eatMeBox":
                properName = "Box";
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
                properName = "Cake";
                break;
            case "mallet":
                properName = "Mallet";
                break;
            case "jam":
                properName = "Jam";
                break;
            case "gasMask":
                properName = "Mask";
                break;
            case "umbrella":
                properName = "Umbrella";
                break;
            case "playingCard":
                properName = "Card";
                break;
            case "haystack":
                properName = "Haystack";
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

    public Items getContainedItem() {
        return containedItem;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public boolean getIsSafeDroppable() {
        return isSafeDrop;
    }

    public int getHealth() {
        return health;
    }


    //SETTERS//


    public void removeContainedItem() {
        this.containedItem = null;
    }

    public void setIsWeapon(boolean set) {
        this.isWeapon = set;
    }

    public void setIsDroppable(boolean set) {
        this.isDroppable = set;
    }

    public void setIsSafeDroppable(boolean set) {
        this.isSafeDrop = set;
    }

    public void setIsTakeable(boolean set) {
        this.isTakeable = set;
    }

    public void setIsUsable(boolean set) {
        this.isUsable = set;
    }

    public void setIsDestroyable(boolean set) {
        this.isDestroyable = set;
        this.health = 1;
    }

    public void setIsOpenable(boolean set, Items item) {
        containedItem = item;
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

    public void setWeaponDamage(int damage) {
        this.weaponDamage = damage;
    }

    /**
     * Method to assign attributes to the specific Item
     */
    public void assignAttributes() {

    }

}
