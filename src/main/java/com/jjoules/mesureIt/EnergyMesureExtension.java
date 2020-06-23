/**
 * 
 */
package com.jjoules.mesureIt;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store;

import com.jjoules.EnergyMesureIt;
import com.jjoules.energyDisplay.EnergyPrinter;
import com.jjoules.energyDisplay.EnergyRegisterCSV;
import com.jjoules.energyDisplay.EnergyRegisterJson;
import com.jjoules.energyDomain.EnergyDomain;
import com.jjoules.energyDomain.rapl.RaplPackageDomain;
import com.jjoules.utils.Result;

/**
 * @author sanoussy
 *
 */
public class EnergyMesureExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback,BeforeAllCallback {

	private static final Logger LOGGER = Logger.getLogger(EnergyMesureExtension.class.getName());
	private static final EnergyDomain domain = new RaplPackageDomain(0);
	private static EnergyMesureIt ENERGY_MESURE_IT = new EnergyMesureIt(domain);
	private static Map<String,Result> resultEnergyConsumed;
	
	
	@Override
	public void beforeAll(ExtensionContext context) throws Exception {
		EnergyMesureIt.ENERGY_MESURE_IT.setEnergyDomain(domain);
		resultEnergyConsumed =  new HashMap<String,Result>();
	}
	
	@Override
	public void beforeTestExecution(ExtensionContext context) throws Exception {
		ENERGY_MESURE_IT.begin();
		getMethodStore(context).put(LaunchEnergyKey.METHOD_TEST_ENERGY,ENERGY_MESURE_IT.getEnergyBefore());
		getMethodStore(context).put(LaunchEnergyKey.METHOD_TEST_DURATION,System.currentTimeMillis());
	}
	
	@Override
	public void afterTestExecution(ExtensionContext context) throws Exception {
		Method testMethod = context.getRequiredTestMethod();
		double startEnergy = getMethodStore(context).remove(LaunchEnergyKey.METHOD_TEST_ENERGY, double.class);
		long startTime = getMethodStore(context).remove(LaunchEnergyKey.METHOD_TEST_DURATION, long.class);
		double end = ENERGY_MESURE_IT.end();
		long duration = System.currentTimeMillis() - startTime;
		
		// end can be replace by ENERGY_MESURE_IT.getEnergyAfter()-startEnergy
		this.resultEnergyConsumed.put(testMethod.getName(), new Result(end,duration));
		
		//LOGGER.info(() -> 
				//String.format("Method [%s]  took  %s ; end => %s  mj", testMethod.getName(),ENERGY_MESURE_IT.getEnergyAfter()-startEnergy, end));
	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {
		// printing result
		EnergyPrinter.ENERGY_PRINTER.displayIt(resultEnergyConsumed);
		
		String className = context.getRequiredTestClass().getSimpleName();
		
		//saving result in CSV file
		EnergyRegisterCSV.CURRENT_CLASS_NAME = className;
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.setFileName("out.csv");
		EnergyRegisterCSV.ENERGY_REGISTER_CSV.displayIt(resultEnergyConsumed);
		
		//saving result in CSV file
		EnergyRegisterJson.CURRENT_CLASS_NAME = className;
		EnergyRegisterJson.ENERGY_REGISTER_Json.setFileName("out.json");
		EnergyRegisterJson.ENERGY_REGISTER_Json.displayIt(resultEnergyConsumed);
		
	}
	
	private Store getMethodStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context.getRequiredTestMethod()));
    }
	
	private Store getClassStore(ExtensionContext context) {
        return context.getStore(Namespace.create(getClass(), context.getRequiredTestClass()));
    }

	private enum LaunchEnergyKey {
		METHOD_TEST_ENERGY, CLASS_TEST_ENERGY,
		METHOD_TEST_DURATION, CLASS_TEST_DURATION
	}

}
