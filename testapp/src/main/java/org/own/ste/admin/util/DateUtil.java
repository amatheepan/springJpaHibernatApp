/**
 * 
 */
package org.own.ste.admin.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.own.ste.admin.constants.InfraConstants;



/**
 * Utility for Date Management.
 * 
 * @author Karthik.
 *
 */
public class DateUtil {

    /**
     * Overriden private constructor.
     */
    private DateUtil() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Fetches the current date with the time set as midnight.
     * 
     * @return Date - Thanks Stackoverflow.
     */
    public static Date getCurrentDate() {
        Date date = new Date(); // timestamp now
        Calendar cal = Calendar.getInstance(); // get calendar instance
        cal.setTime(date); // set cal to date
        cal.set(Calendar.HOUR_OF_DAY, 0); // set hour to midnight
        cal.set(Calendar.MINUTE, 0); // set minute in hour
        cal.set(Calendar.SECOND, 0); // set second in minute
        cal.set(Calendar.MILLISECOND, 0); // set millis in second
        return cal.getTime();
    }

    /**
     * Fetches the current time.
     * 
     * @return Date - The current time.
     */
    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }
    
    /**
     * Format the date to a string.
     * 
     * @param date Date - The date.
     * @param format String - The format.
     * @return String - The formatted date.
     */
    public static String formatDate(Date date,String format) {
        if (date != null) {
            DateFormat formatter = new SimpleDateFormat(
                    format);
            return formatter.format(date);
        }
        return ""; //$NON-NLS-1$
    }

    /**
     * Validates if the format is that of a timestamp YYYYMMDDHHMMSS.
     * 
     * 
     * @param timestamp String - The time.
     * @return boolean - True if matches the format.
     */
    public static boolean validateTimestampFormat(String timestamp) {
        boolean retVal=true;
        try {
            DateFormat formatter=new SimpleDateFormat(InfraConstants.STD_TIMESTAMP_FMT);
            formatter.format(timestamp);
        }
        catch(Throwable t) {
            retVal=false;
        }
        return retVal;
    }


}
