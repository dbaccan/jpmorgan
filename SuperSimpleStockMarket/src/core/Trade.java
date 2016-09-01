package core;

public class Trade {
	
	private long timeStamp;
	private boolean isBuy;
	private double price;
	private int quantity;
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public boolean isBuy() {
		return isBuy;
	}
	
	public double getPrice() {
		return price;
	}
	
	public Trade(long pTimeStamp, int pQuantity, boolean pIsBuy, double pPrice) {
		this.timeStamp = pTimeStamp;
		this.quantity = pQuantity;
		this.isBuy = pIsBuy;
		this.price = pPrice;
	}
	
}
