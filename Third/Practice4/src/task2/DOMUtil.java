package task2;

import org.w3c.dom.*;

/**
 *
 * @author maxkrivich
 */
public class DOMUtil
{

    private static void stepThrough(Node start)
    {
        System.out.println(start.getNodeName() + " = " + start.getNodeValue());
        if (start instanceof Element)
        {
            NamedNodeMap startAttr = start.getAttributes();
            for (int i = 0; i < startAttr.getLength(); i++)
            {
                Node attr = startAttr.item(i);
                System.out.printf(" Attribute: %s = %s\n", attr.getNodeName(), attr.getNodeValue());
            }
        }
        for (Node child = start.getFirstChild(); child != null; child = child.getNextSibling())
        {
            stepThrough(child);
        }
    }

    public static void processDocument(Document doc)
    {
        Element rootEl = doc.getDocumentElement();
        System.out.println("Root element: " + rootEl.getNodeName());
        System.out.println("Child elements: ");
        stepThrough(rootEl);
    }

    public static void getSelectInfa(Document doc)
    {
        NodeList nl1 = doc.getDocumentElement().getElementsByTagName("x");
        NodeList nl2 = doc.getDocumentElement().getElementsByTagName("y");
        if (nl1.getLength() == nl2.getLength())
        {
            for (int i = 0; i < nl1.getLength(); i++)
            {
                System.out.println(nl1.item(i).getNodeName() + " "
                        + nl1.item(i).getTextContent() + "\t"
                        + nl2.item(i).getNodeName() + " "
                        + nl2.item(i).getTextContent());
            }
        }
    }

    public static void showDocument(Document doc)
    {
        Element rootElem = doc.getDocumentElement();
        NodeList childs = rootElem.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++)
        {
            Element child = (Element) childs.item(i);
            
            System.out.println(child.getTagName() + ": date "
                    + child.getAttribute("date"));
            Element child1 = (Element) child.getFirstChild();
            System.out.println("\t" + child1.getTagName() + ": "
                    + ((Text) child1.getFirstChild()).getData().trim());
//child1 = (Element)child.getLastChild();
            child1 = (Element) child1.getNextSibling(); // Так тоже можно
            System.out.println("\t" + child1.getTagName() + ": "
                    + ((Text) child1.getFirstChild()).getData().trim());
        }
    }
}
