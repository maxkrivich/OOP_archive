package task2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;

/**
 *
 * @author maxkrivich
 */
public class DataSheet
{

    private Document doc;

    public DataSheet(Document doc)
    {
        super();
        this.doc = doc;
    }

    public DataSheet()
    {
//        this(null);
    }

    public Document getDoc()
    {
        return doc;
    }

    public void setDoc(Document doc)
    {
        this.doc = doc;
    }

    public int numData()
    {
        return doc.getDocumentElement().getElementsByTagName("data").getLength();
    }

    public double getX(int pos)
    {
        return Double.parseDouble(doc.getDocumentElement().getElementsByTagName("x").item(pos).getTextContent());
    }

    public void setX(int pos, double val)
    {
        doc.getDocumentElement().getElementsByTagName("x").item(pos).setTextContent(val + "");
    }

    public double getY(int pos)
    {
        return Double.parseDouble(doc.getDocumentElement().getElementsByTagName("y").item(pos).getTextContent());
    }

    public void setY(int pos, double val)
    {
        doc.getDocumentElement().getElementsByTagName("y").item(pos).setTextContent(val + "");
    }

    public void removeElement(int pos)
    {
        Node n = doc.getDocumentElement().getElementsByTagName("data").item(pos);
        doc.getDocumentElement().removeChild(n);
    }

    public void t() throws ParserConfigurationException
    {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("datasheet");
        doc.appendChild(rootElement);
    }

    public Element newElement(String date, double x, double y)
    {
        Element data = doc.createElement("data");
        Attr attr = doc.createAttribute("date");
        attr.setValue(date.trim());
        data.setAttributeNode(attr);
        Element elemX = doc.createElement("x");
        elemX.appendChild(doc.createTextNode(x + ""));
        data.appendChild(elemX);
        Element elemY = doc.createElement("y");
        elemY.appendChild(doc.createTextNode(y + ""));
        data.appendChild(elemY);
        return data;
    }

    public void replaceElementAt(int pos, Node nd)
    {
        Node n = doc.getDocumentElement().getElementsByTagName("data").item(pos);
        doc.getDocumentElement().replaceChild(nd, n);
    }

    public void addElement(Element data)
    {
        doc.getDocumentElement().appendChild(data);
    }

    public void insertElementAt(int pos, Node nd)
    {
        Node n = doc.getDocumentElement().getElementsByTagName("data").item(pos);
        doc.getDocumentElement().insertBefore(nd, n);
    }

}
