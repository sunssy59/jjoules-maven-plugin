/**
 * 
 */
package com.jjoules;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.jjoules.energyDomain.rapl.RaplPackageDomain;

/**
 * @author sanoussy
 *
 */
//@ExtendWith(EnergyMesureExtension.class)
class EnergyMesureItTest {

	@Test
	public void mesureItGiveAnEnergyMesuredValueGreaterOrEqualsToValueBeforeCheking() {
		EnergyMesureIt.ENERGY_MESURE_IT.setEnergyDomain(new RaplPackageDomain(0));
		EnergyMesureIt.ENERGY_MESURE_IT.begin();
		for(int i=0;i<1000;i++) {}
		assertThat(EnergyMesureIt.ENERGY_MESURE_IT.end()).isGreaterThanOrEqualTo(0);
	}

}
