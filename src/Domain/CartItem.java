package Domain;

public class CartItem {
    private Product product;
    private int totalPrice;
    private int quantity;

    public CartItem(Product product, int totalPrice, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Product getProduct() {
        return product;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStringTotalPrice() {
        return String.format("%,d원", totalPrice);
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
