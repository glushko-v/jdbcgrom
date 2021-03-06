package jdbc.lesson1_2;

import java.sql.*;


public class JDBCFirstStep {

    //1. Database driver+
    //2. create connection+
    //3. create query+
    //4. execute query+
    //5. work with result+
    //6. close all connections

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";


    public static void main(String[] args) {


        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.err.println("Class " + JDBC_DRIVER + " not found");
                return;
            }


            try (ResultSet rs = statement.executeQuery("SELECT * FROM ORDER1 WHERE PRICE < 150")) {
                while (rs.next()) {
                    long id = rs.getLong(1);
                    String productName = rs.getString(2);
                    int price = rs.getInt(3);
                    Date dateOrdered = rs.getDate(4);
                    Date dateConfirmed = rs.getDate(5);
                    Order order = new Order(id, productName, price, dateOrdered, dateConfirmed);

                    System.out.println(order);

                }

            }


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }
    }


}
