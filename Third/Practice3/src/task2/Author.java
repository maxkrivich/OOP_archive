package task2;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author maxkrivich
 */
public class Author extends Human
{

    Author(String fName, String lName)
    {
        super(fName, lName);
    }

    private void writeObject(ObjectOutputStream out) throws IOException
    {
        throw new NotSerializableException();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        throw new NotSerializableException();
    }

    public static void main(String[] args)
    {
        System.out.println(new Author("", "") instanceof Serializable);
    }
}
