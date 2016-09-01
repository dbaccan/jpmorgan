package core;

public class PreferredStock extends Stock {
	
	public PreferredStock() {
		this.isCommon = false;
	}
	
	@Override
	protected double calculateDividendYield(double pPrice) {
		super.validatePrice(pPrice);
		
		return this.fixedDividend * this.parValue / pPrice;
	}

}
