package 도전기능;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        Scanner keyboard = new Scanner(System.in);

        for(CartItem cartItem : cartItems)
        {
            Product p = cartItem.getProduct();
            temp = cartItem.getTotalPrice();
            totalCost += temp;
            System.out.println( p.getName() + " | " + String.format("%,d원", temp) + " | " + p.getDescription() + " | 수량: " + cartItem.getQuantity() + "개");
        }

        System.out.println("\n[ 총 주문 금액 ]");
        System.out.println("상품 " + size + "개: " + String.format("%,d원", totalCost));

        System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");

        String input = keyboard.next();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("다시 입력하세요! (1,2): ");
            input = keyboard.next();
        }
        if(input.equals("1")) {
            System.out.println("주문이 완료되었습니다! 총 금액: " + String.format("%,d원", totalCost));
            for(CartItem cartItem : cartItems) {
                Product p = cartItem.getProduct();
                int newAmount = p.getAmount() - cartItem.getQuantity();
                System.out.println(p.getName() + " 재고가 " + p.getAmount() + "개 -> " + newAmount + "개로 업데이트되었습니다." );
                p.setAmount(newAmount);
            }
            clearCart();
        }
        //input 2-> return

    }

    public void clearCart(){
        cartItems.clear(); //장바구니 비우기
        size = 0;
    }
}
