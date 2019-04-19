import java.sql.*;


public class Solution {
    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";

    class Product {
        private long id;
        private String name;
        private String description;
        private int price;

         Product(long id, String name, String description, int price) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
        }

        @Override
        public String toString() {
            return id + " " + name + " " + description + " " + price;
        }
    }

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

        try (Connection connection = DriverManager.getConnection(JDBCFirstStep.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE NAME != 'toy'");
            System.out.println(response);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }

    public void deleteProductsByPrice() {

        try (Connection connection = DriverManager.getConnection(JDBCFirstStep.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            int response = statement.executeUpdate("DELETE FROM PRODUCT WHERE PRICE < 100");
            System.out.println(response);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }

    public void getAllProducts() {



        try (Connection connection = DriverManager.getConnection(JDBCFirstStep.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            ResultSet rs = statement.executeQuery("SELECT * FROM PRODUCT");

            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int price = rs.getInt(4);


                Product product = new Product(id, name, description, price);

                System.out.println(product);
            }


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }

    public void getProductsByPrice() {

        try (Connection connection = DriverManager.getConnection(JDBCFirstStep.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            ResultSet rs = statement.executeQuery("SELECT * FROM PRODUCT WHERE PRICE < 100");

            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int price = rs.getInt(4);


                Product product = new Product(id, name, description, price);

                System.out.println(product);
            }


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }

    public void getProductsByDescription(){

        try (Connection connection = DriverManager.getConnection(JDBCFirstStep.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            ResultSet rs = statement.executeQuery("SELECT * FROM PRODUCT");

            while (rs.next()) {
                if (rs.getString(3).length()> 50) {
                    long id = rs.getLong(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);
                    int price = rs.getInt(4);


                    Product product = new Product(id, name, description, price);

                    System.out.println(product);

                }
            }


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }

    }



}
