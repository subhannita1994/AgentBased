import java.util.LinkedList;
public class Supplier {
	//supplier variables
	private LinkedList<Boolean> historyOfArrest;
	private int efficiency;
	private double complianceCost;
	private double complianceCostStep;
	private double defectTendency;
	private double defectTendencyStep;
	
	public Supplier() {
		historyOfArrest = new LinkedList<Boolean>();
		defectTendencyStep = 0.1;
		complianceCostStep = 0.5;
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
	
	public void setEfficiency(int e) {
		this.efficiency = e;
	}
	public int getEfficiency() {
		return this.efficiency;
	}

	public void setComplianceCost(int c) {
		this.complianceCost = c;
	}
	public double getComplianceCost() {
		return this.complianceCost;
	}
	public void decreaseComplianceCost() {
		this.complianceCost -= this.complianceCostStep;
	}
	
	public void setDefectTendency(double t) {
		this.defectTendency = t;
	}
	public void increaseDefect() {
		this.defectTendency = this.defectTendency + this.defectTendencyStep;
	}
	public void decreaseDefect() {
		this.defectTendency = this.defectTendency - this.defectTendencyStep;
	}
	public double getDefectTendency() {
		return this.defectTendency;
	}
}
