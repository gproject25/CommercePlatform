package Domain;

public class Product {
    private static int productId = 100; //product 생성시 증가

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

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIntPrice(){
        String intprice = this.price;
        int priceInt = Integer.parseInt(intprice.replace(",", "").replace("원", ""));
        return priceInt;
    }

    public void printProduct(){
        System.out.printf("%-15s | %10s | %s | 재고: %d%n", name, price, description, amount);
    }
}
