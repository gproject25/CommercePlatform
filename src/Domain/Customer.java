package Domain;

public class Customer {
    private String name;
    private String email;
    private CustomerRank rank;
    private long totalSpent;


    public Customer(String name, String email, CustomerRank rank){
        this.name = name;
        this.email = email;
        this.rank = rank;
        this.totalSpent = 0;
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

    public void setRank(CustomerRank rank) {
        this.rank = rank;
    }
    public long getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(long totalSpent) {
        this.totalSpent = totalSpent;
    }
}
