package by.edabudet.persistence.dao.repositories;

import by.edabudet.bean.Manufacturer;
import by.edabudet.config.DatabaseConection;
import by.edabudet.converters.ResultSetConverter;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;
import by.edabudet.strings.SqlQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional
public class ManufacturerRepository implements SimpleService<Manufacturer> {

    private DatabaseConection databaseConnection = new DatabaseConection();
    static Logger log = Logger.getLogger(ProductRepository.class.getName());

    @Override
    public void save(Manufacturer obj) throws SQLException {

    }

    @Override
    public List<Manufacturer> findAll() throws SQLException {
        String query = SqlQuery.GET_MANUFACTURERS;
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListManufacturer(resultSet);
        }
    }

    @Override
    public Manufacturer getByName(String name) throws SQLException {
        return null;
    }

    @Override
    public void delete(Long id) throws SQLException {

    }

    @Override
    public void update(Manufacturer obj) throws SQLException {

    }
}
