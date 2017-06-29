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


Iteration2.zip contains all project files, readme.txt and log.txt
The manager ACCOUNT NAME: dmb001 
		password: 0000
The customer¡¯s account can be created by user 
		Or account name: asdf
			Password: 1234


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
	


Git clone: https://github.com/RedRect/Android_BookOrdering.git
repository location: git@github.com:RedRect/Android_BookOrdering.git
Location of log.txt : Android_BookOrdering/log.txt

The major change in main page is that user need to log in before viewing the list of books.
The issues that we were not able to resolve in this iteration are : 
	1. The big story "sort" need to sort the list of books in different order, but we could not let the system sort the searched result.
	2. We couldn't upload a picture as the cover for this book when adding a new book to the database.

	
