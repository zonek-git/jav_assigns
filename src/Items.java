import java.util.ArrayList;
import java.util.HashMap;

public class Items {

    //Global Items class variables
    private String name;
    private String itemDescription;
    private Items containedItem;

    private int healingModifier = 0;
    private int weaponDamage = 0;

    private boolean isUsable = false;
    private boolean isWeapon = false;
    private boolean isDroppable = false;
    private boolean isSafeDrop = false;
    private boolean isTakeable = false;
    private boolean isOpenable = false;
    private boolean isDestroyable = false;
    private boolean isBurn = false;

    /**
     * Default constructor
     */
    Items() {

    }

    /**
     * Primary constructor for Items object
     * @param name the name of the Items object
     * @param desc the description of the Items object
     */
    Items(String name, HashMap<String, String> desc) {
        this.name = name;
        this.itemDescription = desc.get(name);
    }

    // Getters //

    /**
     * Retrieval of object name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieval of weapon boolean
     * @return boolean isWeapon
     */
    public boolean getIsWeapon() {
        return isWeapon;
    }

    /**
     * Retrieval of openable boolean
     * @return boolean isOpenable
     */
    public boolean getIsOpenable() {
        return isOpenable;
    }

    /**
     * Retrieval of usable boolean
     * @return boolean isUsable
     */
    public boolean getIsUsable() {
        return isUsable;
    }

    /**
     * Retrieval of takeable boolean
     * @return boolean isTakeable
     */
    public boolean getIsTakeable() {
        return isTakeable;
    }

    /**
     * Retrieval of droppable boolean
     * @return boolean isDroppable
     */
    public boolean getIsDroppable() {
        return isDroppable;
    }

    /**
     * Retrieval of destroyable boolean
     * @return boolean isDestroyable
     */
    public boolean getIsDestroyable() {
        return isDestroyable;
    }

    /**
     * Retrieval of Proper Item Name
     * @return String properItemName
     */
    public String getProperItemName() {
        String properName = null;
        switch (name) {
            case "rabbitsHouseBackdoor":
                properName = "Back Door";
                break;
            case "sword":
                properName = "Sword";
                break;
            case "forestZoneChest":
                properName = "Chest";
                break;
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

    /**
     * Retrieval of item description
     * @return String itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Retrieval of Healing factor
     * @return int healingModifier
     */
    public int getHealingModifier() {
        return healingModifier;
    }

    /**
     * Retrieval of contained items list, if an Item can contain another Item
     * @return Items containedItems
     */
    public Items getContainedItem() {
        return containedItem;
    }

    /**
     * If the object is a weapon, retrieve weapon damage modifier
     * @return int weaponDamage
     */
    public int getWeaponDamage() {
        return weaponDamage;
    }

    /**
     * If the object is safe droppable, which means that it can only be dropped into the safeHave Locations object
     * @return boolean isSafeDroppable
     */
    public boolean getIsSafeDroppable() {
        return isSafeDrop;
    }

    /**
     * Retrieval of boolean of can item burn other items, if possible
     * @return boolean isBurn
     */
    public boolean getIsBurn() {
        return isBurn;
    }


    //SETTERS//


    /**
     * Remove the contained item inside another item, if possible
     */
    public void removeContainedItem() {
        this.containedItem = null;
    }

    /**
     * Set if item is weapon
     * @param set boolean
     */
    public void setIsWeapon(boolean set) {
        this.isWeapon = set;
    }

    /**
     * Set if item is droppable anywhere
     * @param set boolean
     */
    public void setIsDroppable(boolean set) {
        this.isDroppable = set;
    }

    /**
     * Set if item has to be dropped inside safeHaven location object
     * @param set boolean
     */
    public void setIsSafeDroppable(boolean set) {
        this.isSafeDrop = set;
    }

    /**
     * Set if item can be taken into inventory
     * @param set boolean
     */
    public void setIsTakeable(boolean set) {
        this.isTakeable = set;
    }

    /**
     * Set if item can be used
     * @param set boolean
     */
    public void setIsUsable(boolean set) {
        this.isUsable = set;
    }

    /**
     * Set if item can be destroyed (removed from location object)
     * @param set boolean
     */
    public void setIsDestroyable(boolean set) {
        this.isDestroyable = set;
    }

    /**
     * Set if the item is openable to the player (if player can retrieve item inside object)
     * @param set boolean
     * @param item Item, what item does this item contain?
     */
    public void setIsOpenable(boolean set, Items item) {
        containedItem = item;
        this.isOpenable = set;
    }

    /**
     * Set the healing modifier of an object if the Item is used
     * @param effect int value of healing
     */
    public void setHealingModifier(int effect) {
        this.healingModifier = effect;
    }

    /**
     * Set the damage factor of the weapon
     * @param damage int weapon damage
     */
    public void setWeaponDamage(int damage) {
        this.weaponDamage = damage;
    }

    /**
     * Set whether the item can burn another item
     * @param set boolean
     */
    public void setIsBurn(boolean set) {
        this.isBurn = set;
    }
}
