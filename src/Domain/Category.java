package Domain;

public class Category {

    private String categoryName;
    private int databaseIndex;

    public Category(String name, int databaseIndex){
        this.categoryName = name;
        this.databaseIndex = databaseIndex;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getDatabaseIndex() {
        return databaseIndex;
    }

}
