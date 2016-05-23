package task1;

import java.util.Locale;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author maxkrivich
 */
public class DataHandler2 extends DefaultHandler
{

    private boolean isX, isY;
    private DataSheet dsh;
    private Data tmpData;

    public DataSheet getDataSheet()
    {
        return dsh;
    }

    public void setDataSheet(DataSheet dsh)
    {
        this.dsh = dsh;
    }

    @Override
    public void startDocument() throws SAXException
    {
        System.out.println("Start parsing XML document");
        Locale.setDefault(Locale.US);
        if (dsh == null)
        {
            dsh = new DataSheet();
            dsh.setName("New DataSheet");
        }
    }

    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("End parsing XML document");
        System.out.println(dsh);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        System.out.println("Start processing");
        switch (qName)
        {
            case "x":
                isX = true;
                break;
            case "y":
                isY = true;
                break;
            case "data":
                tmpData = new Data();
                if (attributes.getLength() > 0)
                {
                    tmpData.setDate(attributes.getValue(0));
                }
                break;
        }
//        if (attributes.getLength() > 0)
//        {
//            for (int i = 0; i < attributes.getLength(); i++)
//            {
//                System.out.printf("\t%s: %s", attributes.getLocalName(i), attributes.getValue(i));
//            }
//        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        System.out.println("End processing");
        switch (qName)
        {
            case "x":
                isX = false;
                break;
            case "y":
                isY = false;
                break;
            case "data":
                dsh.addDataItem(tmpData);
                tmpData = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String str = new String(ch, start, length).trim();
        if (str.trim().length() > 0)
        {
            double tmp = Double.parseDouble(str);
            if (isX)
            {
                tmpData.setX(tmp);
            } else if (isY)
            {
                tmpData.setY(tmp);
            }
        }
    }

    @Override
    public void warning(SAXParseException ex) throws SAXException
    {
        System.err.println("Warning: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = " + ex.getColumnNumber());
    }

    @Override
    public void error(SAXParseException ex) throws SAXException
    {
        System.err.println("Error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = " + ex.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException ex) throws SAXException
    {
        System.err.println("Fatal error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = " + ex.getColumnNumber());
    }
}
