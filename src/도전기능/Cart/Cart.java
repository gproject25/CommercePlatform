package 도전기능.Cart;

import 도전기능.Customer.CustomerRank;
import 도전기능.ProductManagement.Product;

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

    public int orderCart(CustomerRank customerRank){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        int totalCost = displayCart();

        double discountRate = customerRank.getDiscountRate() / 100.0;
        int discount = (int) (totalCost * discountRate);
        System.out.println(customerRank.name() + " 등급 할인(" + customerRank.getDiscountRate() + "%): -" + String.format("%,d원", discount));
        int finalCost = totalCost - discount;
        System.out.println("최종 결제 금액: " + String.format("%,d원", finalCost));

        System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");

        String input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("다시 입력하세요! (1,2): ");
            input = keyboard.nextLine();
        }
        if(input.equals("1")) {
            System.out.println("주문이 완료되었습니다!");
            System.out.println();


            for(CartItem cartItem : cartItems) {
                Product p = cartItem.getProduct();
                int newAmount = p.getAmount() - cartItem.getQuantity();
                System.out.println(p.getName() + " 재고가 " + p.getAmount() + "개 -> " + newAmount + "개로 업데이트되었습니다." );
                p.setAmount(newAmount);

            }

            clearCart();
            return totalCost; //rankup 메서드에 활용
        }

        return 0;
    }

    public void clearCart(){
        cartItems.clear(); //장바구니 비우기
        size = 0;
    }
}
