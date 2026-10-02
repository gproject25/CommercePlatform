package 도전기능;

import 도전기능.Cart.Cart;
import 도전기능.Customer.Customer;
import 도전기능.ProductManagement.Category;
import 도전기능.ProductManagement.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private Scanner keyboard;
    private Cart cart;;
    private Admin admin; //시스템마다 하나의 admin 가정

    private List<Category> categoryList;
    private List<Customer> customers;
    private Customer currentCustomer;

    public CommerceSystem(){
        Category electronics = new Category("전자제품");
        Category clothing =  new Category("의류");
        Category food = new Category("식품");

        categoryList = new ArrayList<>();
        categoryList.add(electronics);
        categoryList.add(clothing);
        categoryList.add(food);


        keyboard = new Scanner(System.in);
        cart = new Cart();
        admin = new Admin(this);
        customers = new ArrayList<>();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public int login(){
        System.out.println();
        System.out.println("==================================================");
        System.out.println("                 실시간 커머스 플랫폼");
        System.out.println("==================================================");
        System.out.println();
        System.out.println("        1. 로그인                   0. 종료");
        System.out.println();
        System.out.println("--------------------------------------------------");
        String input = keyboard.nextLine();
        while(!input.equals("1") && !input.equals("0")){
            System.out.print("다시 입력하세요: ");
            input = keyboard.nextLine();
        }
        if(input.equals("1")){
            System.out.println();
            System.out.println("==================================================");
            System.out.println("                    사용자 선택");
            System.out.println("==================================================");
            System.out.println();
            for(int i=0; i<customers.size(); i++){
                Customer customer = customers.get(i);
                System.out.printf("  %d. %-12s | 등급: %s%n", i + 1, customer.getName(), customer.getRank());
            }
            System.out.println();
            System.out.println("--------------------------------------------------");
            System.out.print("로그인할 사용자를 선택하세요: ");
            int input2 = admin.readPositiveInt();
            while(input2 > customers.size()) {
                System.out.print("다시 입력하세요: ");
                input2 = admin.readPositiveInt();
            }
            this.currentCustomer = customers.get(input2-1);
            return 0;
        }
        else
            return -1;
    }

    public void start(){
        System.out.println("\n안녕하세요 "+ currentCustomer.getName() + "님!");

        while(true) {
            System.out.println("\n=========================");
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("1. 전자제품");
            System.out.println("2. 의류");
            System.out.println("3. 식품");
            System.out.println("--------------");
            System.out.println("4. 관리자 모드");
            System.out.println("5. 고객 정보");
            System.out.println("0. 로그아웃");

            if(!cart.isEmpty()){
                System.out.println("\n[ 장바구니 ]");
                System.out.println("6. 장바구니 확인");
                System.out.println("7. 상품 제거");
                System.out.println("8. 주문하기");
                System.out.println("9. 주문 취소");
            }

            System.out.println("=========================");
            System.out.print("\n메뉴를 선택하세요: ");
            String input = keyboard.nextLine();

            while (!input.equals("0") && !input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("4") && !input.equals("5")) {
                if(!cart.isEmpty()){
                    if(input.equals("6") || input.equals("7") || input.equals("8") || input.equals("9"))
                        break;
                }

                System.out.print("다시 입력하세요!: ");
                input = keyboard.nextLine();
            }


            Category category = null;
            switch (input) {
                case "1":
                    category = findCategory("전자제품");
                    while(true){
                        int id= category.productMenu();
                        if(id == 0)
                            break;
                        else if(id == -1)
                            continue;
                        else
                            addToCart(findProduct(id));
                    }
                    break;
                case "2":
                    category = findCategory("의류");
                    while(true){
                        int id= category.productMenu();
                        if(id == 0)
                            break;
                        else if(id == -1)
                            continue;
                        else
                            addToCart(findProduct(id));
                    }
                    break;
                case "3":
                    category = findCategory("식품");
                    while(true){
                        int id= category.productMenu();
                        if(id == 0)
                            break;
                        else if(id == -1)
                            continue;
                        else
                            addToCart(findProduct(id));
                    }
                    break;
                case "4":
                    if(admin.adminLogin() == 0)
                        break;
                    while(admin.adminMenu() == 1){}
                    break;
                case "5":
                    System.out.println("\n-| 고객 정보 |- ");
                    System.out.println("이름 : " + currentCustomer.getName());
                    System.out.println("이메일: " + currentCustomer.getEmail());
                    System.out.println("등급 : " + currentCustomer.getRank());
                    System.out.println("할인율 : " + currentCustomer.getRank().getDiscountRate() + "%");
                    System.out.println("총 사용 금액 : " + String.format("%,d원", currentCustomer.getTotalSpent()));
                    break;
                case "6":
                    cart.displayCart();
                    break;
                case "7":
                    boolean found = false;
                    cart.displayCart();
                    System.out.print("\n장바구니에서 제거할 상품명을 입력하세요: ");
                    input = keyboard.nextLine();
                    for(Category ct : categoryList){
                        for(Product p : ct.getProducts()){
                            if(p.getName().equals(input)){
                                found = true;
                                System.out.println(p.getName() + "을 제거하시겠습니까?" );
                                System.out.println("1. 삭제    2. 취소");

                                input = keyboard.nextLine();
                                while(!input.equals("1") && !input.equals("2")){
                                    System.out.print("다시 입력하세요: ");
                                    keyboard.nextLine();
                                }

                                if (input.equals("1")){
                                    removeFromCart(p);
                                    System.out.println("상품을 장바구니에서 제거했습니다. ");
                                }
                                else{
                                    System.out.println("상품 삭제를 취소했습니다.");
                                }
                                break;
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("상품을 찾지 못했습니다.");
                    }

                    break;
                case "8":
                    int originalCost = cart.orderCart(currentCustomer.getRank());
                    currentCustomer.rankUp(originalCost);
                    break;
                case "9":
                    cart.clearCart();
                    System.out.println("주문을 취소했습니다.");
                    break;
                case "0":
                    cart.clearCart(); //로그아웃 하면 장바구니 상품들 제거
                    return;
                default:
                    continue;
            }
        }

    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void addProductToCategory(Product newItem, String category){
        Category ct = findCategory(category);

        if (ct != null)
            ct.addProducts(newItem);
    }

    public void addToCart(Product product){
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인        2. 취소");

        String input = keyboard.nextLine();
        while (!input.equals("1") && !input.equals("2")) {
            System.out.print("다시 입력하세요! (1,2): ");
            input = keyboard.nextLine();
        }

        if(input.equals("1")){
            System.out.println("구매할 수량을 입력하세요:");
            int input2;
            int amount = product.getAmount();
            while(true){
                try{
                    input2 = Integer.parseInt(keyboard.nextLine());
                    while(input2<0){
                        System.out.print("다시 입력하세요!: ");
                        input2 = Integer.parseInt(keyboard.nextLine());
                    }
                    if(input2>amount) {
                        System.out.println("재고가 부족하여 구매할 수 없습니다.");
                        break;
                    }
                    else if(input2==0) {
                        System.out.println("상품을 추가하지 않았습니다.");
                        break;
                    }
                    cart.addItems(product, input2);
                    break;
                }
                catch(NumberFormatException e){
                    System.out.print("다시 입력하세요!: ");
                }
            }
        }
    }

    public void removeFromCart(Product p){
        cart.removeItems(p);
    }

    private Category findCategory(String categoryName) {
        for (Category ct : categoryList) {
            if (categoryName.equals(ct.getCategoryName())) {
                return ct;
            }
        }
        return null;
    }

    public Product selectFromProducts(String msg){
        System.out.println("\n[ " + msg + "전체 상품 ID 목록 ] ");
        for(Category ct : categoryList){
            for(Product p : ct.getProducts()){
                System.out.println(p.getId() + " - " + p.getName());
            }
        }

        System.out.print("\n상품 ID를 입력하세요: ");
        int id = admin.readPositiveInt();

        Product foundProduct = findProduct(id);
        if(findProduct(id) == null){
            System.out.println("상품을 찾지 못했습니다.");
            return null;
        }

        return foundProduct;
    }

    public Product findProduct(int id){
        for(Category ct : categoryList){
            for(Product p : ct.getProducts()){
                if(p.getId() == id)
                    return p;
            }
        }
        return null;
    }

    public void printAllProducts(){
        System.out.println("\n[ 전체 상품 현황 ] ");
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-10s | %-20s | %-5s%n", "ID", "상품명", "가격", "설명", "재고");
        System.out.println("----------------------------------------------------------------------------");

        for(Category ct : categoryList){
            for(Product p : ct.getProducts()){
                System.out.printf("| %-5d | %-10s | %s | %s | 재고: %d%n", p.getId(), p.getName(), p.getPrice(), p.getDescription(), p.getAmount());
            }
        }
    }

}
