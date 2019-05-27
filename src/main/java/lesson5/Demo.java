package lesson5;

import org.hibernate.Session;

public class Demo {

    public static void main(String[] args) {


        Product product = new Product();
        product.setId(99);
        product.setName("folded table");
        product.setDescription("black/blue/black");
        product.setPrice(170);

        ProductRepository pr = new ProductRepository();

        pr.delete(99);




    }
}
