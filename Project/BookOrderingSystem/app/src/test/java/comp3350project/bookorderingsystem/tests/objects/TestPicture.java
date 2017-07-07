package comp3350project.bookorderingsystem.tests.objects;

import comp3350project.bookorderingsystem.R;

import comp3350project.bookorderingsystem.objects.Picture;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPicture
{
    @Before
    public void setUp()
    {
        //leave it blank
    }

    @Test
    public void testPicture()
    {
        Picture thePic;

        System.out.println("\nStarting testPicture");

        int tempPid = 0;    //set the temp id to 0
        int tempPicture = R.drawable.noimage;   //set the temp picture to no image
        thePic = new Picture(tempPid, tempPicture); //create the test object

        assertNotNull(thePic);  //the Picture Object should be created correctly
        assertTrue(tempPid == thePic.getPID()); //test the getter method
        assertTrue(tempPicture == thePic.getPicture());

        tempPicture = R.drawable.book1; //test the setter method
        thePic.setPicture(tempPicture);
        assertTrue(tempPicture == thePic.getPicture());

        System.out.println("Finished testPicture");
    }
}