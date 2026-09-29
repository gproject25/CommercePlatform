package 도전기능;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommerceSystem {
    private Scanner keyboard;
    private Category electronics;
    private Category clothing;
    private Category food;
    private Cart cart;
    private String input;

    public CommerceSystem(){
        electronics = new Category("전자제품");
        clothing =  new Category("의류");
        food = new Category("삭품");
        keyboard = new Scanner(System.in);
        cart = new Cart();
    }

    public void start(){
        while(true) {
            System.out.println("\n-------------------------");
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 프로그램 종료");

            if(!cart.isEmpty()){
                System.out.println("\n[ 주문 관리 ]");
                System.out.println("4. 장바구니 확인");
                System.out.println("5. 주문 취소");
            }

            System.out.println("-------------------------");
            System.out.print("\n메뉴를 선택하세요: ");
            input = keyboard.next();

            while (!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3")) {
                if(!cart.isEmpty()){
                    if(input.equals("4") || input.equals("5"))
                        break;
                }

                System.out.print("다시 입력하세요!: ");
                input = keyboard.next();
            }

            switch (input) {
                case "1":
                    while(selectMenu(electronics) == 1) {}
                    break;
                case "2":
                    while(selectMenu(clothing) == 1) {};
                    break;
                case "3":
                    while(selectMenu(food) == 1) {};
                    break;
                case "0":
                    return;
                case "4":
                    cart.orderCart();
                    break;
                default:
                    continue;
            }
        }

    }

    public void addProductToCategory(Product newitem, String category){
        if(category.equals("전자제품"))
            electronics.addProducts(newitem);
        else if(category.equals("의류"))
            clothing.addProducts(newitem);
        else if(category.equals("식품"))
            food.addProducts(newitem);
    }

    public int selectMenu(Category category){
        if(category.getSize() == 0){
            System.out.println("상품이 없습니다.");
            return 0;
        }

        category.productMenu();
        System.out.println("0. 뒤로가기");
        System.out.print("상품을 선택하세요: ");

        int input2;
        while(true){
            try{
                input2 = keyboard.nextInt();
                while(input2>category.getSize() || input2<0){
                    System.out.print("다시 입력하세요!: ");
                    input2 = keyboard.nextInt();
                }

                if(input2 == 0)
                    return 0;

                break;
            }
            catch(InputMismatchException e) {
                System.out.print("다시 입력하세요!: ");
                keyboard.next();
            }
        }
        Product selectedProduct = category.selectProduct(input2-1);
        addToCart(selectedProduct);
        return 1;
    }


    public void addToCart(Product product){
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        input = keyboard.next();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("다시 입력하세요! (1,2): ");
            input = keyboard.next();
        }

        if(input.equals("1")){
            System.out.println("구매할 수량을 입력하세요:");
            int input2;
            int amount = product.getAmount();
            while(true){
                try{
                    input2 = keyboard.nextInt();
                    while(input2<0){
                        System.out.print("다시 입력하세요!: ");
                        input2 = keyboard.nextInt();
                    }
                    if(input2>amount) {
                        System.out.println("재고가 부족하여 구매할 수 없습니다.");
                        break;
                    }
                    else if(input2==0) {
                        System.out.println("상품을 추가하지 않았습니다.");
                        break;
                    }
                    cart.addItems(product, input2);
                    System.out.println(product.getName() + "가 장바구니에 추가되었습니다.");
                    break;
                }
                catch(InputMismatchException e){
                    System.out.print("다시 입력하세요!: ");
                    keyboard.next();
                }
            }
        }

    }
}
