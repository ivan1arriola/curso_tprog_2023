package Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import excepciones.ExceptionEmpresaInvalida;
import excepciones.ExceptionUsuarioCorreoRepetido;
import excepciones.ExceptionUsuarioNickRepetido;
import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import excepciones.ExceptionUsuarioNoEncontrado;
import logica.Controladores.CtrlOferta;
import logica.Controladores.CtrlUsuario;
import logica.Datatypes.DTUsuario;
import logica.Enumerados.DepUY;
import logica.Datatypes.DTEmpresa;
import logica.Datatypes.DTHora;
import logica.Datatypes.DTHorario;
import logica.Datatypes.DTPostulante;
import logica.Interfaces.ICtrlOferta;
import logica.Interfaces.ICtrlUsuario;
import logica.Fabrica;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class FabricaTest {

	@Test
	void altaEmpresaTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		
		try {
			boolean b = ICU.altaEmpresa("Apple Co.", "Steve", "Jobs", "stevejobs@hotmail.com", "Apple Co.", "Vendemos celulares caros pero buenos.");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido e1) {
			// no se realiza ninguna acción.
		}

		DTEmpresa datosEmpresa = (DTEmpresa) ICU.obtenerDatosUsuario("Apple Co.");           // Obtengo los datos
		assertEquals(datosEmpresa.getNombre(), "Steve");
		assertEquals(datosEmpresa.getApellido(), "Jobs");
		assertEquals(datosEmpresa.getNickname(), "Apple Co.");
		assertEquals("Vendemos celulares caros pero buenos.",datosEmpresa.getDescripcion()); // La descripción asociada es correcta.
		assertEquals(datosEmpresa.getCorreo_electronico(), "stevejobs@hotmail.com");
		assertEquals(datosEmpresa.getNombreEmpresa(), "Apple Co.");
	}
	
	@Test
	void altaEmpresaURLTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
		boolean b = false;
		try {
			b = ICU.altaEmpresaURL("Samsung", "Lee", "Byung", "leebyung@hotmail.com", "Samsung", "Vendemos celulares a precio de competencia", "www.samsung.com");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido e1) {
			// no se realiza ninguna acción.
		}
		DTEmpresa datosEmpresa = (DTEmpresa) ICU.obtenerDatosUsuario("Samsung");
		assertEquals(datosEmpresa.getUrl(),"www.samsung.com");
		HashSet<String> usuarios = ICU.listarNicknamesUsuarios();
		
		assertEquals(true,usuarios.contains("Samsung"));        // Si "Apple Co." esta en la lista de usuarios.
	}
	
	@Test
	void altaKeywordTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		boolean b = ICO.altaKeyword("Trabajo nocturno");
		
		HashSet<String> keywords = ICO.listarKeywords();
		
		assertEquals(true, keywords.contains("Trabajo nocturno")); // Se dio de alta la keyword
	}
	
	@Test
	void altaPostulanteTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();

		LocalDate fechaDeNacGauss = LocalDate.of(1777, 4, 30);
		try {
			boolean b = ICU.altaPostulante("gaussito","Frederich", "Gauss", "gauss@hotmail.com", fechaDeNacGauss, "Aleman");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido e) {
		}
		
		DTPostulante gauss = (DTPostulante) ICU.obtenerDatosUsuario("gaussito");
		
		assertTrue(gauss.getNacionalidad().equals("Aleman"));
	}
	
	@Test
	void listarPostulanteS() {
		Fabrica f = Fabrica.getInstance();
		ICtrlOferta ICO = f.getICtrlOferta();
		
		HashSet<String> postulantes = ICO.listarPostulantes();
		
		assertEquals(13, postulantes.size());
	}
	
	@Test
	void usuarioTest(){
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario icu = f.getICtrlUsuario();
		boolean b;
		LocalDate fecha = LocalDate.of(1800,7,4);
		
		try {
			b = icu.altaPostulante("Name", "mellamo", "ape", "mailcito@h.com", fecha, "Uruguayo");
		} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
		}
		
		
		assertThrows( ExceptionUsuarioNickYCorreoRepetidos.class, () -> {icu.altaPostulante("Name", "mellamo", "ape", "mailcito@h.com", fecha, "Venezolano"); });

	}
	
	@Test
    void testObtenerDatosUsuario() {
		Fabrica f = Fabrica.getInstance();
        ICtrlUsuario ctrlUsuario = f.getICtrlUsuario();

        // Agregar un usuario ficticio para probar la obtención de datos
        try {
            ctrlUsuario.altaPostulante("nickU", "NombreU", "ApellidoU", "correoU@example.com", LocalDate.now(), "NacionalidadU");
        } catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
            // Manejo de excepciones
        }

        DTUsuario datosUsuario = ctrlUsuario.obtenerDatosUsuario("nickU");
        assertNotNull(datosUsuario);
        assertEquals("NombreU", datosUsuario.getNombre());
    }
	
	@Test
	void contieneTest(){
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario icu =f.getICtrlUsuario();
		try {
			boolean b = icu.altaEmpresa("Ezequiel", "Dan", "Ale", "soyoy@mail.com", "Empresarios","Los mejores");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido e) {

		}
		try {
			boolean b2 = icu.altaEmpresa("Otro", "Dan", "Ale", "nadie@mail.com", "Empresarios","Geniales");
		} catch (ExceptionUsuarioCorreoRepetido | ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido e) {

		}
		icu.ingresarDatosEditados("Otro", "danu", "alen");
		
		HashSet<String> empre; empre = icu.listarEmpresas();
		
	}
	
	@Test
	void testIngresarDatosEditados() {
			Fabrica f = Fabrica.getInstance();
		    ICtrlUsuario ICU = f.getICtrlUsuario();
		    LocalDate fechaNacimiento = LocalDate.of(1707, 5, 23);
			try {
				ICU.altaPostulante("Linneo", "Carl", "Linnaeus", "linneo@example.com", fechaNacimiento, "Sweden");
			} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
			}
		    
		    DTUsuario KVL = ICU.obtenerDatosUsuario("Linneo");
		    ICU.ingresarDatosEditados("Linneo", "Karl Von", "Linneus");
		    DTUsuario KVLActualizado = ICU.obtenerDatosUsuario("Linneo");
		    assertEquals("Linneus", KVLActualizado.getApellido());
	}
	
	@Test
	public void postulantesOfertaLaboralTest() {
		Fabrica f = Fabrica.getInstance();
		ICtrlUsuario ICU = f.getICtrlUsuario();
	    
		try {
            assertTrue(ICU.altaPostulante("nick1", "Juan", "Perez", "juan@example.com", LocalDate.of(1990, 5, 15), "Mexicana"));
            assertTrue(ICU.altaPostulante("nick2", "Maria", "Lopez", "maria@example.com", LocalDate.of(1988, 9, 10), "Colombiana"));
            assertTrue(ICU.altaPostulante("nick3", "Carlos", "Gomez", "carlos@example.com", LocalDate.of(1995, 2, 25), "Argentino"));
            assertTrue(ICU.altaPostulante("nick4", "Laura", "Ramirez", "laura@example.com", LocalDate.of(1992, 7, 8), "Chilena"));
            assertTrue(ICU.altaPostulante("nick5", "Andres", "Torres", "andres@example.com", LocalDate.of(1987, 11, 3), "Peruano"));
            assertTrue(ICU.altaPostulante("nick6", "Sofia", "Martinez", "sofia@example.com", LocalDate.of(1998, 4, 20), "Ecuatoriana"));
            assertTrue(ICU.altaPostulante("nick7", "Gabriel", "Rios", "gabriel@example.com", LocalDate.of(1993, 10, 1), "Uruguaya"));
            assertTrue(ICU.altaPostulante("nick8", "Ana", "Silva", "ana@example.com", LocalDate.of(1991, 12, 12), "Paraguaya"));
            assertTrue(ICU.altaPostulante("nick9", "Miguel", "Fernandez", "miguel@example.com", LocalDate.of(1989, 6, 30), "Boliviana"));
            assertTrue(ICU.altaPostulante("nick10", "Carmen", "Gutierrez", "carmen@example.com", LocalDate.of(1997, 3, 5), "Venezolana"));
            
        } catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
            
        }

		HashSet<String> nicknames = ICU.listarNicknamesUsuarios();
        
        assertTrue(nicknames.contains("nick1")); // Asegura que el nickname "nick1" esté en la lista
        assertTrue(nicknames.contains("nick10")); // Asegura que el nickname "nick10" esté en la lista

        HashSet<String> nicknamesPostu = ICU.obtenerNicknamesPostulantes();
        
        assertTrue(nicknamesPostu.contains("nick10")); // Asegura que el nickname "nick10" esté en la lista
        
		 DTUsuario dtus = new DTUsuario("nick1", "Juan", "Perez", "juan@example.com");
		 dtus = ICU.obtenerDatosUsuario("nick1");

         assertNotNull(dtus); // Asegura que se haya obtenido un DTUsuario válido
         assertEquals("nick1", dtus.getNickname());
         assertEquals("Juan", dtus.getNombre());
         assertEquals("Perez", dtus.getApellido());
         assertEquals("juan@example.com", dtus.getCorreo_electronico());

	}
	
    @Test
    void testAltaPostulante() {
		Fabrica f = Fabrica.getInstance();
        ICtrlUsuario ctrlUsuario = f.getICtrlUsuario();
        LocalDate fechaNac = LocalDate.of(1990, 1, 1);

        // Verificar que se puede dar de alta un postulante correctamente
        assertDoesNotThrow(() -> {
            ctrlUsuario.altaPostulante("pepito", "Nombre", "Apellido", "correo1@example.com", fechaNac, "Nacionalidad");
        });
    }
	
    @Test
	void testListarEmpresas() {
		Fabrica f = Fabrica.getInstance();
        ICtrlUsuario ctrlUsuario = f.getICtrlUsuario();

        // Agregar una empresa ficticia para probar la lista
        try {
            ctrlUsuario.altaEmpresa("nickE", "NombreE", "ApellidoE", "correoE@example.com", "EmpresaE", "DescripciónE");
        } catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
            // Manejo de excepciones
        }

        HashSet<String> empresas = ctrlUsuario.listarEmpresas();
        assertTrue(empresas.contains("nickE"));
    }
	
    @Test
    public void testDifferentDataScenario() {
	Fabrica f = Fabrica.getInstance();
	ICtrlUsuario CtrlUsuario = f.getICtrlUsuario();
        CtrlUsuario ctrlUsuario = new CtrlUsuario();
        LocalDate fechaNac = LocalDate.of(1990, 1, 1);

        // Alta de postulante exitoso
        boolean altaExitosa = false;
		try {
			altaExitosa = ctrlUsuario.altaPostulante("nick123", "Juan", "Perez", "ajuan@example.com", fechaNac, "Argentina");
		} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertTrue(altaExitosa);



        // Alta exitosa de empresa
        boolean altaEmpresaExitoso = false;
		try {
			altaEmpresaExitoso = ctrlUsuario.altaEmpresa("nick789", "Empresa2", "Apellido2", "empresa@example.com", "EmpresaE", "DescripciónE");
		} catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido
				| ExceptionUsuarioCorreoRepetido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        assertTrue(altaEmpresaExitoso);

        // Listar empresas para verificar que la empresa se haya agregado
        HashSet<String> empresas = ctrlUsuario.listarEmpresas();
        assertTrue(empresas.contains("nick789"));

        // Obtener datos de un usuario para verificar que los datos sean correctos
        DTUsuario datosUsuario = ctrlUsuario.obtenerDatosUsuario("nick123");
        assertNotNull(datosUsuario);
        assertEquals("Juan", datosUsuario.getNombre());

        // Verificar que no existe postulación en un usuario
        assertFalse(ctrlUsuario.existePostulacion("nick123", "Oferta1"));

    }

}
