package task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author maxkrivich
 */
public class Main
{

    static File f = new File("data.xml");
    static File xsdF = new File("datasheet.xds");

    public static void main(String[] args) throws Exception
    {
        
//        task4();
//        task5();
    }

    public static Document task1()
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(f);
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return doc;
    }

    public static Document task2() throws Exception //DTD
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        builder.setErrorHandler(new MyErrorHandler());
        return builder.parse(f);
    }

    public static void task3() throws Exception //XSD
    {
        Validator validator = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).
                newSchema(new File(xsdF.getPath())).newValidator();
        validator.validate(new StreamSource(new File(f.getPath())));
    }

    public static void task4() throws Exception
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        Document doc = null;
        DocumentBuilder db = null;
        db = dbf.newDocumentBuilder();
        doc = db.parse(f);
        db.setErrorHandler(new MyErrorHandler());
//        DOMUtil.showDocument(doc);
        System.out.printf("DataSheet menu\n");
        Scanner in = new Scanner(System.in);
        int c = 0xcaffe;
        DataSheet ds = new DataSheet(doc);
//        DOMUtil.showDocument(doc);
        while (c != 4)
        {
            System.out.printf("1)Add; 2)Insert; 3)Delete; 4)Exit\nChoice:\t");
            switch (c = in.nextInt())
            {
                case 1:
                    System.out.println("Input date,x,y");
                    ds.addElement(ds.newElement(in.next(), in.nextDouble(), in.nextDouble()));
                    break;
                case 2:
                    System.out.println("Input pos and new element(date,x,y)");
                    ds.insertElementAt(in.nextInt(), ds.newElement(in.next(), in.nextDouble(), in.nextDouble()));
                    break;
                case 3:
                    System.out.print("Input pos: ");
                    ds.removeElement(in.nextInt());
                    break;
            }

        }
//        DOMUtil.showDocument(ds.getDoc());
        WriteToXMLFile.write(ds.getDoc());

    }

    public static void task5() throws Exception
    {
        try
        {
            task1.Main.task2();
        } catch (Exception ex)
        {
            System.err.println("Not Valid XSD");
        }
        try
        {
            task1.Main.task3();
        } catch (Exception ex)
        {
            System.err.println("Not Valid DTD");
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(true);
        SAXParser saxParser = factory.newSAXParser();
        task1.DataHandler2 handler = new task1.DataHandler2();
        InputStream xmlInput = new FileInputStream(f.getPath());
        saxParser.parse(xmlInput, handler);
        WriteToXMLFile.write(toDOM(handler.getDataSheet()),new File("task5.out"));
    }

    public static Document toDOM(task1.DataSheet dsh) throws Exception
    {
        DataSheet d = new DataSheet();
        d.t();
        List<task1.Data> l = dsh.getDl();
        for (int i = 0; i < l.size(); i++)
            d.addElement(d.newElement(l.get(i).getDate(), l.get(i).getX(), l.get(i).getY()));
        return d.getDoc();
    }
}

class WriteToXMLFile
{

    public static void write(Document document, File f) throws TransformerException, FileNotFoundException
    {
        TransformerFactory transFactory = TransformerFactory.newInstance();
        Transformer transformer = transFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(document);
        FileOutputStream fos = new FileOutputStream(f);
        StreamResult result = new StreamResult(fos);
        transformer.transform(source, result);

    }

    public static void write(Document document) throws TransformerException, FileNotFoundException
    {
        write(document, Main.f);
    }
}
