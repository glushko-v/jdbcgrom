package lesson4.hw1;

public interface DAO<T> {

    public T save(T t);

    public void delete(long id);

    public T update(T t);

    public T findById(long id);

}
