
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
                {
                    Array.set(arr, i, ownNewLength(arr, nLen));
                }
                arr = ownNewLength(arr, nLen);
                break;
        }
        return arr;
    }

    private static Object ownNewLength(Object arr, int nLen)
    {
        Object tmp = new Object[nLen];
        if (nLen < Array.getLength(arr))
            System.arraycopy(arr, 0, tmp, 0, nLen);
        else
            System.arraycopy(arr, 0, tmp, 0, Array.getLength(arr));
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

    public static void main(String[] args) throws ClassNotFoundException
    {
        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.println("Ввдите тип: ");
        String name = in.nextLine();
        Class cl = Class.forName(name);
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
                break;
        }
        System.out.println("Новый размер: ");
        do
        {
            len = in.nextInt();
        } while (len < 0);
        obs = newLength(obs, len, d);

    }

}
