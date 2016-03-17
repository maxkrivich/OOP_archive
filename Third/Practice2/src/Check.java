
/**
 *
 * @author maxkrivich
 */
public class Check
{

    int x;
    int y;
    
    public Check()
    {
        this(1, 1);
    }
    
    public Check(int x, int y)
    {
        setData(x, y);
    }
    
    public Check(int x)
    {
        setData(x, x);
    }
    
    public void setData(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double dist()
    {
        return Math.sqrt(x * x + y * y);
    }
    
    @Override
    public String toString()
    {
        return String.format("x\t%d\ny\t%d", x, y);
    }
    
}
