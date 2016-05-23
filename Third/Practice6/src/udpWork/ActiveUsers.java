package udpWork;


import java.util.*;
import udpWork.User;

/**
 *
 * @author maxkrivich
 */
public class ActiveUsers
{

    private List<User> users;

    public ActiveUsers()
    {
        users = new ArrayList();
    }

    public void add(User u)
    {
        users.add(u);
    }

    public boolean isEmpty()
    {
        return users.isEmpty();
    }

    public int size()
    {
        return users.size();
    }

    public boolean contains(User u)
    {
        return users.contains(u);
    }

    public User get(int i)
    {
        return users.get(i);
    }

    public void clear()
    {
        users.clear();
    }

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        for (User u : users)
            buf.append(String.format("%s\n", u));
        return buf.toString();
    }
}
