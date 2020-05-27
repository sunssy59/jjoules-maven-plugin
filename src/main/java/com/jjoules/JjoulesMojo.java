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
import org.apache.maven.plugins.annotations.Mojo;

import com.jjoules.energyDomain.rapl.RaplPackageDomain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

///**
// * Goal which touches a timestamp file.
// *
// * @goal touch
// * 
// * @phase process-sources
// */
@Mojo(name = "runtest")
public class JjoulesMojo
    extends AbstractMojo
{	
	
	/**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
      private File outputDirectory;
      
      private EnergyMesureIt energyMesureIt = new EnergyMesureIt(new RaplPackageDomain(0));
		
	  public void execute() throws MojoExecutionException, MojoFailureException {
		  
		  Logger.setLog(getLog());
		  
		  energyMesureIt.begin();
		  getLog().info("En cours d'exécution.........");
		  double end = energyMesureIt.end();
		  
		  getLog().info("Fin de l'exécution => energy consumed = "+end);
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
	
	
	
	
	
	
	
//	@Parameter(defaultValue = "${project}", readonly = true, required = true)
//    private MavenProject project;
//
//	public void execute() throws MojoExecutionException, MojoFailureException {
//        getLog().info("Hello world");
//   }
    
    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
//    private File outputDirectory;
//
//    public void execute()
//        throws MojoExecutionException
//    {
//        File f = outputDirectory;
//
//        if ( !f.exists() )
//        {
//            f.mkdirs();
//        }
//
//        File touch = new File( f, "touch.txt" );
//
//        FileWriter w = null;
//        try
//        {
//            w = new FileWriter( touch );
//
//            w.write( "touch-test.txt\n" );
//        }
//        catch ( IOException e )
//        {
//            throw new MojoExecutionException( "Error creating file " + touch, e );
//        }
//        finally
//        {
//            if ( w != null )
//            {
//                try
//                {
//                    w.close();
//                }
//                catch ( IOException e )
//                {
//                    // ignore
//                }
//            }
//        }
//    }
}
