package by.edabudet.persistence.dao.repositories;

import by.edabudet.bean.Subcategory;
import by.edabudet.config.DatabaseConection;
import by.edabudet.converters.ResultSetConverter;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;
import by.edabudet.strings.SqlQuery;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SubcategoryRepository implements SimpleService<Subcategory> {

    @Override
    public void save(Subcategory obj) throws SQLException {

    }

    private DatabaseConection databaseConnection = new DatabaseConection();

    @Override
    public List<Subcategory> findAll() throws SQLException {
        String query = SqlQuery.GET_SUBCATEGORIES;
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListSubcategory(resultSet);
        }
    }

    @Override
    public Subcategory getByName(String name) throws SQLException {
        String query = SqlQuery.GET_SUBCATEGORIES_BY_NAME + name + "'";
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return  ResultSetConverter.convertToSubcategory(resultSet);
        }
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Subcategory obj) throws SQLException {

    }

    public List<Subcategory> findAllFirstPart() throws SQLException {
        String query = SqlQuery.GET_SUBCATEGORIES_PART_1;
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListSubcategory(resultSet);
        }
    }

    public List<Subcategory> findAllSecondPart() throws SQLException {
        String query = SqlQuery.GET_SUBCATEGORIES_PART_2;
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListSubcategory(resultSet);
        }
    }
}