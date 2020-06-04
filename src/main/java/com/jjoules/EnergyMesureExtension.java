/**
 * 
 */
package com.jjoules;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import com.jjoules.energyDisplay.EnergyPrinter;
import com.jjoules.energyDisplay.EnergyRegisterCSV;
import com.jjoules.energyDomain.rapl.RaplPackageDomain;

/**
 * @author sanoussy
 *
 */
public class EnergyMesureExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

	private static final Logger LOGGER = Logger.getLogger(EnergyMesureExtension.class.getName());
	private static final String START_ENERGY = "start energy";
	private static EnergyMesureIt ENERGY_MESURE_IT = new EnergyMesureIt(new RaplPackageDomain(0));
	private static Map<String,Double> resultEnergyConsumed = new HashMap<String,Double>();
	
	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		ENERGY_MESURE_IT.begin();
		getStore(context).put(START_ENERGY,ENERGY_MESURE_IT.getEnergyBefore());

	}
	
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		Method testMethod = context.getRequiredTestMethod();
		double startEnergy = getStore(context).remove(START_ENERGY, double.class);
		double end = ENERGY_MESURE_IT.end();
		
		// end can be replace by ENERGY_MESURE_IT.getEnergyAfter()-startEnergy
		this.resultEnergyConsumed.put(testMethod.getName(), end);
		
		//LOGGER.info(() -> 
				//String.format("Method [%s]  took  %s ; end => %s  mj", testMethod.getName(),ENERGY_MESURE_IT.getEnergyAfter()-startEnergy, end));
		
		//System.out.println(String.format("Method [%s]  took  %s ; end => %s  mj", testMethod.getName(),ENERGY_MESURE_IT.getEnergyAfter()-startEnergy , end));
	}
	
	private Store getStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
    }

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		// printing result
		EnergyPrinter.ENERGY_PRINTER.displayIt(resultEnergyConsumed);
		
		//saving result in CSV file
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.setFileName("out.csv");
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.displayIt(resultEnergyConsumed);
		
	}

	

}
