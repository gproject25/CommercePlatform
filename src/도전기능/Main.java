package 도전기능;

public class Main {
    static void main(String[] args) {

        //전자제품
        Product product1 = new Product("Galaxy S25", "1,200,000원", "최신 안드로이드 스마트폰", 30);
        Product product2 = new Product("iPhone 16", "1,350,000원", "Apple의 최신 스마트폰", 30);
        Product product3 = new Product("MacBook Pro", "2,400,000원", "M3 칩셋이 탑재된 노트북", 30);
        Product product4 = new Product("AirPods Pro", "350,000원", "노이즈 캔슬링 무선 이어폰", 30);

        //의류
        Product product5 = new Product("오버핏 후드티", "59,000원", "편안한 오버핏 디자인의 후드티", 30);
        Product product6 = new Product("베이직 청바지", "69,000원", "데일리로 착용하기 좋은 청바지", 30);
        Product product7 = new Product("코트", "189,000원", "겨울철 따뜻하게 입을 수 있는 울 코트", 30);
        Product product8 = new Product("운동화", "129,000원", "가볍고 편안한 러닝용 운동화", 30);

        //식품
        Product product9 = new Product("신라면 5입", "4,500원", "얼큰하고 매콤한 국민 라면", 30);
        Product product10 = new Product("햇반 12개입", "14,900원", "간편하게 즐길 수 있는 즉석밥", 30);
        Product product11 = new Product("감귤 3kg", "19,900원", "달콤하고 신선한 제주 감귤", 30);
        Product product12 = new Product("한우 1kg", "59,000원", "고품질 국내산 한우", 30);

        CommerceSystem commerceSystem = new CommerceSystem();
        commerceSystem.addProductToCategory(product1,"전자제품");
        commerceSystem.addProductToCategory(product2, "전자제품");
        commerceSystem.addProductToCategory(product3, "전자제품");
        commerceSystem.addProductToCategory(product4, "전자제품");
        commerceSystem.addProductToCategory(product5, "의류");
        commerceSystem.addProductToCategory(product6, "의류");
        commerceSystem.addProductToCategory(product7, "의류");
        commerceSystem.addProductToCategory(product8, "의류");
        commerceSystem.addProductToCategory(product9, "식품");
        commerceSystem.addProductToCategory(product10, "식품");
        commerceSystem.addProductToCategory(product11, "식품");
        commerceSystem.addProductToCategory(product12, "식품");

//        Customer customer1 = new Customer("Steve", "steve232@gmail.com", "BRONZE");
//        Customer customer2 = new Customer("Tom", "tom10@daum.net", "SILVER");
//        Customer customer3 = new Customer("Kim", "kim7@naver.com", "GOLD");
//        Customer customer4 = new Customer("Park", "park23@gmail.com", "PLATINUM");

        commerceSystem.start();
    }
}