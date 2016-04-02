package task1;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author maxkrivich
 */
public class BookReader extends Human
{

    private int id;
    private Set<Book> bookList;

    BookReader(String fName, String lName, int id)
    {
        super(fName, lName);
        bookList = new TreeSet();
        this.id = id;
    }

    void addBook(Book b)
    {
        bookList.add(b);
    }

    void removeBook(Book b)
    {
        bookList.remove(b);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final BookReader other = (BookReader) obj;
        if (this.id != other.id)
            return false;
        if(!super.equals(obj))
            return false;
        return true;
    }
    
    

    @Override
    public String toString()
    {
        return String.format("%s\t%d\t(%s)\n", super.toString(), id, bookList);
    }

}
