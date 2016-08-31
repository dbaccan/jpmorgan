package core;

import java.util.LinkedList;

public abstract class Stock {
	
	private String stockSymbol = new String();
	protected boolean isCommon;
	protected LinkedList<Trade> trades = new LinkedList<Trade>(); 
	protected double lastDividend;
	protected double parValue;
	protected double fixedDividend;
	
	public double getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double pParValue) {
		this.parValue = pParValue;
	}

	public double getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(double pLastDividend) {
		this.lastDividend = pLastDividend;
	}
	
	public void recordTrade(Trade pTrade) {
		trades.add(pTrade);
	}
	
	public LinkedList<Trade> getAllTrades() {
		return trades;
	}
	
	private LinkedList<Trade> getTradesInPastFiveMinutes(long pCurrentTime) {
		LinkedList<Trade> tradesInPastFiveMinutes = new LinkedList<Trade>(); 
		long fiveMinutesAgo = pCurrentTime - 300000;

		for (int i = this.trades.size() - 1; i > -1; i--) {
			Trade trade = this.trades.get(i);
			if(trade.getTimeStamp() < fiveMinutesAgo) {
				break;
			}
			
			tradesInPastFiveMinutes.add(trade);
		}
		
		return tradesInPastFiveMinutes;
	}
	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String pStockSymbol) {
		this.stockSymbol = pStockSymbol;
	}
	
	public boolean isCommon() {
		return isCommon;
	}

	protected abstract double calculateDividendYield(double pPrice);
	
	protected double getPeRatio(double pPrice) {
		return this.lastDividend / pPrice;
	}
	
	protected double calculateVolumeWeightedStockPrice(long pCurrentTime) {
		LinkedList<Trade> tradesInPastFiveMinutes = this.getTradesInPastFiveMinutes(pCurrentTime);
		
		int sumQuantity = 0;
		double temp = 0.0;
		for (Trade trade : tradesInPastFiveMinutes) {
			int quantity = trade.getQuantity();
			
			temp += trade.getPrice() * quantity;
			sumQuantity += quantity;
		}
		
		return temp/sumQuantity;
	}

}
