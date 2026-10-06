package Service;

import java.util.Scanner;

public class UserInput {
    private Scanner keyboard;

    public UserInput(){
        keyboard = new Scanner(System.in);
    }

    public String loginMenuInput(){
        String input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("0")) {
            System.out.print("다시 입력하세요: ");
            input = keyboard.nextLine();
        }
        return input;
    }

    public int loginUserInput(int size){
        int input = readPositiveInt();
        while (input > size) {
            System.out.print("다시 입력하세요: ");
            input = readPositiveInt();
        }

        return(input-1);    //선택된 customer index를 반환
    }

    public String mainScreenInput(int cartEmpty){
        String input = keyboard.nextLine();

        while (!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")) {
            if(cartEmpty == 1){
                if(input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9"))
                    break;
            }

            System.out.print("다시 입력하세요!: ");
            input = keyboard.nextLine();
        }

        return input;
    }

    public String fourNumberInput(){
        String input = keyboard.nextLine();
        while(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("0")){
            System.out.print("다시 입력하세요: ");
            input = keyboard.nextLine();
        }
        return input;
    }

    public int selectProductInput(int size){
        int input;
        while(true){
            try{
                input = Integer.parseInt(keyboard.nextLine());
                while(input>size || input<0){
                    System.out.print("다시 입력하세요: ");
                    input = Integer.parseInt(keyboard.nextLine());
                }

                if(input == 0){
                    return -1;
                }

                break;
            }
            catch(NumberFormatException e) {
                System.out.print("다시 입력하세요!: ");
            }
        }
        return input;
    }

    public String twoNumberInput(){
        String input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("다시 입력하세요!: ");
            input = keyboard.nextLine();
        }
        return input;
    }

    public int addCartQuantityInput(int amount){
        int input2 = -1;
        while(true) {
            try {
                input2 = Integer.parseInt(keyboard.nextLine());
                while (input2 < 0) {
                    System.out.print("다시 입력하세요!: ");
                    input2 = Integer.parseInt(keyboard.nextLine());
                }
                if (input2 > amount) {
                    System.out.println("재고가 부족하여 구매할 수 없습니다.");
                    return -1;
                } else if (input2 == 0) {
                    System.out.println("상품을 추가하지 않았습니다.");
                    return -1;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("다시 입력하세요!: ");
            }
        }
        return input2;
    }

    public int adminLoginInput(String password){
        int wrongCount = 1;
        String input = keyboard.nextLine();
        while(!input.equals(password)){
            if(wrongCount == 3){
                System.out.println("인증에 실패했습니다. (오류 횟수: 3)  다시 시도해주세요.");
                return 0; //admin login 실패
            }

            System.out.print("잘못된 비밀번호입니다. (오류 횟수: " + wrongCount + ") 다시 입력하세요:");
            input = keyboard.nextLine();
            wrongCount++;
        }
        return 1; //admin login 성공
    }

    public String adminMenuInput(){
        String input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("0")) {
            System.out.print("다시 입력하세요!: ");
            input = keyboard.nextLine();
        }
        return input;
    }

    public int adminAddToCategory(int size){
        int input2 = 0;
        while(true) {
            try {
                input2 = Integer.parseInt(keyboard.nextLine());
                while (input2 < 1 || input2 > size) {
                    System.out.print("다시 입력하세요!: ");
                    input2 = Integer.parseInt(keyboard.nextLine());
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("다시 입력하세요!: ");
            }
        }
        return input2;
    }

    public String readStringInput(){
       String input = keyboard.nextLine();
        while(input.isBlank()){
            System.out.print("공백일 수 없습니다. 다시 입력해주세요:");
            input = keyboard.nextLine();
        }
       return input;
    }

    public int readPositiveInt() {
        while (true) {
            try {
                int value = Integer.parseInt(keyboard.nextLine());

                if (value > 0) {
                    return value;
                }

                System.out.print("다시 입력하세요!: ");

            } catch (NumberFormatException e) {
                System.out.print("숫자(Int)를 입력하세요!: ");
            }
        }
    }
}
