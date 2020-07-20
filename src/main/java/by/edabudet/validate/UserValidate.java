package by.edabudet.validate;

import by.edabudet.authentication.bean.User;
import by.edabudet.strings.DataCondition;

public class UserValidate {


    private UserValidate(){}


    public static boolean validateFirstName(String firstName) {
        return (firstName != null && firstName.length() >= DataCondition.MIN_LENGTH_NAME &&
                firstName.length() <= DataCondition.MAX_LENGTH_NAME);
    }

    public static boolean validateUserName(String userName) {
        return (userName != null && userName.length() >= DataCondition.MIN_LENGTH_USER_NAME &&
                userName.length() <= DataCondition.MAX_LENGTH_USER_NAME);
    }

    public static boolean validateLastName(String lastName) {
        return (lastName != null && lastName.length() >= DataCondition.MIN_LENGTH_NAME &&
                lastName.length() <= DataCondition.MAX_LENGTH_NAME);
    }


    public static boolean validatePassword(String password) {
        return (password != null && password.length() >= DataCondition.MIN_LENGTH_PASSWORD);
    }

    public static boolean checkValidateDataUser(User user) {
        return  validateFirstName(user.getFirstName()) &&
                validateUserName(user.getUserName()) &&
                validateLastName(user.getLastName()) &&
                validatePassword(user.getPassword());
    }
}
