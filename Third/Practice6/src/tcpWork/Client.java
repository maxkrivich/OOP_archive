package tcpWork;

import java.io.*;
import tcpWork.*;
import java.net.*;

/**
 *
 * @author maxkrivich
 */
public class Client
{

    private int port;
    private String server;
    private Socket socket;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public Client(String server, int port)
    {
        this.port = port;
        this.server = server;
        try
        {
            socket = new Socket();
            socket.connect(new InetSocketAddress(this.server, this.port), 1000);
//            assert socket.getOutputStream() == null;
            outStream = new ObjectOutputStream(socket.getOutputStream());
//            assert outStream == null;
            inStream = new ObjectInputStream(socket.getInputStream());
        } catch (InterruptedIOException ex)
        {
            ex.printStackTrace();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void finish()
    {
        try
        {
//            assert outStream == null;
            outStream.writeObject(new StopOperation());
            outStream.flush();
            System.out.println(inStream.readObject());
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    public void applyOperation(CardOperation op)
    {
        try
        {
//            assert outStream == null;
            outStream.writeObject(op);
            outStream.flush();
            System.out.println(inStream.readObject());
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        Client cl = new Client("127.0.0.1", 27015);
        AddMetroCardOperation op = new AddMetroCardOperation();
        op.getCard().setUser(new User("25.12.1968", "Petr", "Petrov", "male"));
        op.getCard().setSerialNumber(1l);
        op.getCard().setCollege("KhNU");
        op.getCard().setBalance(25);
        cl.applyOperation(op);
        cl.finish();
//
        cl = new Client("127.0.0.1", 27015);
        cl.applyOperation(new AddMoneyOperation(1l, 100));
        cl.applyOperation(new ShowBalanceOperation(1l));
        cl.finish();
    }
}
