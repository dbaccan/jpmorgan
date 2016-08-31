package core;

public class CommonStock extends Stock {
	
	public CommonStock() {
		this.isCommon = true;
	}
	
	@Override
	protected double calculateDividendYield(double pPrice) {
		return this.lastDividend / pPrice;
	}

}
