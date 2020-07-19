package by.edabudet.model;

import lombok.Data;

/**
 * Created by itvdn on 31.07.18.
 */
@Data
public class User {


    private String name;
    private String surname;
    private Integer years;

    public User() {

    }

    public User(String name, String surname, Integer years) {
        this.name = name;
        this.surname = surname;
        this.years = years;
    }

    public static String getUserName() {
        throw new UnsupportedOperationException("No implementation!!!");
    }

    public static void waitInQueue() {
        try {
            Thread.sleep(30L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
