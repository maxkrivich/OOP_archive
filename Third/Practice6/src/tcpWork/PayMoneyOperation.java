package tcpWork;

import tcpWork.CardOperation;

/**
 *
 * @author maxkrivich
 */
public class PayMoneyOperation extends CardOperation
{

    private long sn = 0l;
    private double money = 0.0;

    public PayMoneyOperation(long serial, double money)
    {
        this.sn = serial;
        this.money = money;
    }

    public PayMoneyOperation()
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
