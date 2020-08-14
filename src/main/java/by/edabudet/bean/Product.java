package by.edabudet.bean;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Length(min = 3, max = 32, message = "The name cannot be less than 3 characters or more than 32")
    @NotEmpty(message = "Please fill in the product name")
    private String name;

    @Column(name = "subcategory")
    private String subcategory;

    @Column(name = "idsubcategory")
    private int idSubcategory;

    @Column(name = "amount")
    private int amount;

    @Column(name = "start_price")
    @NotEmpty(message = "Product price must be greater than 0")
    private float starPrice;

    @Column(name = "discount")
    private float discount;

    @Column(name = "price")
    @NotEmpty(message = "Product price must be greater than 0")
    private float price;

    @Column(name = "idmanufacturer")
    private int idManufacturer;

    public Product(String name, Float price, String subcategory, Float discount, int id) {
        this.name = name;
        this.price = price;
        this.subcategory = subcategory;
        this.discount = discount;
        this.id = id;
    }

    public Product(String productName, Long price, Long discount, Long amount, int subcategoryId, int manufacturerId) {
        this.name = productName;
        this.starPrice = price;
        this.discount = discount;
        this.price = countingPrice(price, discount);
        this.amount = Math.toIntExact(amount);
        this.idManufacturer = manufacturerId;
        this.idSubcategory = subcategoryId;
    }

    public float countingPrice(float starPrice, float discount){
        return (starPrice - (starPrice * discount)/100);
    }
}
