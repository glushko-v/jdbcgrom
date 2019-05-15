package lesson4.hw1;

public interface DAO<T> {

    public <T> T save(T t);

    public void delete(long id);

    public <T> T update(T t);

    public <T> T findById(long id);

}
