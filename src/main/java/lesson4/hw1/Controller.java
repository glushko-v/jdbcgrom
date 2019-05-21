package lesson4.hw1;

import java.sql.*;
import java.util.List;

public class Controller {


    private FileDAO fd = new FileDAO();

    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "main";
    public static final String PASS = "test123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public File put(Storage storage, File file) throws Exception {


        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ?")) {

            conn.setAutoCommit(false);

            if (checkStorageSize(storage, file) && !isFileInStorage(storage, file)) {

                file.setStorage(storage);
                ps.setLong(1, storage.getId());
                ps.setLong(2, file.getId());
                int res = ps.executeUpdate();

                System.out.println("File " + file.getName() + "." + file.getFormat() + " successfully been put to storage " + storage.getId() + " with a result " + res);

                conn.commit();
            } else throw new Exception("Something wrong. Please check your file and storage");


        } catch (SQLException e) {
            e.printStackTrace();
            getConnection().rollback();
        }

        System.out.println(file.toString());
        return file;
    }

    public List<File> putAll(Storage storage, List<File> files) {

        return null;
    }

    public void delete(Storage storage, File file) {

        //1. удалить storage_id из БД
        //2. storage = null для файла

        file.setStorage(null);


    }

    public List<File> transferAll(Storage storageFrom, Storage storageTo) {

        return null;
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {


        File file = fd.findById(id);


        if (!checkStorageSize(storageTo, file))
            throw new FileSizeException("Not enough space for file " + file.getName() + "." + file.getFormat() + " in storage " + storageTo.getId());
        if (!isFileInStorage(storageFrom, file))
            throw new Exception("There's no file " + file.getName() + "." + file.getFormat() + " in storage " + storageFrom.getId());
        else {
            fd.update(file);
            file.setStorage(storageTo);

        }


        return file;
    }


    private boolean checkStorageSize(Storage storage, File file) throws Exception {

        if (storage != null && file != null)
            return ((storage.getStorageMaxSize() - getFilesSize(storage)) > file.getSize());
        else if (storage == null) throw new Exception("There's no storage " + storage.getId());
        else throw new Exception("Something wrong");
    }

    private long getFilesSize(Storage storage) {

        long filesTotalSize = 0;


        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from Files where storage_id = ?")) {

            ps.setLong(1, storage.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                filesTotalSize += rs.getLong(4);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return filesTotalSize;
    }

    private boolean isFileInStorage(Storage storage, File file) throws SQLException {

        long databaseID = 0;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT STORAGE_ID FROM FILES WHERE ID = ?")) {

            conn.setAutoCommit(false);

            ps.setLong(1, file.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                databaseID = rs.getLong(1);
            }

            conn.commit();


        } catch (SQLException e) {
            e.printStackTrace();
            getConnection().rollback();
        }


        return (databaseID == storage.getId() && file.getStorage().getId() == storage.getId());

    }


}
