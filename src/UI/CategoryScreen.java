package UI;

import Domain.Category;
import Service.Database;
import Service.UserInput;

public class CategoryScreen extends Screen {

    public CategoryScreen(UserInput userInput, Database database){
        super(userInput, database);
    }

    public String mainScreen(int cartEmpty){
        //2-1. 메인 화면 출력
        System.out.println("\n=========================");
        System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
        System.out.println("1. 전자제품");
        System.out.println("2. 의류");
        System.out.println("3. 식품");
        System.out.println("--------------");
        System.out.println("4. 관리자 모드");
        System.out.println("5. 고객 정보");
        System.out.println("0. 로그아웃");

        //2-2 추가 징바구니 화면
        if(cartEmpty == 1){
            System.out.println("\n[ 장바구니 ]");
            System.out.println("6. 장바구니 확인");
            System.out.println("7. 상품 제거");
            System.out.println("8. 주문하기");
            System.out.println("9. 주문 취소");
        }

        System.out.println("=========================");
        System.out.print("\n메뉴를 선택하세요: ");

        //2-3 사용자 입력
        String input = userInput.mainScreenInput(cartEmpty);
        return input;
    }

    //3-1 카테고리 선택
    public Category selectCategory(String input){
        Category category;
        switch(input){
            case "1":
                category = database.findCategory("전자제품");
                break;
            case "2":
                category = database.findCategory("의류");
                break;
            case "3":
                category = database.findCategory("식품");
                break;
            default:
                return null;
        }

        //카테고리 선택 완료
        return category;
    }

}
