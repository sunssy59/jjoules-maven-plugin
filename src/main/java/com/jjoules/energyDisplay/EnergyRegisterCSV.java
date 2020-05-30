package com.jjoules.energyDisplay;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @author sanoussy
 *
 */
class EnergyRegisterCSV extends EnergyDisplayHandler{
	
	public static EnergyRegisterCSV ENERGY_REGISTER_CSV = new EnergyRegisterCSV();
	
	private String fileName;

	private EnergyRegisterCSV() {
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

	@Override
	public void displayIt(Map<String, Double> energyConsumedByDevice) {
		int id = 1;
		File file = new File(this.fileName);
		if (file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter  fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("id;tag;energyConsumed\n");
			for(String domainName : energyConsumedByDevice.keySet()){
				bw.write(id+";"+domainName+";"+energyConsumedByDevice.get(domainName)+"\n");
				id +=1;
			}
			bw.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			
	}

}
