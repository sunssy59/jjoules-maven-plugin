/**
 * 
 */
package com.jjoules.energyDisplay;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.jjoules.utils.Result;

/**
 * @author sanoussy
 *
 */
public class EnergyRegisterJson extends EnergyDisplayHandler {

	public static EnergyRegisterJson ENERGY_REGISTER_Json = new EnergyRegisterJson();
	
	private static String fileName = "out.json";
	
	
	private EnergyRegisterJson() {
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
	public void displayIt(Map<String, Result> energyConsumedByDevice) {
		Gson gson = new Gson();
		List<Result> res = new ArrayList<Result>();
		for(String name : energyConsumedByDevice.keySet()) {
			res.add(new Result(name,energyConsumedByDevice.get(name)));
		}
		File file = new File(this.fileName);
		if (! file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter  fw = new FileWriter(file.getAbsoluteFile());
			gson.toJson(gson.toJsonTree(res), fw);
			fw.flush();
			fw.close();
			System.out.println(gson.toJsonTree(res));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
