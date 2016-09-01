package core;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class Testing {
	
	@Test
	public void calculationOfVolumeWeightedStockPriceForOneStock() {
		long currentTime = System.currentTimeMillis();
		
		Stock tea = new CommonStock();
		tea.setStockSymbol("TEA");
		tea.recordTrade(new Trade(currentTime-300001, 10000, true, 10000.0));
		tea.recordTrade(new Trade(currentTime-300000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-290000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-280000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-275000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-250000, 100, true, 10.0));
		
		BigDecimal volumeWeightedStockPrice = new BigDecimal(tea.calculateVolumeWeightedStockPrice(currentTime)).
				setScale(4,BigDecimal.ROUND_HALF_UP);

		assertEquals(10.0000, volumeWeightedStockPrice.doubleValue(), 0.0001);
		
		assertNotEquals(10.0002, volumeWeightedStockPrice.doubleValue(), 0.0001);
		
		Stock gin = new PreferredStock();
		gin.setStockSymbol("GIN");
		gin.recordTrade(new Trade(currentTime-300001, 10000, true, 10000.0));
		gin.recordTrade(new Trade(currentTime-300000, 100, true, 100.0));
		gin.recordTrade(new Trade(currentTime-290000, 100, true, 100.0));
		gin.recordTrade(new Trade(currentTime-280000, 100, true, 100.0));
		gin.recordTrade(new Trade(currentTime-275000, 100, true, 100.0));
		gin.recordTrade(new Trade(currentTime-250000, 100, true, 100.0));
		
		volumeWeightedStockPrice = new BigDecimal(gin.calculateVolumeWeightedStockPrice(currentTime)).
				setScale(4,BigDecimal.ROUND_HALF_UP);

		assertEquals(100.0000, volumeWeightedStockPrice.doubleValue(), 0.0001);
		
		assertNotEquals(100.0002, volumeWeightedStockPrice.doubleValue(), 0.0001);
		
	}
	
	@Test
	public void calculationOfVolumeWeightedStockPriceForSeveralStocks() {
		long currentTime = System.currentTimeMillis();
		
		CommonStock tea = new CommonStock();
		tea.setStockSymbol("TEA");
		tea.recordTrade(new Trade(currentTime-300001, 10000, true, 1000.0));
		tea.recordTrade(new Trade(currentTime-300000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-290000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-280000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-275000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-250000, 100, true, 10.0));
		
		CommonStock pop = new CommonStock();
		pop.setStockSymbol("POP");
		pop.recordTrade(new Trade(currentTime-300001, 10000, true, 10000.0));
		pop.recordTrade(new Trade(currentTime-300000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-290000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-280000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-275000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-250000, 100, true, 100.0));
		
		CommonStock gin = new CommonStock();
		gin.setStockSymbol("POP");
		gin.recordTrade(new Trade(currentTime-300001, 10000, true, 100000.0));
		gin.recordTrade(new Trade(currentTime-300000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-290000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-280000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-275000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-250000, 100, true, 1000.0));
		
		GbceExchange exchange = new GbceExchange();
		exchange.addStock(tea);
		exchange.addStock(pop);
		exchange.addStock(gin);
		
		BigDecimal volumeWeightedStockPriceIndex = new BigDecimal(exchange.calculateAllShareIndex()).
				setScale(4,BigDecimal.ROUND_HALF_UP);
		
		assertEquals(100.0000, volumeWeightedStockPriceIndex.doubleValue(), 0.0001);
		
	}
	
	@Test
	public void gettingDividendYieldCommonStock() {
		Stock tea = new CommonStock();
		tea.setStockSymbol("TEA");
		tea.setLastDividend(0);
		tea.setParValue(1.00);
		
		assertEquals(0.0, tea.calculateDividendYield(9.7), 0.0001);
		
		Stock ale = new CommonStock();
		ale.setStockSymbol("ALE");
		ale.setLastDividend(0.23);
		ale.setParValue(0.60);
		
		assertEquals(0.0219, ale.calculateDividendYield(10.50), 0.0001);
		
	}
	
	@Test
	public void gettingDividendYieldPreferredStock() {
		Stock gin = new PreferredStock();
		gin.setStockSymbol("GIN");
		gin.setLastDividend(0.08);
		gin.setFixedDividend(0.02);
		gin.setParValue(1.00);
		
		BigDecimal dividendYield = new BigDecimal(gin.calculateDividendYield(5.87)).
				setScale(4,BigDecimal.ROUND_HALF_UP);
		
		assertEquals(0.0034, dividendYield.doubleValue(), 0.0001);
		
		dividendYield = new BigDecimal(gin.calculateDividendYield(8.6)).
				setScale(4,BigDecimal.ROUND_HALF_UP);
		
		assertEquals(0.0023, dividendYield.doubleValue(), 0.0001);
		
	}
	
	@Test
	public void calculationOfGbceAllShareIndex() {
		long currentTime = System.currentTimeMillis();
		
		CommonStock tea = new CommonStock();
		tea.setStockSymbol("TEA");
		tea.recordTrade(new Trade(currentTime-300001, 10000, true, 1000.0));
		tea.recordTrade(new Trade(currentTime-300000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-290000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-280000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-275000, 100, true, 10.0));
		tea.recordTrade(new Trade(currentTime-250000, 100, true, 10.0));
		
		CommonStock pop = new CommonStock();
		pop.setStockSymbol("POP");
		pop.recordTrade(new Trade(currentTime-300001, 10000, true, 10000.0));
		pop.recordTrade(new Trade(currentTime-300000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-290000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-280000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-275000, 100, true, 100.0));
		pop.recordTrade(new Trade(currentTime-250000, 100, true, 100.0));
		
		CommonStock gin = new CommonStock();
		gin.setStockSymbol("POP");
		gin.recordTrade(new Trade(currentTime-300001, 10000, true, 100000.0));
		gin.recordTrade(new Trade(currentTime-300000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-290000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-280000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-275000, 100, true, 1000.0));
		gin.recordTrade(new Trade(currentTime-250000, 100, true, 1000.0));
		
		GbceExchange exchange = new GbceExchange();
		exchange.addStock(tea);
		exchange.addStock(pop);
		exchange.addStock(gin);
		
		BigDecimal shareIndex = new BigDecimal(exchange.calculateAllShareIndex()).
				setScale(4,BigDecimal.ROUND_HALF_UP);
		
		assertEquals(100.0000, shareIndex.doubleValue(), 0.0001);
	}
	
	@Test
	public void gettingPeRatio() {
		Stock gin = new PreferredStock();
		gin.setStockSymbol("GIN");
		gin.setLastDividend(0.08);
		gin.setFixedDividend(0.02);
		gin.setParValue(1.00);
		
		BigDecimal peRatio = new BigDecimal(gin.getPeRatio(3.00)).
				setScale(4,BigDecimal.ROUND_HALF_UP);
		
		assertEquals(0.0267, peRatio.doubleValue(), 0.0001);
	}
	
}
