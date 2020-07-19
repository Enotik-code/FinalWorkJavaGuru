package by.edabudet.bean;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@Entity
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

    public Product(int id, String name, String subcategory, int amount, float starPrice, float discount, float price, int idManufacturer){
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
        this.amount = amount;
        this.starPrice = starPrice;
        this.discount = discount;
        this.price = countingPrice(starPrice, discount);
        this.idManufacturer = idManufacturer;
    }


    public Product(String name, Float price, String subcategory, Float discount) {
        this.name = name;
        this.price = price;
        this.subcategory = subcategory;
        this.discount = discount;
    }

    public float countingPrice(float starPrice, float discount){
        return (starPrice * discount)/100;
    }
}
