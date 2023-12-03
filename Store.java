import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Store {
    private Map<String, Product> products;
    
    public Store() {
    	products = new HashMap<String, Product>();
    	this.readFromFile();
    }

    // Constructors, getters, setters
    public void addProduct(String name, Product product) 
    {
        products.put(name, product);
    }

    public void removeProduct(String name) 
    {
        products.remove(name);
    }

    public Product searchProduct(String name) {
    	// If the product does not exists, the method returns a NULL
        return products.get(name);
    }
    
    private void readFromFile() {
    	try (BufferedReader reader = new BufferedReader(new FileReader("store.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("-----------------------------------")) 
                {
                    // Skip the dashed line
                    continue;
                }

                String name = line.trim();
                double price = Double.parseDouble(reader.readLine().trim());
                int inventoryLevel = Integer.parseInt(reader.readLine().trim());
                double discountRate = Double.parseDouble(reader.readLine().trim());

                
                Product product = new Product(name, price, inventoryLevel, discountRate);

                // Add the product to the products map
                if(product != null)
                {
                    products.put(name, product);
                }
            }
        } 
    	catch (IOException | NumberFormatException e) 
    	{
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
    
 // Method to convert the products map to a formatted string
    private String ToString() {
        StringBuilder result = new StringBuilder();  // Using StringBuilder for much more efficient concatenation

        for (Map.Entry<String, Product> entry : products.entrySet()) 
        {
            Product product = entry.getValue();

            result.append(product.getName()).append("\n");
            result.append(product.getPrice()).append("\n");
            result.append(product.getInventoryLevel()).append("\n");
            result.append(product.getDiscountRate()).append("\n");
            result.append("-----------------------------------").append("\n");
        }

        return result.toString();  // Converting the result to string 
    }
    
 // Method to save the string representation to a file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("store.txt")))
        {
            writer.write(this.ToString());
        } 
        catch (IOException e) 
        {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
    
    public void Display() { // Displays the current products in the store with details like name, price, discount, and availability.
    	for (Map.Entry<String, Product> entry : products.entrySet()) {
    		Product product = entry.getValue();
    		
			System.out.println("Item: " + product.getName());
			System.out.println("Price: " + product.getPrice());
			System.out.println("Discout: " + product.getDiscountRate() * 10 + "%");
			if(product.getInventoryLevel() > 0) {
				System.out.println("Availability: In Stock");
			}
			else {
				System.out.println("Availability: Out of Stock");
			}
			System.out.println("----------------------");
		}
    }
}


