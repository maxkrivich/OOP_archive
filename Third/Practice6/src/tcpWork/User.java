package tcpWork;

import java.io.*;
import java.text.*;
import java.util.*;

/**
 *
 * @author maxkrivich
 */
enum Sex
{
    male, female
}

public class User implements Serializable
{

    private String fName;
    private String lName;
    private Sex sex;
    private Date birthday;
    private static final DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat dateParser = dateFormatter;

    public User()
    {
        this("01.01.2016", "Max", "Krivich", "male");
    }

    public User(String date, String fName, String lName, String sex)
    {
        setfName(fName);
        setlName(lName);
        setSex(sex);
        setDate(date);
    }

    public String getfName()
    {
        return fName;
    }

    public void setfName(String fName)
    {
        this.fName = fName;
    }

    public String getlName()
    {
        return lName;
    }

    public void setlName(String lName)
    {
        this.lName = lName;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        setSex(Sex.valueOf(sex));
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public String getDate()
    {
        return dateFormatter.format(birthday);
    }

    public void setDate(String date)
    {
        try
        {
            this.birthday = dateParser.parse(date);
        } catch (ParseException ex)
        {
            System.err.println("Error:\t" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return String.format("NAME: %s %s SEX: %s BIRTHDAY: %s", getfName(), getlName(), sex, getDate());
    }

}
