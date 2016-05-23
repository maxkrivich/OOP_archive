package tcpWork;

import tcpWork.CardOperation;

/**
 *
 * @author maxkrivich
 */
public class ShowBalanceOperation extends CardOperation
{

    private long sn;

    public ShowBalanceOperation()
    {
        this(0l);
    }

    public ShowBalanceOperation(long sn)
    {
        this.sn = sn;
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
