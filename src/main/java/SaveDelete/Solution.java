package SaveDelete;

import java.sql.*;


public class Solution {
    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";


    public Product saveProduct() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {
//
//
//            int response = statement.executeUpdate("INSERT INTO PRODUCT VALUES (999, 'toy', 'for children', 60)");
//
//            System.out.println(response);
//
//
//        } catch (SQLException se) {
//            System.err.println("Something went wrong");
//            se.printStackTrace();
//
//
//        }

        try (ResultSet rs = statement.executeQuery("INSERT INTO PRODUCT VALUES (999, 'toy', 'for children', 60)")) {
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int price = rs.getInt(4);

                Product product = new Product(id, name, description, price);

                return product;

            }

        }


    } catch (SQLException se) {
        System.err.println("Something went wrong");
        se.printStackTrace();


    }


    return null;

    }

    public void deleteProducts() {

        try (Connection connection = DriverManager.getConnection(Solution.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME != 'toy'");
            System.out.println(response);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }

    public void deleteProductsByPrice() {

        try (Connection connection = DriverManager.getConnection(Solution.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE PRICE < 100");
            System.out.println(response);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }


}
