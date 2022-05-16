public class Items {

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

    Items(String name, String itemDescription) {
        this.name = name;
        this.itemDescription = itemDescription;
    }

    //GETTERS//

    public String getName() {
        return name;
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
