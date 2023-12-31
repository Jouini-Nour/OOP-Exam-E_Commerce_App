import java.util.ArrayList;
import java.util.Scanner;
public class ProductManagementService {
    private void createProduct(ArrayList<Product> products,Scanner sc){
        System.out.print("Product Reference Number: ");
        int ref ;
        try {
            ref = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        if(validateRef(products,ref)){
            System.out.println("Sorry the reference number You tried to add already exists.");
            return;
        }
        System.out.print("Name of the product: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        double price;
        try {
            price = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        System.out.print("Quantity: ");
        int quantity ;
        try {
            quantity = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        System.out.print("Category (Electronics/Clothing/Beauty): ");
        String category = sc.nextLine().toLowerCase();
        Product newProduct;
        switch (category) {
            case "electronics":
                System.out.print("Brand: ");
                String brand = sc.nextLine();
                System.out.print("Model: ");
                String model = sc.nextLine();
                System.out.print("Warranty months: ");
                int warrantyMonths ;
                try {
                    warrantyMonths = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    return;
                }
                newProduct = new ElectronicProduct(ref,name, price, quantity, brand, model, warrantyMonths);
                break;
            case "clothing":
                System.out.print("Size: ");
                String size = sc.nextLine();
                System.out.print("Color: ");
                String color = sc.nextLine();
                System.out.print("Material: ");
                String material = sc.nextLine();
                newProduct = new ClothingProduct(ref,name, price, quantity, size, color, material);
                break;
            case "beauty":
                System.out.print("Brand: ");
                String brandb = sc.nextLine();
                System.out.print("Type: ");
                String type = sc.nextLine();
                System.out.print("Target Audience: ");
                String targetAudience = sc.nextLine();
                newProduct = new BeautyProduct(ref,name, price, quantity, brandb, type, targetAudience);
                break;
            default:
                System.out.println("Invalid category.");
                return;
        }
        if(isProductredudant(products,newProduct)){
            System.out.println("Sorry the product You tried to add already exists.");
        }else{
        products.add(newProduct);
        System.out.println("Product added successfully.");
        }
    }
    private void displayProducts(ArrayList<Product> products){
        System.out.println("Products in the store:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
    private void updateProduct(ArrayList<Product> products,Scanner sc){
        System.out.print("Enter the reference number of the product to be updated: ");
        int ref ;
        try {
            ref = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getReferenceNumber() == ref) {
                found = true;
                System.out.println("Product found.");
                System.out.println("1. Update Price");
                System.out.println("2. Update Quantity");
                System.out.println("3. Quit");
                System.out.print("Enter your choice: ");
                int choice ;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    return;
                }
                switch (choice) {
                    case 1:
                        System.out.print("Enter the new price: ");
                        double price ;
                        try {
                            price = Double.parseDouble(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            return;
                        }
                        products.get(i).setPrice(price);
                        System.out.println("Price updated successfully.");
                        break;
                    case 2:
                        System.out.print("Enter the new quantity: ");
                        int quantity ;
                        try {
                            quantity = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            return;
                        }
                        products.get(i).setQuantity(quantity);
                        System.out.println("Quantity updated successfully.");
                        break;
                    case 3:
                        System.out.println("Quitting the update menu.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Product not found.");
        }
    }
    private static void deleteProduct(ArrayList<Product> products,Scanner sc) {
        System.out.print("Enter the reference number of the product to be deleted: ");
        int ref ;
        try {
            ref = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }
        boolean found = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getReferenceNumber() == ref) {
                products.remove(i);
                found = true;
                break;
            }
        }
        if (found) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
        
    }
    public void manageProducts(ArrayList<Product> products,Scanner sc){
        boolean stay=true;
        while (stay) {
            System.out.println("1. Create Product");
            System.out.println("2. Display Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Manage Stock");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice ;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    createProduct(products,sc);
                    break;
                case 2:
                    displayProducts(products);
                    break;
                case 3:
                    updateProduct(products,sc);
                    break;
                case 4:
                    deleteProduct(products,sc);
                    break;
                case 5:
                    manageStock(products,sc);
                    break;
                case 6:
                    System.out.println("Quitting the Product Management Service.");
                    stay =false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }
    private boolean validateRef(ArrayList<Product> products,int ref){
        for (Product product : products) {
            if(product.getReferenceNumber()==ref){
                return true;
            }
        }
        return false;
    }
    private boolean isProductredudant(ArrayList<Product> products,Product p){
        for (Product product : products) {
            switch(p.getCategory()){
                case ELECTRONICS:
                    if(product instanceof ElectronicProduct){
                        ElectronicProduct ep = (ElectronicProduct)product;
                        ElectronicProduct ep2 = (ElectronicProduct)p;
                        if(ep.getName().equals(ep2.getName()) && ep.getBrand().equals(ep2.getBrand()) && ep.getModel().equals(ep2.getModel())){
                            return true;
                        }
                    }
                    break;
                case CLOTHING:
                    if(product instanceof ClothingProduct){
                        ClothingProduct cp = (ClothingProduct)product;
                        ClothingProduct cp2 = (ClothingProduct)p;
                        if(cp.getName().equals(cp2.getName()) && cp.getColor().equals(cp2.getColor()) && cp.getSize().equals(cp2.getSize())){
                            return true;
                        }
                    }
                    break;
                case BEAUTY:
                    if(product instanceof BeautyProduct){
                        BeautyProduct bp = (BeautyProduct)product;
                        BeautyProduct bp2 = (BeautyProduct)p;
                        if(bp.getName().equals(bp2.getName()) && bp.getBrand().equals(bp2.getBrand()) && bp.getType().equals(bp2.getType())){
                            return true;
                        }
                    }
                    break;
            }
        }
        return false;
    }
    private void viewStock(ArrayList<Product> products) {
        System.out.println("Stock Status:");
        for (Product product : products) {
            System.out.println(product.getName() + ": " + product.getQuantity() + " units");
        }
    }

    private void checkLowStock(ArrayList<Product> products, int threshold) {
        System.out.println("Low Stock Check:");
        for (Product product : products) {
            if (product.getQuantity() < threshold) {
                System.out.println(product.getName() + " is running low on stock. Quantity: " + product.getQuantity());
            }
        }
    }

    private void restockProduct(ArrayList<Product> products, Scanner sc) {
        System.out.print("Enter the name of the product to restock: ");
        String productName = sc.nextLine();
        Product foundProduct = findProductByName(products, productName);

        if (foundProduct != null) {
            System.out.print("Enter the quantity to restock: ");
            int restockQuantity;
            try {
                restockQuantity = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid quantity.");
                return;
            }

            foundProduct.restock(restockQuantity);
            System.out.println(productName + " has been restocked by " + restockQuantity + " units. New quantity: " + foundProduct.getQuantity());
        } else {
            System.out.println("Product not found.");
        }
    }

    private Product findProductByName(ArrayList<Product> products, String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public void manageStock(ArrayList<Product> products, Scanner sc) {
        boolean stay = true;
        while (stay) {
            System.out.println("1. View Stock");
            System.out.println("2. Check Low Stock");
            System.out.println("3. Restock Product");
            System.out.println("4. Back");

            System.out.print("Enter your choice: ");
            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    viewStock(products);
                    break;
                case 2:
                    System.out.print("Enter the low stock threshold: ");
                    int threshold;
                    try {
                        threshold = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid threshold.");
                        continue;
                    }
                    checkLowStock(products, threshold);
                    break;
                case 3:
                    restockProduct(products, sc);
                    break;
                case 4:
                    stay = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
