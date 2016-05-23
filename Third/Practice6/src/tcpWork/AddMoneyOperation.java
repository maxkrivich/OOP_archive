package tcpWork;

/**
 *
 * @author maxkrivich
 */
public class AddMoneyOperation extends CardOperation
{

    private long sn = 0l;
    private double money = 0.0;

    public AddMoneyOperation(long serial, double money)
    {
        this.sn = serial;
        this.money = money;
    }

    public AddMoneyOperation()
    {
        this(0l, .0);
    }

    public double getMoney()
    {
        return money;
    }

    public void setMoney(double money)
    {
        this.money = money;
    }

    public long getSerialNumber()
    {
        return sn;
    }

    public void setSerialNumber(long serial)
    {
        this.sn = serial;
    }

}
