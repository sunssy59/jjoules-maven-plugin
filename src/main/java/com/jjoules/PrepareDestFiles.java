/**
 * 
 */
package com.jjoules;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.jjoules.energyDisplay.EnergyDisplayHandler;

/**
 * @author sanoussy
 *
 */
@Mojo(name = "prepare-output",
defaultPhase = LifecyclePhase.INITIALIZE)
public class PrepareDestFiles extends AbstractMojo {
	
	
	@Parameter(defaultValue = "target/jjoules-reports", required = true)
    private String outputDirectory;


	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		String[] dirs = outputDirectory.split("/");
		String curDir = "";
		boolean isFirstDir = true;
		for(String s : dirs) {
			File newTargetDir;
			if (!isFirstDir)
				newTargetDir = new File(curDir+"/"+s);
			else {
				newTargetDir = new File(curDir+s);
				isFirstDir = false;
			}
			boolean b = newTargetDir.mkdir();
			curDir = newTargetDir.getAbsolutePath();
		}
		File fileCSV = new File(curDir+"/report.csv");
		File fileJSON = new File(curDir+"/report.json");
		if (! (fileCSV.exists() && fileJSON.exists())) {
			try {
				boolean b = fileCSV.createNewFile();
				if(b)
					getLog().info("file "+fileCSV.getAbsolutePath()+" create successfully");
				else
					getLog().error("file "+fileCSV.getAbsolutePath()+" create error");
				b = fileJSON.createNewFile();
				if(b)
					getLog().info("file "+fileJSON.getAbsolutePath()+" create successfully");
				else
					getLog().error("file "+fileJSON.getAbsolutePath()+" create error");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
