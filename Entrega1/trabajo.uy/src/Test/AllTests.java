package Test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({FabricaTest.class, ControladorUsuarioTest.class})

public class AllTests {

}
