package lesson4.hw1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private StorageDAO sd = new StorageDAO();
    private FileDAO fd = new FileDAO();

    public static final String DB_URL = "jdbc:oracle:thin:@gromcode.cfc1xaielreq.us-east-2.rds.amazonaws.com:1521:ORCL";
    public static final String USER = "main";
    public static final String PASS = "test123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }



    public File put(Storage storage, File file){



        return null;
    }

    public List<File> putAll(Storage storage, List<File>files){

        return null;
    }

    public void delete (Storage storage, File File){




    }

    public List<File> transferAll(Storage storageFrom, Storage storageTo){

        return null;
    }

    public File transferFile(Storage storageFrom, Storage storageTo, long id){

        //1. достаем файл из БД используя findById+++
        //2. точно также достаем storageFrom и storageTo+++
        //3. Проверяем storageTo на вместимость
        //4. меняем storage_id у файла в БД
        //5. меняем поле storage у файла
        //4 и 5 объединяем в одну транзакцию

        File file = fd.findById(id);
        Storage storFrom = sd.findById(storageFrom.getId());
        Storage storTo = sd.findById(storageTo.getId());






        return null;
    }


}
