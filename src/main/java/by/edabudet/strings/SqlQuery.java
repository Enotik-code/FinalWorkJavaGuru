package by.edabudet.strings;

public class SqlQuery {

    private SqlQuery(){
    }

    public static final String GET_PRODUCTS = "select product.name ,product.price,  subcategory.Description, product.dicsount from product join subcategory on product.idsubcategory = subcategory.id;";
    public static final String GET_MANUFACTURERS = "select * from country;";
    public static final String GET_SUBCATEGORIES = "select * from subcategory";
    public static final String GET_SUBCATEGORIES_BY_NAME = "select * from subcategory where Description = '";
    public static final String GET_SUBCATEGORIES_PART_1 = "select * from subcategory where id < 44";
    public static final String GET_SUBCATEGORIES_PART_2 = "select * from subcategory where id > 43";
    public static final String GET_CATEGORIES = "select * from category";
    public static final String GET_PRODUCT_BY_NAME = "select product.name ,product.price,  subcategory.Description, product.dicsount from product join subcategory on product.idsubcategory = subcategory.id where product.name = '";
}
