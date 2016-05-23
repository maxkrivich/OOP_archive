package udpWork;

import java.io.*;
import java.net.*;

/**
 *
 * @author maxkrivich
 */
public class UDPServer
{

    private ActiveUsers userList;
    private DatagramSocket dgs;
    private DatagramPacket dgp;
    private InetAddress addr;
    private int port = -1;

    public UDPServer(int port)
    {
        this.port = port;
        try
        {
            dgs = new DatagramSocket(port);
        } catch (SocketException ex)
        {
            System.out.println("Error: " + ex);
        }
        userList = new ActiveUsers();
    }

    public void work(int bufferSize)
    {
        try
        {
            System.out.println("Server start...");
            while (true)
            {

                getUserData(bufferSize);
                log(addr, port);
                sendUserData();
            }
        } catch (IOException e)
        {
            System.out.println("Error: " + e);
        } finally
        {
            System.out.println("Server end...");
            dgs.close();
        }
    }

    private void log(InetAddress address, int port)
    {
        System.out.println("Request from: " + address.getHostAddress()
                + " port: " + port);
    }

    private void clear(byte buff[])
    {
        buff = new byte[0];
    }

    private void getUserData(int bufferSize) throws IOException
    {
        byte[] buffer = new byte[bufferSize];
        dgp = new DatagramPacket(buffer, buffer.length);
        dgs.receive(dgp);
        addr = dgp.getAddress();
        port = dgp.getPort();
        User usr = new User(addr, port);
        if (userList.isEmpty())
        {
            userList.add(usr);
        } else if (!userList.contains(usr))
        {
            userList.add(usr);
        }
        clear(buffer);
    }

    private void sendUserData() throws IOException
    {
        byte[] buffer;
        for (int i = 0; i < userList.size(); i++)
        {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(userList.get(i));
            buffer = bout.toByteArray();
            dgp = new DatagramPacket(buffer, buffer.length, addr, port);
            dgs.send(dgp);
        }
        buffer = "end".getBytes();
        dgp = new DatagramPacket(buffer, 0, addr, port);
        dgs.send(dgp);
    }

    public static void main(String[] args)
    {
        (new UDPServer(27015)).work(256);
    }
}
