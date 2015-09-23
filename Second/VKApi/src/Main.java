
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        Socket s = new Socket("api.vk.com", 80);

        PrintWriter w = new PrintWriter(s.getOutputStream());
        BufferedReader r = new BufferedReader(
                new InputStreamReader(s.getInputStream()));

        w.println("GET /method/friends.get?user_id=205387401&list_id HTTP/1.1");
        w.println("host:api.vk.com");
        w.println();
        w.flush();

        int c = -1;
        while ((c = r.read()) >= 0) {
            System.out.write(c);
            System.out.flush();
        }
        s.close();
    }

}
