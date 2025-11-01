package repo;



public interface Repository<T> {
    void findById(Long id);
    void findAll();
    void save(T entity);
    void delete(Long id);
}