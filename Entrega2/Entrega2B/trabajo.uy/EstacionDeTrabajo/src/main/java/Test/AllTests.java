package main.java.Test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import main.java.Test.FabricaTest;




@Suite
@SelectClasses({ControladorUsuarioTest.class, 
				ControladorUsuarioTest2.class, 
				ctrlOfertaTest.class,
				ctrlOfertaTest3.class
				})

public class AllTests {

}
