package UI;

import Service.Database;
import Service.UserInput;

public abstract class Screen {

    protected final UserInput userInput;
    protected final Database database;

    protected Screen(UserInput userInput, Database database) {
        this.userInput = userInput;
        this.database = database;
    }
}
