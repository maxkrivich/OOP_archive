package tcpWork;

import tcpWork.ClientHandler;
import tcpWork.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author maxkrivich
 */
public class MetroServer implements Runnable
{

    private MetroCardBank mcb;
    private ServerSocket ss;
    private int port = -1;
    private boolean isRun;

    public MetroServer(int port)
    {
        this.port = port;
        mcb = new MetroCardBank();
        isRun = true;
    }

    @Override
    public void run()
    {
        try
        {
            ss = new ServerSocket(port);
            System.out.println("Metro Server started");
            while (true)
            {
                System.out.println("New Client Waiting...");
                Socket s = ss.accept();
                System.out.println("New client: " + s);
                ClientHandler ch = new ClientHandler(mcb, s);
                ch.start();
            }

        } catch (IOException ex)
        {
            ex.printStackTrace();
        } finally
        {
            try
            {
                ss.close();
                System.out.println("Metro Server stopped");
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void stop()
    {
        isRun = false;
    }

    public boolean isRun()
    {
        return isRun;
    }

    public static void main(String[] args)
    {
        MetroServer ms = new MetroServer(27015);
        new Thread(ms).start();
    }
}
