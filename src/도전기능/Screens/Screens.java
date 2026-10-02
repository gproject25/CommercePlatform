package 도전기능.Screens;

import 도전기능.Customer.CustomersSetting;
import 도전기능.UserInput;

public class Screens {

    private UserInput userInput;
    private CustomersSetting customersSetting;

    public Screens(){
        userInput = new UserInput();
        customersSetting = new CustomersSetting();
    }

    public int login(){
        //1-1. login 화면 출력
        System.out.println();
        System.out.println("==================================================");
        System.out.println("                 실시간 커머스 플랫폼");
        System.out.println("==================================================");
        System.out.println();
        System.out.println("        1. 로그인                   0. 종료");
        System.out.println();
        System.out.println("--------------------------------------------------");

        //1-2. 사용자 입력  + 예외 처리
        String input = userInput.loginMenuInput();

        //1-3. '1' 입력시 사용자 선택
        if (input.equals("1")) {
            System.out.println();
            System.out.println("==================================================");
            System.out.println("                    사용자 선택");
            System.out.println("==================================================");
            System.out.println();
            customersSetting.printCustomers();

            int customerIndex = userInput.loginUserInput(customersSetting.getSize());

            customersSetting.setCurrentCustomer(customerIndex);
            return 1; //login 성공
        }
        else
            return -1; //프로그램 종료

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

        String input = userInput.mainScreenInput(cartEmpty);
        return input;
    }

    public void printUserInfo(){
        customersSetting.printCurrentCustomer();
    }
}
