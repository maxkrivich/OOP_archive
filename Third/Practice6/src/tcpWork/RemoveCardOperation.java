package tcpWork;

import tcpWork.CardOperation;

/**
 *
 * @author maxkrivich
 */
public class RemoveCardOperation extends CardOperation
{

    private long sn;

    public RemoveCardOperation()
    {
        this(0l);
    }

    public RemoveCardOperation(long sn)
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
