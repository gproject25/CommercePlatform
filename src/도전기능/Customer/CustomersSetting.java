package 도전기능.Customer;

import java.util.ArrayList;
import java.util.List;

//시스템 안에 저장된 customers 관리하는 클래스
public class CustomersSetting {
    private List<Customer> customers;

    private Customer currentCustomer;   //현재 login 된 사용자

    public CustomersSetting(){
        customers = new ArrayList<>();
    }

    public int getSize() {
        return customers.size();
    }

    public void setCurrentCustomer(int index) {
        currentCustomer = customers.get(index);
        System.out.println("\n안녕하세요 " + currentCustomer.getName() + "님!");
    }

    public void printCurrentCustomer(){
        System.out.println("\n-| 고객 정보 |- ");
        System.out.println("이름 : " + currentCustomer.getName());
        System.out.println("이메일: " + currentCustomer.getEmail());
        System.out.println("등급 : " + currentCustomer.getRank());
        System.out.println("할인율 : " + currentCustomer.getRank().getDiscountRate() + "%");
        System.out.println("총 사용 금액 : " + String.format("%,d원", currentCustomer.getTotalSpent()));
    }

    public void printCustomers() {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.printf("  %d. %-12s | 등급: %s%n", i + 1, customer.getName(), customer.getRank());
        }
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.print("로그인할 사용자를 선택하세요: ");
    }
}
