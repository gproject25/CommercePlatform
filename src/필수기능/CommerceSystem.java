package 필수기능;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CommerceSystem {
    private Scanner keyboard;
    Category electronics;
    Category clothing;
    Category food;

    public CommerceSystem(){
        electronics = new Category("전자제품");
        clothing =  new Category("의류");
        food = new Category("삭품");
        keyboard = new Scanner(System.in);
    }

    public void start(){
        String input;
        int input2;

        while(true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("0. 프로그램 종료");

            System.out.print("메뉴를 선택하세요 (1,2,3,0): ");
            input = keyboard.next();

            while (!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3")) {
                System.out.print("다시 입력하세요! (1,2,3,0): ");
                input = keyboard.next();
            }

            switch (input) {
                case "1":
                    selectMenu(electronics);
                    break;
                case "2":
                    selectMenu(clothing);
                    break;
                case "3":
                    selectMenu(food);
                    break;
                case "0":
                    return;
                default:
                    continue;
            }
        }

    }

    public void addProductToCategory(Product newitem, String category){
        if(category.equals("전자제품"))
            electronics.addProducts(newitem);
        else if(category.equals("의류"))
            clothing.addProducts(newitem);
        else if(category.equals("식품"))
            food.addProducts(newitem);
    }

    public void selectMenu(Category category){
        if(category.getSize() == 0){
            System.out.println("상품이 없습니다.");
            return;
        }

        category.productMenu();
        System.out.println("0. 뒤로가기");
        System.out.print("상품을 선택하세요: ");

        int input2;
        while(true){
            try{
                input2 = keyboard.nextInt();
                while(input2>category.getSize() || input2<0){
                    System.out.print("다시 입력하세요!: ");
                    input2 = keyboard.nextInt();
                }
                break;
            }
            catch(InputMismatchException e) {
                System.out.print("다시 입력하세요!: ");
                keyboard.next();
            }
        }
        category.selectProduct(input2-1);
    }
}
