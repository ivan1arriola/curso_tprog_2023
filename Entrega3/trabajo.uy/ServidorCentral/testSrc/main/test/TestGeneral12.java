package main.test;



import java.time.LocalDate;
import java.util.List;

//import java.util.Map;

import org.junit.Test;

import excepciones.ErrorAgregarUsuario;
//import excepciones.ExcepcionKeywordVacia;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.clases.Keyword;
import logica.clases.Paquete;
import logica.clases.TipoOferta;
//import logica.clases.Keyword;
import logica.controladores.CtrlCargaDeDatos;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.dto.EmpresaDTO;
import logica.dto.OfertaLaboralDTO;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlOferta;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.KeywordHandler;
import logica.manejadores.OfertaLaboralHandler;
import logica.manejadores.PaqueteHandler;
import logica.manejadores.TipoOfertaHandler;
import logica.manejadores.UsuarioHandler;


public class TestGeneral12 {
	@Test
	public void test1() {
		Fabrica fabri = Fabrica.getInstance();
        ICtrlUsuario ICU = fabri.getICtrlUsuario();
        ICtrlOferta ICO = fabri.getICtrlOferta();
        CtrlCargaDeDatos ICC = fabri.getICtrlCargaDeDatos();
        // obtener handeler
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TrabajoUy");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // Set the EntityManager for the handlers
        KeywordHandler.setBaseDatos(entityManager);
        KeywordHandler keywordHandler = KeywordHandler.getInstance();
        OfertaLaboralHandler.setBaseDatos(entityManager);
        PaqueteHandler.setBaseDatos(entityManager);
        TipoOfertaHandler.setBaseDatos(entityManager);
        UsuarioHandler.setBaseDatos(entityManager);
        // ============================================
        System.out.println("################## Test 12 ##################");
        // ============================================
        DTHora hora12 = new DTHora(8,     0);
        DTHora hora22 = new DTHora(1,     0);
        DTHorario horario2 = new DTHorario(hora12,     hora22);
        DepUY dep111 = DepUY.Montevideo;
        LocalDate currentDate11 = LocalDate.now();
        
        
        EmpresaDTO aasd = new EmpresaDTO("f","s","a","a", "2","a");
        OfertaLaboralDTO asdweqe = new OfertaLaboralDTO("a","a","a",3.14f, dep111,"a","b",currentDate11,currentDate11,3.14f,aasd);
        
        aasd.getApellido();
        aasd.getDescripcion();
        aasd.getEmail();
        aasd.getId();
        aasd.getNickname();
        aasd.getNombre();
        aasd.getSitioweb();
        String generico = "a";
        aasd.setApellido(generico);
        aasd.setDescripcion(generico);
        aasd.setEmail(generico);
        aasd.setId((long) 1234);
        aasd.setNickname(generico);
        
        
        asdweqe.getCiudad();
        asdweqe.getDepartamento();
        asdweqe.getDescripcion();
        asdweqe.getEmpresa();
        asdweqe.getHorario();
        asdweqe.getId();
        asdweqe.getNombre();
        asdweqe.getRemuneracion();
        asdweqe.setCiudad(generico);
        asdweqe.setDepartamento(dep111);
        asdweqe.setEmpresa(aasd);
        asdweqe.setHorario(generico);
        asdweqe.setId((long) 1234);
        asdweqe.getNombre();
        asdweqe.setDescripcion(generico);
        asdweqe.setNombre(generico);
        asdweqe.setRemuneracion(34.0f);
		// ============================================
        entityManager.close();
        entityManagerFactory.close();
	}
}
