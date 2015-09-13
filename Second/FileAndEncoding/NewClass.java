
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class NewClass {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        String s = "abc абв";
        FileOutputStream f = new FileOutputStream(new File("c:/utf8.htm"));
        f.write(s.getBytes("UTF-8"));

        f = new FileOutputStream(new File("c:/utf16.htm"));
        f.write(s.getBytes("UTF-16"));

        f = new FileOutputStream(new File("c:/CP1251.htm"));
        f.write(s.getBytes("WINDOWS-1251"));

        DataOutputStream sa = new DataOutputStream(new FileOutputStream(new File("c:/te.htm")));
        sa.writeChars(s);

    }

}
