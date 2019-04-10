
public class Buyer {

	private int sustainability_threshold;
	private int arrestPenalty;
	
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
}
