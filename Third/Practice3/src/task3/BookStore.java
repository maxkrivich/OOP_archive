package task3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author maxkrivich
 */
public class BookStore implements Externalizable
{

    private String name;
    private List<Book> books;
    private static final long serialVersionUID = 7343289159425580635L;

    BookStore()
    {
        this("booooook");
    }
    
    BookStore(String name)
    {
        this.name = name;
        books = new ArrayList();
    }

    BookStore(String name, Book[] b)
    {
        this(name);
        books = Arrays.asList(b);
    }

    void addBook(Book b)
    {
        books.add(b);
    }

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return String.format("%s\t(%s)\n", name, books);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(name);
        out.writeInt(books.size());
        for (Externalizable b : books)
            b.writeExternal(out);
        out.flush();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        name = (String) in.readObject();
        int cnt = in.readInt();
        while(cnt-- > 0)
        {
            Book b = new Book();
            b.readExternal(in);
            books.add(b);
        }
    }

}
