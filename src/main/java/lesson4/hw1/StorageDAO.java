package lesson4.hw1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StorageDAO implements DAO<Storage> {

    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "main";
    public static final String PASS = "test123456";





    @Override
    public Storage save(Storage storage) {

        try(Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("insert into Storages values(?, ?, ?, ?)")){



            ps.setLong(1, storage.getId());
            ps.setString(2, convertFormats(storage.getFormatsSupported()));
            ps.setString(3, storage.getStorageCountry());
            ps.setLong(4, storage.getStorageMaxSize());

            int res = ps.executeUpdate();

            System.out.println("Sace finished with result " + res);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return storage;
    }

    @Override
    public void delete(long id) {

        

    }

    @Override
    public Storage update(Storage storage) {
        return null;
    }

    @Override
    public Storage findById(long id) {
        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private String convertFormats (String[] formatsSupported){

        StringBuffer stringBuffer = new StringBuffer();

        for (String format: formatsSupported) {
            stringBuffer.append(format + ", ");
        }
        stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(", "));
        String formats = stringBuffer.toString();



        return formats;
    }
}
