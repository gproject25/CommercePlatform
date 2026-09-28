package 필수기능;

public class Product {
    private String name;
    private String price;
    private String description;
    private int amount;

    public Product(String name, String price, String description, int amount){
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public String getDescription() {
        return description;
    }
    public int getAmount() {
        return amount;
    }
}
