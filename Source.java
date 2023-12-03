import java.util.Scanner;

public class Source {
	public static Sale checkout(String customerName, Cart cart, Store store, Sale sale, Scanner scan) {
	    String input;

	    store.Display();
	    System.out.println("\nEnter the name of the product you want to add to the cart: ");
	    input = scan.next(); 

	    Product product = store.searchProduct(input); 

	    if (product == null) {
	        System.out.println("Product not found!");  
	    } else {
	        if (product.getInventoryLevel() <= 0) {
	            System.out.println("Product is out of stock!");
	        } 
	        else {
	            cart.addToCart(product, 1);
	            System.out.println("Product added to cart!");
	        }
	    }

	    System.out.println("Press 'P' to proceed to checkout. Else press any key to continue shopping. Enter choice: ");
	    input = scan.next();

	    if (input.equalsIgnoreCase("P")) {
	        cart.Display();
	        sale = new Sale(customerName, cart);
	        sale.Display();
	        
	        // Update inventory here (outside the recursive call)
	        updateInventory(cart, store);
	        store.saveToFile();
	        return sale;
	    } 
	    else {
	        // Continue shopping
	        return checkout(customerName, cart, store, sale, scan);
	    }
	}

	public static void updateInventory(Cart cart, Store store) {
	    // Iterate through items in the cart and update inventory
	    for (CartItem cartItem : cart.getItems()) {
	        Product product = cartItem.getProduct();
	        int quantity = cartItem.getQuantity();
	        // Update inventory
	        product.setInventoryLevel(product.getInventoryLevel() - quantity);
	    }
	}

	
	public static void main(String[] args) {
		Store store = new Store();
		Scanner scanner = new Scanner(System.in);
		Report report = new Report();
		
		while(true) { //This is the interface of the program
			System.out.println("1. Store Manager");
			System.out.println("2. Customer");  
			System.out.println("3. Exit");  
			int choice1 = scanner.nextInt();
			
			if(choice1 == 1) {
				while(true) {
					System.out.println("1. Add Product");  
					System.out.println("2. Remove Product");
					System.out.println("3. Update Inventory");
					System.out.println("4. Generate Report");
					System.out.println("5. Go Back");
					
					int choice2 = scanner.nextInt();
					
					if(choice2 == 1) {
						System.out.println("Enter product name: ");
						String pName = scanner.next();
						System.out.println("Enter product price:");
						double pPrice = scanner.nextDouble();
						System.out.println("Enter discout on product (0 -1): ");
						double pDiscount = scanner.nextDouble();
						System.out.println("Enter initial inventory level: ");
						int pInventory = scanner.nextInt();
						
						store.addProduct(pName, new Product(pName, pPrice, pInventory, pDiscount));
						store.saveToFile();
						
						System.out.println("Product Added!");
					}
					else if(choice2 == 2) {
						System.out.println("Enter product name: ");
						String pName = scanner.next();
						
						Product P = store.searchProduct(pName);
						if(P == null)
						{
							System.out.println("No product found!");
						}
						else 
						{
							store.removeProduct(pName);
							System.out.println("Product removed!");
						}
					}
					if(choice2 == 3) {
						System.out.println("Enter product name: ");
						String pName = scanner.next();
						
						Product P = store.searchProduct(pName);
						if(P == null)
						{
							System.out.println("No product found!");
						}
						else 
						{
							System.out.println("Enter quantity to add: ");
							int q = scanner.nextInt();
							P.setInventoryLevel(P.getInventoryLevel() + q);
							store.saveToFile();
							System.out.println("Inventory Updated!");
						}
					} else if(choice2 == 4) {
							report.Display();
					} else if(choice2 == 5) {
						break;
					} else {
						System.out.println("Invalid Choice");
					}
				}
				
			} else if(choice1 == 2) {
				while(true) {
					System.out.println("1. Start Shopping");
					System.out.println("2. Go Back");
					
					int choice2 = scanner.nextInt();
					
					if(choice2 == 1) {
						System.out.println("Enter customer name: ");
						String cName = scanner.next();
						
						Cart cart = new Cart();
						Sale sale = new Sale();
						
						report.addSale(checkout("Albaraa", cart, store, sale, scanner)); // Adding sale to data report
					} else if(choice2 == 2) {
						break;
					} else {
						System.out.println("Invalid Choice!");
					}
				}
			} else if(choice1 == 3) {
				return;
			} else {
				System.out.println("Invalid Choice!");
			}
		}
	}
}
