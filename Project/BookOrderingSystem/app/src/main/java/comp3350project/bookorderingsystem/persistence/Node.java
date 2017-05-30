package comp3350project.bookorderingsystem.persistence;

import comp3350project.bookorderingsystem.objects.Data;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class Node {
    private Data data;
    private Node next;

    public Node (Data newData, Node newNext)
    {
        data = newData;
        next = newNext;
    }

    public Data getData(){return data;}

    public void setData(Data newData){data = newData;}

    public Node getNext(){return next;}

    public void setNext(Node newNext){next = newNext;}
}
