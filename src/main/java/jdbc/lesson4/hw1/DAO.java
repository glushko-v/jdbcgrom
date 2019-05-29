package jdbc.lesson4.hw1;

import java.sql.SQLException;

public interface DAO<T> {

     T save(T t);

     void delete(long id);

     T update(T t) throws SQLException;

      T findById(long id);



}
