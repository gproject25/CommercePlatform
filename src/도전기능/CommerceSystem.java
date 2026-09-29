package 도전기능;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private Scanner keyboard;
    private Cart cart;
    private String input;
    private Admin admin; //시스템마다 하나의 admin 가정

    private List<Category> categoryList;

    public CommerceSystem(){
        Category electronics = new Category("전자제품");
        Category clothing =  new Category("의류");
        Category food = new Category("식품");

        categoryList = new ArrayList<>();
        categoryList.add(electronics);
        categoryList.add(clothing);
        categoryList.add(food);


        keyboard = new Scanner(System.in);
        cart = new Cart();
        admin = new Admin(this);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void start(){
        while(true) {
            System.out.println("\n-------------------------");
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("4. 관리자 모드");
            System.out.println("0. 프로그램 종료");

            if(!cart.isEmpty()){
                System.out.println("\n[ 주문 관리 ]");
                System.out.println("5. 장바구니 확인");
                System.out.println("6. 주문 취소");
            }

            System.out.println("-------------------------");
            System.out.print("\n메뉴를 선택하세요: ");
            input = keyboard.next();

            while (!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                if(!cart.isEmpty()){
                    if(input.equals("5") || input.equals("6"))
                        break;
                }

                System.out.print("다시 입력하세요!: ");
                input = keyboard.next();
            }


            Category category = null;
            switch (input) {
                case "1":
                    category = findCategory("전자제품");
                    while (selectMenu(category) == 1) {}
                    break;
                case "2":
                    category = findCategory("의류");
                    while (selectMenu(category) == 1) {}
                    break;
                case "3":
                    category = findCategory("식품");
                    while (selectMenu(category) == 1) {}
                    break;
                case "0":
                    return;
                case "4":
                    if(admin.adminLogin() == 0)
                        break;
                    while(admin.adminMenu() == 1){}
                    break;
                case "5":
                    cart.orderCart();
                    break;
                case "6":
                    cart.clearCart();
                    System.out.println("주문을 취소했습니다.");
                    break;
                default:
                    continue;
            }
        }

    }

    public void addProductToCategory(Product newItem, String category){
        Category ct = findCategory(category);

        if (ct != null)
            ct.addProducts(newItem);
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
                    break;
                }
                catch(InputMismatchException e){
                    System.out.print("다시 입력하세요!: ");
                    keyboard.next();
                }
            }
        }

    }

    private Category findCategory(String categoryName) {
        for (Category ct : categoryList) {
            if (categoryName.equals(ct.getCategoryName())) {
                return ct;
            }
        }
        return null;
    }

}
