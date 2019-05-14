package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";


    public List<Product> findProductsByPrice(int price, int delta) {

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM PRODUCT WHERE PRICE <= ? AND PRICE >= ?")) {


            List<Product> products = new ArrayList<>();

            ps.setInt(1, (price + delta));
            ps.setInt(2, (price - delta));


            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                products.add(product);


            }

            System.out.println(products);
            return products;


        } catch (SQLException e) {
            System.err.println("Something wrong");
            e.printStackTrace();
        }


        return null;
    }

    public List<Product> findProductsByName(String word) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from PRODUCT where NAME = ?")) {

            List<Product> products = new ArrayList<>();

            ps.setString(1, word);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Product product = new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                products.add(product);
            }

            System.out.println(products);
            return products;

        } catch (SQLException e) {
            System.err.println("Something wrong");
            e.printStackTrace();
        }


        return null;
    }

    public List<Product> findProductsWithEmptyDescription(){

        try(Connection conn = getConnection();
        Statement st = conn.createStatement()){

            ResultSet rs = st.executeQuery("select * from PRODUCT");

            List<Product> products = new ArrayList<>();

            while(rs.next()) {
                if (rs.getString(3) == null) {
                    Product product = new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getInt(4));
                    products.add(product);
                }
            }

            System.out.println(products);
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
