package task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 *
 * @author maxkrivich
 */
public class LibraryDriver
{

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        Random r = new Random();
        Book bs[] = new Book[3];
        bs[0] = new Book("book1", new Author[]
        {
            new Author("name1", "name2")
        }, 2014, 12);
        bs[1] = new Book("book2", new Author[]
        {
            new Author("name3", "name4")
        }, 2016, 0);
        bs[2] = new Book("book3", new Author[]
        {
            new Author("name5", "name6")
        }, 2106, 123);
        BookReader br[] = new BookReader[3];
        br[0] = new BookReader("ivan", "ivanov", 2131231);
        br[1] = new BookReader("petr", "petrov", 213645641);
        br[2] = new BookReader("lolka", "lolkovna", 346655641);
        for (int i = 0; i < br.length + bs.length; i++)
            br[r.nextInt(br.length)].addBook(bs[r.nextInt(br.length)]);
        BookStore be[] = new BookStore[3];
        be[0] = new BookStore("store1", new Book[]
        {
            bs[0], bs[1]
        });
        be[1] = new BookStore("store2", new Book[]
        {
            bs[1], bs[2]
        });
        be[2] = new BookStore("store3", new Book[]
        {
            bs[2], bs[0]
        });
        Library l = new Library("lib1");
        for (BookStore rr : be)
            l.addBookStore(rr);
        for (BookReader rr : br)
            l.addBookReader(rr);
        System.out.printf("%s\n", l);
        
        
        writeLibrary(l);
        Library l0 = readLibrary();
        System.out.printf("%d\n", l0.getSerialVer());
        System.out.println(l.equals(l0)); //test correct read
        
    }
    static File f = new File("src/task2/library.dat");

    private static void writeLibrary(final Library l) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(l);
        oos.flush();
        oos.close();
        fos.close();
    }

    private static Library readLibrary() throws IOException, ClassNotFoundException
    {
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream oin = new ObjectInputStream(fis);
        Library l = (Library) oin.readObject();
        fis.close();
        oin.close();
        return l;
    }
}
