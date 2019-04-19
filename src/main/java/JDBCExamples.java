import java.sql.*;

public class JDBCExamples {

    public static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";

    public static final String USER = "main";
    public static final String PASS = "test123456";


    public static void main(String[] args) {


/*        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {

            boolean res = statement.execute("INSERT INTO PRODUCT VALUES (2, 'toy111', 'for children', 60)");
            System.out.println(res);

            boolean res = statement.execute("DELETE FROM PRODUCT WHERE NAME = 'toy111'");
            System.out.println(res);

            int response = statement.executeUpdate("INSERT INTO PRODUCT VALUES (5, 'car', 'for children', 770)");
            System.out.println(response);

            int response = statement.executeUpdate("delete from PRODUCT where NAME = 'car'");
            System.out.println(response);




        } catch (SQLException se) {
            System.err.println("Something went wrong");
            se.printStackTrace();


        }*/

        NewSolution solution = new NewSolution();

        solution.changeDescription();



    }
}
