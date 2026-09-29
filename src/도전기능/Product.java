package 도전기능;

public class Product {
    private static int productId = 0; //product 생성시 증가

    private int id;
    private String name;
    private String price;
    private String description;
    private int amount;

    public Product(String name, String price, String description, int amount){
        id = productId++;
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
    }

    public int getId() {
        return id;
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
    public int getIntPrice(){
        String intprice = this.price;
        int priceInt = Integer.parseInt(intprice.replace(",", "").replace("원", ""));
        return priceInt;
    }
}
