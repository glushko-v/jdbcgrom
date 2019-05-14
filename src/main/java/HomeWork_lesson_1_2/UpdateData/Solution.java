package HomeWork_lesson_1_2.UpdateData;

import java.sql.*;

public class Solution {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";



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


