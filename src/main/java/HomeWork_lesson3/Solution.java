package HomeWork_lesson3;

import java.sql.*;

public class Solution {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";

    public void testSavePerformance() {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into TEST_SPEED values (?, ?, ?)")) {

            long start = System.currentTimeMillis();


            for (int i = 0; i < 1000; i++) {

                ps.setLong(1, i);
                ps.setString(2, "test");
                ps.setInt(3, i + 100);
                ps.executeQuery();

            }

            long finish = System.currentTimeMillis();

            long totalTime = finish - start;

            System.out.println("Total time " + totalTime + " millis");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void testDeletePerformance(){
        try(Connection conn = getConnection();
       Statement st = conn.createStatement()){

            long start = System.currentTimeMillis();

            ResultSet res = st.executeQuery("delete from TEST_SPEED");

            long finish = System.currentTimeMillis();

            long totalTime = finish - start;

            System.out.println("Finished with result " + res);
            System.out.println("Total time " + totalTime + " millis");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testSelectPerformance() {
        try(Connection conn = getConnection();
        Statement st = conn.createStatement()){

            long start = System.currentTimeMillis();

            ResultSet res = st.executeQuery("select * from TEST_SPEED");

           

            long finish = System.currentTimeMillis();

            long totalTime = finish - start;

//            System.out.println("Finished with result " + res);
            System.out.println("Total time " + totalTime + " millis");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
