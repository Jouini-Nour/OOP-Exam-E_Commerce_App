public class ElectronicProduct extends Product {
    private String brand;
    private String model;
    private int warrantyMonths;
    


    public ElectronicProduct(int ref,String name, double price, int quantity, String brand, String model, int warrantyMonths) {
        super(ref,name, price, quantity);
        this.brand = brand;
        this.model = model;
        this.warrantyMonths = warrantyMonths;
    }
    public Category getCategory() {
        return Category.ELECTRONICS;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getWarrantyMonths() {
        return warrantyMonths;
    }
    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }
    public void displayDetails() {
        System.out.println("Product Details:");
        System.out.println("Category: " + getCategory());
        System.out.println("Reference Number: " + getReferenceNumber());
        System.out.println("Name: " + getName());
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity in Stock: " + getQuantity());
        System.out.println("Warranty: " + warrantyMonths + " months");
    }
    public String toString() {
        return "ElectronicProduct{" +
                "Reference Number: " + getReferenceNumber() + 
                ", name='" + getName() + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                ", warrantyMonths=" + warrantyMonths +
                '}';
    }
}