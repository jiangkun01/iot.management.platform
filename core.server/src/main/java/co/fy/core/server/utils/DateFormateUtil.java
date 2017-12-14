package co.fy.core.server.utils;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by joker on 7/5/17.
 */
public class DateFormateUtil {
    public static String FORMAT_TEMPLATE = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date){
        if(date==null){
            return null;
        }
        return new SimpleDateFormat(FORMAT_TEMPLATE).format(date);
    }

    public static Date parse(String dataString){
        if(StringUtils.isEmpty(dataString)){
            return null;
        }
        try {
            return new SimpleDateFormat(FORMAT_TEMPLATE).parse(dataString);
        } catch (ParseException e) {
            return null;
        }
    }
}
