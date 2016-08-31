package core;

import java.util.LinkedList;

public class GbceExchange {
	private LinkedList<Stock> stocks = new LinkedList<Stock>();

	public void addStock(Stock pStock) {
		this.stocks.add(pStock);
	}
	
	public LinkedList<Stock> getAllStocks() {
		return this.stocks;
	}

	public double calculateAllShareIndex() {
		//This way we assure that the current time is the same for all stocks
		long currentTime = System.currentTimeMillis();
		LinkedList<Stock> stocks = getAllStocks();
		double product = 1.0;
		for (Stock stock : stocks) {
			product = product * stock.calculateVolumeWeightedStockPrice(currentTime);
		}

		return Math.pow(product, 1.0/stocks.size());
	}
	
}
