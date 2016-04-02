package task3;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author maxkrivich
 */
public class Book implements Externalizable, Comparable<Book>
{
    
    private String name;
    private List<Author> authors;
    private int year;
    private int editNum;
    private static final long serialVersionUID = 5302340881846837863L;
    
    public Book()
    {
        authors = new LinkedList();
    }
    
    public Book(String name, Author[] authors, int year, int editNum)
    {
        this.name = name;
        this.authors = Arrays.asList(authors);
        this.year = year;
        this.editNum = editNum;
    }
    
    @Override
    public String toString()
    {
        return String.format("%s\t(%s)\t%d\t%d\n", name, authors, year, editNum);
    }
    
    public String getName()
    {
        return name;
    }
    
    public String toListString()
    {
        return Arrays.toString(authors.toArray(new Author[authors.size()]));
    }
    
    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.authors);
        hash = 37 * hash + this.year;
        hash = 37 * hash + this.editNum;
        return hash;
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
        final Book other = (Book) obj;
        if (this.year != other.year)
            return false;
        if (this.editNum != other.editNum)
            return false;
        if (!Objects.equals(this.name, other.name))
            return false;
        if (!Objects.equals(this.authors, other.authors))
            return false;
        return true;
    }
    
    @Override
    public int compareTo(Book o)
    {
        return this.name.compareTo(o.getName());
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        out.writeObject(name);
        out.writeInt(year);
        out.writeInt(editNum);
        out.writeInt(authors.size());
        for (Externalizable a : authors)
            a.writeExternal(out);
        out.flush();
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException
    {
        name = (String) in.readObject();
        year = in.readInt();
        editNum = in.readInt();
        int cnt = in.readInt();
        while(cnt-- > 0)
        {
            Author a = new Author();
            a.readExternal(in);
            authors.add(a);
        }
    }
}
