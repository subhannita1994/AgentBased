
public class Buyer {

	private double totCost;
	private double obsCost;
	private double buyerPenalty;
	private double sustainability_threshold;
	private double arrestPenalty;
	private double efficiency;
	
	public void setThreshold(double t) {
		this.sustainability_threshold = t;
	}
	public double getThreshold() {
		return this.sustainability_threshold;
	}
	
	public void setArrestPenalty(double a) {
		this.arrestPenalty = a;
	}
	public double getArrestPenalty() {
		return this.arrestPenalty;
	}
	
	public void setEfficiency(double e) {
		this.efficiency = e;
	}
	public double getEfficiency() {
		return this.efficiency;
	}
	public void setObsCost(double e) {
		this.obsCost = e;
	}
	public double getObsCost() {
		return this.obsCost;
	}
	public void setBuyerPenalty(double d) {
		this.buyerPenalty = d;
	}
	public double getbuyerPenalty() {
		return this.buyerPenalty;
	}
	public void setTotCost(double d) {
		this.totCost = d;
	}
	public double getTotCost() {
		return this.totCost;
	}
}
