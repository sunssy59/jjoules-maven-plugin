package com.jjoules;

import com.jjoules.energyDevice.EnergyDevice;
import com.jjoules.energyDevice.rapl.RaplDevice;
import com.jjoules.energyDomain.rapl.RaplPackageDomain;

public class Main {

	public static void main(String[] args) {
		try {
			EnergyDevice device = new RaplDevice();
			device.configure(device.getAvailableDomains());
			System.out.println("All available domains => "+device.getAvailableDomains());
			System.out.println("All configured domains => "+device.getConfiguredDomains());
		} catch (Exception e) {
			e.printStackTrace();
		}
		RaplPackageDomain pkg = new RaplPackageDomain(0);
		EnergyMesureIt mesureIt = new EnergyMesureIt(pkg);
		
		System.out.println("\n---- Energy consumed in "+pkg+" ----");
		mesureIt.begin();
		System.out.println("Energy consumed before => "+mesureIt.getEnergyBefore());
		
		for(int i=0;i<10000; i++) {}
		
		double diff = mesureIt.end();
		System.out.println("Energy consumed after => "+ mesureIt.getEnergyAfter());
		System.out.println("diff => "+diff);
		
	}

}
