public abstract class Product {
    private static final int LOW_STOCK_THRESHOLD = 3;
    private int referenceNumber;
    private String name;
    private double price;
    private int quantity;

    public int getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Product(int ref,String name, double price, int quantity) {
        referenceNumber = ref;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void updateInventory(int purchasedQuantity) {
        if (purchasedQuantity > 0 && purchasedQuantity <= quantity) {
            quantity -= purchasedQuantity;
        } else {
            System.out.println("Invalid quantity or insufficient stock.");
        }
    }
    public boolean isLowStock() {
        return quantity > 0 && quantity <= LOW_STOCK_THRESHOLD;  // Define a threshold as needed
    }

    public boolean isOutOfStock() {
        return quantity == 0;
    }
    public void restock(int quantity) {
        if (quantity > 0) {
            this.quantity += quantity;
        } else {
            System.out.println("Invalid quantity.");
        }
    }
    public abstract String toString() ;//for admin
    public abstract void displayDetails();//for User
    public abstract Category getCategory();    
}
