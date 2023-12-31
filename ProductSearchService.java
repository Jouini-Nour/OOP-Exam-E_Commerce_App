import java.util.ArrayList;
import java.util.Scanner;

public class ProductSearchService {

    public void searchAndFilterProducts(ArrayList<Product> products, Scanner sc) {
        boolean stay = true;
        while (stay) {
            System.out.println("Product Search and Filtering:");
            System.out.println("1. Search Products");
            System.out.println("2. Filter Products");
            System.out.println("3. Back");

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
                    searchProducts(products, sc);
                    break;
                case 2:
                    filterProducts(products, sc);
                    break;
                case 3:
                    stay = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchProducts(ArrayList<Product> products, Scanner sc) {
        System.out.print("Enter the search keyword: ");
        String keyword = sc.nextLine().toLowerCase();

        ArrayList<Product> searchResults = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword)) {
                searchResults.add(product);
            }
        }

        displaySearchResults(searchResults);
    }

    private void filterProducts(ArrayList<Product> products, Scanner sc) {
        System.out.println("Filtering Options:");
        System.out.println("1. Filter by Category");
        System.out.println("2. Filter by Price Range");
        System.out.println("3. Back");

        System.out.print("Enter your choice: ");
        int choice;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                filterByCategory(products, sc);
                break;
            case 2:
                filterByPriceRange(products, sc);
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void filterByCategory(ArrayList<Product> products, Scanner sc) {
        System.out.print("Enter the category to filter by: ");
        String category = sc.nextLine().toLowerCase();

        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().toString().toLowerCase().equals(category)) {
                filteredProducts.add(product);
            }
        }

        displaySearchResults(filteredProducts);
    }

    private void filterByPriceRange(ArrayList<Product> products, Scanner sc) {
        System.out.print("Enter the minimum price: ");
        double minPrice;
        try {
            minPrice = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid price.");
            return;
        }

        System.out.print("Enter the maximum price: ");
        double maxPrice;
        try {
            maxPrice = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid price.");
            return;
        }

        ArrayList<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }

        displaySearchResults(filteredProducts);
    }

    private void displaySearchResults(ArrayList<Product> searchResults) {
        if (!searchResults.isEmpty()) {
            System.out.println("Search Results:");
            for (Product product : searchResults) {
                System.out.println(product);
            }
        } else {
            System.out.println("No matching products found.");
        }
    }
}