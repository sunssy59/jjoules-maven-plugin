package com.jjoules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.model.Build;

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
import org.apache.maven.plugins.annotations.LifecyclePhase;
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
@Mojo(name = "runtest",
		defaultPhase = LifecyclePhase.TEST)
public class JjoulesMojo
    extends AbstractMojo
{	
	
//	/**
//     * Location of the file.
//     * @parameter expression="${project.build.directory}"
//     * @required
//     */
//      private File outputDirectory;
      
	/**
	 * The Maven Project Object
	 */
	  @Parameter(defaultValue = "${project}", required = true, readonly = true)
      private MavenProject project;
	  
	  /**
	   * The interfaces 
	   */
	  @Parameter
	  private List<String> interfaces;
	  
	  /**
	   * Coverage file
	   */
	  @Parameter(defaultValue = "coverage.txt")
	  private String coverageFile;
	 
	  @Parameter
	  public List<URL> classPathUrls = new ArrayList<>();
   
//      private EnergyMesureIt energyMesureIt = new EnergyMesureIt(new RaplPackageDomain(0));
		
		
	  public void execute() throws MojoExecutionException, MojoFailureException {
		  //Build build = project.getBuild();
		  final File classesDirectory = new File("/home/sanoussy/stage/plugins/jjoules-plugin/target/test-classes"/*build.getTestOutputDirectory()*/);
		  this.getAllUrls(classesDirectory);
		  ClassLoader cl = new URLClassLoader(classPathUrls.toArray(new URL[classPathUrls.size()]),getClass().getClassLoader());
		  getLog().info("Classes to scan are in : " + classesDirectory.getPath());
		  
	  } 
	  
	  public void getAllUrls(File classesDirectory) {
		  //File currentFile = classesDirectory;
		  File[] allFiles = classesDirectory.listFiles();
		  for(File file : allFiles) {
			  if(existsFile(file)) {
				  if(file.isFile()) {
					  try {
						  classPathUrls.add(file.toURI().toURL());
					  } catch (MalformedURLException e) {
						  e.printStackTrace();
					  }
				  }else if(file.isDirectory()) {
					  getAllUrls(file);
				  }
			  }

		  }
	  }
	  
	  private boolean existsFile(File file) {
		  if (!file.exists()) {
				getLog().error(
						"Skipping jjoules execution due to missing classes directory:"
								+ file);
				return false;
			}
		  return true;
	  }
	  
	  
	  
	private URL getClassPathUrls(File file) {
		getLog().debug("Adding project compile classpath element : " + file.getName());
		try {
			return file.toURI().toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return null;
	}


	private void readInterface(File rootDirectory, List<String> classes) {
		  //String cf = System.getProperty("coverage.file",coverageFile);
		  File coverage = new File(coverageFile);
		  if(coverage.exists()) {
			  getLog().info("Reading interfaces from "+ coverage);
			  try {
				BufferedReader reader = new BufferedReader(new FileReader(coverage));
				String line;
				while((line = reader.readLine()) != null) {
					if (line.length() > 0 && (!line.startsWith("#")) && (!line.startsWith("//")) && (!line.startsWith("/*"))){
						classes.add(line);
					}
				}
			} catch (FileNotFoundException e) {
				getLog().error("file not found : " + coverage);
			} catch (IOException e) {
				getLog().error("io exception");
			}
			  
		  }else {
			  getLog().error("file not found : " + coverage);
		  }
	
	  }

	
	  
	  private boolean canCheck() {
		  final File classesDirectory = new File(this.project.getBuild().getTestOutputDirectory());
		  if (!classesDirectory.exists()) {
				getLog().error(
						"Skipping jjoules execution due to missing classes directory:"
								+ classesDirectory);
				return false;
			}
		  	getLog().info("Classes to scan: " + classesDirectory);
		  	try {
		  		getLog().info("tests");
				for(String path : project.getTestClasspathElements()) {
					getLog().info(path);
				}
				getLog().info(System.getProperties().toString());
			} catch (DependencyResolutionRequiredException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	
			return true;
	  }
	  
	  private List<String> projectTestCompileClassPathElements() throws MojoExecutionException{
		  try {
			return project.getTestClasspathElements();
		} catch (DependencyResolutionRequiredException e) {
			throw new MojoExecutionException("Call to MavenProject#getCompileClasspathElements required dependency resolution");
		}
		  
	  }
	  
	  
	  
//	  public static String readProcessus(Process p) throws IOException {
//	        String retour = "";
//
//	        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
//	        String line = "";
//	        while ((line = reader.readLine()) != null) {
//	            retour += line +"\n";
//	        }
//	        reader.close();
//
//	        return retour;
//	    }
	
}
