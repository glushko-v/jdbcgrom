package UpdateData;

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

    public void increasePrice() {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            int response = statement.executeUpdate("update PRODUCT set PRICE = (PRICE + 100) where PRICE < 970 ");
            System.out.println(response);


        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }


    }

    public void changeDescription() {


        try (Connection connection = DriverManager.getConnection(Solution.DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {


            int response = statement.executeUpdate("update product set description = replace (substr(description, 1, instr(description, '.', -1)), description) where length(description)>100 and instr(description, '.') > 0");
            System.out.println(response);





        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }


    }

//    public String modifyString(String description) {
//
//        int indexOfPoint = description.lastIndexOf(".");
//
//        String subString = description.substring(indexOfPoint);
//
//        description = description.replaceAll(subString, " ");
//
//
//
//
//        return description;
//    }

}


