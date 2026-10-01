package 도전기능;

public class Customer {
    private String name;
    private String email;
    private CustomerRank rank;

    public Customer(String name, String email, CustomerRank rank){
        this.name = name;
        this.email = email;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public CustomerRank getRank() {
        return rank;
    }
}
