package UI;

import Domain.Category;
import Domain.Product;
import Service.Database;
import Service.UserInput;

import java.util.List;

public class ProductScreen {

    private UserInput userInput;
    private Database database;

    public ProductScreen(UserInput userInput, Database database){
        this.userInput = userInput;
        this.database = database;

    }

    public String printScreen(Category category){
        if(database.categorySize(category) == 0){
            System.out.println("상품이 없습니다.");
            return "상품없음";
        }

        //4-1 상품 화면 가격별 출력
        System.out.println("\n[ " + category.getCategoryName() +  " 카테고리 ]");
        System.out.println("1. 전체 상품 보기");
        System.out.println("2. 가격대별 필터링 (100만원 이하)");
        System.out.println("3. 가격대별 필터링 (100만원 초과)");
        System.out.println("0. 뒤로가기");

        //4-2 사용자 입력
        String input = userInput.fourNumberInput();

        return input;
    }

    public Product selectProduct(String input, Category category){
        List<Product> temp = null;
        switch(input){
            case "1":
                temp = database.printAllProductsCategory(category); //전체 상품 보기
                break;
            case "2":
                temp = database.printLessThan(category); //가격대별 필터링 (100만원 이하)
                break;
            case "3":
                temp = database.printGreaterThan(category); //가격대별 필터링 (100만원 초과)
                break;
            case "0":
                return null;
        }
        System.out.println("0. 뒤로가기");
        System.out.print("상품을 선택하세요: ");

        int input2 = userInput.selectProductInput(temp.size());
        if(input2 == -1)
            return null;

        Product p = temp.get(input2-1);
        System.out.println("\n선택한 상품: " + p.getName() + " | " + p.getPrice() + " | " + p.getDescription() + " | 재고: " + p.getAmount());
        return p;
    }

}
