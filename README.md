<groupId>org.powerapi.jjoules</groupId>
  <artifactId>jjoules-maven-plugin</artifactId>
  <packaging>maven-plugin</packaging># jjoules-plugin

**Commandes utiles**

`mvn install`


**Using in other project**

Just add these tow dependencies in your pom.xml.

```
	<dependency>
		<groupId>org.powerapi.jjoules</groupId>
  	<artifactId>jjoules-maven-plugin</artifactId>
  	<packaging>maven-plugin</packaging>
	  <scope>test</scope>
  </dependency>
```



```
	<!-- contains the engine that actually runs the Jupiter-tests -->
	<dependency>
		<groupId>org.junit.jupiter</groupId>
		<artifactId>junit-jupiter-engine</artifactId>
		<version>${junit-jupiter-version}</version>
		<scope>test</scope>
	</dependency>
```


And now you can just use this annotation  `@MesureIt` like

```
//...

@MesureIt
public class MyTest{
	//...
}

```

The reports are in files `target/jjoules-reports/report.csv` for csv report and `target/jjoules-reports/report.json` for json report.
