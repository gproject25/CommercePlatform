package UI;

import Domain.Category;
import Domain.Product;
import Service.Database;
import Service.UserInput;

public class AdminScreen {
    private UserInput userInput;
    private Database database;
    private String password;

    public AdminScreen(UserInput userInput, Database database){
        this.userInput = userInput;
        this.database = database;
        this.password = "admin123";
    }

    //4-1 admin login
    public int adminLogin(){
        System.out.print("\n관리자 비밀번호를 입력해주세요: ");
        int wrongCount = 1;

        //사용자 입력
        int adminLoginStatus = userInput.adminLoginInput(password);
        if(adminLoginStatus == 0) { //인증 실패
            return 0;
        }

        //인증 성공
        return 1;
    }

    //4-2 admin menu 기능
    public void adminMenuHandler(){
        while(true){
            System.out.println("\n[ 관리자 모드 ]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");

            String input = userInput.adminMenuInput();

            switch(input) {
                case "1":
                    adminAddProduct();      //상품 추가
                    break;
                case "2":
                    adminEditProduct();     //상품 수정
                    break;
                case "3":
                    adminDeleteProduct();   //상품 삭제
                    break;
                case "4":
                    database.printAllProducts(0);
                    break;
                case "0":                   //취소
                    return;
                default:
                    return;
            }
        }
    }

    // --------------------------------- 카테고리에 상품 추가 ----------------------------------------------

    //4-2-1.  상품을 데이터베이스로 추가
    private void adminAddProduct(){
        //카테고리 list 출력
        System.out.println("\n어느 카테고리에 상품을 추가하시겠습니까?");
        database.printCategoryList();

        //사용자 입력
        int index= (userInput.adminAddToCategory(database.categoryListSize()) - 1);

        //카테고리 선택
        Category category = database.findCategoryByIndex(index);
        System.out.println("\n[ " + category.getCategoryName() + " 카테고리에 상품 추가 ]");
        System.out.print("상품명을 입력해주세요: ");

        //사용자 상품명 입력
        String productName = userInput.readStringInput();

        //중복 상풍명 확인
        if(database.checkDuplicate(category, productName)){
            System.out.println("중복 상품명이 있습니다!");
            return;
        }

        //상품 세부 정보 입력
        System.out.print("가격을 입력해주세요: ");
        int productPrice = userInput.readPositiveInt();

        System.out.print("상품 설명을 입력해주세요: ");
        String productDesc = userInput.readStringInput();

        System.out.print("재고수량을 입력해주세요: ");
        int productAmount = userInput.readPositiveInt();

        //최종 확인
        String price = String.format("%,d원", productPrice);
        System.out.println(productName + " | " + price + " | " + productDesc + " | " + "재고: " + productAmount + "개");
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인    2. 취소");

        String confirm = userInput.twoNumberInput();

        if(confirm.equals("1")){
            Product product = new Product(productName,price,productDesc,productAmount);
            database.createProducts(product, category.getCategoryName()); //성공시 데이터배이스에 추가
            System.out.println("상품이 성공적으로 추가되었습니다!");
        }

    }
    //------------------------------------------------------------------------------------------


    // ---------------------------------  상품 수정 ----------------------------------------------
    //4-2-2.  특정 상품을 수정
    private void adminEditProduct(){
        System.out.println("\n[ (상품 수정) 전체 상품 ID 목록 ] ");
        database.printAllProducts(1);

        //상품 ID 입력
        System.out.print("\n상품 ID를 입력하세요: ");
        int id = userInput.readPositiveInt();

        //상품 존재 여부 확인
        Product foundProduct = database.findProductByID(id);
        if(foundProduct == null){
            System.out.println("상품을 찾지 못했습니다.");
            return;
        }

        //상품 수정 항목
        System.out.println("현재 상품 정보: " + foundProduct.getName() + " | " + foundProduct.getPrice() + " | " + foundProduct.getDescription() + " | 재고" + foundProduct.getAmount() + "개");
        System.out.println("\n수정할 항목을 선택해주세요:");
        System.out.println("1. 가격");
        System.out.println("2. 설명 ");
        System.out.println("3. 재고수량");
        System.out.println("0. 취소");

        String input = userInput.fourNumberInput();
        System.out.println();

        //사용자 입력에 따라 가격 수정, 설명 수정, 재고수량 수정
        switch(input){
            case "1": //가격 수정
                System.out.println("현재 가격: " + foundProduct.getPrice());
                System.out.print("새로운 가격을 입력해주세요: ");
                String newPrice = String.format("%,d원", userInput.readPositiveInt());
                System.out.println(foundProduct.getName() + "의 가격이 " + foundProduct.getPrice() + " -> " + newPrice + "으로 수정되었습니다.");
                foundProduct.setPrice(newPrice);
                break;
            case "2":  //Description 변경
                System.out.println("현재 설명: " + foundProduct.getDescription());
                System.out.print("새로운 설명을 입력해주세요: ");
                String newDesc = userInput.readStringInput();
                System.out.println(foundProduct.getName() + "의 설명이 '" + foundProduct.getDescription() + "' -> '" + newDesc + "'으로 수정되었습니다.");
                foundProduct.setDescription(newDesc);
                break;
            case "3":   //재고수량 수정
                System.out.println("현재 재고수량: " + foundProduct.getAmount());
                System.out.print("새로운 재고수량을 입력해주세요: ");
                int newAmount = userInput.readPositiveInt();
                System.out.println(foundProduct.getName() + "의 재고수량이 " + foundProduct.getAmount() + " -> " + newAmount + "으로 수정되었습니다.");
                foundProduct.setAmount(newAmount);
                break;
            case "01":
                System.out.println("상품 수정을 취소합니다");
                return;
        }
    }

    //----------------------------------------------------------------------------------------------------


    // --------------------------------- 카테고리에서 상품 삭제 ----------------------------------------------
    //4-2-3.  상품을 데이터베이스에서 삭제
    private void adminDeleteProduct(){
        System.out.println("\n[ (상품 삭제) 전체 상품 ID 목록 ] ");
        database.printAllProducts(1);

        //상품 ID 입력
        System.out.print("\n상품 ID를 입력하세요: ");
        int id = userInput.readPositiveInt();

        //상품 존재 여부 확인
        Product foundProduct = database.findProductByID(id);
        if(foundProduct == null){
            System.out.println("상품을 찾지 못했습니다.");
            return;
        }

        //상품 삭제 확인 메세지
        System.out.println("상품 정보: " +foundProduct.getName() + " | " + foundProduct.getPrice() + " | " + foundProduct.getDescription() + " | 재고" + foundProduct.getAmount() + "개");
        System.out.println("상품을 삭제하시겠습니까?");
        System.out.println("1. 삭제    2. 취소");

        String input = userInput.twoNumberInput();

        if(input.equals("1")){
            database.removeProduct(foundProduct);
        }
    }
}
