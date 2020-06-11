/**
 * 
 */
package com.jjoules.energyDisplay;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jjoules.energyDisplay.util.MockEnergyDevice;
import com.jjoules.exceptions.NoSuchEnergyDeviceException;
import com.jjoules.utils.Result;

/**
 * @author sanoussy
 *
 */
class EnergyRegisterCSVTest {
	
	private MockEnergyDevice mockDevice;
	private Map<String, Result> energyConsumedByDevice;
	private EnergyRegisterCSV registerCsv;
	
	
	@BeforeEach
	public void init() {
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.setFileName("out.csv");
		try {
			this.energyConsumedByDevice = new HashMap<String,Result>();
			this.mockDevice = new MockEnergyDevice();
			Map<String, Double> res = EnergyRegisterCSV.ENERGY_REGISTER_CSV.getEnergyConsumedByDevice(this.mockDevice);
			for(String deviceName : res.keySet()){
				this.energyConsumedByDevice.put(deviceName, new Result(res.get(deviceName),0));
			}
		} catch (NoSuchEnergyDeviceException e) {
			e.printStackTrace();
		}	
	}

	@Test
	public void displayItPoduceFileWithCorrecteLines() {
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.displayIt(energyConsumedByDevice);
		File file = new File(EnergyRegisterCSV.ENERGY_REGISTER_CSV.getFileName());
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			for(int i=0; i<energyConsumedByDevice.size();i++) {
				assertThat(br.readLine()).isIn("id;tag;energyConsumed;duration","1;package-0;1000.0;0","2;core;100.0;0","3;dram;400.0;0","4;uncore;59.0;0");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
