import java.util.ArrayList;

public class Cart{
	ArrayList<CartItem> items;
	
	public Cart() {
		items = new ArrayList<CartItem>();
	}
	
	public void addToCart(Product P, int quantity) {
		// Check if the product is already in the cart
        for (CartItem item : items) {
            if (item.getProduct().equals(P)) {
                // Product already in the cart, update quantity
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Product not in the cart, add a new CartItem
        items.add(new CartItem(P, quantity));
	}
	
	public boolean removeFromCart(String name) {
		// Check if the product is in the cart
        for (CartItem item : items) {
            if (item.getProduct().getName().equals(name)) {
            	if(item.getQuantity() == 1) {
            		items.remove(item); // Remove the item from list altogether
            	}
            	else {
            		item.setQuantity(item.getQuantity() - 1);  // Decrement the quantity
                }
                return true;
            }
        }

        // Product not in the cart
        return false;
	}
	
	public double calculateTotalWithoutDiscount() {
		double total = 0.0;
		
		for(CartItem item:items) {
			total += (item.getProduct().getPrice() * item.getQuantity());
		}
		
		return total;
	}
	
	public double calculateTotalDiscount() {
		double discount = 0.0;
		
		for(CartItem item:items) {
			discount += (item.getProduct().getDiscountRate() * (item.getProduct().getPrice() * item.getQuantity()) );
		}
		
		return discount;
	}
	
	public ArrayList<CartItem> getItems()
	{
		return items;
	}
	
	public void Display() {
		for(CartItem item:items) {
			System.out.println("Item: " + item.getProduct().getName());
			System.out.println("Price: " + item.getProduct().getPrice());
			System.out.println("Discout: " + item.getProduct().getDiscountRate() * 100 + "%");
			System.out.println("Quantity: " + item.getQuantity());
			System.out.println("----------------------");
		}
		
	}
}
