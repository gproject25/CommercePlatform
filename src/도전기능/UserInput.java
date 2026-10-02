package 도전기능;

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




    private int readPositiveInt() {
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
