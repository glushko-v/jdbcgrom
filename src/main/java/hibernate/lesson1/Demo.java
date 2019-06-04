package hibernate.lesson1;

public class Demo {

    public static void main(String[] args) {


        Product product = new Product();
        product.setId(99);
        product.setName("folded table");
        product.setDescription("black/blue");
        product.setPrice(270);

        ProductRepository pr = new ProductRepository();

        pr.update(product);




    }
}
