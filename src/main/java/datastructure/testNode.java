package datastructure;

import org.junit.Before;
import org.junit.Test;

public class testNode {
    private MyLinkedList head = new MyLinkedList();
    @Before
    public void init(){
        head.addNode(1);
        head.addNode(2);
        head.addNode(3);
    }
    @Test
    public void testReverse(){
        printListReversely(head.head);
    }

    public void printListReversely(Node pListHead){
        if(pListHead != null){
            printListReversely(pListHead.next);
            System.out.println(pListHead.data);
        }
    }
}
