package UI;

import Domain.Customer;
import Domain.CustomerRank;
import Service.Database;
import Service.UserInput;

public class CustomerScreen {
    private Customer currentCustomer;   //현재 login 된 사용자
    private UserInput userInput;
    private Database database;

    public CustomerScreen(UserInput userInput, Database database){
        this.userInput = userInput;
        this.database = database;
    }

    public int loginScreen(){
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
            database.printCustomers();
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.print("로그인할 사용자를 선택하세요: ");

            int customerIndex = userInput.loginUserInput(database.getCustomersSize());

            currentCustomer = database.getCustomer(customerIndex);
            System.out.println("\n안녕하세요 " + currentCustomer.getName() + "님!");
            return 1; //login 성공
        }
        else
            return -1; //프로그램 종료

    }

    //5. 사용자 정보 출력
    public void printCurrentCustomer(){
        System.out.println("\n-| 고객 정보 |- ");
        System.out.println("이름 : " + currentCustomer.getName());
        System.out.println("이메일: " + currentCustomer.getEmail());
        System.out.println("등급 : " + currentCustomer.getRank());
        System.out.println("할인율 : " + currentCustomer.getRank().getDiscountRate() + "%");
        System.out.println("총 사용 금액 : " + String.format("%,d원", currentCustomer.getTotalSpent()));
    }

    public CustomerRank getCurrentCustomerRank(){
        return currentCustomer.getRank();
    }



    // ====================== 추가 기능: 상품 구매시 등급 upgrade 여부 확인 ==========================================

    public void rankUp(long totalCost){
        //사용자의 누적 금액 추가
        currentCustomer.setTotalSpent(totalCost + currentCustomer.getTotalSpent());
        CustomerRank rank = currentCustomer.getRank();
        long  totalSpent = currentCustomer.getTotalSpent();

        System.out.println("\n현재 고객 등급: " + rank);
        System.out.println("--------------------------");

        // BRONZE 등급인 경우
        if(rank == CustomerRank.BRONZE){
            if(totalSpent >= 10000000) {
                currentCustomer.setRank(CustomerRank.PLATINUM);
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 10,000,000원을 달성하여 'BRONZE'에서 'PLATINUM' 등급으로 승급되었습니다! ");
            }
            else if(totalSpent >= 5000000) {
                currentCustomer.setRank(CustomerRank.GOLD);
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 5,000,000원을 달성하여 'BRONZE'에서 'GOLD' 등급으로 승급되었습니다! ");
            }
            else if(totalSpent >= 1000000) {
                currentCustomer.setRank(CustomerRank.SILVER);
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 1,000,000원을 달성하여 'BRONZE'에서 'SILVER' 등급으로 승급되었습니다! ");
            }
            else{
                System.out.println("구매해주셔서 감사합니다!");
                System.out.println("다음 등급인 'SILVER'까지 " + String.format("%,d원", 1000000 - totalSpent) + "의 추가 구매가 필요합니다.");
            }
        }

        // SILVER 등급인 경우
        else if(rank == CustomerRank.SILVER){
            if(totalSpent >= 10000000) {
                currentCustomer.setRank(CustomerRank.PLATINUM);
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 10,000,000원을 달성하여 'SILVER'에서 'PLATINUM' 등급으로 승급되었습니다! ");
            }
            else if(totalSpent >= 5000000) {
                currentCustomer.setRank(CustomerRank.GOLD);
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 5,000,000원을 달성하여 'SILVER'에서 'GOLD' 등급으로 승급되었습니다! ");
            }
            else{
                System.out.println("구매해주셔서 감사합니다!");
                System.out.println("다음 등급인 'GOLD'까지 " + String.format("%,d원", 5000000 - totalSpent) + "의 추가 구매가 필요합니다.");
            }
        }

        // GOLD 등급인 경우
        else if(rank == CustomerRank.GOLD){
            if(totalSpent >= 10000000) {
                currentCustomer.setRank(CustomerRank.PLATINUM);
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 10,000,000원을 달성하여 'GOLD'에서 'PLATINUM' 등급으로 승급되었습니다! ");
            }
            else{
                System.out.println("구매해주셔서 감사합니다!");
                System.out.println("다음 등급인 'PLATINUM'까지 " + String.format("%,d원", 10000000 - totalSpent) + "의 추가 구매가 필요합니다.");
            }
        }
    }

}
