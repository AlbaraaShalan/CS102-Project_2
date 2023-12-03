import java.util.ArrayList;

public class Report implements ReportInter{
	ArrayList<Sale> sales;
	double total;
	ArrayList<Product> PopularProducts; //#1
	
	public Report() {
		sales = new ArrayList<Sale>();
		PopularProducts = new ArrayList<Product>();
		total = 0.0;
	}

	public ArrayList<Sale> getSales() {
		return sales;
	}

	public void setSales(ArrayList<Sale> sales) {
		this.sales = sales;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public ArrayList<Product> getPopularProducts() {
		return PopularProducts;
	}

	public void setPopularProducts(ArrayList<Product> popularProducts) {
		PopularProducts = popularProducts;
	}

	public void addSale(Sale sale) {
		sales.add(sale);
		total += (sale.calculateTotal() - sale.calculateDiscount());
		PopularProducts.add(sale.getCart().items.get(0).getProduct());
	}
	
	public void Display() {
		System.out.println("Total Sale: " + total);
		for(Product item:PopularProducts) {
			System.out.println("Item: " + item.getName());
			System.out.println("Price: " + item.getPrice());
			System.out.println("Discout: " + item.getDiscountRate() * 100 + "%");
			System.out.println("----------------------");
		}
	}
}
