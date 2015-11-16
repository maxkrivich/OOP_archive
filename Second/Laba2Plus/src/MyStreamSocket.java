
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MyStreamSocket {

    DatagramSocket s;
    SocketAddress remote;
    MyOutputStream os;
    MyInputStream is;
    
    public MyStreamSocket(int port, InetAddress laddr) throws SocketException{
        s = new DatagramSocket(port, laddr);
    }

    public void connect(SocketAddress remote) {
        this.remote = remote;
    }

    public InputStream getInputStream() {
        return is.getOutputStream();
    }

    public OutputStream getOutputStream() {
        return os.getOutputStream();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        MyStreamSocket ss= new MyStreamSocket(9999, InetAddress.getByName("localhost"));
        PrintWriter w= new PrintWriter(ss.getOutputStream());
        w.write("abc");
        w.flush();
}
}
