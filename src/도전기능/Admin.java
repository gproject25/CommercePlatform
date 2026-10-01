package 도전기능;

import 도전기능.ProductManagement.Category;
import 도전기능.ProductManagement.Product;

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
                System.out.println("인증에 실패했습니다. (오류 횟수: 3)  다시 시도해주세요.");
                return 0;
            }

            System.out.print("잘못된 비밀번호입니다. (오류 횟수: " + wrongCount + ") 다시 입력하세요:");
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
        input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("0")) {
            System.out.print("다시 입력하세요!: ");
            input = keyboard.nextLine();
        }

        switch(input){
            case "1":
                adminAddProduct();
                break;
            case "2":
                adminEditProduct();
                break;
            case "3":
                adminDeleteProduct();
                break;
            case "4":
                commerceSystem.printAllProducts();
                break;
            case "0":
                return 0;
            default:
                return 1;

        }
        return 1;
    }

    public int readPositiveInt() {
        while (true) {
            try {
                int value = Integer.parseInt(keyboard.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.print("다시 입력하세요!: ");

            } catch (NumberFormatException e) {
                System.out.print("숫자(Int)를 입력하세요!: ");
            }
        }
    }

    // --------------------------------- 카테고리에 상품 추가 ----------------------------------------------
    public void adminAddProduct(){
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        for(int i=0; i<commerceSystem.getCategoryList().size(); i++){
            System.out.println((i+1) + ". " + commerceSystem.getCategoryList().get(i).getCategoryName());
        }

        int input2;
        while(true) {
            try {
                input2 = Integer.parseInt(keyboard.nextLine());
                while(input2<1 || input2>commerceSystem.getCategoryList().size()){
                    System.out.print("다시 입력하세요!: ");
                    input2 = Integer.parseInt(keyboard.nextLine());
                }
                addProduct(input2);
                break;
            }
            catch (NumberFormatException e) {
                System.out.print("다시 입력하세요!: ");
            }
        }
    }

    private void addProduct(int input2){
        Category category = commerceSystem.getCategoryList().get(input2-1);
        System.out.println("\n[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");

        System.out.print("상품명을 입력해주세요: ");
        String productName = keyboard.nextLine();
        while(productName.isBlank()){
            System.out.print("상품명은 공백일 수 없습니다. 다시 입력해주세요:");
            productName = keyboard.nextLine();
        }

        if(category.checkDuplicate(productName)){
            System.out.println("중복 상품명이 있습니다!");
            return;
        }

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
        input = keyboard.nextLine();
        while(!input.equals("1") && !input.equals("2")){
            System.out.print("다시 입력하세요!: ");
            input = keyboard.nextLine();
        }

        if(input.equals("1")){
            Product product = new Product(productName,price,productDesc,productAmount);
            commerceSystem.addProductToCategory(product, category.getCategoryName());
            System.out.println("상품이 성공적으로 추가되었습니다!");
        }
    }
    //------------------------------------------------------------------------------------------


    // ---------------------------------  상품 수정 ----------------------------------------------
    private void adminEditProduct(){
        Product foundProduct = commerceSystem.selectFromProducts("(상품 수정)");
        if(foundProduct == null)
            return;

        //상품 수정 항목
        System.out.println("현재 상품 정보: " + foundProduct.getName() + " | " + foundProduct.getPrice() + " | " + foundProduct.getDescription() + " | 재고" + foundProduct.getAmount() + "개");
        System.out.println("\n수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명 ");
        System.out.println("3. 재고수량");

        input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3")){
            System.out.print("다시 입력하세요!: ");
            input = keyboard.nextLine();
        }

        System.out.println();
        switch(input){
            case "1":
                System.out.println("현재 가격: " + foundProduct.getPrice());
                System.out.print("새로운 가격을 입력해주세요: ");
                String newPrice = String.format("%,d원", readPositiveInt());
                System.out.println(foundProduct.getName() + "의 가격이 " + foundProduct.getPrice() + " -> " + newPrice + "으로 수정되었습니다.");
                foundProduct.setPrice(newPrice);
                break;
            case "2":
                System.out.println("현재 설명: " + foundProduct.getDescription());
                System.out.print("새로운 설명을 입력해주세요: ");
                String newDesc = keyboard.nextLine();
                System.out.println(foundProduct.getName() + "의 설명이 '" + foundProduct.getDescription() + "' -> '" + newDesc + "'으로 수정되었습니다.");
                foundProduct.setDescription(newDesc);
                break;
            case "3":
                System.out.println("현재 재고수량: " + foundProduct.getAmount());
                System.out.print("새로운 재고수량을 입력해주세요: ");
                int newAmount = readPositiveInt();
                System.out.println(foundProduct.getName() + "의 재고수량이 " + foundProduct.getAmount() + " -> " + newAmount + "으로 수정되었습니다.");
                foundProduct.setAmount(newAmount);
                break;
            default:
                return;
        }

    }
    //----------------------------------------------------------------------------------------------------


    // --------------------------------- 카테고리에서 상품 삭제 ----------------------------------------------
    private void adminDeleteProduct(){
        Product p = commerceSystem.selectFromProducts("(상품 삭제)");

        if(p == null)
            return;

        System.out.println("상품 정보: " +p.getName() + " | " + p.getPrice() + " | " + p.getDescription() + " | 재고" + p.getAmount() + "개");
        System.out.println("상품을 삭제하시겠습니까?");
        System.out.println("1. 삭제    2. 취소");
        input = keyboard.nextLine();
        while(!input.equals("1") && !input.equals("2")){
            System.out.print("다시 입력하세요!: ");
            keyboard.nextLine();
        }

        if(input.equals("1")){
            for(Category ct : commerceSystem.getCategoryList()){
                if(ct.removeProduct(p.getId())){
                    commerceSystem.removeFromCart(p);
                    System.out.println("상품이 성공적으로 삭제되었습니다!");
                    return;
                }
            }
        }
    }
    // ---------------------------------------------------------------------------------------------------------
}
