import java.util.LinkedList;
public class Supplier {
	//supplier variables
	private LinkedList<Boolean> historyOfArrest;
	private double efficiency;
	private double complianceCost;
	private double complianceCostStep;
	private double complianceTendency;
	private double complianceTendencyStep;
	private double totCost;
	
	public Supplier() {
		historyOfArrest = new LinkedList<Boolean>();
		complianceTendencyStep = 0.05;
		complianceCostStep = 0.05;
		complianceTendency = 0;
	}
	
	public boolean getLastArrest() {
		//return true if caught last time, else return false
		if(historyOfArrest.isEmpty())
			return false;
		else return historyOfArrest.peekLast();
	}
	public void addArrest(boolean b) {
		this.historyOfArrest.add(b);
	}
	
	public void setEfficiency(double e) {
		this.efficiency = e;
	}
	public double getEfficiency() {
		return this.efficiency;
	}

	public double getComplianceCost() {
		return this.complianceCost;
	}
	public void setComplianceCost(double t) {
		this.complianceCost = t;
	}
	public void decreaseComplianceCost() {
		if(this.complianceCost - this.complianceCostStep <=0.3)
			this.complianceCost = 0.3;
		else
			this.complianceCost -= this.complianceCostStep;
	}
	
	
	public void increaseCompliance() {
		this.setComplianceTendency(this.complianceTendencyStep);
	}
	public void decreaseCompliance() {
		this.setComplianceTendency(-this.complianceTendencyStep);
	}
	public void setComplianceTendency(double step) {
		double t = this.complianceTendency + step;
		if(t>=1)
			this.complianceTendency = 1.0;
		else if(t<=0)
			this.complianceTendency = 0.0;
		else
			this.complianceTendency = t;
	}
	
	public double getComplianceTendency() {
		return this.complianceTendency;
	}
	public void setTotCost(double t) {
		this.totCost = t;
	}
	public double getTotCost() {
		return this.totCost;
	}
}
