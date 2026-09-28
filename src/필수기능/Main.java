package 필수기능;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Product product1 = new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 1);
        Product product2 = new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 1);
        Product product3 = new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 1);
        Product product4 = new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 1);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        Scanner keyboard = new Scanner(System.in);

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
}
