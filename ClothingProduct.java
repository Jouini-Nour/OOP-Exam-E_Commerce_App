public class ClothingProduct extends Product {
    private String size;
    private String color;
    private String material;
    
    

    public ClothingProduct(int ref,String name, double price, int quantity, String size, String color, String material) {
        super(ref,name, price, quantity);
        this.size = size;
        this.color = color;
        this.material = material;
    }
    public Category getCategory() {
        return Category.CLOTHING;
    }
    
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
    public void displayDetails() {
        System.out.println("Product Details:");
        System.out.println("Category: " + getCategory());   
        System.out.println("Reference Number: " + getReferenceNumber());
        System.out.println("Name: " + getName());
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Material: " + material);
        System.out.println("Price: $" + getPrice());
        System.out.println("Quantity in Stock: " + getQuantity());
    }
    public String toString() {
        return "ClothingProduct{" +
                "Reference Number: " + getReferenceNumber() +
                ", name='" + getName() + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", price=" + getPrice() +
                ", quantity=" + getQuantity() +
                '}';
    }
}