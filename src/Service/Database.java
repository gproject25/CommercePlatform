package Service;

import Domain.Category;
import Domain.Customer;
import Domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Database {
    private List<List<Product>> productList;
    private List<Category> categoryList;
    private List<Customer> customerList;
    private List<Product> deleteProductFromCart;

    private static int categoryIndex = 0;

    public Database(){
        productList = new ArrayList<>();
        categoryList = new ArrayList<>();
        customerList = new ArrayList<>();
        deleteProductFromCart = new ArrayList<>();
    }

    // ------------- Customers ------------------
    public int getCustomersSize(){
        return customerList.size();
    }

    public Customer getCustomer(int index){
        return customerList.get(index);
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }

    public void printCustomers(){
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            System.out.printf("  %d. %-12s | 등급: %s%n", i + 1, customer.getName(), customer.getRank());
        }
    }


    // ------------- Domain.Category ------------------
    public Category findCategory(String categoryName) {
        for (Category ct : categoryList) {
            if (categoryName.equals(ct.getCategoryName())) {
                return ct;
            }
        }
        return null;
    }

    public Category findCategoryByIndex(int index){
        return categoryList.get(index);
    }

    public void printCategoryList(){
        for(int i=0; i<categoryList.size(); i++){
            System.out.println((i+1) + ". " + categoryList.get(i).getCategoryName());
        }
    }

    public int categorySize(Category category){
        int index = category.getDatabaseIndex();
        return productList.get(index).size();
    }

    public boolean checkDuplicate(Category category, String productName){
        int index = category.getDatabaseIndex();
        List<Product> products = productList.get(index);

        for(Product p : products){
            if(p.getName().equals(productName))
                return true;
        }
        return false;
    }

    public int categoryListSize(){
        return categoryList.size();
    }

    public void createCategory(String name){
        Category category = new Category(name,categoryIndex++);
        productList.add(new ArrayList<>());
        categoryList.add(category);
    }

    // ------------- Products ------------------

    public void createProducts(Product product, String categoryName){
        Category category = findCategory(categoryName);
        int index = category.getDatabaseIndex();

        productList.get(index).add(product);
    }

    public void removeProduct(Product product){
        for(List<Product> products : productList){
            for(Product p : products){
                if(p.getId() == product.getId()){
                    products.remove(p);
                    deleteProductFromCart.add(p);
                    System.out.println("상품이 성공적으로 삭제되었습니다!");
                    return;
                }
            }
        }
    }

    //관리자 모드 exit시 카트에도 있는 상품 제거
    public List<Product> scheduleDeleteFromCart(){
        return deleteProductFromCart;
    }

    public int deleteFromCartSize(){
        return deleteProductFromCart.size();
    }

    public void clearDeleteFromCart(){
        deleteProductFromCart.clear();
    }

    public Product findProduct(String name){
        for(List<Product> pList : productList){
            for(Product p : pList){
                if(p.getName().equals(name))
                    return p;
            }
        }
        return null;
    }

    public Product findProductByID(int id){
        for(List<Product> pList : productList){
            for(Product p : pList){
                if(p.getId() == id)
                    return p;
            }
        }
        return null;
    }

    public void printAllProducts(int idOnly){
        if(idOnly == 0){    //전체 출력
            System.out.println("\n[ 전체 상품 현황 ] ");
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("| %-5s | %-10s | %-10s | %-20s | %-5s%n", "ID", "상품명", "가격", "설명", "재고");
            System.out.println("----------------------------------------------------------------------------");

            for(List<Product> products : productList){
                for( Product p : products) {
                    System.out.printf("| %-5d | %-10s | %s | %s | 재고: %d%n", p.getId(), p.getName(), p.getPrice(), p.getDescription(), p.getAmount());
                }
            }
        }
        else{   //ID + 이름만 출력
            for(List<Product> products : productList) {
                for (Product p : products) {
                    System.out.println(p.getId() + " - " + p.getName());
                }
            }
        }
    }


    // 도전 과제 lambda thread 방식 조회
    public List<Product> printAllProductsCategory(Category category) {
        List<Product> products = productList.get(category.getDatabaseIndex());

        System.out.println("\n[ 전체 상품 ]");
        IntStream.range(0, products.size()).forEach(i -> {
            System.out.print((i + 1) + ". ");
            if (products.get(i).getAmount() == 0) {
                System.out.print("(품절) ");
            }
            products.get(i).printProduct();
        });
        return products;
    }

    public List<Product> printLessThan(Category category){
        List<Product> temp = new ArrayList<>();
        List<Product> products = productList.get(category.getDatabaseIndex());

        System.out.println("\n[ 100만원 이하 상품 ]");
        products.stream().filter(product -> product.getIntPrice() <= 1000000).forEach(temp::add);

        IntStream.range(0, temp.size())
                .forEach(i -> {
                    System.out.print((i+1) + ". ");
                    if(temp.get(i).getAmount() == 0)
                        System.out.print("(품절) ");
                    temp.get(i).printProduct();
                });
        return temp;
    }

    public List<Product> printGreaterThan(Category category){
        List<Product> temp = new ArrayList<>();
        List<Product> products = productList.get(category.getDatabaseIndex());

        System.out.println("\n[ 100만원 초과 상품 ]");
        products.stream().filter(product -> product.getIntPrice() > 1000000).forEach(temp::add);

        IntStream.range(0, temp.size())
                .forEach(i -> {
                    System.out.print((i+1) + ". ");
                    if(temp.get(i).getAmount() == 0)
                        System.out.print("(품절) ");
                    temp.get(i).printProduct();
                });
        return temp;
    }
}
