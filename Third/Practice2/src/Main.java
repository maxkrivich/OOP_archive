
/**
 *
 * @author maxkrivich
 */
import java.lang.reflect.*;
import java.util.Scanner;

public class Main
{

    public static void main(String[] args) throws ClassNotFoundException
    {
        Scanner in = new Scanner(System.in);
        System.out.println(taskOne("java.util.Scanner"));
    }

    public static String taskOne(final String className) throws ClassNotFoundException
    {
        Class c = Class.forName(className);
        StringBuffer sb = new StringBuffer();
        int modif = c.getModifiers();
        sb.append(String.format("%s class %s extends %s\n{\n", new Modifier().toString(modif), c.getName(), c.getSuperclass().getName()));
        Field fs[] = c.getFields();
        Method ms[] = c.getMethods();
        Constructor cs[] = c.getConstructors();
        for (Constructor cc : cs)
            sb.append(String.format("\t%s\n", cc));
        sb.append("\n");
        for (Method m : ms)
            sb.append(String.format("\t%s\n", m));
        sb.append(String.format("\t...........................................\n"));
        for (Field f : fs)
            sb.append(String.format("\t%s\n", f));
        sb.append(String.format("\t...........................................\n}\n"));
        return sb.toString();
    }
    
}
