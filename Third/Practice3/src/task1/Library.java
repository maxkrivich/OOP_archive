package task1;

import static com.sun.org.apache.xalan.internal.lib.ExsltDynamic.map;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author maxkrivich
 */
public class Library implements Serializable
{

    private final String name;
    private Map<String, BookStore> br;
    private List<BookReader> ls;
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
        if(!equalsList(other))
            return false;
        return true;
    }

    private boolean equalsList(Library obj)
    {
        BookReader ob1[] = this.ls.toArray(new BookReader[ls.size()]);
        BookReader ob2[] = obj.getLs().toArray(new BookReader[obj.getLs().size()]);
        return Arrays.equals(ob1, ob2);
    }
}
