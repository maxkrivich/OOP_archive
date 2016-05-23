package udpWork;

import java.io.*;
import java.net.*;

/**
 *
 * @author maxkrivich
 */
public class User implements Serializable
{

    private InetAddress inetAddress;
    private int port;
//    private static final long serialVersionUID = -5535067040489637753L;

    public User() throws UnknownHostException
    {
        this(InetAddress.getLocalHost(), 27015);
    }

    public User(InetAddress add, int port)
    {
        this.inetAddress = add;
        this.port = port;
    }

    public InetAddress getAdd()
    {
        return inetAddress;
    }

    public int getPort()
    {
        return port;
    }

    public void setInetAddress(InetAddress inetAddress)
    {
        this.inetAddress = inetAddress;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    @Override
    public String toString()
    {
        return "User{" + "add=" + inetAddress + ", port=" + port + '}';
    }

}
