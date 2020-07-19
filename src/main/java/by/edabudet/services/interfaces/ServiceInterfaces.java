package by.edabudet.services.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface ServiceInterfaces<T> {
    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    T getById(Long id) throws SQLException;
    void remove(Long id) throws SQLException;
    List<T> findAll() throws SQLException;
}
