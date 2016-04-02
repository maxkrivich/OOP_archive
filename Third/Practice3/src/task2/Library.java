package task2;

import java.io.*;
import java.util.*;

/**
 *
 * @author maxkrivich
 */
public class Library implements Serializable
{
    
    private final String name;
    private Map<String, BookStore> br;
    private transient List<BookReader> ls;
    private static final long serialVersionUID = 5269603556885959513L;
    
    Library(String name)
    {
        this.name = name;
        br = new HashMap();
        ls = new ArrayList();
    }
    
    public List<BookReader> getLs()
    {
        return ls;
    }
    
    String getName()
    {
        return name;
    }
    
    long getSerialVer()
    {
        return serialVersionUID;
    }
    
    void addBookStore(BookStore b)
    {
        if (!br.containsKey(b.getName()))
            br.put(b.getName(), b);
    }
    
    void addBook(BookStore o1, Book o2)
    {
        if (br.containsKey(o2.getName()))
            br.get(o1.getName()).addBook(o2);
        else
        {
            br.put(o1.getName(), o1);
            this.addBook(o1, o2);
            return;
        }
    }
    
    void addBookReader(BookReader b)
    {
        ls.add(b);
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (String keys : br.keySet())
            sb.append(String.format("%s\n", br.get(keys)));
        return String.format("Name: %s\nBookReaders:\n%s\nBookStors:\n%s", name, ls, sb.toString());
    }
    
    @Override
    public boolean equals(Object obj)
    {
        //TODO: may equals by map. Think about that
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Library other = (Library) obj;
        if (!Objects.equals(this.name, other.getName()))
            return false;
        if (!equalsList(other))
            return false;
        return true;
    }
    
    private boolean equalsList(Library obj)
    {
        BookReader ob1[] = this.ls.toArray(new BookReader[ls.size()]);
        BookReader ob2[] = obj.getLs().toArray(new BookReader[obj.getLs().size()]);
        return Arrays.equals(ob1, ob2);
    }
    
    private void writeObject(ObjectOutputStream out) throws IOException
    {
        out.defaultWriteObject();
        out.writeInt(ls.size());
        for (BookReader rr : ls)
        {
            out.writeObject(rr.getfName());
            out.writeObject(rr.getlName());
            out.writeInt(rr.getId());
            out.writeInt(rr.getBookList().size());
            for (Book b : rr.getBookList())
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
        }
        out.flush();
        
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
        int cnt = in.readInt();
        ls = new LinkedList();
        while (cnt-- > 0)
        {
            String fName = (String) in.readObject(),
                   lName = (String) in.readObject();
            int id = in.readInt(), sz = in.readInt();
            BookReader ee = new BookReader(fName, lName, id);
            Book b = null;
            while (sz-- > 0)
            {
                String name = (String) in.readObject();
                int y = in.readInt(),
                    en = in.readInt(),
                    s = in.readInt();
                b = new Book(name, y, en);
                while (s-- > 0)
                    b.addAuthor(new Author((String) in.readObject(), (String) in.readObject()));
            ee.addBook(b);
            }
            ls.add(ee);
        }
    }
}
