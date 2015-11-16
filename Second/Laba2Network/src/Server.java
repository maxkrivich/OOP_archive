
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9984);
        while (true) {
            Socket s = ss.accept();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));

            String l;
            while ((l = in.readLine()) != null) {
                System.out.println(l);
            }
//            new Thread(new Runnable() {
//                public void run() {
//                    try {
//                        BufferedReader in;
//                        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
//                        String l;
//                        while ((l = in.readLine()) != null) {
//                            System.out.println(l);
//                        }
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//
//            }).start();
        }// while

    }
}
