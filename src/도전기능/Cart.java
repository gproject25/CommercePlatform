package 도전기능;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;

    public Cart(){
        cartItems = new ArrayList<>();
    }

    public void addItems(Product product, int quantity){
        int totalprice = product.getIntPrice()*quantity;
        CartItem cartItem = new CartItem(product,totalprice,quantity);
        cartItems.add(cartItem);
    }
}
