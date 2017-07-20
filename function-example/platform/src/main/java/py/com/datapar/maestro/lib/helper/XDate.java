package py.com.datapar.maestro.lib.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class XDate {
    // todo refatorar para privado e adicionar métodos get nas constantes
    public static String PATTERN_DATE = "dd/MM/yyyy";
    public static String PATTERN_DATE_HOUR = "dd/MM/yyyy HH:mm:ss";

    public static Calendar addDays(Integer numDadys) {

        Calendar calendario = Calendar.getInstance();

        calendario.add(Calendar.DATE, numDadys);

        return calendario;
    }

    public static String formatDate(Calendar calendar) {
        return getSimpleDateFormat(PATTERN_DATE).format(calendar.getTime());
    }

    public static String formatDateHour(Calendar calendar) {
        return getSimpleDateFormat(PATTERN_DATE_HOUR).format(calendar.getTime());
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat(PATTERN_DATE).format(date);
    }

    public static String formatDateHour(Date date) {
        return new SimpleDateFormat(PATTERN_DATE_HOUR).format(date);
    }

    public static SimpleDateFormat getSimpleDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar calendar = null;

        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }

        return calendar;
    }

    public static Date calendarToDate(Calendar calendar) {
        Date dataFormat = null;

        if (calendar != null)
            dataFormat = calendar.getTime();

        return dataFormat;
    }


}
