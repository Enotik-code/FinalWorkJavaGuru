package by.edabudet.persistence.dao.repositories;

import by.edabudet.bean.Category;
import by.edabudet.config.DatabaseConection;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class CategoryRepository implements SimpleService<Category> {

    private DatabaseConection databaseConnection = new DatabaseConection();

    static Logger log = Logger.getLogger(ProductRepository.class.getName());

    @Override
    public void save(Category obj) throws SQLException {

    }

    @Override
    public List<Category> findAll() throws SQLException {
        return null;
    }

    @Override
    public Category getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Category obj) throws SQLException {

    }
}
