/**
 * 
 */
package com.jjoules.energyDisplay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jjoules.energyDevice.EnergyDevice;
import com.jjoules.exceptions.DeviceNotConfiguredException;
import com.jjoules.utils.Data;
import com.jjoules.utils.Result;

/**
 * @author sanoussy
 *
 */
public abstract class EnergyDisplayHandler {
	
	
	/**
	 * Display energy consumed according to the way of representation either on the screen or saved in file ...
	 * @param energyConsumedByDevice energy consumed by all configured domains
	 */
	public abstract void displayIt(Map<String, Result> energyConsumedByDevice);
	
	/**
	 * @param device
	 * @return energy consumed by all domain in the device
	 */
	public Map<String, Double> getEnergyConsumedByDevice(EnergyDevice device) {
		Map<String, Double> energyConsumedByDevice = new HashMap<String,Double>();
		try {
			energyConsumedByDevice = device.getEnergyConsumed();
		} catch (DeviceNotConfiguredException e) {
			e.printStackTrace();
		}
		return energyConsumedByDevice;
	}
	
	/**
	 * @param energyConsumed energy consumed by all configured domains
	 * @param domainName domain name which looking for energy consumed
	 * @return energy consumed by domain
	 */
	public double getEnergyToPrint(Map<String, Result> energyConsumed,String domainName) {
		return energyConsumed.get(domainName).getEnergyConsumed();		
	}
	
	/**
	 * @param energyConsumed energy consumed by all configured domains
	 * @param domainName domain name which looking for energy consumed
	 * @return energy consumed by domain
	 */
	public long getDuration(Map<String, Result> energyConsumed,String domainName) {
		return energyConsumed.get(domainName).getDuration();		
	}
	
	/**
	 * @param energyConsumedByDevice
	 */
	public void saveResultOfClass(Map<String, Result> energyConsumedByDevice,String currentClassName,List<Data> allData) {
		List<Result> results = new ArrayList<Result>();
		for(String name : energyConsumedByDevice.keySet()) {
			results.add(new Result(name,energyConsumedByDevice.get(name)));
		}
		Data data = new Data(currentClassName,results);
		allData.add(data);
	}

}
