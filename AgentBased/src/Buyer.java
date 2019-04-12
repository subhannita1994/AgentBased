
public class Buyer {

	private int cost;
	private int sustainability_threshold;
	private int arrestPenalty;
	private int efficiency;
	
	public void setThreshold(int t) {
		this.sustainability_threshold = t;
	}
	public int getThreshold() {
		return this.sustainability_threshold;
	}
	
	public void setArrestPenalty(int a) {
		this.arrestPenalty = a;
	}
	public int getArrestPenalty() {
		return this.arrestPenalty;
	}
	
	public void setEfficiency(int e) {
		this.efficiency = e;
	}
	public int getEfficiency() {
		return this.efficiency;
	}
}
