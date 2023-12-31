***Session 1
I implemented the first project requirement wich is the user authentication service . My method was mostly 
based on conditional loops to keep waiting for a specific user input in order to move to the next "part" of
the menu. I decide to go with this idea(conditional loops) for implementing the signup, login and logout
logics because of its similarity to real world example, where a failure to login would cause the web page 
to refresh and let the user try again with the same menu. The authetication service uses a hash map to store
passwords of various created users.A simple and intuitive feedback (in the form of messages) is set in place
responding to every action made by the User. Both the Customer and Admin menus aren't clear right now but
my current understanding is that they share no commun functionalities which is why i decide to use a switch
statement instead of conditional logic. I will be moving on next time to the product management .
***Session 2
I created the ProductManagementService Class that handels products. I decided to keep the category choices 
simple with only 3 possibilities. Different methods such as "isProductredundant" and "verifref" where 
implemented to ensure no duplicates where made. Considering error handlinf when interacting with the menu 
it's still not set in place (MismatchExceptions must be added to avoid crashing when the program is presented
with an unexpected value) 
***Session3
-Session ended with the project finished
-Error Handling mechanism were added in the form of try and catch loops arounf every input that might prompt 
the User to enter an unexpected result
-Discount and promotion feature was chosen. promotions are selected randomly and displayed in the Menu for the 
customer. Discount is only used when placing orders with a specefic code
-Shipping methods were added. That would increase the order total amount. The only problem is that I complicated 
it a bit so the user can not place an order if they don't have enough money to support the express shipping method 
even though they can chose not to go with it. 
-Inventory management lets the admin chose whatever threshhold he wants to check with and act accordingly 
-Decided to make the shopping cart a map(Product,integer) to track the quantity of each product bought 
