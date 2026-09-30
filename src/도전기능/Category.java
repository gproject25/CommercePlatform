package 도전기능;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private List<Product> products;
    private String categoryName;

    public Category(String name){
        this.categoryName = name;
        this.products = new ArrayList<>();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void productMenu() {
        System.out.println("\n[ " + getCategoryName() +  " 카테고리 ]");

        for (int i = 0; i < products.size(); i++) {
            Product iproduct = products.get(i);
            System.out.printf("%d. ", i + 1);

            if(iproduct.getAmount() == 0)
                System.out.print("(품절) ");

            System.out.printf("%-15s | %10s | %s | 재고: %d%n", iproduct.getName(), iproduct.getPrice(), iproduct.getDescription(), iproduct.getAmount());
        }
    }

    public void addProducts(Product newitem){
        products.add(newitem);
    }

    public int getSize(){
        return products.size();
    }

    public Product selectProduct(int index){
        Product p = products.get(index);
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
}
