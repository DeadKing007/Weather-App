package dead.weatherapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Rahul Garg on 24/10/16.
 */

public class Utility {

    public static String getTodaysDate(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM");
        String todayDate = dateFormat.format(calendar.getTime());
        return todayDate;
    }


}

