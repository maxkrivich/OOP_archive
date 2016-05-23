package task1;

import java.util.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author maxkrivich
 */
@XmlRootElement(name = "Analyser")
@XmlType(propOrder = {"name", "age", "role", "gender"})
public class DataSheet
{

    private List<Data> dl;
    private double sumX, sumY, sumX2, sumXY;
    private double k, b;
    private String name = "";

    public DataSheet()
    {
        dl = new LinkedList();
        sumX = sumY = sumX2 = sumXY = 0;
    }

    public List<Data> getDl()
    {
        return dl;
    }

    public void addDataItem(Data d)
    {
        //calculation
        //end calculation
        dl.add(d);
        sumX += d.getX();
        sumX2 += d.getX() * d.getX();
        sumY += d.getY();
        sumXY += d.getY() * d.getX();
        k = (sumXY - sumX * sumY / dl.size()) / (sumX2 - sumX * sumX / dl.size());
        b = sumY / dl.size() - k * sumX / dl.size(); // TODO
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return String.format("%s\n%s\ny = %f * x + %f\n", name, dl, k, b);
    }

}
