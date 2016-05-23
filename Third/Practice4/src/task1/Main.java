package task1;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 *
 * @author maxkrivich
 */
public class Main
{

    static File f = new File("data.xml");

    public static void main(String[] args) throws Exception
    {
        task1();
        task2();
        task3();
    }

    public  static void task1() throws Exception
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        DataHandler saxp = new DataHandler();
        parser.parse(f, saxp);
    }

    public  static void task2() throws SAXException, ParserConfigurationException, IOException // XSD Validation
    {
        Schema schema = null;
        try
        {

            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factor = SchemaFactory.newInstance(language);
            schema = factor.newSchema(new File("datasheet.xsd"));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setSchema(schema);
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();

        DataHandler2 saxp = new DataHandler2();
        parser.parse(f, saxp);
        DataSheet dsh = saxp.getDataSheet();
    }

    public static void task3() throws ParserConfigurationException, SAXException, IOException //DTD validation
    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        XMLReader reader = factory.newSAXParser().getXMLReader();
        reader.setErrorHandler(new DataHandler());
        reader.parse(new InputSource(f.getAbsolutePath()));
    }

}
