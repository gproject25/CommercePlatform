package 필수기능.ProductManagement;

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
            System.out.printf("%d. %-15s | %10s | %s%n", i + 1, iproduct.getName(), iproduct.getPrice(), iproduct.getDescription());
        }
    }

    public void addProducts(Product newitem){
        products.add(newitem);
    }

    public int getSize(){
        return products.size();
    }

    public void selectProduct(int index){
        Product p = products.get(index);
        System.out.println("선택한 상품: " + p.getName() + " | " + p.getPrice() + " | " + p.getDescription() + " | 재고: " + p.getAmount());
    }
}
