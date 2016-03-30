
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maxkrivich
 */
public class Test
{
    public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException
    {
        savaObject(new Check());
    }
    public static void savaObject(Object obj) throws IOException, IllegalArgumentException, IllegalAccessException
    {
        Class cl = obj.getClass();
        Field fs[] = cl.getDeclaredFields();
        PrintWriter pw = new PrintWriter(new FileWriter("object.dat"),true);
        pw.println(cl.getName());
        for(Field ff:fs)
            pw.printf("%s\t%s\n",ff,ff.get(obj));
    }
    
}
