package 도전기능;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> cartItems;
    private int size;

    public Cart(){
        cartItems = new ArrayList<>();
        size = 0;
    }

    public void addItems(Product product, int quantity){
        //중복 확인
        int totalprice = product.getIntPrice()*quantity;
        size += quantity;
        for(CartItem cartItem : cartItems){
            if(cartItem.getProduct().getId() == product.getId()){
                cartItem.setTotalPrice(cartItem.getTotalPrice() + totalprice);
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                return;
            }
        }

        CartItem cartItem = new CartItem(product,totalprice,quantity);
        cartItems.add(cartItem);
    }

    public boolean isEmpty(){
        if(size==0)
            return true;
        else
            return false;
    }

    public void orderCart(){
        System.out.println("\n아래와 같이 주문 하시겠습니까?\n");
        System.out.println("[ 장바구니 내역 ]");

        int temp;
        int totalCost = 0;
        for(CartItem cartItem : cartItems)
        {
            Product p = cartItem.getProduct();
            temp = cartItem.getTotalPrice();
            totalCost += temp;
            System.out.println( p.getName() + " | " + String.format("%,d원", temp) + " | " + p.getDescription() + " | 수량: " + cartItem.getQuantity() + "개");
        }

        System.out.println("\n[ 총 주문 금액 ]");
        System.out.println("상품 " + size + "개: " + String.format("%,d원", totalCost));
    }
}
