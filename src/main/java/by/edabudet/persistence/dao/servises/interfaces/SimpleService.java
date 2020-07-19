
package by.edabudet.persistence.dao.servises.interfaces;

import by.edabudet.bean.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface SimpleService<T> {
    void save(T obj) throws SQLException;
    List<T> findAll() throws SQLException;
    T getByName(String name) throws SQLException;
    void delete(Long id) throws SQLException;
    void update(T obj) throws SQLException;
}


