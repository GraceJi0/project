package com.example.dinghanji.a3350_iteration1.application.persistence;

import com.example.dinghanji.a3350_iteration1.application.objects.Book;
import com.example.dinghanji.a3350_iteration1.application.objects.Data;

/**
 * Created by dinghanji on 2017-05-29.
 */

public class LinkedList {
    Node top;

    public LinkedList(){top = null;}

    public void insert(Data newData)
    {
        Node curr = top;
        Node prev = null;
        while((curr != null) && curr.getData().compareName(newData)< 0 )
        {
            prev = curr;
            curr = curr.getNext();
        }
        if(prev != null)
        {
            prev.setNext(new Node(newData,curr));
        }
        else
        {
            top = new Node(newData, top);
        }
    }

    public void delete(Data newData)
    {
        Node prev = null;
        Node curr = top;

        while(curr != null && curr.getData().compareName(newData) != 0)
        {
            prev = curr;
            curr = curr.getNext();
        }
        if(curr != null)
        {
            if(prev == null)
            {
                top = curr.getNext();
            }
            else
            {
                prev.setNext(curr.getNext());
            }
        }
    }

    public Node search(Data newData)
    {
        Node curr = top;

        while(curr != null && curr.getData().compareName(newData) != 0)
        {
            curr = curr.getNext();
        }
        return curr;
    }

    public double getOrderAmount()
    {
        double result = 0;
        Node curr = top;
        while(curr != null)
        {
            result += ((Book)curr.getData()).getBookPrice();
        }
        result += result * 0.13;
        return result;
    }

    public String printBooks()
    {
        Node curr = top;
        String result ="";
        while(curr != null)
        {
            result += "\n"+(Book)curr.getData();
        }
        return result;
    }

    public String printBooksInCategory(String newCategory)
    {
        Node curr = top;
        String result = "";
        while(curr != null)
        {
            if(((Book)curr.getData()).getCategory().equals(newCategory)) {
                result += "\n" + (Book) curr.getData();
            }
        }
        return result;
    }
}
