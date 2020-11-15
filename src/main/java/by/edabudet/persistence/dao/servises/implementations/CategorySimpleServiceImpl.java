package by.edabudet.persistence.dao.servises.implementations;

import by.edabudet.bean.Category;
import by.edabudet.config.DatabaseConection;
import by.edabudet.converters.ResultSetConverter;
import by.edabudet.persistence.dao.repositories.ProductRepository;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;
import by.edabudet.strings.SqlQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CategorySimpleServiceImpl implements SimpleService<Category> {

    private DatabaseConection databaseConnection = new DatabaseConection();
    static Logger log = Logger.getLogger(ProductRepository.class.getName());

    @Override
    public void save(Category obj) throws SQLException {

    }

    @Override
    public List<Category> findAll() throws SQLException {
        String query = SqlQuery.GET_CATEGORIES;
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListCategory(resultSet);
        }
    }

    @Override
    public Category getByName(String name) throws SQLException {
        String query = "select * from category where category.Descrition = '" + name + "'";
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToCategory(resultSet);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Category obj) throws SQLException {

    }
}
