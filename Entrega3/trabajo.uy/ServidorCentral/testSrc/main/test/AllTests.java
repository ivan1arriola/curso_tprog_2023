package main.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
		TestGeneral1.class,
		TestGeneral2.class,
		TestGeneral3.class,
		TestGeneral4.class,
		TestGeneral5.class,
		TestGeneral6.class
//		ControladorUsuarioTest.class,
//		ControladorUsuarioTest2.class,
//		ControladorUsuarioTest3.class,
//		ControladorUsuarioTest4.class,
//		ControladorUsuarioTest5.class,
//		ctrlOfertaTest.class,
//		OfertasTest.class
})

public class AllTests {

}
