package 필수기능;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private List<Product> products;
    private Scanner keyboard;

    public CommerceSystem(){
        products = new ArrayList<>();
        keyboard = new Scanner(System.in);
    }

    public void start(){
        String input;
        while(true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 - 전자제품 ]");
            for (int i = 0; i < products.size(); i++) {
                Product iproduct = products.get(i);
                System.out.printf("%d. %-15s | %10s | %s%n", i + 1, iproduct.getName(), iproduct.getPrice(), iproduct.getDescription());
            }
            System.out.printf("0. %-15s| %s%n", "종료", "프로그램 종료");

            System.out.print("상품을 선택하세요: ");
            input = keyboard.next();

            while (!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4")) {
                System.out.print("다시 입력하세요! (1,2,3,4,0):");
                input = keyboard.next();
            }

            switch (input) {
                case "0":
                    return;
                default:
                    continue;
            }
        }

    }

    public void addProduct(Product newitem){
        products.add(newitem);

    }

}
