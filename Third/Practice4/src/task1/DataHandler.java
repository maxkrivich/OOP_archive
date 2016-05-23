package task1;

import java.util.Locale;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author maxkrivich
 */
public class DataHandler extends DefaultHandler
{

    private boolean isX, isY;
    private double sumX, sumY, sumX2, sumXY, t;
    private double k, b;
    private int num;

    @Override
    public void startDocument() throws SAXException
    {
        System.out.println("Start parsing XML document");
        Locale.setDefault(Locale.US);
        sumX = sumY = sumX2 = sumXY = t = num = 0;
    }

    @Override
    public void endDocument() throws SAXException
    {
        System.out.println("End parsing XML document");
        num /= 2;
        k = (sumXY - sumX * sumY / num) / (sumX2 - sumX * sumX / num);
        b = sumY / num - k * sumX / num;
        System.out.printf("k:%f\tb:%f", k, b);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        System.out.println("Start processing");
        switch (qName)
        {
            case "x":
            case "X":
                isX = true;
                break;
            case "y":
            case "Y":
                isY = true;
        }
        if (attributes.getLength() > 0)
        {
            for (int i = 0; i < attributes.getLength(); i++)
            {
                System.out.printf("\t%s: %s", attributes.getLocalName(i), attributes.getValue(i));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        System.out.println("End processing");
        switch (qName)
        {
            case "x":
            case "X":
                isX = false;
                num += 1;
                break;
            case "y":
            case "Y":
                isY = false;
                t = 0;
                num += 1;
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException
    {
        String str = new String(ch, start, length).trim();
        if (str.trim().length() > 0)
        {
            System.out.printf("\tValue: %s\n", str);
            double tmp = Double.parseDouble(str);
            if (isX)
            {
                sumX += tmp;
                sumX2 += tmp * tmp;
            } else if (isY)
            {
                sumY += tmp;
                t = t * tmp;
                sumXY += t;
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
