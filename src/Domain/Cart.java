package Domain;

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
        for(CartItem cartItem : cartItems){
            if(cartItem.getProduct().getId() == product.getId()){
                int newQuantity = cartItem.getQuantity() + quantity;
                if(newQuantity > product.getAmount()){
                    System.out.println("(장바구니) 재고가 부족하여 추가할 수 없습니다.");
                    return;
                }

                cartItem.setTotalPrice(cartItem.getTotalPrice() + totalprice);
                cartItem.setQuantity(newQuantity);
                System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
                size += quantity;
                return;
            }
        }

        CartItem cartItem = new CartItem(product,totalprice,quantity);
        cartItems.add(cartItem);
        System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
        size += quantity;
    }

    public void removeItems(Product p){
        cartItems.stream().filter(item -> p.getId() == item.getProduct().getId())
                .forEach(item -> size -= item.getQuantity());

        cartItems.removeIf(item -> p.getId() == item.getProduct().getId());
    }

    public boolean isEmpty(){
        if(size==0)
            return true;
        else
            return false;
    }

    public int displayCart(){
        System.out.println("\n[ 장바구니 내역 ]");
        int temp;
        int totalCost = 0;
        for(CartItem cartItem : cartItems)
        {
            Product p = cartItem.getProduct();
            temp = cartItem.getTotalPrice();
            totalCost += temp;
            System.out.println(" - " +  p.getName() + " | " + String.format("%,d원", temp) + " | " + p.getDescription() + " | 수량: " + cartItem.getQuantity() + "개");
        }
        System.out.println("\n[ 총 주문 금액 ]");
        System.out.println("상품 " + size + "개: " + String.format("%,d원", totalCost));
        return totalCost;
    }

    public void orderCart(){
        for (CartItem cartItem : cartItems) {
            Product p = cartItem.getProduct();
            int newAmount = p.getAmount() - cartItem.getQuantity();
            System.out.println(p.getName() + " 재고가 " + p.getAmount() + "개 -> " + newAmount + "개로 업데이트되었습니다.");
            p.setAmount(newAmount);
        }
        clearCart();
    }

    public Product isInCart(String input){
        for(CartItem ci : cartItems){
            Product p = ci.getProduct();
            if(p.getName().equals(input)){
                return p;
            }
        }
        return null;
    }

    public void scehduleDelete(List<Product> deleteProduct){
        for(Product product : deleteProduct){
            Product p = isInCart(product.getName());
            if(p == null) //상품 카트안에 있는지 확인
                return;
            else{   //상품 제거
                removeItems(p);
                System.out.println("\n ** 관리자에 의해 " + p.getName() + " 상품이 삭제되어 장바구니에서 제거되었습니다. **");
            }
        }
    }

    public void clearCart(){
        cartItems.clear(); //장바구니 비우기
        size = 0;
    }
}
