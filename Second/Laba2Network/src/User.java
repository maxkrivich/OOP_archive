
import java.io.*;
import java.net.*;

public class User {

    public static void main(String[] args) throws IOException {
        int port = 9999;
        Socket s = new Socket("192.168.0.150",9999);
        BufferedReader r = new BufferedReader(new InputStreamReader(s.getInputStream())), 
                       in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter w = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        w.write(in.readLine());
        
        w.flush();
        s.close();
    }
}
