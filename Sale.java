import java.text.DecimalFormat;

public class Sale implements SalesInter {
	private String customerName;
	private Cart cart;
	private double total;
	private double discount;
	
	public Sale(String customerName, Cart cart) {
		this.customerName = customerName;
		this.cart = cart;
	}
	
	public Sale() {
		// Default constructor
	}
	
	public Cart getCart() {
		return this.cart;
	}
	@Override
	public double calculateTotal() {
		total = cart.calculateTotalWithoutDiscount();
		return cart.calculateTotalWithoutDiscount();
	}
	@Override
	public double calculateDiscount() {
		discount = cart.calculateTotalDiscount();
		return cart.calculateTotalDiscount();
	}
	@Override
	public void Display() { // Displays the details of the sale, including customer name, cart items, and total amount.
		System.out.println("Name: " + this.customerName);
		System.out.println("\n      Cart Items ");
		System.out.println("----------------------");
		cart.Display();
		
		System.out.println("\n        Total");
		System.out.println("----------------------");
		
		// Using DecimalFormat
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String subTotal = decimalFormat.format(this.calculateTotal());
        String discoutFormatted = decimalFormat.format(this.calculateDiscount());
        String totalFormatted = decimalFormat.format(total - discount);
        
        
		System.out.println("Sub Total: $" + subTotal);
		System.out.println("Discout: $" + discoutFormatted);
		System.out.println("Total: " + totalFormatted);
	}
	
}
