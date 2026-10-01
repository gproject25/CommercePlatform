package 도전기능;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Category {

    private List<Product> products;
    private String categoryName;
    private Scanner keyboard;

    public Category(String name){
        this.categoryName = name;
        this.products = new ArrayList<>();
        keyboard = new Scanner(System.in);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int productMenu() {
        if(products.size() == 0){
            System.out.println("상품이 없습니다.");
            return 0;
        }

        System.out.println("\n[ " + getCategoryName() +  " 카테고리 ]");

        System.out.println("1. 전체 상품 보기");
        System.out.println("2. 가격대별 필터링 (100만원 이하)");
        System.out.println("3. 가격대별 필터링 (100만원 초과)");
        System.out.println("0. 뒤로가기");

        String input = keyboard.nextLine();
        while(!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("0")){
            System.out.print("다시 입력하세요: ");
            input = keyboard.nextLine();
        }

        List<Product> temp = new ArrayList<>();
        Product p;
        switch(input){
            case "1":
                System.out.println("\n[ 전체 상품 ]");
                IntStream.range(0, products.size()).forEach(i -> {
                    System.out.print((i+1) + ". ");
                    if(products.get(i).getAmount() == 0)
                        System.out.print("(품절) ");
                    products.get(i).printProduct();
                    temp.add(products.get(i));
                });

                break;
            case "2":
                System.out.println("\n[ 100만원 이하 상품 ]");
                products.stream().filter(product -> product.getIntPrice() <= 1000000).forEach(temp::add);

                IntStream.range(0, temp.size())
                        .forEach(i -> {
                            System.out.print((i+1) + ". ");
                            if(temp.get(i).getAmount() == 0)
                                System.out.print("(품절) ");
                            temp.get(i).printProduct();
                        });
                break;
            case "3":
                System.out.println("\n[ 100만원 초과 상품 ]");
                products.stream().filter(product -> product.getIntPrice() > 1000000).forEach(temp::add);

                IntStream.range(0, temp.size())
                        .forEach(i -> {
                            System.out.print((i+1) + ". ");
                            if(temp.get(i).getAmount() == 0)
                                System.out.print("(품절) ");
                            temp.get(i).printProduct();
                        });
                break;
            case "0":
                return 0;
        }

        p = selectProduct(temp);
        if(p == null)   //0. 뒤로가기
            return -1;
        else
            return p.getId();
    }

    public void addProducts(Product newitem){
        products.add(newitem);
    }

    public int getSize(){
        return products.size();
    }

    public Product selectProduct(List<Product> temp){
        System.out.println("0. 뒤로가기");
        System.out.print("상품을 선택하세요: ");

        int input;
        while(true){
            try{
                input = Integer.parseInt(keyboard.nextLine());
                while(input>temp.size() || input<0){
                    System.out.print("다시 입력하세요: ");
                    input = Integer.parseInt(keyboard.nextLine());
                }

                if(input == 0){
                    return null;
                }

                break;
            }
            catch(NumberFormatException e) {
                System.out.print("다시 입력하세요!: ");
            }
        }

        Product p = temp.get(input-1);
        System.out.println("\n선택한 상품: " + p.getName() + " | " + p.getPrice() + " | " + p.getDescription() + " | 재고: " + p.getAmount());
        return p;
    }

    public boolean checkDuplicate(String productName){
        for(Product p : products){
            if(p.getName().equals(productName))
                return true;
        }
        return false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean removeProduct(int id){
        for(int i=0; i<products.size(); i++){
            if(products.get(i).getId() == id){
                products.remove(i);
                return true;
            }
        }
        return false;
    }
}
