package lesson4.hw1;

public interface DAO<T> {

     T save(T t);

     void delete(long id);

     T update(T t);

      T findById(long id);



}
