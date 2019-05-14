package lesson3;

public class Demo {
    public static void main(String[] args) {

        Product product = new Product(10, "test", "testDescription", 99);
        Product product1 = new Product(10, "test111", "test Description", 199);

//        ProductDAO productDAO = new ProductDAO();
//        productDAO.save(product);
//        System.out.println(productDAO.getProducts());
//        productDAO.delete(10);
//        productDAO.update(product1);

        Solution solution = new Solution();

//        solution.findProductsByName("toy");
        solution.findProductsWithEmptyDescription();

    }
}
