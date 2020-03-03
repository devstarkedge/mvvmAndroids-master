package com.app.fitv1.ProjectUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

// WishDropMVP


public class DateHelper
{
    //
    public static final String DateFormat="yyyy-MM-dd HH:mm:ss";
    public static SimpleDateFormat serverDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleDateFormat onlyHoursFormat = new SimpleDateFormat("hh:mm a");

    public static SimpleDateFormat DOB     = new SimpleDateFormat("dd-MMM-yyyy");
    public static SimpleDateFormat COMMENT = new SimpleDateFormat("MMMM dd, hh:mm a");

    public static SimpleDateFormat CHAT = new SimpleDateFormat("MMMM dd, hh:mm a");

    public static SimpleDateFormat DAY   = new SimpleDateFormat("dd");
    public static SimpleDateFormat MONTH = new SimpleDateFormat("MMMM");

    private static DateHelper ourInstance = new DateHelper();

    public static DateHelper getInstance()
    {
        return ourInstance;
    }

    private DateHelper()
    {
    }

    public String getFormattedDateOfBirth(Date date)
    {
        return DOB.format(date);
    }

    public String getDateString(String date, SimpleDateFormat simeSimpleDateFormat)
    {
        try {
            Date date1 = serverDate.parse(date);
            return simeSimpleDateFormat.format(date1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String convertDateToRequiredFormat(String stringDate, String requiredDateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy hh:mm a");
        SimpleDateFormat requiredFormat = new SimpleDateFormat(requiredDateFormat);
        try {
            return requiredFormat.format(sdf.parse(stringDate.replace("T", " ")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertFromServerDateToRequiredDate(String stringDate, String requiredDateFormat) {
         TimeZone timeZone = TimeZone.getTimeZone("UTC");
        serverDate.setTimeZone(timeZone);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(requiredDateFormat);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());

        try {

            Date myDate = serverDate.parse(stringDate);

            String formattedDate = simpleDateFormat.format(myDate);

            return formattedDate;
           // return requiredFormat.format(serverDate.parse(stringDate.replace("T", " ")));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCurrentDateString()
    {
        try {
            return serverDate.format(new Date(System.currentTimeMillis()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public Date getFormattedDateOfBirthString(String dob)
    {
        try {
            return DOB.parse(dob);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date(System.currentTimeMillis());
    }

    public String getCurrentDateForChat()
    {
        try {
            return serverDate.format(new Date(System.currentTimeMillis()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDateForComment(String date)
    {
        try {
            return COMMENT.format(serverDate.parse(date));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDateForNotification(String date)
    {
        try {
            return COMMENT.format(serverDate.parse(date));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getDateForChat(String date)
    {
        try {
            return CHAT.format(serverDate.parse(date));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getCompleteDateForEvent(String date)
    {
        try {
            return CHAT.format(serverDate.parse(date));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getDayForEvent(String date)
    {
        try {
            return DAY.format(serverDate.parse(date));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public String getMonthForEvent(String date)
    {
        try {
            return MONTH.format(serverDate.parse(date));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
