public class BeautyProduct extends Product {
    private String brand;
    private String type;
    private String targetAudience;

    public BeautyProduct(int ref,String name, double price, int quantity, String brand, String type, String targetAudience) {
        super(ref,name, price, quantity);
        this.brand = brand;
        this.type = type;
        this.targetAudience = targetAudience;

    }
    public Category getCategory() {
        return Category.BEAUTY;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTargetAudience() {
        return targetAudience;
    }
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }
    public void displayDetails() {
        System.out.println("Product Details:");
        System.out.println("Category: " + getCategory());
        System.out.println("Reference Number: " + getReferenceNumber());
        System.out.println("Name: " + getName());
        System.out.println("Brand: " + brand);
        System.out.println("Type: " + type);
        System.out.println("Target Audience: " + targetAudience);
        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity in Stock: " + getQuantity());
    }
    public String toString() {
        return "BeautyProduct{" +
                "Reference Number: " + getReferenceNumber() + 
                ", name='" + getName() + '\'' +
                ", brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", targetAudience='" + targetAudience + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                '}';
    }
}