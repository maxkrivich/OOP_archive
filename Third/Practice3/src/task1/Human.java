package task1;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author maxkrivich
 */
public abstract class Human implements Serializable
{

    protected String fName;
    protected String lName;

    Human(String fName, String lName)
    {
        this.fName = fName;
        this.lName = lName;
    }

    String getfName()
    {
        return fName;
    }

    String getlName()
    {
        return lName;
    }

    @Override
    public String toString()
    {
        return String.format("%s:\t%s\t%s", this.getClass().getSimpleName(), fName, lName);
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.fName);
        hash = 17 * hash + Objects.hashCode(this.lName);
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
        final Human other = (Human) obj;
        if (!Objects.equals(this.fName, other.fName))
            return false;
        if (!Objects.equals(this.lName, other.lName))
            return false;
        return true;
    }

}
