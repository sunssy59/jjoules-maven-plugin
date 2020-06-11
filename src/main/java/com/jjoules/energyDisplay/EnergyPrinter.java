/**
 * 
 */
package com.jjoules.energyDisplay;

import java.util.Map;

import com.jjoules.utils.Result;


/**
 * @author sanoussy
 *
 */
public class EnergyPrinter extends EnergyDisplayHandler {
	
	public static final EnergyPrinter ENERGY_PRINTER = new EnergyPrinter();
	
	private EnergyPrinter() {
		super();
	}

	@Override
	public void displayIt(Map<String, Result> energyConsumedByDevice) {
		for(String name: energyConsumedByDevice.keySet()) {
			System.out.println("["+name+"] energy consumed => " + this.getEnergyToPrint(energyConsumedByDevice, name)+" microJ");
			System.out.println("["+name+"] duration => " + this.getDuration(energyConsumedByDevice, name)+" ms");
		}
	}

}
