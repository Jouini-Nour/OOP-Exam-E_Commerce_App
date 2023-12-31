import java.util.Map;
import java.util.Scanner;

public class PaymentService {

    public boolean processPayment(User user, double totalAmount,Scanner sc) {
        System.out.println("Payment Processing:");
        System.out.println("Total Amount: $" + totalAmount);
        boolean distry  =true;
        while(distry){
            System.out.println("Do you have a discount code? (yes/no): ");
            String choice = sc.nextLine().toLowerCase();
            if ("yes".equals(choice)) {
                System.out.print("Enter the discount code: ");
                String discountCode = sc.nextLine();
                if (discountCode.equals("ABCD")) {
                    totalAmount = totalAmount * 0.9;
                    System.out.println("Discount code applied. 10% discount.");
                } else {
                    System.out.println("Invalid discount code.");
                }
            }
            if("no".equals(choice)){
                distry = false;
            }
            else{
                System.out.println("Invalid choice. Please try again.");
            }
        }
        if (user.getBalance() >= totalAmount +10) {
            System.out.println("Balance: $" + user.getBalance());
            System.out.println("Payment successful! Thank you for your purchase.");

            // Deduct the total amount from the user's balance
            user.setBalance(user.getBalance() - totalAmount);

            return true;
        } else {
            System.out.println("Insufficient balance. Please add funds to your account.");
            return false;
        }
    }
    public double calculateTotalAmount(ShoppingCart cart) {
        double totalAmount = 0;
        Map<Product, Integer> cartItems = cart.getCartItems();
        for (Product product : cartItems.keySet()) {
            int quantity = cartItems.get(product);
            totalAmount += product.getPrice() * quantity;
        }
        return totalAmount;
    }
}