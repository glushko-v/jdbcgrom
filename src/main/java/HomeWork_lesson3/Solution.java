package HomeWork_lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public void testSavePerformance() {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into TEST_SPEED values (?, ?, ?)")) {

            long start = System.currentTimeMillis();


            for (int i = 0; i < 1000; i++) {

                ps.setLong(1, i);
                ps.setString(2, "test");
                ps.setInt(3, i);
                ps.executeQuery();

            }

            long finish = System.currentTimeMillis();

            long totalTime = finish - start;

            System.out.println("Total time " + totalTime + " millis");

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Total time 135878 millis


    }

    public void testDeletePerformance() {
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

            long start = System.currentTimeMillis();

            ResultSet res = st.executeQuery("delete from TEST_SPEED");

            long finish = System.currentTimeMillis();

            long totalTime = finish - start;


            System.out.println("Total time " + totalTime + " millis");
//            Total time 224 millis


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testSelectPerformance() {
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {


            long start = System.currentTimeMillis();

            ResultSet res = st.executeQuery("select * from TEST_SPEED");


            long finish = System.currentTimeMillis();

            long totalTime = finish - start;


//            Total time 197 millis
            System.out.println("Total time " + totalTime + " millis");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testSelectByIdPerformance(){

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from TEST_SPEED where id = ?")) {



            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                ps.setLong(1, i);
                ps.executeQuery();
                System.out.println("read " + i);
            }




            long finish = System.currentTimeMillis();

            long totalTime = finish - start;


//            Total time 136599 millis
            System.out.println("Total time " + totalTime + " millis");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void testDeleteByIdPerformance(){

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("delete from TEST_SPEED where id = ?")) {



            long start = System.currentTimeMillis();

            for (int i = 0; i < 1000; i++) {
                ps.setLong(1, i);
                ps.executeQuery();
                System.out.println("deleted " + i);
            }




            long finish = System.currentTimeMillis();

            long totalTime = finish - start;


//            Total time 135511  millis
            System.out.println("Total time " + totalTime + " millis");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
