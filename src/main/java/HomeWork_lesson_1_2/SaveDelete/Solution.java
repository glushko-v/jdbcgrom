package HomeWork_lesson_1_2.SaveDelete;

import java.sql.*;


public class Solution {
    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";


    public void saveProduct() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            int response = statement.executeUpdate("INSERT INTO PRODUCT VALUES (999, 'toy', 'for children', 60)");

            System.out.println(response);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }


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
