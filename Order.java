import java.util.Date;
import java.util.Map;


public class Order {

    private Map<Product,Integer> orderedProducts;
    private double totalAmount;
    private Date orderDate;
    private ShipmentMethod shippingMethod;


    public Order(Map<Product,Integer> orderedProducts) {
        this.orderedProducts =orderedProducts ;
        calculateTotalAmount();
        this.orderDate = new Date(); // Set the order date to the current date and time
    }

    private void calculateTotalAmount() {
        this.totalAmount =0;
        for (Map.Entry<Product,Integer> entry : orderedProducts.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            this.totalAmount += product.getPrice() * quantity;
        }
    }

    // Getters for order details
    public Map<Product,Integer> getOrderedProducts() {
        return orderedProducts;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String toString() {
        String orderDetails = "";
        for (Product product : orderedProducts.keySet()) {
            int quantity = orderedProducts.get(product);
            orderDetails += "Name: " + product.getName() + ", Quantity: " + quantity + ", Price: $" + product.getPrice() + "\n";
        }
        orderDetails += "Total amount: $" + totalAmount + "\n";
        orderDetails += "Order date: " + orderDate + "\n";
        return orderDetails;
    }
    public void updateInventory() {
        for (Map.Entry<Product, Integer> entry : orderedProducts.entrySet()) {
            Product product = entry.getKey();
            int purchasedQuantity = entry.getValue();
            product.updateInventory(purchasedQuantity);
        }
    }

    public ShipmentMethod getShipmentMethod() {
        return shippingMethod;
    }

    public void setShipmentMethod(ShipmentMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
        if(shippingMethod == ShipmentMethod.STANDARD){
            this.totalAmount += 5;
        }
        else if(shippingMethod == ShipmentMethod.EXPRESS){
            this.totalAmount += 10;
        }
    }

    
}