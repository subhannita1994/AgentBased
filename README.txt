Dyadic Agency Governance

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
- complianceCost : Integer
- defectTendency : Double
- defectTendencyStep : Double - set to 0.1
  Supplier functions:
- getLastArrest() : boolean - return true if caught last time, else return false
- setEfficiency(int) : void - set or update the efficiency of this supplier
- getEfficiency() : int - get the efficiency of this supplier
- setComplianceCost(int) : void - set or update the compliance cost of this supplier
- getComplianceCost() : int - get the current compliance cost of this supplier
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
