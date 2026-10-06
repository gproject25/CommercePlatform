package UI;

import Domain.Category;
import Service.Database;
import Service.UserInput;

public class CategoryScreen {
    private Database database;

    public CategoryScreen(UserInput userInput, Database database){
        this.database = database;
    }

    public Category selectCategory(String input){
        Category category;
        switch(input){
            case "1":
                category = database.findCategory("전자제품");
                break;
            case "2":
                category = database.findCategory("의류");
                break;
            case "3":
                category = database.findCategory("식품");
                break;
            default:
                return null;
        }

        //카테고리 선택 완료
        return category;
    }

}
