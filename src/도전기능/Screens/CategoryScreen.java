package 도전기능.Screens;

import 도전기능.ProductManagement.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryScreen {

    private List<Category> categoryList;

    public CategoryScreen(){
        categoryList = new ArrayList<>();

        Category electronics = new Category("전자제품");
        Category clothing =  new Category("의류");
        Category food = new Category("식품");
        categoryList.add(electronics);
        categoryList.add(clothing);
        categoryList.add(food);
    }



}
