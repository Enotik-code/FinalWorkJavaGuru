package by.edabudet.authentication.bean;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String password;
    @Column(name = "idPosition")
    private int idPosition;
    @Column(name = "idCountry")
    private int idCountry;
    private float account;
    @Column(name = "dateOfBirthday")
    private Date dateOfBirthday;
    @Column(name = "dateOfCreated")
    private Date dateOfCreated;
    @Column(name = "dateOfModified")
    private Date dateOfModified;
}

