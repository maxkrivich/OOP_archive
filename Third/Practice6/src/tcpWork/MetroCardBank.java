package tcpWork;

import java.util.*;

/**
 *
 * @author maxkrivich
 */
public class MetroCardBank
{

    private List<MetroCard> bank;
    private boolean isChange;

    public MetroCardBank()
    {
        bank = new ArrayList();
        isChange = false;
    }

    public List<MetroCard> getBank()
    {
        return bank;
    }

    public void setBank(List<MetroCard> bank)
    {
        this.bank = bank;
    }

    public int findMetroCard(long sn)
    {
        if (isChange)
        {
            Collections.sort(bank);
            isChange = false;
        }
        int low = 0, high = bank.size() - 1;
        while (low <= high)
        {
            int mid = (low + high) / 2;
            if (bank.get(mid).getSerialNumber() > sn)
                high = mid - 1;
            else if (bank.get(mid).getSerialNumber() < sn)
                low = mid + 1;
            else
                return mid; // find
        }
        return -1; // not find
    }

    public void addMetroCard(MetroCard mc)
    {
        bank.add(mc);
        isChange = true;
    }

    public boolean removeCard(long sn)
    {
        int index = findMetroCard(sn);

        if (index == -1)
            return false;

        bank.remove(index);
//        isChange = true;
        return true;

    }

    public boolean addMoney(long sn, double money)
    {
        int index = findMetroCard(sn);

        if (index == -1)
            return false;
        
        bank.get(index).addMoney(money);
        return true;
    }

    public boolean getMoney(long sn, double money)
    {
        int index = findMetroCard(sn);

        if (index == -1)
            return false;
        
        return bank.get(index).payMoney(money);
    }

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard mc : bank)
            buf.append("\n\n" + mc);
        return buf.toString();
    }

}
