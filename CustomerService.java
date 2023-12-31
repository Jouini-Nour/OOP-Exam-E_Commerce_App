import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;

public class CustomerService {
    public void manageCustomer(User customer,ArrayList<Product> products,ShoppingCart cart,Scanner sc) {
    boolean customerChoice = true;
    while (customerChoice) {
        System.out.println("Welcome, our Dear Customer.");
        System.out.println("Try this product:");
        Random rand = new Random();
        int randomIndex = rand.nextInt(products.size());
        Product randomProduct = products.get(randomIndex);
        randomProduct.displayDetails();
        System.out.println("Enter this code when checking out to recieve a 10% discount: ABCD");
        System.out.println("1. View Products");
        System.out.println("2. Search Products");
        System.out.println("3. View Cart");
        System.out.println("4. Update Cart");
        System.out.println("5. Remove from Cart");
        System.out.println("6. Checkout");            
        System.out.println("7. Order History");
        System.out.println("8. Account Information");
        System.out.println("9. Deposit Money");
        System.out.println("10. Logout");

        System.out.print("Enter your choice: ");
        int choice;
        do{
            try {
                choice = Integer.parseInt(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
        } while(true);

        switch (choice) {
            case 1:
                viewProducts(products);
                break;
            case 2:
                ProductSearchService searchService = new ProductSearchService();
                searchService.searchAndFilterProducts(products, sc);
                System.out.println("This is the Apps Search Bar: ");
                searchProducts(cart,products,sc);
                break;
            case 3:
                // View Cart
                cart.viewCart();
                break;
            case 4:
                // Update Cart
                System.out.println("Here is your cart: ");
                cart.viewCart();
                updateProduct(cart, sc);
                break;
            case 5:
                // Remove from Cart
                System.out.println("Here is your cart: ");
                cart.viewCart();
                deleteProduct(cart, sc);
                break;
            case 6:
                checkout(customer,cart,sc);
                break;
            case 7:
                viewOrderHistory(customer);
                break;
            case 8:
                viewAccountInformation(customer);
                break;
            case 9:
                System.out.print("Enter the amount to deposit: ");
                double amount;
                do{
                    try {
                        amount = Double.parseDouble(sc.nextLine());
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }
                } while(true);
                customer.setBalance(customer.getBalance() + amount);
                System.out.println("Your new balance is: $" + customer.getBalance());
                break;    
            case 10:
                customerChoice = false;
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}


private  void viewProducts(ArrayList<Product> products) {
    for (Product product : products) {
        product.displayDetails();
    }
}

private  void searchProducts(ShoppingCart cart,ArrayList<Product> products,Scanner sc) {
    System.out.print("Enter the reference number of the product: ");
    int ref ;
    do{
        try {
            ref = Integer.parseInt(sc.nextLine());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }
    } while(true);
    boolean found = false;
    for (Product product : products) {
        if (product.getReferenceNumber() == ref) {
            product.displayDetails();
            found = true;
            System.out.println("Do you want to add this product to cart? (yes/no): ");
            String choice = sc.nextLine().toLowerCase();
            if ("yes".equals(choice)) {
                System.out.print("Enter the quantity: ");
                int quantity = sc.nextInt();
                sc.nextLine(); // Consume the newline character
                if(quantity>product.getQuantity()){
                    System.out.println("Sorry, the quantity you entered is more than the available quantity.");
                    break;
                }
                cart.addProductToCart(product, quantity);
            }
            break;
        }
    }
    if (!found) {
        System.out.println("Product not found.");
    }
}
private void checkout(User customer, ShoppingCart cart, Scanner sc) {
    if (!cart.getCartItems().isEmpty()) {
        PaymentService payService =new PaymentService();
        double totalAmount = payService.calculateTotalAmount(cart);
        System.out.println("Total Amount: $" + totalAmount);
        
        System.out.print("Do you want to proceed with the payment? (yes/no): ");
        String choice = sc.nextLine().toLowerCase();
        if("yes".equals(choice)){
            boolean paymentStatus = payService.processPayment(customer, totalAmount,sc);
            if(paymentStatus){
                Order order = new Order(cart.getCartItems());
                String shipment;
                do{
                    System.out.println("Choose a shipment Method (stadard/express): ");
                    shipment = sc.nextLine().toLowerCase();
                    if("standard".equals(shipment)){
                        order.setShipmentMethod(ShipmentMethod.STANDARD);
                        customer.setBalance(customer.getBalance() - 5);
                        break;
                    }
                    else if("express".equals(shipment)){
                        order.setShipmentMethod(ShipmentMethod.EXPRESS);
                        customer.setBalance(customer.getBalance() - 10);
                        break;
                    }
                    else{
                        System.out.println("Invalid choice. Please try again.");
                    }
                }while(true);
                // Display order details
                System.out.println("Order placed successfully:");
                System.out.println(order);
                order.updateInventory();
                // Update user's transaction history
                customer.addOrder(order);                    // Clear the cart after the order is placed
                
                cart.getCartItems().clear();
            }
            else{
                System.out.println("Payment Failed.");
            }
        }else{
            System.out.println("Your Payment is cancelled.");
        }    
        // Create an order and transition from cart to order completion  
    } else {
        System.out.println("Your cart is empty. Add products before checking out.");
    }
}

private void viewOrderHistory(User customer) {
    List<Order> orderHistory = customer.getOrders();
    if (!orderHistory.isEmpty()) {
        System.out.println("Order History for " + customer.getUsername() + ":");
        for (Order order : orderHistory) {
            System.out.println("Order Date: " + order.getOrderDate());
            for (Map.Entry<Product, Integer> entry : order.getOrderedProducts().entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("  Product Name: " + product.getName() + ", Quantity: " + quantity);
            }
            System.out.println("Total amount: $" + order.getTotalAmount());
            System.out.println("------------------------------");
        }
    } else {
        System.out.println("No order history available.");
    }
}
private  void viewAccountInformation(User customer) {
    System.out.println("Account Information:");
    System.out.println("Name: " + customer.getUsername());
    System.out.println("Password: " + customer.getPassword());  
    System.out.println("Balance: $" + customer.getBalance());
    
}



private void updateProduct(ShoppingCart shoppingCart, Scanner sc) {
    // Update product quantity in cart
    System.out.print("Enter the name of the product to be updated: ");
    String productName = sc.nextLine();

    // Search for the product in the list
    Product foundProduct = null;
    for (Product product : shoppingCart.getCartItems().keySet()) {
        if (product.getName().equalsIgnoreCase(productName)) {
            foundProduct = product;
            break;
        }
    }

    if (foundProduct != null) {
        // Prompt for the new quantity
        int newQuantity;
        do {
            System.out.print("Enter the new quantity (must be non-negative): ");
            newQuantity = sc.nextInt();
            sc.nextLine(); 

            if (newQuantity < 0) {
                System.out.println("Invalid quantity. Please enter a non-negative value.");
            }
        } while (newQuantity < 0);
        // Update the quantity in the shopping cart
        shoppingCart.updateProductQuantity(foundProduct, newQuantity);
    } else {
        System.out.println("Product not found in the cart.");
    }
}

private void deleteProduct(ShoppingCart shoppingCart, Scanner sc) {
    // Remove product from cart
    System.out.print("Enter the name of the product to be removed: ");
    String productName = sc.nextLine();

    // Search for the product in the shopping cart
    Product foundProduct = null;
    for (Product product : shoppingCart.getCartItems().keySet()) {
        if (product.getName().equalsIgnoreCase(productName)) {
            foundProduct = product;
            break;
        }
    }

    if (foundProduct != null) {
        // Remove the product from the shopping cart
        shoppingCart.removeProductFromCart(foundProduct);
    } else {
        System.out.println("Product not found in the cart.");
    }
}   
}
