package tcpWork;

import java.io.Serializable;

/**
 *
 * @author maxkrivich
 */
public class MetroCard implements Comparable<MetroCard>, Serializable
{

    private long serialNumber;
    private User user;
    private String college;
    private double balance;

    public MetroCard()
    {
        this(1l, new User(), "V. N. Karazin Kharkiv National University", 100.0f);
    }

    public MetroCard(long serialNumber, User user, String college, double balance)
    {
        this.serialNumber = serialNumber;
        this.user = user;
        this.college = college;
        this.balance = balance;
    }

    public long getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getCollege()
    {
        return college;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public double getBalance()
    {
        return balance;
    }

    public void setBalance(double balans)
    {
        this.balance = balans;
    }

    public void addMoney(double sum)
    {
        balance += sum;
    }

    public boolean payMoney(double sum)
    {
        if (balance - sum >= 0)
        {
            balance -= sum;
            return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        return String.format("No: %d\nUser: %s\nCollege: %s\nBalance: %f", serialNumber, user, college, balance);
    }

    @Override
    public int compareTo(MetroCard o)
    {
        return Long.compare(serialNumber, o.serialNumber);
    }

}
