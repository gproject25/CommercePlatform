package 필수기능;

public class Main {
    static void main(String[] args) {
        Product product1 = new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 1);
        Product product2 = new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 1);
        Product product3 = new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 1);
        Product product4 = new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 1);

        CommerceSystem commerceSystem = new CommerceSystem();
        commerceSystem.addProductToCategory(product1,"전자제품");
        commerceSystem.addProductToCategory(product2, "전자제품");
        commerceSystem.addProductToCategory(product3, "전자제품");
        commerceSystem.addProductToCategory(product4, "전자제품");

        commerceSystem.start();
    }
}
