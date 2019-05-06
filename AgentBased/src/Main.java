import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.chart.plot.CategoryMarker;
import javax.xml.soap.SOAPPart;

import java.awt.Color;
import java.io.File;
import java.io.IOException;


public class Main {

	
	
	static Buyer buyer = new Buyer();
	static Supplier supplier = new Supplier();
	static DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
	static CategoryMarker marker; 
	private static int caughtFreq = 0;
	
	//global variables
	private static int tick;
	private static double sustainability;	
	
	//initial setup
	private static void setup() {
		tick = 0;
		sustainability = 0.6;
		buyer.setArrestPenalty(80);
		buyer.setThreshold(0.8);
		buyer.setEfficiency(0.8);
		buyer.setObsCost(20);
		supplier.setComplianceCost(100);
		supplier.setEfficiency(50);
		supplier.setDefectTendency(0.3);
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
			if(random < Math.abs(supplier.getDefectTendency()))
				return false;
			else
				return true;
			
		}
	}
	
	private static void updateSustainability(double value) {
		double s = sustainability+value;
		if(s>=1)
			sustainability = 1;
		else if(s<=0)
			sustainability = 0;
		else
			sustainability = s;
	}
	
	//update parameters
	private static void update(boolean compliance, boolean check) {
		if(compliance) {
			updateSustainability(0.05);
			supplier.decreaseComplianceCost();
			supplier.addArrest(false);
		}
		else {
			updateSustainability(-0.05);
			if(check) {
				supplier.addArrest(true);
				System.out.println("Caught at tick: "+tick);
				caughtFreq++;
			}
			else {
				supplier.addArrest(false);
			}
		}
		buyer.setBuyerPenalty(buyer.getbuyerPenalty()+0.5);
	}
	private static void print(boolean check) {
		//displays all relevant info : plots go here
//		System.out.println("printing...");
//		lineDataset.addValue(supplier.getEfficiency(), "supplier efficiency", Integer.toString(tick));
//		lineDataset.addValue(buyer.getEfficiency(), "buyer efficiency", Integer.toString(tick));
		lineDataset.addValue(sustainability, "sustainability", Integer.toString(tick));
		if(check) {
			marker = new CategoryMarker("supplier efficiency");
			marker.setLabel("caught");
			marker.setLabelAnchor(RectangleAnchor.TOP);
			marker.setPaint(Color.red);
		}
		
	}
	
	private static boolean buyer_subProcess() {
		//return true if caught else false
		//probability of monitoring efficiency * (event that defect tendency < threshold)
		if(supplier.getDefectTendency() < buyer.getThreshold()) {
			double random = Math.random();
			if(random < buyer.getEfficiency())
				return true;
		}
		return false;
	}
	public static void main(String[] args) throws Exception {
		
		setup();
		for(int i =0;i<500;i++) {
			boolean compliance = supplier_subProcess();
			boolean check = buyer_subProcess();
			update(compliance, check);
			print(check);
			tick++;
		}
		JFreeChart lineChartObject = ChartFactory.createLineChart("Efficiency", "ticks", "Efficiency", lineDataset);
		ChartUtilities.saveChartAsJPEG(new File("lineChart1.jpeg") ,lineChartObject, 1000 ,1000);
		System.out.println("Caught frequency: "+caughtFreq+" in "+tick+" ticks");
	}
}
