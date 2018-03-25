package controller.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormValidation {

    public static boolean checkDate(String dateToCheck, String format) {
        boolean isValid = false;
        if(dateToCheck != null) {

            SimpleDateFormat formatter = new SimpleDateFormat(format);
            formatter.setLenient(false);
            try  {
                formatter.parse(dateToCheck);
                isValid = true;
            } catch (ParseException e) {
                e.printStackTrace();
                isValid = false;
            }
        }
        return isValid;
    }
}
