Development tools
-------------------
The following tools will be used to develop the software; many will be discussed in class.

Java JDK (SE, version 7 or newer)
Android Studio (version 2.2 or newer)
Android SDK version 4 (ICS) or newer (comes with Android Studio)
Subversion/git version control system (integrated with Android Studio, served on CS UNIX)
HSQLDB database management system (version 1.8 is recommended)
JUnit testing framework
a tool for automated acceptance testing (Robotium or Espresso).
Or you may choose some alternatives to these tools; consult your instructor.


Project iterations
------------------------
The project will be developed in four iterations:

Iteration 0: project vision statement, big stories, user stories for iteration 1 (no coding).
Iteration 1: GUI for at least one big user story, model objects and some processing, unit testing for model objects and processing, stub database.
Iteration 2: replace stub with real SQL database, more stories, integration testing.
Iteration 3: acceptance testing, more stories, final release.

Project topics
------------------
Book ordering System



*************************************************************************
README.txt


xxx.zip file contains our project file and log.txt
The major source code files of our project includes:

comp3350project.bookorderingsystem folder:
business folder: AccessBook.java and AccessCustomer.java
objects folder: Book.java and Customer.java
persistence folder: DataAccessStub.java

comp3350project.bookorderingsystem.tests folder:
objects folder: BookTest.java, CustomerTest.java
ExampleUnitTest.java


log.txt locates inside the zip file

The major feature of our project includes search books by entering keyword, a list of search result that includes the title, author and price for each book. A book page can be accessed by clicking the search result that contain more detail about this book. Customer has option to add this book to cart or wish list.

GUI locate the folder res/layout/activity_main.xml. 


