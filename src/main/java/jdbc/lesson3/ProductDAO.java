package jdbc.lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public Product save(Product product) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into PRODUCT values (?, ?, ?, ?)")) {

            statement.setLong(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPrice());

            int res = statement.executeUpdate();

            System.out.println("save was finished with result " + res);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }


        return product;
    }

    public Product update(Product product) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("update PRODUCT set NAME = ?, DESCRIPTION = ?, PRICE = ? where ID = ? ")) {




            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            statement.setLong(4, product.getId());



            int res = statement.executeUpdate();

            System.out.println("update was finished with result " + res);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }


        return product;
    }

    public List<Product> getProducts() {


        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {


            ResultSet res = statement.executeQuery("select * from PRODUCT");
            List<Product> products = new ArrayList<>();

            while (res.next()) {

                Product product = new Product(res.getLong(1), res.getString(2), res.getString(3), res.getInt(4));
                products.add(product);

            }


            return products;


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

        return null;

    }

    public void delete(long id) {

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from PRODUCT where id = ?")) {

            statement.setLong(1, id);

            int res = statement.executeUpdate();

            System.out.println(res);


        } catch (SQLException e){
            System.err.println("Something went wrong");
            e.printStackTrace();
        }



    }



}
