
public class Main {

	static Buyer buyer = new Buyer();
	static Supplier supplier = new Supplier();
	
	//global variables
	private static int tick;
	private static int sustainability;	//?global to entire process or specific to a pair of buyer-supplier?
	
	//initial setup
	private static void setup() {
		tick = 0;
		//sustainability = ;
		buyer.setArrestPenalty(60);
		buyer.setThreshold(60);
		supplier.setComplianceCost(100);
		supplier.setEfficiency(100);
		supplier.setDefectTendency(0.5);
	}
	
	//function to decide cost of compliance
	private static int costOfCompliance() {
		return supplier.getComplianceCost() - buyer.getArrestPenalty() - supplier.getEfficiency();
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
			/*
			 * ?how is tendency to defect affect compliance - simply a probability?*
			 * ?return true if new f(c)<0 else return false?
			 * ?how is supplier.defectTendency affecting either arrestPenalty or supplier.efficiency?
			 */
		}
		return false;	//to be removed
	}
	
	//update parameters
	private static void update() {
		//sustainability = //?how will sustainability update?//
		//?what other parameters to update - complianceCost,supplier.efficiency,buyer.efficiency?//
	}
	
	private static void print() {
		//displays all relevant info : plots go here
	}
	
	public static void main(String[] args) {
		
		setup();
		boolean compliance = supplier_subProcess();
		if(compliance) {
			update();
			tick++;
		}
		else {
			update();
			if(sustainability < buyer.getThreshold()) {
				/*
				?does this mean that supplier has been caught or is it a function of monitoring efficiency
				- if yes then update Supplier.historyOfArrest?
				*/
				//flash danger
			}
			else
				tick++;
		}
		print();
	}
}
