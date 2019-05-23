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
import java.util.Random;


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
		buyer.setArrestPenalty(0.8);
		buyer.setThreshold(0.8);
		buyer.setEfficiency(0.8);
		buyer.setObsCost(0.2);
		buyer.setBuyerPenalty(0.5);
		supplier.setComplianceCost(1);
		supplier.setEfficiency(0.5);
		supplier.setComplianceTendency(0.6);
		System.out.println("SETUP");
		System.out.println("sustainability"+sustainability);
		System.out.println("buyer arrest penalty"+buyer.getArrestPenalty());
		System.out.println("buyer threshold"+buyer.getThreshold());
		System.out.println("buyer monitoring efficiency"+buyer.getEfficiency());
		System.out.println("buyer monitoring cost"+buyer.getObsCost());
		System.out.println("buyer penalty"+buyer.getbuyerPenalty());
		System.out.println("supplier compliance cost"+supplier.getComplianceCost());
		System.out.println("supplier efficiency"+supplier.getEfficiency());
		System.out.println("supplier compliance tendency"+supplier.getComplianceTendency());
		
	}
	
	//function to decide cost of compliance or supplier cost
	private static double supplierTotCost() {
		return (double) supplier.getComplianceCost() - buyer.getArrestPenalty() - supplier.getEfficiency();
	}
	
	
	
	//supplier sub process -- returns true if comply, else return false
	private static double supplier_subProcess() {
		if(supplier.getLastArrest() == true) {	//got caught last time
			System.out.println("caught last time; returning 1");
			return 1;
		}
		else {	
			//not caught last time
			if(supplierTotCost()>=0) {
				supplier.decreaseCompliance();
				System.out.println("f(c)>=0 -> compliance tendency decreased to "+supplier.getComplianceTendency());
			}
			else {
				supplier.increaseCompliance();
				System.out.println("f(c)<0 -> compliance tendency increased to "+supplier.getComplianceTendency());
			}
			double random = Math.random();
			System.out.println("not caught last time; returning "+Double.toString(random));
			return random;
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
	private static void update(double compliance, boolean check) {
		if(compliance < supplier.getComplianceTendency()) {
			supplier.setTotCost(supplier.getComplianceCost()-buyer.getArrestPenalty()-supplier.getEfficiency());
			System.out.println("supplier says complied-> supplier cost:"+supplier.getTotCost());
		}
		else {
			if(check) {
				System.out.println("Caught at tick: "+tick);
				caughtFreq++;
				supplier.setTotCost(buyer.getArrestPenalty());
				System.out.println("supplier has not complied this game and been caught-> supplier cost:"+supplier.getTotCost());
			}
			else {
				supplier.setTotCost(-buyer.getArrestPenalty());
				System.out.println("supplier has not complied this game and not been caught-> supplier cost:"+supplier.getTotCost());
			}
		}
		
		buyer.setBuyerPenalty(buyer.getbuyerPenalty()+0.5);
		buyer.setObsCost(buyer.getObsCost()+0.2);	//buyer monitoring cost varying with time
		if(sustainability <=0.5)	//buyer getting penalized if sustainability falls below 0.5
			buyer.setTotCost(buyer.getbuyerPenalty()+buyer.getObsCost());
		else
			buyer.setTotCost(buyer.getObsCost()-buyer.getbuyerPenalty());
		System.out.println("buyer cost: "+buyer.getTotCost());
	}
	private static void print(boolean check) {
		//displays all relevant info : plots go here
		lineDataset.addValue(sustainability, "sustainability", Integer.toString(tick));
		lineDataset.addValue(supplier.getTotCost(), "Supplier cost", Integer.toString(tick));
		lineDataset.addValue(buyer.getTotCost(), "Buyer cost", Integer.toString(tick));
		
		if(check) {
			marker = new CategoryMarker("supplier efficiency");
			marker.setLabel("caught");
			marker.setLabelAnchor(RectangleAnchor.TOP);
			marker.setPaint(Color.red);
		}
		
	}
	
	private static boolean buyer_subProcess(double compliance) {
		//return true if caught else false
		//probability of monitoring efficiency * (event that defect tendency < threshold)
		if(compliance < buyer.getThreshold()) {
			supplier.setComplianceCost(0);
			updateSustainability(-0.05);
			System.out.println("buyer says supplier is not complying; supplier compliance cost set to 0; sustainability decreased to "+Double.toString(sustainability));
			double random = Math.random();
			if(random < buyer.getEfficiency()) {
				supplier.addArrest(true);
				System.out.println("supplier caught!");
				return true;
			}
			else {
				supplier.addArrest(false);
				System.out.println("supplier not caught!");
				return false;
			}
		}
		supplier.decreaseComplianceCost();
		supplier.addArrest(false);
		updateSustainability(0.05);
		System.out.println("buyer says supplier is complying; supplier compliance cost decreased to "+supplier.getComplianceCost()+"; sustainability increased to "+Double.toString(sustainability));
		return false;
	}
	public static void main(String[] args) throws Exception {
		
		setup();
		for(int i =0;i<50;i++) {
			System.out.println("tick: "+Integer.toString(tick));
			double compliance = supplier_subProcess();
			boolean check = buyer_subProcess(compliance);
			update(compliance, check);
			print(check);
			tick++;
			System.out.println("--------------------");
		}
		JFreeChart lineChartObject = ChartFactory.createLineChart("Efficiency", "ticks", "Efficiency", lineDataset);
		ChartUtilities.saveChartAsJPEG(new File("lineChart1.jpeg") ,lineChartObject, 1000 ,1000);
//		System.out.println("Caught frequency: "+caughtFreq+" in "+tick+" ticks");
	}
}
