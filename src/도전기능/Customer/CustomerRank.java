package 도전기능.Customer;

public enum CustomerRank {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    private final int discountRate;

    CustomerRank(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountRate() {
        return discountRate;
    }
}
