package jdbc.lesson4.hw1;

import java.sql.*;

public class StorageDAO implements DAO<Storage> {

    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "main";
    public static final String PASS = "test123456";


    @Override
    public Storage save(Storage storage) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into Storages values(?, ?, ?, ?)")) {


            ps.setLong(1, storage.getId());
            ps.setString(2, convertFormats(storage.getFormatsSupported()));
            ps.setString(3, storage.getStorageCountry());
            ps.setLong(4, storage.getStorageMaxSize());

            int res = ps.executeUpdate();

            System.out.println("Save finished with result " + res);


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return storage;
    }

    @Override
    public void delete(long id) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("delete from Storages where id = ?")) {

            ps.setLong(1, id);


            idCheck(ps, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Storage update(Storage storage) {

        try(Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("update Storages set storageCountry = ?, storageMaxSize = ? where id = ?")){

            ps.setString(1, storage.getStorageCountry());
            ps.setLong(2, storage.getStorageMaxSize());
            ps.setLong(3, storage.getId());

            idCheck(ps, storage.getId());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return storage;
    }

//    @Override
    public Storage findById(long id) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("select * from Storages where id = ?")) {

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {


                String[] formats = rs.getString(2).split(",");
                Storage storage = new Storage(rs.getLong(1), formats, rs.getString(3), rs.getLong(4));

                System.out.println(storage.toString());
                idCheck(ps, id);


                return storage;

            }




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private String convertFormats(String[] formatsSupported) {

        StringBuffer stringBuffer = new StringBuffer();

        for (String format : formatsSupported) {
            stringBuffer.append(format + ", ");
        }
        stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(", "));
        String formats = stringBuffer.toString();


        return formats;
    }

    private void idCheck(PreparedStatement ps, long id) throws SQLException {
        int res = ps.executeUpdate();
        if (res == 0) System.err.println("There's no ID " + id);
        else System.out.println("Process finished with result " + res);
    }


}
