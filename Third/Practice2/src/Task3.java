
/**
 *
 * @author maxkrivich
 */
import java.lang.reflect.*;
import java.util.Arrays;

public class Task3
{

    private static Object newLength(Object arr, int nLen, int d)
    {

        switch (d)
        {
            case 1:
                arr = ownNewLength(arr, nLen);
                break;
            case 2:
                int l1 = Array.getLength(arr);
                for (int i = 0; i < l1; i++)
                    Array.set(arr, i, ownNewLength(Array.get(arr, i), nLen));
                //arr = ownNewLength(arr, nLen);
                for(;nLen<l1;nLen++)
                    Array.set(arr, nLen, null);
                break;
        }
        return arr;
    }

    private static Object ownNewLength(Object arr, int nLen)
    {
//        Object tmp[] = new Object[nLen];
        Object tmp  = Array.newInstance(cl, nLen);
        if (nLen < Array.getLength(arr))
        {
//            System.out.println(Arrays.deepToString((Object[]) tmp));
            System.arraycopy(arr, 0, tmp, 0, nLen);   
//            System.out.println(Arrays.deepToString((Object[]) tmp));
        } else
        {
            System.arraycopy(arr, 0, tmp, 0, Array.getLength(arr));
        }
        return tmp;
    }

    private static Object getArray(Class c, int... d)
    {
        return Array.newInstance(c, d);
    }

    private static Object getArray(Class c, int len)
    {
        return Array.newInstance(c, len);
    }

    private static void fill(Object[] ob, Class c)
    {
        String s = c.getSimpleName();
        switch (s)
        {
            case "Integer":
                Arrays.fill(ob, Integer.valueOf(0));
                break;
            case "Long":
                Arrays.fill(ob, Long.valueOf(0));
                break;
            case "Boolean":
                Arrays.fill(ob, Boolean.FALSE);
                break;
            case "Double":
                Arrays.fill(ob, Double.valueOf(.0));
                break;
            case "Float":
                Arrays.fill(ob, Float.valueOf(0));
                break;
            default:
                Arrays.fill(ob, null);
        }
    }

    private static void fill(Object[][] ob, Class c)
    {
        for (int i = 0; i < ob.length; i++)
        {
            fill(ob[i], c);
        }
    }

    static Class cl;
    public static void main(String[] args) throws ClassNotFoundException
    {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Ввдите тип: ");
        String name = in.nextLine();
        cl = Class.forName(name);
        Object obs = null;
        int c, d, len;
        System.out.println("Введите размерность массива(1d-1 or 2d-2): ");
        do
        {
            d = in.nextInt();
        } while (d <= 0 || d >= 3);
        switch (d)
        {
            case 1:
                System.out.println("Введите размер: ");
                loop:
                {
                    c = in.nextInt();
                    if (c > 0)
                    {
                        break loop;
                    }

                }
                obs = (Object[]) getArray(cl, c);
                fill((Object[]) obs, cl);
                break;
            case 2:
                System.out.println("Введите размер(x,y): ");
                int x,
                 y;
                loop:
                {
                    x = in.nextInt();
                    y = in.nextInt();
                    if (x > 0 && y > 0)
                    {
                        break loop;
                    }
                }
                obs = (Object[][]) getArray(cl, new int[]
                {
                    x, y
                });
                fill((Object[][]) obs, cl);
                break;
        }
        System.out.println(Arrays.deepToString((Object[]) obs));
        System.out.println("Новый размер: ");
        do
        {
            len = in.nextInt();
        } while (len < 0);
        obs = newLength(obs, len, d);
        System.out.println(Arrays.deepToString((Object[]) obs));
    }

}
