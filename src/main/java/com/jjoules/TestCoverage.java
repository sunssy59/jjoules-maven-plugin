/**
 * 
 */
package com.jjoules;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author sanoussy
 *
 */
public class TestCoverage {
	private static final Logger log = Logger.getLogger(TestCoverage.class.getName());

	/**
	 * 
	 */
	public TestCoverage() {
		
	}
	
	public static void report(ClassLoader classLoader,List<URL> urls) throws IOException {
		if(urls.isEmpty()) {
			log.warning("No test classes to cover");
			return;
		}
		
		//for(URL url : urls) {
			
			InputStream is = classLoader.getResourceAsStream("/home/sanoussy/stage/plugins/jjoules-plugin/target/test-classes/com/jjoules/energyDevice/rapl/RaplDeviceTest".replace(".", "/") + ".class");
			
//			ClassFile classFile = getClassFile("/home/sanoussy/stage/plugins/jjoules-plugin/target/test-classes/com/jjoules/energyDevice/rapl/RaplDeviceTest",is);
//			
//			//ConstPool cPool = classFile.getConstPool();
//			
//			for(MethodInfo method : classFile.getMethods()) {
//				System.out.println(method.getName());
//			//}
//			
		}
		
		
	
//	private static ClassFile getClassFile(Object info, InputStream is) throws IOException {
//        if (is == null) {
//            throw new IOException("Missing class: " + info);
//        }
//
//        ClassFile cf;
//        try {
//            cf = new ClassFile(new DataInputStream(is));
//        } finally {
//            is.close();
//        }
//        return cf;
//    }
}

