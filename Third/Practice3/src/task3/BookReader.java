package task3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
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

    public BookReader()
    {
        this(null, null, -1);
    }

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
        if (!super.equals(obj))
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return String.format("%s\t%d\t(%s)\n", super.toString(), id, bookList);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        super.writeExternal(out);//name
        out.writeInt(id);//id
        out.writeInt(bookList.size());//cnt books
        for (Externalizable b : bookList)
            b.writeExternal(out);
        out.flush();
//        out.close();
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        super.readExternal(in);
        id = in.readInt();
        int cnt = in.readInt();
        while (cnt-- > 0)
        {
            Book b = new Book();
            b.readExternal(in);
            bookList.add(b);
        }
    }

}
