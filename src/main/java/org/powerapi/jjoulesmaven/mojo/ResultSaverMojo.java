/**
 * 
 */
package org.powerapi.jjoulesmaven.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.ResolutionScope;

import org.powerapi.jjoules.energy.display.EnergyRegisterJson;

/**
 * @author sanoussy
 *
 */
@Mojo(name = "save-result",defaultPhase = LifecyclePhase.TEST,requiresDependencyResolution = ResolutionScope.RUNTIME)
public class ResultSaverMojo extends AbstractJjoulesMojo{
	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		//EnergyRegisterJson.saveIt(this.directory);
		
	}

}

