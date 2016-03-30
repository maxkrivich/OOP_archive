
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.StringTokenizer;

/**
 *
 * @author maxkrivich
 */
public final class TaskTwo
{

    final static java.util.Map<Class<?>, Class<?>> map = new java.util.HashMap<Class<?>, Class<?>>()
    {
        {
            put(boolean.class, Boolean.class);
            put(byte.class, Byte.class);
            put(short.class, Short.class);
            put(char.class, Character.class);
            put(int.class, Integer.class);
            put(long.class, Long.class);
            put(float.class, Float.class);
            put(double.class, Double.class);
        }
    };
    final static File f = new File("object.dat");

    public static Constructor[] getConstructors(Class c)
    {
        return c.getConstructors();
    }

    public static Method[] getMethods(Class c)
    {
        return c.getDeclaredMethods();
    }

    public static Object newObject(Constructor con) throws Exception
    {
        Class args[] = con.getParameterTypes();
        if (0 == args.length)
            return con.newInstance(null);
        else
        {
            System.out.printf("Параметры конструктора:\n");
            return con.newInstance(inputArgs(args));
        }
    }

    public static Object callMethod(Method met, Object obj) throws Exception
    {
        Class args[] = met.getParameterTypes();
        if (0 == args.length)
            return met.invoke(obj, null);
        else
        {
            System.out.printf("Параметры метода:\n");
            return met.invoke(obj, inputArgs(args));
        }
    }

    private static Object[] inputArgs(Class args[]) throws Exception
    {
        java.util.Scanner in = new java.util.Scanner(System.in);
        Object[] objcs = new Object[args.length];
        for (int i = 0, j = 0; i < args.length; i++)
        {
            System.out.printf("Введите %s значение: ", args[i]);
            objcs[i] = param(args[i], in.nextLine());
        }
        return objcs;
    }

    private static Object param(Class cls, String value) throws Exception
    {
        Object obj;
        boolean t = false;
        Constructor cons[] = map.get(cls).getConstructors();
        int j = 0;
        for (; j < cons.length; j++)
            if (1 == cons[j].getParameterTypes().length && String.class == cons[j].getParameterTypes()[0])
            {
                t = true;
                break;
            }
        if (t)
        {
            obj = map.get(cls).getConstructors()[j].newInstance(value);
        } else
            throw new Exception("Что-то явно пошло не по плану");
        return obj;
    }

    public static void main(String[] args) throws Exception
    {
        Class c = Check.class;
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.printf("Создание объекта...\n\tСписок конструкторов:\n");
        Constructor con[] = getConstructors(c);
        for (int i = 0; i < con.length; i++)
            System.out.printf("%d). %s\n", i + 1, con[i]);
        System.out.printf("Введите порядковый номер конструктора [%d, %d]:\n", 1, con.length);
        int n;
        do
        {
            n = in.nextInt();
        } while (n < 0 || n > con.length);
        Object obj = newObject(con[n - 1]);
        savaObject(obj);
        System.out.printf("\nСостояние объекта:\n%s\n", obj);
        System.out.printf("\nВызов метода...\n\tСписок методов:\n");
        Method met[] = getMethods(c);
        for (int i = 0; i < met.length; i++)
            System.out.printf("%d). %s\n", i + 1, met[i]);
        System.out.printf("Введите порядковый номер метода [%d, %d]:\n", 1, met.length);
        do
        {
            n = in.nextInt();
        } while (n < 0 || n > met.length);
        Object tmp = callMethod(met[n - 1], obj);
        System.out.println(tmp == null ? "" : tmp);
        System.out.printf("\nСостояние объекта:\n%s\n", obj);
        obj = restoreObject();
        System.out.printf("\nВосстановление объекта:\n%s\n", obj);
        
    }

    public static void savaObject(Object obj) throws IOException, IllegalArgumentException, IllegalAccessException
    {
        Class cl = obj.getClass();
        Field fs[] = cl.getDeclaredFields();
        PrintWriter pw = new PrintWriter(new FileWriter(f), true);
        pw.println(cl.getName());
        for (Field ff : fs)
            pw.printf("%s\t%s\n", ff.getName(), ff.get(obj));
    }

    public static Object restoreObject() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String s = br.readLine();
        Class cl = Class.forName(s);
        //Field fs[] = cl.getDeclaredFields();
        Object obj = newObject(getConstructors(cl)[0]);
        while ((s = br.readLine()) != null)
        {
            StringTokenizer st = new StringTokenizer(s);
            Field ff = cl.getDeclaredField(st.nextToken());
            ff.set(obj, param(ff.getType(), st.nextToken()));
        }
        return obj;
    }
}
