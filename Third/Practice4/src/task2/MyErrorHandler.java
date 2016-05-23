package task2;

import org.xml.sax.*;

/**
 *
 * @author maxkrivich
 */
public class MyErrorHandler implements ErrorHandler
{

    @Override
    public void error(SAXParseException ex) throws SAXException
    {
        System.err.println("Error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = "
                + ex.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException ex) throws SAXException
    {
        System.err.println("Fatal Error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = "
                + ex.getColumnNumber());
    }

    @Override
    public void warning(SAXParseException ex) throws SAXException
    {
        System.err.println("Warning: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = "
                + ex.getColumnNumber());
    }
}
