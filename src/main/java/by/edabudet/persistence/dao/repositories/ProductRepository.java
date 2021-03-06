package by.edabudet.persistence.dao.repositories;

import by.edabudet.bean.Product;
import by.edabudet.config.DatabaseConection;
import by.edabudet.converters.ResultSetConverter;
import by.edabudet.persistence.dao.servises.interfaces.SimpleService;

import by.edabudet.strings.SqlQuery;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
@Transactional
public class ProductRepository implements SimpleService<Product> {
    private DatabaseConection databaseConnection = new DatabaseConection();

    static Logger log = Logger.getLogger(ProductRepository.class.getName());

    @Override
    public void save(Product obj) throws SQLException {
        String query = SqlQuery.SAVE_PRODUCTS +"'" + obj.getName() + "'," + obj.getIdSubcategory() + "," +
                obj.getAmount() + "," + obj.getStarPrice() + "," + obj.getDiscount() + "," +
                obj.getPrice() + "," + obj.getIdManufacturer() + ")";
        try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.execute();
        }
    }
     public void deleteProductById(Integer id) throws SQLException{
        String query = "delete from product where id = " + id;
         try (PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
             preparedStatement.execute();
         }
     }

    @Override
    public List<Product> findAll() throws SQLException {
        String query = "SELECT * from product";
        try (ResultSet resultSet = databaseConnection.getDbConnection().createStatement().executeQuery(query)) {
            return ResultSetConverter.convertToListProduct(resultSet);
        }
    }

    public Product findByProductName(String productName) throws SQLException {
        String query = "SELECT * FROM product WHERE product_name = ?";
        try(PreparedStatement preparedStatement = databaseConnection.getDbConnection().prepareStatement(query)) {
            preparedStatement.setString(1, productName);
            return ResultSetConverter.convertToProduct(preparedStatement.executeQuery());
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
        String query = "DELETE FROM product WHERE id=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setLong(1, id);
            statement.execute(query);
        }
    }

    @Override
    public void update(Product product) throws SQLException {
        String query = " UPDATE product SET product_name=?, price=?, description=?,  discount=?, id_category=? " +
                "WHERE id_product=?";
        try(PreparedStatement statement = databaseConnection.getDbConnection().prepareStatement(query)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getSubcategory());
            statement.setInt(4, product.getAmount());
            statement.setFloat(5, product.getStarPrice());
            statement.setFloat(6, product.getDiscount());
            statement.setFloat(7, product.getPrice());
            statement.setInt(8, product.getIdManufacturer());
            statement.executeUpdate();
        }
    }

    public List<Product> findProductByCategory(String categoryName) throws SQLException {
       //todo findProductByCategory
        return null;
    }

    public void changeDiscountForCategories(Long idCategory, Float discount) throws SQLException {
       //todo changeDiscountForCategories
    }
}
