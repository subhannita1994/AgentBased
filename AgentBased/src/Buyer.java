
public class Buyer {

	private int obsCost;
	private double buyerPenalty;
	private double sustainability_threshold;
	private int arrestPenalty;
	private double efficiency;
	
	public void setThreshold(double t) {
		this.sustainability_threshold = t;
	}
	public double getThreshold() {
		return this.sustainability_threshold;
	}
	
	public void setArrestPenalty(int a) {
		this.arrestPenalty = a;
	}
	public int getArrestPenalty() {
		return this.arrestPenalty;
	}
	
	public void setEfficiency(double e) {
		this.efficiency = e;
	}
	public double getEfficiency() {
		return this.efficiency;
	}
	public void setObsCost(int e) {
		this.obsCost = e;
	}
	public int getObsCost() {
		return this.obsCost;
	}
	public void setBuyerPenalty(double d) {
		this.buyerPenalty = d;
	}
	public double getbuyerPenalty() {
		return this.buyerPenalty;
	}
}
