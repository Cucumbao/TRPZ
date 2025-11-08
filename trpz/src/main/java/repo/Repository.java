package repo;


import model.File;

public interface Repository<T> {
    File findById(Long id);
    void findAll();
    void save(T entity);
    T delete(Long id);
}