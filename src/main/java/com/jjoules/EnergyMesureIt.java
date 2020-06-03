/**
 * 
 */
package com.jjoules;

import com.jjoules.energyDomain.EnergyDomain;

/**
 * @author sanoussy
 *
 */
public class EnergyMesureIt {
	
	public static EnergyMesureIt ENERGY_MESURE_IT = new EnergyMesureIt();
	
	private double energyBefore;
	private double energyAfter;
	
	private EnergyDomain domain;
	
	private EnergyMesureIt() {
	}
	
	public EnergyDomain getEnergyDomain() {
		return this.domain;
	}
	public void setEnergyDomain(EnergyDomain newDomain) {
		this.domain = newDomain;
	}
	/**
	 * @return the energy consumed before checking 
	 */
	public double getEnergyBefore() {
		return this.energyBefore;
	}
	
	/**
	 * @return the energy consumed after checking 
	 */
	public double getEnergyAfter() {
		return this.energyAfter;
	}
	/**
	 * 
	 */
	public void begin() {
		this.energyBefore = this.domain.getEneregyConsumed();
		//System.out.println("Start => "+ this.getEnergyBefore());
	}
	/**
	 * 
	 */
	public double end() {
		this.energyAfter = this.domain.getEneregyConsumed();
		//System.out.println("end => "+end);
		//System.out.println("diff => "+ (end - this.getEnergyBefore()));
		return this.getEnergyAfter() - this.getEnergyBefore();
	}

}
