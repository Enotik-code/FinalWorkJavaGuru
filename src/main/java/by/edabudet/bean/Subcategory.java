package by.edabudet.bean;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subcategory")
public class Subcategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "IdCategory")
    @NotEmpty(message = "Please choose the category")
    private Integer IdCategory;

    @Column(name = "Description")
    @Length(min = 5, max = 30, message = "The name cannot be less than 3 characters or more than 32")
    @NotEmpty(message = "Please fill in the name of the subcategory")
    private String nameSubcategory;


}