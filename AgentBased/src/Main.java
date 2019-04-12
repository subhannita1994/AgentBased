import org.eclipse.draw2d.*;
import org.eclipse.nebula.snippets.visualization.*;
public class Main {

	
	
	static Buyer buyer = new Buyer();
	static Supplier supplier = new Supplier();
	
	//global variables
	private static int tick;
	private static int sustainability;	
	
	//initial setup
	private static void setup() {
		tick = 0;
		sustainability = 100;
		buyer.setArrestPenalty(60);
		buyer.setThreshold(60);
		supplier.setComplianceCost(100);
		supplier.setEfficiency(100);
		supplier.setDefectTendency(0.5);
	}
	
	//function to decide cost of compliance or supplier cost
	private static double costOfCompliance() {
		return (double) supplier.getComplianceCost() - buyer.getArrestPenalty() - supplier.getEfficiency();
	}
	
	
	
	//supplier sub process -- returns true if comply, else return false
	private static boolean supplier_subProcess() {
		if(supplier.getLastArrest() == true)	//got caught last time
			return true;
		else {	
			//not caught last time
			if(costOfCompliance()>=0)
				supplier.increaseDefect();
			else
				supplier.decreaseDefect();
			double random = Math.random();
			if(random < supplier.getDefectTendency())
				return false;
			else
				return true;
			
		}
	}
	
	//update parameters
	private static void update_comply() {
		sustainability++;
		supplier.decreaseComplianceCost();
		supplier.addArrest(false);
	}
	private static void update_nonComply() {
		sustainability--;
		
	}
	private static void print() {
		//displays all relevant info : plots go here
	}
	
	private static boolean buyer_subProcess() {
		//return true if caught else false
		//probability of monitoring efficiency * (event that defect tendency < threshold)
		return false;
	}
	public static void main(String[] args) {
		
		setup();
		boolean compliance = supplier_subProcess();
		boolean check = buyer_subProcess();
		if(compliance) {
			update_comply();
		}
		else if(!compliance) {
			update_nonComply();
			if(!check) {
				supplier.addArrest(false);
			}
			else {
				supplier.addArrest(true);
			}
		}
		
		tick++;
		print();
	}
}
