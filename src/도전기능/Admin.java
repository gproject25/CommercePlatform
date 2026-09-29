package 도전기능;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Admin {
    private String password;
    private String input;
    Scanner keyboard;
    CommerceSystem commerceSystem;

    public Admin(CommerceSystem commerce){
        this.password = "admin123";
        this.commerceSystem = commerce;
        keyboard = new Scanner(System.in);
    }

    public int adminLogin(){
        System.out.print("\n관리자 비밀번호를 입력해주세요: ");

        int wrongCount = 1;

        input = keyboard.nextLine();

        while(!input.equals(password)){
            if(wrongCount == 3){
                System.out.println("인증에 실패했습니다. (3)  다시 시도해주세요.");
                return 0;
            }

            System.out.print("잘못된 비밀번호입니다. (" + wrongCount + ") 다시 입력하세요:");
            input = keyboard.nextLine();
            wrongCount++;
        }

        return 1;
    }


    public int adminMenu(){
        System.out.println("\n[ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");

        input = keyboard.next();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("0")) {
            System.out.print("다시 입력하세요!: ");
            input = keyboard.next();
        }

        switch(input){
            case "1":
                adminAddProduct();
                break;

            case "0":
                return 0;
            default:
                return 1;

        }
        return 1;
    }

    public void adminAddProduct(){
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for(int i=0; i<commerceSystem.getCategoryList().size(); i++){
            System.out.println((i+1) + ". " + commerceSystem.getCategoryList().get(i).getCategoryName());
        }

        int input2;
        while(true) {
            try {
                input2 = keyboard.nextInt();
                while(input2<1 || input2>commerceSystem.getCategoryList().size()){
                    System.out.print("다시 입력하세요!: ");
                    input2 = keyboard.nextInt();
                }
                keyboard.nextLine();
                addProduct(input2);
                break;
            }
            catch (InputMismatchException e) {
                System.out.print("다시 입력하세요!: ");
                keyboard.next();
                break;
            }
        }
    }

    private int readPositiveInt() {
        while (true) {
            try {
                int value = keyboard.nextInt();
                keyboard.nextLine();

                if (value > 0) {
                    return value;
                }

                System.out.print("다시 입력하세요!: ");

            } catch (InputMismatchException e) {
                System.out.print("숫자를 입력하세요!: ");
                keyboard.next();
            }
        }
    }

    private void addProduct(int input2){
        Category category = commerceSystem.getCategoryList().get(input2-1);
        System.out.println("\n[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");

        System.out.print("상품명을 입력해주세요: ");
        String productName = keyboard.nextLine();

        String productDesc;
        System.out.print("가격을 입력해주세요: ");
        int productPrice = readPositiveInt();

        System.out.print("상품 설명을 입력해주세요: ");
        productDesc = keyboard.nextLine();

        System.out.print("재고수량을 입력해주세요: ");
        int productAmount = readPositiveInt();

        String price = String.format("%,d원", productPrice);

        System.out.println(productName + " | " + price + " | " + productDesc + " | " + "재고: " + productAmount + "개");
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");
        input = keyboard.next();
        while(!input.equals("1") && !input.equals("2")){
            System.out.print("다시 입력하세요!: ");
            keyboard.next();
        }

        if(input.equals("1")){
            Product product = new Product(productName,price,productDesc,productAmount);
            commerceSystem.addProductToCategory(product, category.getCategoryName());
        }
    }
}
