/**
 * 
 */
package com.jjoules;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author sanoussy
 *
 */
@ExtendWith(EnergyMesureExtension.class)
class EnergyMesureExtensionTest {

	@Test
    void sleep20ms() throws Exception {
        Thread.sleep(20);
    }

    @Test
    void sleep50ms() throws Exception {
        Thread.sleep(50);
    }

}
