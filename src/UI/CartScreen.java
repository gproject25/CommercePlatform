package UI;

import Domain.Cart;
import Domain.CustomerRank;
import Domain.Product;
import Service.Database;
import Service.UserInput;

public class CartScreen {
    private Cart cart;
    private UserInput userInput;
    private Database database;

    public CartScreen(UserInput userInput, Database database){
        cart = new Cart();
        this.userInput = userInput;
        this.database = database;
    }

    public boolean cartIsEmpty(){
        return cart.isEmpty();
    }

    public long handleCartMenu(String input, CustomerRank currentCustomerRank){
        if(input.equals("6")){  //장바구니 확인
            cart.displayCart();
        }
        else if(input.equals("7")){ //상품 제거
            removeFromCart();
        }
        else if(input.equals("8")){ //주문하기
            return placeOrder(currentCustomerRank);
        }
        else{   //주문 취소
            cart.clearCart();
            System.out.println("주문을 취소했습니다.");
        }

        return 0;
    }

    public void addToCart(Product product){
        // 장바구니에 상품 담기 메뉴
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        // 사용자 입력
        String input = userInput.twoNumberInput();

        // '확인' 입력시 장바구니에 입력된 수량 추가
        if(input.equals("1")){
            System.out.println("구매할 수량을 입력하세요:");
            int inputInt = userInput.addCartQuantityInput(product.getAmount());
            if(inputInt == -1)
                return;
            cart.addItems(product, inputInt);
        }
    }

    public void removeFromCart(){
        boolean found = false;
        cart.displayCart();
        System.out.print("\n장바구니에서 제거할 상품명을 입력하세요: ");
        String input = userInput.readStringInput();

        //상품 존재 여부 확인
        Product p = cart.isInCart(input);

        //상품 존재 확인
        if(p != null){
            System.out.println(p.getName() + "을 제거하시겠습니까?" );
            System.out.println("1. 삭제    2. 취소");

            //사용자 입력
            String input2 = userInput.twoNumberInput();

            if (input2.equals("1")){
                cart.removeItems(p);
                System.out.println("상품을 장바구니에서 제거했습니다. ");
            }
            else{
                System.out.println("상품 삭제를 취소했습니다.");
            }
        }
        else{
            System.out.println("상품을 찾지 못했습니다.");
        }
    }

    public void removeFromAdmin(){
        if(database.deleteFromCartSize() == 0){
            return;
        }

        cart.scehduleDelete(database.scheduleDeleteFromCart());
        database.clearDeleteFromCart();
    }

    public long placeOrder(CustomerRank currentCustomerRank){
        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        int totalCost = cart.displayCart();

        //등급별 할인 계산
        double discountRate = currentCustomerRank.getDiscountRate() / 100.0;
        int discount = (int) (totalCost * discountRate);
        System.out.println(currentCustomerRank.name() + " 등급 할인(" + currentCustomerRank.getDiscountRate() + "%): -" + String.format("%,d원", discount));

        //최종 결제 금액 계산
        int finalCost = totalCost - discount;
        System.out.println("최종 결제 금액: " + String.format("%,d원", finalCost));
        System.out.println("\n1. 주문 확정      2. 메인으로 돌아가기");

        //사용자 입력 확인
        String input = userInput.twoNumberInput();

        //주문 확정
        if(input.equals("1")){
            System.out.println("주문이 완료되었습니다!\n");

            //주문 확정 + 재고 감소
            cart.orderCart();
            return totalCost;
        }
        else
            return 0;
    }

    public void logoutClearCart(){
        cart.clearCart();
    }
}
