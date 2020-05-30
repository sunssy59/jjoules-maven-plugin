package com.jjoules;

import com.jjoules.energyDevice.EnergyDevice;
import com.jjoules.energyDevice.rapl.RaplDevice;
import com.jjoules.energyDomain.EnergyDomain;

public class Main {

	public static void main(String[] args) {
		try {
			EnergyDevice device = new RaplDevice();
			device.configure(device.getAvailableDomains());
			System.out.println("All available domains => "+device.getAvailableDomains());
			System.out.println("All configured domains => "+device.getConfiguredDomains());
			
			System.out.println("\n---- Energy consumed in the device -------");
			for(EnergyDomain domain : device.getAvailableDomains()) {
				
				System.out.println("\n++ Energy consumed in "+domain+" ++");
				System.out.println("Energy consumed before => "+EnergyMesureIt.ENERGY_MESURE_IT.getEnergyBefore());
				EnergyMesureIt.ENERGY_MESURE_IT.setEnergyDomain(domain);
				EnergyMesureIt.ENERGY_MESURE_IT.begin();
				
				for(int i=0;i<10000; i++) {}
				
				double diff = EnergyMesureIt.ENERGY_MESURE_IT.end();
				System.out.println("Energy consumed after => "+ EnergyMesureIt.ENERGY_MESURE_IT.getEnergyAfter());
				System.out.println("diff => "+diff+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
