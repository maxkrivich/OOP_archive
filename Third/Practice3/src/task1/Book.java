package task1;

import java.util.List;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author maxkrivich
 */
public class Book implements Serializable, Comparable<Book>
{

    private String name;
    private List<Author> authors;
    private int year;
    private int editNum;
    private static final long serialVersionUID = 5302340881846837863L;

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

}
