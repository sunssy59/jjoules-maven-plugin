package com.jjoules;




/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.lifecycle.Lifecycle;
import org.apache.maven.plugin.lifecycle.Phase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
//import org.junit.jupiter.api.TestInstance.Lifecycle;

///**
// * Goal which touches a timestamp file.
// *
// * @goal touch
// * 
// * @phase process-sources
// */
@Mojo(name = "runtest",Lyfecycle.)
public class JjoulesMojo
    extends AbstractMojo
{	
	
//	/**
//     * Location of the file.
//     * @parameter expression="${project.build.directory}"
//     * @required
//     */
//      private File outputDirectory;
      
      @Parameter(defaultValue = "${project}", required = true, readonly = true)
      MavenProject project;
      
//      private EnergyMesureIt energyMesureIt = new EnergyMesureIt(new RaplPackageDomain(0));
		
		
	  public void execute() throws MojoExecutionException, MojoFailureException {
		  
		  
		  Lifecycle l;
		  boolean b = l.getPhases().contains(new Phase().);
		  
		  
		  
		  
		  
//		  try {
//				EnergyDevice device = new RaplDevice();
//				device.configure(device.getAvailableDomains());
//				getLog().info("All available domains => "+device.getAvailableDomains());
//				getLog().info("All configured domains => "+device.getConfiguredDomains());
//				
//				getLog().info("\n---- Energy consumed in the device -------");
//				for(EnergyDomain domain : device.getAvailableDomains()) {
//					getLog().info("Energy consumed before => "+EnergyMesureIt.ENERGY_MESURE_IT.getEnergyBefore());
//					EnergyMesureIt.ENERGY_MESURE_IT.setEnergyDomain(domain);
//					EnergyMesureIt.ENERGY_MESURE_IT.begin();
//					for(int i=0;i<10000; i++) {}
//					double diff = EnergyMesureIt.ENERGY_MESURE_IT.end();
//					getLog().info("Energy consumed after => "+ EnergyMesureIt.ENERGY_MESURE_IT.getEnergyAfter());
//					getLog().info("diff => "+diff+"\n");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		  
		  
		  
//		  File f = outputDirectory;
//          if ( !f.exists() )
//          {
//              f.mkdirs();
//          }
//  
//          File res = new File( f, "res.txt" );
//  
//          FileWriter w = null;
//          try
//          {
//              w = new FileWriter( res );
//  
//              w.write( "Energy consummed => "+ end);
//          }
//          catch ( IOException e )
//          {
//              throw new MojoExecutionException( "Error creating file " + res, e );
//          }finally {
//        	  getLog().info(".....fin du traitement");
//          }
	}
	
}
