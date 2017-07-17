*****************************************************************************************************************************************************
README.txt
Iteration 1




Project topic: Book ordering System


Group Number: 10

Team member:

	Ruopu Zhang (7723669)

	Junjie Lin (7780545)

	Dinghan Ji (7797141)
	
	Xinyu Li (7768642)
	
	Jianwen Chen (7749375)



iteration1.zip file contains our project file and log.txt


Developer 

tasks:



The big user story we choose to finish in this iteration is “Search a book?and ”View book’s information?



1. create basic classes/objects: (1 hr)
	
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


comp3350project.bookorderingsystem 

folder:

application folder: Main.java, Service.java

business folder: AccessBook.java and AccessCustomer.java

objects folder: Book.java and Customer.java

persistence folder: DataAccessStub.java

comp3350project.bookorderingsystem.tests 
folder:
objects folder: BookTest.java, CustomerTest.java

ExampleUnitTest.java


log.txt locates inside the zip file


repository location: git@github.com:RedRect/Android_BookOrdering.git



The major feature of our project includes 
	search books by entering keyword (locate in the main of the GUI), 
	a list of search result that includes the title, author and price for each book (locate in the search result page).
	A book page can be accessed by clicking the search result that contain more detail about this book. 
	Customer has option to add this book to cart or wish list (also locate in the book detail page).






******************************************************************************************************************************* README.txt
Iteration 2

Project topic: Book ordering System

Group Number: 10
Team member:
Ruopu Zhang (7723669)
Junjie Lin (7780545)
Dinghan Ji (7797141)
Xinyu Li (7768642)
Jianwen Chen (7749375)


Iteration2.zip contains all project files, readme.txt and log.txt




The package major source code files of our project includes:


comp3350project.bookorderingsystem folder:

application folder: Main.java, Service.java
business folder: AccessBook.java and AccessCustomer.java
objects folder: Book.java , Customer.java, Picture.java
persistence folder: DataAccess.java, DataAccessStub.java, DataAccessObject.java, customerPersistence.java, bookPersistence.kava, customerPersistenceHSQL.java, bookPersistenceHSQL.java

comp3350project.bookorderingsystem.tests folder:
objects folder: TestBook.java, TestCustomer.java, TestPicture.java.
Business folder: TestAccessBook.java, TestAccessCustomer.java


AllTests.java and ExampleUnitTest.java






The manager ACCOUNT NAME: dmb001 
		password: 0000
The customer's account can be created by user 
		Or account name: asdf
			Password: 1234


Git clone: https://github.com/RedRect/Android_BookOrdering.git
repository location: git@github.com:RedRect/Android_BookOrdering.git
Location of log.txt : Android_BookOrdering/log.txt


The major change in main page is that user need to log in before viewing the list of books.

The issues that we were not able to resolve in this iteration are : 
	1. The big story "sort" need to sort the list of books in different order, but we could not let the system sort the searched result.
	2. We couldn't upload a picture as the cover for this book when adding a new book to the database.


The User stories we planned for this iteration:
1. WishList
2. Manage book information
3. Add a book into cart
4. Manage stock
5. Create an account



Developer tasks:

1. Create HSQL database, include these tables:
	-customer, for storing all information of customers
	-book, for storing all information of the books
	-cart, records name of customers and their carts
	-wishlist, records name of customers and their wishlists
  1) create sql(.script)files (1 hr)
  2) create DataAccessObject (8 hr)
  3) split the DataAccessObject (1hr)
	-customer
	-book

2. Design UI
	- Sign up interface (1 hr)
	- Sign in interface (1 hr)
	- Update main page (1 hr)
	- Account interface (1 hr)
	- Cart interface (3 hr)	
	- Wishlist interface (2 hr)
	- Manager interface 
		1) edit/view books (2 hr)
		2) edit/view customer (1 hr)
	- Category interface (1 hr)
	
3. Update main activity (2 hr)
	- code part, sign in & sign up
	- UI part, sign in & sign up dialog
4. AddBook activity (3 hr)
        - add new book to database
5. EditBook activity (2 hr)
        -edit book information
        -save edited book information
6. ManagerMain Activity (1 hr)
        -create buttons linked customerlist and booklist
7. ManagerViewBooks Activity (1 hr)
        -display book list
8. ManagerViewCustomer Activity (1 hr)
        -display customer list
9. Account Activity  (4 hr)
	-display books added to cart
	-display books added to wishlist
	-allow deletion and seletion
10. Set up sign in status (3 hr)

11. Testing 	(3 hr)
	objects:
		customer
		book
		picture
	business:
		AccessCustomer
		AccessBook
	






******************************************************************************************************************************* README.txt
Iteration 3

Project topic: Book ordering System

Group Number: 10
Team member:
Ruopu Zhang (7723669)
Junjie Lin (7780545)
Dinghan Ji (7797141)
Xinyu Li (7768642)
Jianwen Chen (7749375)


Iteration3.zip contains all project files, readme.txt and log.txt

Git clone: https://github.com/RedRect/Android_BookOrdering.git
repository location: git@github.com:RedRect/Android_BookOrdering.git
Location of log.txt : Android_BookOrdering/log.txt


The package major source code files of our project includes:

comp3350project.bookorderingsystem folder:
application folder: Main.java, Service.java
business folder: AccessBook.java, AccessCustomer.java and AccessOrder.java
objects folder: Book.java , Customer.java, Picture.java, Cart.java, CustomerOrder.java,  Order.java, WishList.java
persistence folder: DataAccess.java, DataAccessStub.java, DataAccessObject.java, customerPersistence.java, bookPersistence.kava, customerPersistenceHSQL.java, bookPersistenceHSQL.java, orderPersistence.java, orderPersistenceHSQL.java

comp3350project.bookorderingsystem.tests folder:
objects folder: TestBook.java, TestCustomer.java, TestPicture.java., TestOrder.java
Business folder: TestAccessBook.java, TestAccessCustomer.java, TestAccessOrder.java

AllTests.java and ExampleUnitTest.java

The issues that we were not able to resolve in this iteration are : 
	1. When customer filling order information, system donÕt check if address, card number, and email are valid
	2. We couldn't upload a picture as the cover for this book when adding a new book to the database.


***************Changing to our big user stories:
	change the user story "view tracking information" to "Manager manage orders"
	New big user story:

|                                   Manager manage orders						|
|  -As a manager, i want to be able to manage orders that customer placed.				|
|													|
|	Priority: high									cost: 3days	|

The User stories we planned for this iteration:
1. Check out
2. Manager manage orders


Developer tasks:
1.  UI
	-customer check out UI (3 hr)
	-customer view order list UI (1 hr)
	-customer view order detail UI(1 hr)
	-manager view order list UI (2 hr)
	-manager view order detail UI(1 hr)
2. check stock
	-no enough books error checking while checkout(1 hr)
	-update stock of the book from database after customer submitted an order(2 hr)
	
3. business layer
	-Create access order (0.5 hr)
	-update access book  (1 hr)
	-update access customer (1 hr)
4. Fix bug from iteration 2
	-case-sensitive	(0.5 hr)
	-violate the single-responsibility principle (2 hr)
	-lack of unit test (see testing part)
	- donÕt allow customer register account name start with Òdmb?0.5 hr) 
5. persistence layer
	-add orders table in .script file (2 hr)
	-create order function on HSQL and dataAccessObject (3 hr)
	-update previous persistence layer (1 hr)
6. Testing 
	-new object/method testing (5 hr)
	-due to the changes of pervious objects, testing part need to be edited (2 hr)
