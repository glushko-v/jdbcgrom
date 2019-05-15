package lesson4;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDemo {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String[] args) {
        Product product = new Product(55, "test", "testDescription", 99);
        Product product1 = new Product(66, "test111", "test Description", 59);
        Product product2 = new Product(77, "test112", "test Description", 12);
        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);

        save(products);


    }

    public static void save(List<Product> products) {

        try (Connection connection = getConnection()){

            saveList(products, connection);



        } catch (SQLException e) {
            System.err.println("Something went wrong");
            e.printStackTrace();
        }


    }

    private static void saveList(List<Product> products, Connection connection) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement("insert into PRODUCT values (?, ?, ?, ?)")) {

            connection.setAutoCommit(false);

            for (Product product : products) {
                statement.setLong(1, product.getId());
                statement.setString(2, product.getName());
                statement.setString(3, product.getDescription());
                statement.setInt(4, product.getPrice());

                int res = statement.executeUpdate();
                System.out.println("save was finished with result " + res);
            }


            connection.commit();

        } catch (SQLException se) {
            connection.rollback();
            throw se;
        }

    }

}
