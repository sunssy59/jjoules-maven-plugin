/**
 * 
 */
package com.jjoules.energyDevice.rapl;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jjoules.energyDevice.rapl.RaplDevice;
import com.jjoules.exceptions.DeviceNotConfiguredException;
import com.jjoules.exceptions.NoSuchDomainException;
import com.jjoules.exceptions.NoSuchEnergyDeviceException;

/**
 * @author sanoussy
 *
 */
class RaplDeviceTest {

	private RaplDevice raplDevice;
		
	@BeforeEach
	public void initDevice() throws NoSuchEnergyDeviceException {
		this.raplDevice = new RaplDevice();
	}
	
	@Test
	public void getConsumedEnergy() throws DeviceNotConfiguredException, NoSuchDomainException {
		raplDevice.configure(raplDevice.getAvailableDomains());
		
		Collection<Double> consumedEnergy = raplDevice.getEnergyConsumed().values();

		for(Double d : consumedEnergy) {
			assertThat(d).isGreaterThanOrEqualTo(0);
		}
	}
}
