
import java.util.Iterator;
import java.util.LinkedList;



public class CollectionTest {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("element_1");
        linkedList.add("element_2");
        linkedList.add("element_3");
        linkedList.add("element_4");
        linkedList.add("element_5");

        Iterator it = linkedList.iterator();

        System.out.println("LinkedList elements :");
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        it.remove();
        System.out.println("LinkedList elements :");
        Iterator it1 = linkedList.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
    }
}
