package com.jjoules.energyDisplay.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jjoules.energyDevice.EnergyDevice;
import com.jjoules.energyDomain.EnergyDomain;
import com.jjoules.exceptions.DeviceNotConfiguredException;
import com.jjoules.exceptions.NoSuchEnergyDeviceException;

public class MockEnergyDevice extends EnergyDevice{

	public MockEnergyDevice() throws NoSuchEnergyDeviceException {
		super();
	}

	@Override
	public ArrayList<EnergyDomain> availableDomains() throws NoSuchEnergyDeviceException {
		return null;
	}

	@Override
	public Map<String, Double> getEnergyConsumed() throws DeviceNotConfiguredException {
		Map<String, Double> energyConsumedByDevice = new HashMap<String,Double>();
		energyConsumedByDevice.put("package-0",1000.0);
		energyConsumedByDevice.put("core",100.0);
		energyConsumedByDevice.put("uncore",59.0);
		energyConsumedByDevice.put("dram",400.0);
		return energyConsumedByDevice;
		
	}
	
}