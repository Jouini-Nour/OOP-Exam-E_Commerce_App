import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Product, Integer> cartItems;
    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    public ShoppingCart() {
        this.cartItems = new HashMap<>();
    }

    public void addProductToCart(Product product, int quantity) {
        if (cartItems.containsKey(product)) {
            int currentQuantity = cartItems.get(product);
            cartItems.put(product, currentQuantity + quantity);
        } else {
            cartItems.put(product, quantity);
        }
        System.out.println(quantity + " " + product.getName() + "(s) added to the cart.");
    }

    public void updateProductQuantity(Product product, int newQuantity) {
        if (cartItems.containsKey(product)) {
            cartItems.put(product, newQuantity);
            System.out.println("Quantity of " + product.getName() + " updated to " + newQuantity + ".");
        } else {
            System.out.println(product.getName() + " not found in the cart.");
        }
    }

    public void removeProductFromCart(Product product) {
        if (cartItems.containsKey(product)) {
            cartItems.remove(product);
            System.out.println(product.getName() + " removed from the cart.");
        } else {
            System.out.println(product.getName() + " not found in the cart.");
        }
    }

    public void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Your shopping cart is empty.");
        } else {
            System.out.println("Products in your shopping cart:");
            for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("Name: " + product.getName() + ", Quantity: " + quantity + ", Price: $" + product.getPrice());
            }
        }
    }
}