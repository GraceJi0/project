*************************************************************************
README.txt

Project topic: Book ordering System

Group Number: 10
Team member:
Ruopu Zhang (7723669)
Junjie Lin (7780545)
Dinghan Ji (7797141)
Xinyu Li (7768642)
Jianwen Chen (7749375)

iteration1.zip file contains our project file and log.txt


Developer tasks:

The big user story we choose to finish in this iteration is “Search a book” and ”View book’s information”.

1. crate basic classes/objects: (1 hr)
	-Book class
	-Customer class
2. create database (4 hr)
	- make sure to link database so that other class/object can access data
3. crate business class (access database) (1 hr)
	-AccessBook class
	-AccessCustomer class  
4. design UI 
	-main page, include search bar, search button, category button, account button, display logo image (3 hr)
	-search result page, get a list of book from database that contain the keyword (3 hr, can be worked with search activity part)
	-book detail page, include image, title, author, price, and customer review. (3 hr)
5. main activity
	-code part, search method, access data from DB (3 hr)
	-UI part, search bar, buttons ( 3 hr)
6. search activity (2 hr)
	-display search result
	-another search bar
	-link search result to the book detail page
7. view book activity (4 hr)
	-display book’s detail information 
	-add image
	
8. test of Book class, Customer class, AccessBook class and AccessCustomer class (2 hr)





The package major source code files of our project includes:


comp3350project.bookorderingsystem folder:
application folder: Main.java, Service.java
business folder: AccessBook.java and AccessCustomer.java
objects folder: Book.java and Customer.java
persistence folder: DataAccessStub.java

comp3350project.bookorderingsystem.tests folder:
objects folder: BookTest.java, CustomerTest.java
ExampleUnitTest.java


log.txt locates inside the zip file
repository location: git@github.com:RedRect/Android_BookOrdering.git

The major feature of our project includes search books by entering keyword (locate in the main of the GUI), a list of search result that includes the title, author and price for each book (locate in the search result page). A book page can be accessed by clicking the search result that contain more detail about this book. Customer has option to add this book to cart or wish list (also locate in the book detail page).




