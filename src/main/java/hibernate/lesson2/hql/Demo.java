package hibernate.lesson2.hql;


public class Demo {
    public static void main(String[] args) {
        Product product2 = new Product();
        product2.setName("table");
        product2.setDescription("blue");
        product2.setPrice(82);

        Product product3 = new Product();
        product3.setName("dishwasher");
        product3.setDescription("Bosh");
        product3.setPrice(92);

        Product product4 = new Product();
        product4.setName("TV");
        product4.setDescription("LED 32' Sony");
        product4.setPrice(3392);


        ProductDAO pd = new ProductDAO();


        pd.findByPriceSortedDesc(50, 45);


    }
}
