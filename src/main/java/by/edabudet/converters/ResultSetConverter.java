package by.edabudet.converters;

import by.edabudet.bean.Category;
import by.edabudet.bean.Manufacturer;
import by.edabudet.bean.Product;
import by.edabudet.bean.Subcategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    private ResultSetConverter() {
    }

    public static List<Product> convertToListProduct(ResultSet resultSet) throws SQLException {
        List<Product> list = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            String subcategory = resultSet.getString(3);
            Float discount = resultSet.getFloat(4);
            Float price = resultSet.getFloat(2);
            list.add(new Product(name, price, subcategory, discount));
        }
        return list;
    }

    public static List<Subcategory> convertToListSubcategory(ResultSet resultSet) throws SQLException {
        List<Subcategory> list = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(3);
            Integer idCategory = resultSet.getInt(2);
            list.add(new Subcategory(id, idCategory, name));
        }
        return list;
    }

    public static List<Category> convertToListCategory(ResultSet resultSet) throws SQLException {
        List<Category> list = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            list.add(new Category(id, name));
        }
        return list;
    }



    public static List<Manufacturer> convertToListManufacturer(ResultSet resultSet) throws SQLException {
        List<Manufacturer> list = new ArrayList<>();
        while (resultSet.next()) {
            Integer id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            list.add(new Manufacturer(id, name));
        }
        return list;
    }

    public static Product convertToProduct(ResultSet resultSet) throws SQLException {
        Product product = null;
        while (resultSet.next()) {
            product = Product.builder()
                    .name(resultSet.getString(1))
                    .subcategory(resultSet.getString(3))
                    .discount(resultSet.getFloat(4))
                    .price(resultSet.getFloat(2))
                    .build();
        }
        return product;
    }

    public static Subcategory convertToSubcategory(ResultSet resultSet) throws SQLException{
        Subcategory subcategory = null;
        while(resultSet.next()){
            subcategory = Subcategory.builder()
                    .nameSubcategory(resultSet.getString(3))
                    .IdCategory(resultSet.getInt(2))
                    .id(resultSet.getInt(1))
                    .build();
        }
        return subcategory;
    }
}