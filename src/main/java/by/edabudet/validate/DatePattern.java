package by.edabudet.validate;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatePattern {

    public static Date getCurrentDate(){
        return new Date(System.currentTimeMillis());
    }
}
