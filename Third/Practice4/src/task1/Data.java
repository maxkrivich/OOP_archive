package task1;

import java.text.*;
import java.util.*;
import lib.Point2D;

/**
 *
 * @author maxkrivich
 */
public class Data
{

    private Point2D p;
    private String d;
    private static final String PATTERN_DATE = "dd.MM.yyyy";

    public Data()
    {
        p = new Point2D();
        d = "";
    }

    public void setDate(String date)
    {
        d = date;
//        if (!date.isEmpty())
//        {
//            try
//            {
//                d = new SimpleDateFormat(Data.PATTERN_DATE).parse(date);
//            } catch (ParseException ex)
//            {
//            }
//        }
    }

    public String getDate()
    {
        return d;
    }

    public void setX(double x)
    {
        p.setX(x);
    }

    public double getX()
    {
        return p.getX();
    }

    public void setY(double y)
    {
        p.setY(y);
    }

    public double getY()
    {
        return p.getY();
    }

    @Override
    public String toString()
    {
        return String.format("%s\t%s", p, d);
    }

}
