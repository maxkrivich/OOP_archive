package tcpWork;

import java.io.*;
import tcpWork.*;
import java.net.*;

/**
 *
 * @author maxkrivich
 */
public class ClientHandler extends Thread
{

    private boolean isRun;
    private MetroCardBank mcb;
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ClientHandler(MetroCardBank mcb, Socket socket)
    {
        this.mcb = mcb;
        this.socket = socket;
        isRun = true;
        try
        {
            inStream = new ObjectInputStream(socket.getInputStream());
            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex)
        {
            ex.printStackTrace();
            System.err.println("Error:\t" + ex);
        }
    }

    @Override
    public void run()
    {
        synchronized (mcb)
        {
            System.out.println("Client Handler Started for: " + socket);
            while (isRun)
            {
                Object obj;
                try
                {
                    obj = inStream.readObject();
                    processOperation(obj);
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex)
                {
                    ex.printStackTrace();
                }
            }
            try
            {
                System.out.println("Client Handler Stopped for: " + socket);
                socket.close();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    private void processOperation(Object obj) throws IOException, ClassNotFoundException
    {
        if (obj instanceof StopOperation)
            finish();
        else if (obj instanceof AddMetroCardOperation)
            addCard(obj);
        else if (obj instanceof AddMoneyOperation)
            addMoney(obj);
        else if (obj instanceof PayMoneyOperation)
            payMoney(obj);
        else if (obj instanceof RemoveCardOperation)
            removeCard(obj);
        else if (obj instanceof ShowBalanceOperation)
            showBalance(obj);
        else
            error();

    }

    private void finish() throws IOException
    {
        isRun = false;
        outStream.writeObject("Finish Work " + socket);
        outStream.flush();
    }

    private void error() throws IOException
    {
        outStream.writeObject("Bad Operation");
        outStream.flush();
    }

    private void addCard(Object obj) throws IOException, ClassNotFoundException
    {
        mcb.addMetroCard(((AddMetroCardOperation) obj).getCard());
        outStream.writeObject("Card Added");
        outStream.flush();
    }

    private void addMoney(Object obj) throws IOException, ClassNotFoundException
    {
        AddMoneyOperation oper = (AddMoneyOperation) obj;
        boolean res = mcb.addMoney(oper.getSerialNumber(), oper.getMoney());
        if (res)
            outStream.writeObject("Balance Added");
        else
            outStream.writeObject("Cannot Balance Added");
        outStream.flush();
    }

    private void payMoney(Object obj) throws IOException, ClassNotFoundException
    {
        PayMoneyOperation oper = (PayMoneyOperation) obj;
        boolean res = mcb.getMoney(oper.getSerialNumber(), oper.getMoney());
        if (res)
            outStream.writeObject("Money Payed");
        else
            outStream.writeObject("Cannot Pay Money");
        outStream.flush();
    }

    private void removeCard(Object obj) throws IOException, ClassNotFoundException
    {
        RemoveCardOperation oper = (RemoveCardOperation) obj;
        boolean res = mcb.removeCard(oper.getSerialNumber());
        if (res)
            outStream.writeObject("Metro Card Succesfully Remove: " + oper.getSerialNumber());
        else
            outStream.writeObject("Cannot Remove Card" + oper.getSerialNumber());
        outStream.flush();

    }

    private void showBalance(Object obj) throws IOException, ClassNotFoundException
    {
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int index = mcb.findMetroCard(op.getSerialNumber());
        if (index != -1)
            outStream.writeObject("Card : " + op.getSerialNumber() + " balance: " + mcb.getBank().get(index).getBalance());
        else
            outStream.writeObject("Cannot Show Balance for Card: " + op.getSerialNumber());
        outStream.flush();
    }

}
