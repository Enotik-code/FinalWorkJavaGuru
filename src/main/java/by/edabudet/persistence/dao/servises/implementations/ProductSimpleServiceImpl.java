package by.edabudet.persistence.dao.servises.implementations;

import by.edabudet.bean.Product;
import by.edabudet.config.DatabaseConection;
import by.edabudet.converters.ResultSetConverter;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;
import by.edabudet.strings.SqlQuery;
import org.springframework.stereotype.Service;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ProductSimpleServiceImpl implements SimpleService<Product> {

    private DatabaseConection databaseConnection = new DatabaseConection();


    @Override
    public void save(Product obj) throws SQLException {
    }

    @Override
    public List<Product> findAll() throws SQLException{
        String query = SqlQuery.GET_PRODUCTS;
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListProduct(resultSet);
        }
    }

    @Override
    public Product getByName(String name) throws SQLException {
        String query = SqlQuery.GET_PRODUCT_BY_NAME + name + "'";
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToProduct(resultSet);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Product obj) throws SQLException {

    }

    public List<Product> findAllBySubcategory(String subcategory) throws SQLException {
        return null;
    }

}
