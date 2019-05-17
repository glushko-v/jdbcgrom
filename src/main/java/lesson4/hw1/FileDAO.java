package lesson4.hw1;

import java.sql.*;

public class FileDAO implements DAO<File> {

    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "main";
    public static final String PASS = "test123456";

    @Override
    public File save(File file) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("insert into Files values(?, ?, ?, ?, ?)")) {


            ps.setLong(1, file.getId());
            ps.setString(2, file.getName());
            ps.setString(3, file.getFormat());
            ps.setLong(4, file.getSize());
            ps.setLong(5, file.getStorage().getId());



            int res = ps.executeUpdate();

            System.out.println("Save finished with result " + res);


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return file;
    }

    @Override
    public void delete(long id) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("delete from Files where id = ?")) {

            ps.setLong(1, id);


            idCheck(ps, id);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public File update(File file) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("update Files set fileName = ?, fileFormat = ?, fileSize = ?, storage_id = ? where id = ?")) {

            ps.setString(1, file.getName());
            ps.setString(2, file.getFormat());
            ps.setLong(3, file.getSize());
            ps.setLong(4, file.getStorage().getId());

            idCheck(ps, file.getId());


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return file;
    }

//    @Override
    public File findById(long id) {

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("select files.id, files.fileName, files.fileFormat, files.fileSize, storages.id,\n" +
                     "storages.formatsSupported, storages.storageCountry, storages.storageMaxSize\n" +
                     "from files\n" +
                     "join storages on files.storage_id = storages.id")) {


            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String[] formats = rs.getString(6).split(",");
                Storage storage = new Storage(rs.getLong(5), formats, rs.getString(7), rs.getLong(8));
                File file = new File(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), storage);

                System.out.println(file.toString());
                idCheck(ps, id);
                return file;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void idCheck(PreparedStatement ps, long id) throws SQLException {
        int res = ps.executeUpdate();
        if (res == 0) System.err.println("There's no ID " + id);
        else System.out.println("Process finished with result " + res);
    }
}
