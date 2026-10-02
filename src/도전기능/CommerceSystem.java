package 도전기능;

import 도전기능.Cart.Cart;
import 도전기능.Customer.Customer;
import 도전기능.Screens.Screens;
import 도전기능.ProductManagement.Category;
import 도전기능.ProductManagement.Product;

import java.util.ArrayList;
import java.util.List;

public class CommerceSystem {
    //private Scanner keyboard;
    private Cart cart;;
    private Admin admin; //시스템마다 하나의 admin 가정
    private int loginStatus;        //현재 사용자 login 상태 확인

    //private List<Category> categoryList;
    //private List<Customer> customers;
//    private CustomersSetting customersSetting;
//
//    private Customer currentCustomer;
    private Screens screen;

    public CommerceSystem(){
//        Category electronics = new Category("전자제품");
//        Category clothing =  new Category("의류");
//        Category food = new Category("식품");
//
//        categoryList = new ArrayList<>();
//        categoryList.add(electronics);
//        categoryList.add(clothing);
//        categoryList.add(food);
        loginStatus = 0;    //login 안한 생태로 초기화

        //keyboard = new Scanner(System.in);
        cart = new Cart();
        admin = new Admin(this);
        //customers = new ArrayList<>();
//        customersSetting = new CustomersSetting();

        screen = new Screens();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void start(){

        while(true) {
            // 1. Login
            if(loginStatus == 0) //login 안한 상태
                loginStatus = screen.login();
            if(loginStatus == -1)   //프로그램 종료
                return;
            //loginStatus ==1 login 성공

            // 2. 메인 화면
            int cartEmpty = 0;
            if(!cart.isEmpty())
                cartEmpty = 1;   //장바구니가 비어있지 않으면 추가메뉴 출력
            String input = screen.mainScreen(cartEmpty);

            //3. 카테고리 화면
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
                    screen.printUserInfo();
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
                    loginStatus = 0;
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
