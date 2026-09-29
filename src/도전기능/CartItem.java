package 도전기능;

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

    public int getQuantity() {
        return quantity;
    }
}
