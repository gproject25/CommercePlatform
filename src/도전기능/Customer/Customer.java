package 도전기능.Customer;

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

    public long getTotalSpent() {
        return totalSpent;
    }

    public void rankUp(int totalCost){
        totalSpent += totalCost;
        System.out.println("\n현재 고객 등급: " + rank);
        System.out.println("--------------------------");

        if(rank == CustomerRank.BRONZE){
            if(totalSpent >= 10000000) {
                rank = CustomerRank.PLATINUM;
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 10,000,000원을 달성하여 'BRONZE'에서 'PLATINUM' 등급으로 승급되었습니다! ");
            }
            else if(totalSpent >= 5000000) {
                rank = CustomerRank.GOLD;
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 5,000,000원을 달성하여 'BRONZE'에서 'GOLD' 등급으로 승급되었습니다! ");
            }
            else if(totalSpent >= 1000000) {
                rank = CustomerRank.SILVER;
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 1,000,000원을 달성하여 'BRONZE'에서 'SILVER' 등급으로 승급되었습니다! ");
            }
            else{
                System.out.println("구매해주셔서 감사합니다!");
                System.out.println("다음 등급인 'SILVER'까지 " + String.format("%,d원", 1000000 - totalSpent) + "의 추가 구매가 필요합니다.");
            }
        }
        else if(rank == CustomerRank.SILVER){
            if(totalSpent >= 10000000) {
                rank = CustomerRank.PLATINUM;
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 10,000,000원을 달성하여 'SILVER'에서 'PLATINUM' 등급으로 승급되었습니다! ");
            }
            else if(totalSpent >= 5000000) {
                rank = CustomerRank.GOLD;
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 5,000,000원을 달성하여 'SILVER'에서 'GOLD' 등급으로 승급되었습니다! ");
            }
            else{
                System.out.println("구매해주셔서 감사합니다!");
                System.out.println("다음 등급인 'GOLD'까지 " + String.format("%,d원", 5000000 - totalSpent) + "의 추가 구매가 필요합니다.");
            }
        }
        else if(rank == CustomerRank.GOLD){
            if(totalSpent >= 10000000) {
                rank = CustomerRank.PLATINUM;
                System.out.println("\uD83C\uDF89 축하합니다! \uD83C\uDF89");
                System.out.println("누적 구매 금액이 10,000,000원을 달성하여 'GOLD'에서 'PLATINUM' 등급으로 승급되었습니다! ");
            }
            else{
                System.out.println("구매해주셔서 감사합니다!");
                System.out.println("다음 등급인 'PLATINUM'까지 " + String.format("%,d원", 10000000 - totalSpent) + "의 추가 구매가 필요합니다.");
            }
        }
    }
}
