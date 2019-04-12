Dyadic Agency Governance

SETUP
- install and setup JDK
	* https://www.oracle.com/technetwork/java/javase/downloads/index.html : select download JDK under Java SE 8u201 / Java SE 8u202
	* accept license
	* select the link for your OS
	* run the installer : accept the defaults and follow the screen instructions
	* Add the JDK's bin directory to Path : Launch "Control Panel" => "System and Security" => "Advanced System settings" on left pane => "Advanced" tab => "Environment variables" button => under "System Variables" , scroll down to "Path" => Click "Edit" => Click "New" => Click "browse" => go to JDK's bin directory (usually similar to C:\Program Files\Java\jdk-8\bin)
- install and setup Eclipse
	* https://www.eclipse.org/downloads/ : download Eclipse
- clone or download (zip) this project into a directory
- open Eclipse with this directory as the workspace
- if nothing appears on the "Package Explorer" panel to the left, select "File" => "Import" => under "General" select "Existing projects into Workspace" => "Browse" and navigate to the directory of project => check the project appearing under "Projects:" pane => "Next"/"Finish"
- Download Nebula XYGraph for visualisation : In Eclipse, select "Help" => "Install new software" => "Add" => Type Nebula visualisation in "Name" field and http://download.eclipse.org/nebula/snapshot in url field => Expand "Nebula Release Individual Widgets" => select "Nebula visualisation widgets" => Click "next" => Accept license => Finish and restart Eclipse


Java files are located in AgentBased/src/

Buyer.java - Class or template of a buyer. Instances/Objects of this class represent a buyer.
	Buyer attributes: 
- sustainability_threshold : Integer
- arrestPenalty : Integer
	Buyer functions:
- setThreshold(int) : void - set the sustainability threshold of this buyer
- getThreshold() : int - get the sustainability threshold of this buyer
- setArrestPenalty(int) : void - set the arrest penalty of this buyer
- getArrestPenalty() : int - get the arrest penalty set for this buyer
- setEfficiency(int) : void - set the monitoring efficiency of this buyer
- getEfficiency() : int - get the monitoring efficiency of this buyer

Supplier.java - Class or template of a supplier. Instances/Objects of this class represent a supplier.
  Supplier attributes:
- historyOfArrest : LinkedList<Boolean> 
- efficiency : Integer
- complianceCost : Double
- complianceCostStep : Double - set to 0.5
- defectTendency : Double
- defectTendencyStep : Double - set to 0.1
  Supplier functions:
- getLastArrest() : boolean - return true if caught last time, else return false
- setEfficiency(int) : void - set or update the efficiency of this supplier
- getEfficiency() : int - get the efficiency of this supplier
- setComplianceCost(int) : void - set the initial compliance cost of this supplier
- getComplianceCost() : double - get the current compliance cost of this supplier
- decreaseComplianceCost() : void - decrease the compliance cost by complianceCostStep
- setDefectTendency(double) : void - set the initial defect tendency of this supplier
- increaseDefect() : void - increase the defect tendency of this supplier by 0.1
- decreaseDefect() : void - decrease the defect tendency of this supplier by 0.1

Main.java - Actual place where the process takes place.
  Main or Global attributes:
- tick : Integer 
- sustainability : Integer - ????supplier sustainability or global or specific to this buyer-supplier pair????
  Main functions:
- setup() : void - initialises all parameters-
  * tick 
  * buyer.arrestPenalty
  * buyer.sustainability_threshold
  * supplier.complianceCost
  * supplier.efficiency
  * supplier.defectTendency
- costOfCompliance() : int - returns F(C) = supplier.complianceCost - buyer.arrestPenalty - supplier.efficiency 
- supplier_subProcess() : boolean - implements a sub-process of the supplier and returns true if supplier is complying, else false. The sub-process is as follows-
  * if supplier caught in last tick, return true
  * if supplier not caught in last tick, check cost of compliance (Main.costOfCompliance())
    * if cost of compliance >= 0, increase tendency of defect (Supplier.increaseDefect())
    * if cost of compliance < 0, decrease tendency of defect (Supplier.decreaseDefect())
    * ????how to know now if supplier is complying or not????
- update() : void - updates all parameters (????specifically which parameters????) after every compliance decision
- main() : void - entry point of the entire application. This is like the console of the application that controls the flow of events -
  * setup()
  * supplier_subprocess()
  * if supplier is complying : update(), tick
  * if supplier is not complying : update(),
    * if Main.sustainability < buyer.sustainability_threshold : flash "danger" 
    * if Main.sustainability >= buyer.sustainability_threshold : tick
