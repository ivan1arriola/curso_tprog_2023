package test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import test.logica.FabricaTest;
import test.logica.Controladores.ControladorUsuarioTest;
import test.logica.Controladores.ctrlOfertaTest;



@Suite
@SelectClasses(
		{
			FabricaTest.class, 
			ControladorUsuarioTest.class,
			ctrlOfertaTest.class
			
			
		}
)

public class AllTests {

}
