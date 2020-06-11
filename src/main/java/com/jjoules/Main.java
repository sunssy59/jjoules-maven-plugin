package com.jjoules;

import java.io.File;
import java.net.URL;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.jjoules.energyDevice.EnergyDevice;
import com.jjoules.energyDevice.rapl.RaplDevice;
import com.jjoules.energyDomain.EnergyDomain;

public class Main {

	public static void main(String[] args) {
		try {
			EnergyDevice device = new RaplDevice();
			device.configure(device.getAvailableDomains());
			System.out.println("\nAll available domains => "+device.getAvailableDomains());
			System.out.println("All configured domains => "+device.getConfiguredDomains());
			
			System.out.println("\n---- Energy consumed in the device -------");
			for(EnergyDomain domain : device.getAvailableDomains()) {
				
				System.out.println("\n++ Energy consumed in "+domain+" ++");
				EnergyMesureIt.ENERGY_MESURE_IT.setEnergyDomain(domain);
				EnergyMesureIt.ENERGY_MESURE_IT.begin();
				System.out.println("Energy consumed before => "+EnergyMesureIt.ENERGY_MESURE_IT.getEnergyBefore());
				
				for(int i=0;i<10000; i++) {}
				
				double diff = EnergyMesureIt.ENERGY_MESURE_IT.end();
				System.out.println("Energy consumed after => "+ EnergyMesureIt.ENERGY_MESURE_IT.getEnergyAfter());
				System.out.println("diff => "+diff+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		File file = new File("/home/sanoussy/stage/plugins/jjoules-plugin/target/test-classes");
////		System.out.println("-- List --");
////		for(String s : file.list()) {
////			System.out.println(s);
////		}
////		System.out.println("-- ListFile --");
////		for(File f : file.listFiles()) {
////			System.out.println(f.getName());
////		}
//		
//		JjoulesMojo moj = new JjoulesMojo();
//		try {
//			moj.execute();
//		} catch (MojoExecutionException e) {
//			e.printStackTrace();
//		} catch (MojoFailureException e) {
//			e.printStackTrace();
//		}
//		for(URL url : moj.classPathUrls) {
//			System.out.println(url.getPath().replace(".", "/"));
//		}
		
	}

}
