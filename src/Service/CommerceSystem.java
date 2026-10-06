package Service;

import Domain.Category;
import Domain.Product;
import UI.*;

public class CommerceSystem {
    private int loginStatus;        //현재 사용자 login 상태 확인

    private CategoryScreen categoryScreen;
    private ProductScreen productScreen;
    private CartScreen cartScreen;
    private CustomerScreen customerScreen;
    private AdminScreen adminScreen;

    private UserInput userInput;

    public CommerceSystem(Database database){
        loginStatus = 0;    //login 안한 생태로 초기화

        userInput = new UserInput();

        customerScreen = new CustomerScreen(userInput, database);
        categoryScreen = new CategoryScreen(userInput, database);
        productScreen = new ProductScreen(userInput, database);
        cartScreen = new CartScreen(userInput, database);
        adminScreen = new AdminScreen(userInput,database);

    }

    public void start(){

        while(true) {
            // 1. Login
            if(loginStatus == 0) //login 안한 상태
                loginStatus = customerScreen.loginScreen();
            if(loginStatus == -1)   //프로그램 종료
                return;

            // 2. 메인 화면 / 카테고리 화면
            int cartEmpty = 0;
            if(!cartScreen.cartIsEmpty())
                cartEmpty = 1;   //장바구니가 비어있지 않으면 추가메뉴 출력
            String input = mainScreen(cartEmpty);


            //3. 상품 조회 + 선택 단계
            if(input.equals("1") || input.equals("2") || input.equals("3")){
                //3-1 카테고리 선택
                Category category = categoryScreen.selectCategory(input);

                //3-2 상품 선택
                handleProduct(category);
            }

            //4. 관리자 모드 실행
            else if(input.equals("4")){
                //4-1 admin login
                if(adminScreen.adminLogin() == 0)
                    continue;

                //4-2 admin menu 기능
                adminScreen.adminMenuHandler();

                //4-3 Optional: 관리자 삭제 -> 장바구니에서 매칭되는 상품도 제거
                cartScreen.removeFromAdmin();
            }

            //5. 사용자 정보 출력
            else if(input.equals("5")){
                customerScreen.printCurrentCustomer();
            }

            //6. 장바구니 메뉴 + 기능
            else if(input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9")){
                long cost = cartScreen.handleCartMenu(input, customerScreen.getCurrentCustomerRank());
                if(cost > 0){
                    customerScreen.rankUp(cost);
                }
            }

            //7. Logout + 사용자 카트는 저장되지 않음
            else if(input.equals("0")){
                cartScreen.logoutClearCart();
                loginStatus = 0;
            }
        }
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

    //3-2 카테고리 선택후, 상품 선택 단계
    public void handleProduct(Category category){
        while(true) {
            //3-2-1. 상품 화면 가격별 출력
            String input = productScreen.printScreen(category);

            if(input.equals("상품없음") || input.equals("0")){
                return;
            }

            //3-2-2. 상품 선택
            Product p = productScreen.selectProduct(input,category);
            if(p == null) continue;

            //3-2-3 장바구니 추가
            cartScreen.addToCart(p);
        }
    }

}
