package task1;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author maxkrivich
 */
public class BookStore implements Serializable
{
   private String name;
   private List<Book> books;
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
   
}
