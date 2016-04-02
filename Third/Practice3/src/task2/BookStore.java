package task2;

import java.io.*;
import java.util.*;

/**
 *
 * @author maxkrivich
 */
public class BookStore implements Serializable
{

    private String name;
    private transient List<Book> books;
    private static final long serialVersionUID = 7343289159425580635L;

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

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeInt(books.size());
        for (Book b : books)
        {
            out.writeObject(b.getName());
            out.writeInt(b.getYear());
            out.writeInt(b.getEditNum());
            out.writeInt(b.getAuthors().size());
            for (Author a : b.getAuthors())
            {
                out.writeObject(a.getfName());
                out.writeObject(a.getlName());
            }
        }
        out.flush();

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        int cnt = in.readInt();
        books = new LinkedList();
        while (cnt-- > 0)
        {
            String name = (String) in.readObject();
            int year = in.readInt(),
                editNum = in.readInt(),
                sz = in.readInt();
            Book b = new Book(name, year, editNum);
            while (sz-- > 0)
                b.addAuthor(new Author((String) in.readObject(), (String) in.readObject()));
            books.add(b);
        }
    }
}
