# jjoules maven plugin

First of all you must install the project [juint-jjoules](https://github.com/Mamadou59/junit-jjoules) which depends on [j-joules](https://github.com/Mamadou59/j-joules) with


	mvn clean install 
	
Then add this dependency which will allow you to use the project [juint-jjoules](https://github.com/Mamadou59/junit-jjoules)

```
<dependency>
	<groupId>org.powerapi.junitjjoules</groupId>
	<artifactId>junit-jjoules</artifactId>
	<version>1.0-SNAPSHOT</version>
</dependency>
```

**Commandes utiles**

`mvn install` to install the plugin

Add this dependance for your Junit5 test execution

```
	<!-- contains the engine that actually runs the Jupiter-tests -->
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-engine</artifactId>
		<version>${junit-jupiter-version}</version>
		<scope>test</scope>
	</dependency>
```

And add this plugin in your project *pom.xml*

```
<plugin>
	<groupId>org.powerapi.jjoulesmaven</groupId>
	<artifactId>jjoules-maven-plugin</artifactId>
	<version>1.0-SNAPSHOT</version>
	<executions>
		<execution>						
			<!-- <phase>jjoulestest</phase> 
			<configuration>
				<outputDirectory>${basedir}/target/jjoules-reports</outputDirectory>
			</configuration> -->
			<goals>
				<goal>prepare-output</goal> 
				<goal>save-result</goal>
			</goals>
			
		</execution>
	</executions>
</plugin>
```

Now you can just use this annotation  `@MesureIt` like

```
//...

@MesureIt
public class MyTest{
	//...
}

```

By compiling your project with the goals 	`mvn verify`, `mvn test`, ...

You will get the energy consumption reports of all the test classes in the files `target/jjoules-reports/report.csv` for *csv* report and `target/jjoules-reports/report.json` for *json* report.
