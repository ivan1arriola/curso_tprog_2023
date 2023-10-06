package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Datatypes.DTEmpresa;
import model.Datatypes.DTOfertaExtendido;
import model.Datatypes.DTOfertaLaboral;
import model.Datatypes.DTPostulante;
import model.Datatypes.DTUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Servlet implementation class ListarUsuarios
 */
@WebServlet("/usuarios")
public class ListarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarUsuarios() {
        super();
    }
    
    // Simula una funcion de la logica
    private List<DTUsuario> getUsuarios(){
    	
    	List<DTUsuario> usuarios = new ArrayList<>();
    	
    	// Agregar Postulantes
    	usuarios.add(new DTPostulante("lgarcia", "lgarcia85@gmail.com", "García", "Lucía", null, LocalDate.of(1985, 3, 15), "Uruguaya"));
    	usuarios.add(new DTPostulante("matilo", "matias.lopez90@hotmail.com", "López", "Matías", null, LocalDate.of(1990, 8, 21), "Argentina"));
    	usuarios.add(new DTPostulante("maro", "marrod@gmail.com", "Rodríguez", "María", null, LocalDate.of(1988, 11, 10), "Uruguaya"));
    	usuarios.add(new DTPostulante("javierf", "javierf93@yahoo.com", "Fernández", "Javier", null, LocalDate.of(1993, 6, 5), "Mexicana"));
    	usuarios.add(new DTPostulante("valen25", "vale87@gmail.com", "Martínez", "Valentina", null, LocalDate.of(1987, 2, 25), "Uruguaya"));
    	usuarios.add(new DTPostulante("andpe12", "anpe92@hotmail.com", "Pérez", "Andrés", null, LocalDate.of(1992, 4, 12), "Chilena"));
    	usuarios.add(new DTPostulante("sicam", "camilasilva89@gmail.com", "Silva", "Camila", null, LocalDate.of(1989, 9, 30), "Uruguaya"));
    	usuarios.add(new DTPostulante("sebgon", "gonza95@yahoo.com", "González", "Sebastián", null, LocalDate.of(1995, 1, 18), "Colombiana"));
    	usuarios.add(new DTPostulante("isabel", "loisa@gmail.com", "López", "Isabella", null, LocalDate.of(1991, 7, 7), "Uruguaya"));
    	usuarios.add(new DTPostulante("marram02", "marram@hotmail.com", "Ramírez", "Martín", null, LocalDate.of(1986, 12, 2), "Argentina"));

    	// Agregar Empresas
    	// public DTEmpresa(String nickname, String correo_electronico, String apellido, String nombre, String descripcion, String url, HashSet<DTOfertaExtendido> dtOfertas, byte[] imagen)
    	String ecotechDescripcion = "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología y la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible";
    	
    	usuarios.add(new DTEmpresa("EcoTech", "info@EcoTech.com", "Johnson", "Sophia", ecotechDescripcion, "http://www.EcoTechInnovations.com", new HashSet<DTOfertaExtendido>(), null));
    	usuarios.add(new DTEmpresa("FusionTech", "contacto@FusionTech.net", "Smith", "William", "", "FusionTech", new HashSet<DTOfertaExtendido>(), null));
    	usuarios.add(new DTEmpresa("GlobalHealth", "jobs@GlobalHealth.uy", "Brown", "Isabella", "", "GlobalHealth", new HashSet<DTOfertaExtendido>(), null));
		return usuarios;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DTUsuario> ofertas = getUsuarios();

        request.setAttribute("usuarios", ofertas);

        request.getRequestDispatcher("/WEB-INF/listar/usuarios.jsp").forward(request, response);
    
    }

}
