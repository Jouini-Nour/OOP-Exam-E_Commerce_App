import java.util.Scanner;
import java.util.ArrayList;

public class ECommerceApp {
    public static void main(String[] args) {
        AuthenticationService authService = new AuthenticationService();
        ProductManagementService proService = new ProductManagementService();
        CustomerService custService = new CustomerService();
        ArrayList<Product> products = new ArrayList<>();
        ShoppingCart cart = new ShoppingCart();
        Scanner sc = new Scanner(System.in);
        String signupChoice,loginChoice;
        User currentUser=null;
        boolean useChoice = true;//I made this variable to check whether the app user wants to use the app
        while(useChoice){
            
            //sign Up Loop
            //The app user can create as many accounts as he wants
            do {
                System.out.print("Do you want to sign up? (yes/no): ");
                signupChoice = sc.nextLine().toLowerCase();
    
                if ("yes".equals(signupChoice)) {
                    System.out.print("Enter a username: ");
                    String newUsername = sc.nextLine();
    
                    System.out.print("Enter a password: ");
                    String newPassword = sc.nextLine();
    
                    System.out.print("Choose a role (customer/admin): ");
                    String roleChoice = sc.nextLine().toLowerCase();
    
                    Role selectedRole = Role.CUSTOMER; // Default to customer
                    if ("admin".equals(roleChoice)) {
                        selectedRole = Role.ADMIN;
                    }
    
                    authService.signUp(newUsername, newPassword, selectedRole);
                }
    
            } while (!"no".equals(signupChoice));

            //login loop similar  to the signup loop
            do {
                System.out.print("Do you want to log in? (yes/no): ");
                loginChoice = sc.nextLine().toLowerCase();
                if("yes".equals(loginChoice)){
                    System.out.print("Enter a username: ");
                    String logUsername = sc.nextLine();
    
                    System.out.print("Enter a password: ");
                    String logPassword = sc.nextLine();
                    currentUser = authService.authenticateUser(logUsername, logPassword);
                    if(currentUser!=null){
                        break;
                    }
                    else{
                        System.out.println("Login failed. Try again.");
                    }
                }
            }while(!("no".equals(loginChoice)));
            while(currentUser !=null){
                System.out.println("You are currently logged in as "+currentUser.getUsername());
                switch(currentUser.getRole()){
                    case ADMIN:
                        System.out.println("You have ADMIN Privileges.");
                        proService.manageProducts(products, sc);
                        break;
                    case CUSTOMER:
                        custService.manageCustomer(currentUser, products,cart, sc);
                        break;
                }
                System.out.print("Do you want to log out? (yes/no): ");
                String logoutChoice = sc.nextLine().toLowerCase();
                if("yes".equals(logoutChoice)){
                    currentUser = null;
                    System.out.println("Logging Out ...");
                }
            }
            System.out.print("Do you want to quit the app? (yes/no): ");
            String userchoice  = sc.nextLine().toLowerCase();
            if("yes".equals(userchoice)){
                useChoice = false;
            }
        }
        System.out.println("Quitting the App.Goodbye <3.");
        sc.close();
    }
}