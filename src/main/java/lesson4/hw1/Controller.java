package lesson4.hw1;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<File> putAll(Storage storage, List<File> files) throws Exception {


        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE FILES SET STORAGE_ID = ?")) {

            conn.setAutoCommit(false);

            ps.setLong(1, storage.getId());

            if (checkFilesSize(files, storage)) {

                int res = ps.executeUpdate();

                for (File file : files) {
                    if (!isFileInStorage(storage, file)) {
                        file.setStorage(storage);
                        System.out.println("File " + file.getName() + "." + file.getFormat() + " put to storage " + storage.getId());
                    } else {
                        throw new Exception("File " + file.getName() + "." + file.getFormat() + " is already in storage " + storage.getId() + ". Put failed");

                    }
                }


                conn.commit();

            } else conn.rollback();


        } catch (SQLException e) {
            e.printStackTrace();
            getConnection().rollback();
        }


        return files;
    }

    public void delete(Storage storage, File file) throws Exception {


        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE FILES SET STORAGE_ID = ? WHERE ID = ?")) {

            conn.setAutoCommit(false);

            if (isFileInStorage(storage, file)) {

                ps.setObject(1, null);
                ps.setLong(2, file.getId());
                file.setStorage(null);
                int res = ps.executeUpdate();
                conn.commit();
                System.out.println("File " + file.getName() + "." + file.getFormat() + " deleted from storage " + storage.getId() + " with result " + res);
                System.out.println(file.toString());

            }

        } catch (SQLException e) {
            e.printStackTrace();
            getConnection().rollback();
        }


    }

    public List<File> transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        List<File> files = getFiles(storageFrom);


        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE FILES SET STORAGE_ID = \n" +
                     "(SELECT ID FROM STORAGES WHERE ID = ?)\n" +
                     "WHERE FILES.STORAGE_ID = ?")) {


            conn.setAutoCommit(false);

            if (checkFilesSize(files, storageTo)) {
                ps.setLong(1, storageTo.getId());
                ps.setLong(2, storageFrom.getId());
                int res = ps.executeUpdate();
                for (File file : files) {

                    file.setStorage(storageTo);
                    System.out.println("File " + file.getName() + "." + file.getFormat() + " transferred to storage " + storageTo.getId());

                }


                conn.commit();

            } else {
                System.err.println("Not enough space in storage " + storageTo.getId());
                conn.rollback();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            getConnection().rollback();
        }

        return files;
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {


        File file = fd.findById(id);


        if (!checkStorageSize(storageTo, file))
            throw new FileSizeException("Not enough space for file " + file.getName() + "." + file.getFormat() + " in storage " + storageTo.getId());
        if (!isFileInStorage(storageFrom, file))
            throw new Exception("There's no file " + file.getName() + "." + file.getFormat() + " in storage " + storageFrom.getId());
        else {

            file.setStorage(storageTo);
            fd.update(file);


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

    boolean isFileInStorage(Storage storage, File file) throws Exception {

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


        if (storage == null) return false;
        if (file == null) return false;

        else return (databaseID == storage.getId() && file.getStorage().getId() == storage.getId());

    }

    private boolean checkFilesSize(List<File> files, Storage storage) throws Exception {

        long filesTotalSize = 0;

        for (File file : files) {
            filesTotalSize += file.getSize();

        }

        if (storage != null)
            return ((storage.getStorageMaxSize() - getFilesSize(storage) > filesTotalSize));
        else throw new Exception("Something wrong");


    }

    List<File> getFiles(Storage storage) {

        List<File> files = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM FILES WHERE STORAGE_ID = ?")) {

            ps.setLong(1, storage.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                File file = new File(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getLong(4), storage);
                files.add(file);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return files;
    }


}
