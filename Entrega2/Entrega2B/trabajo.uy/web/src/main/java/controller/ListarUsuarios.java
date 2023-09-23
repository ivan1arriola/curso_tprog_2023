package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Datatypes.DTEmpresa;
import model.Datatypes.DTOfertaLaboral;
import model.Datatypes.DTPostulante;
import model.Datatypes.DTUsuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
        // TODO Auto-generated constructor stub
    }
    
    // Simula una funcion de la logica
    private List<DTUsuario> getUsuarios(){
    	
    	List<DTUsuario> usuarios = new ArrayList<>();
    	
    	// Agregar Postulantes
    	usuarios.add(new DTPostulante("lgarcia", "lgarcia85@gmail.com", "García", "Lucía", "https://tinyurl.com/yckek63e", LocalDate.of(1985, 3, 15), "Uruguaya"));
    	usuarios.add(new DTPostulante("matilo", "matias.lopez90@hotmail.com", "López", "Matías", "https://picsum.photos/603", LocalDate.of(1990, 8, 21), "Argentina"));
    	usuarios.add(new DTPostulante("maro", "marrod@gmail.com", "Rodríguez", "María", "https://picsum.photos/602", LocalDate.of(1988, 11, 10), "Uruguaya"));
    	usuarios.add(new DTPostulante("javierf", "javierf93@yahoo.com", "Fernández", "Javier", "https://picsum.photos/604", LocalDate.of(1993, 6, 5), "Mexicana"));
    	usuarios.add(new DTPostulante("valen25", "vale87@gmail.com", "Martínez", "Valentina", "https://picsum.photos/605", LocalDate.of(1987, 2, 25), "Uruguaya"));
    	usuarios.add(new DTPostulante("andpe12", "anpe92@hotmail.com", "Pérez", "Andrés", "https://picsum.photos/606", LocalDate.of(1992, 4, 12), "Chilena"));
    	usuarios.add(new DTPostulante("sicam", "camilasilva89@gmail.com", "Silva", "Camila", "https://picsum.photos/607", LocalDate.of(1989, 9, 30), "Uruguaya"));
    	usuarios.add(new DTPostulante("sebgon", "gonza95@yahoo.com", "González", "Sebastián", "https://picsum.photos/608", LocalDate.of(1995, 1, 18), "Colombiana"));
    	usuarios.add(new DTPostulante("isabel", "loisa@gmail.com", "López", "Isabella", "https://picsum.photos/609", LocalDate.of(1991, 7, 7), "Uruguaya"));
    	usuarios.add(new DTPostulante("marram02", "marram@hotmail.com", "Ramírez", "Martín", "https://picsum.photos/610", LocalDate.of(1986, 12, 2), "Argentina"));

    	// Agregar Empresas
    	usuarios.add(new DTEmpresa("EcoTech", "info@EcoTech.com", "Johnson", "Sophia",  "https://tinyurl.com/mr2hcufa","EcoTech", "EcoTech Innovations es una empresa líder en soluciones tecnológicas sostenibles. Nuestro enfoque se centra en desarrollar y comercializar productos y servicios que aborden los desafíos ambientales más apremiantes de nuestro tiempo. Desde sistemas de energía renovable y dispositivos de monitorización ambiental hasta soluciones de gestión de residuos inteligentes, nuestra misión es proporcionar herramientas que permitan a las empresas y comunidades adoptar prácticas más ecológicas sin comprometer la eficiencia. Creemos en la convergencia armoniosa entre la tecnología y la naturaleza, y trabajamos incansablemente para impulsar un futuro más limpio y sostenible.", "http://www.EcoTechInnovations.com"));
    	usuarios.add(new DTEmpresa("FusionTech", "contacto@FusionTech.net", "Smith", "William", "https://picsum.photos/612","FusionTech", "FusionTech Dynamics es una empresa pionera en el ámbito de la inteligencia artificial y la automatización avanzada. Nuestro equipo multidisciplinario de ingenieros, científicos de datos y desarrolladores crea soluciones innovadoras que aprovechan la potencia de la IA para transformar industrias. Desde la optimización de procesos industriales hasta la creación de asistentes virtuales altamente personalizados, nuestro objetivo es revolucionar la forma en que las empresas operan y se conectan con sus clientes. Creemos en la sinergia entre la mente humana y las capacidades de la IA, y trabajamos para construir un mundo donde la tecnología mejore y amplíe nuestras capacidades innatas.", "http://www.FusionTechDynamics.net"));
    	usuarios.add(new DTEmpresa("GlobalHealth", "jobs@GlobalHealth.uy", "Brown", "Isabella", "https://picsum.photos/613", "GlobalHealth","GlobalHealth Dynamics es una empresa comprometida con el avance de la atención médica a nivel mundial. Como líderes en el campo de la salud digital, desarrollamos plataformas y herramientas que permiten a los profesionales de la salud ofrecer diagnósticos más precisos, tratamientos personalizados y seguimiento continuo de los pacientes. Nuestra visión es crear un ecosistema de salud conectado en el que los datos médicos se utilicen de manera ética y segura para mejorar la calidad de vida de las personas. A través de la innovación constante y la colaboración con expertos médicos, estamos dando forma al futuro de la atención médica, donde la tecnología y la compasión se unen para salvar vidas y mejorar el bienestar en todo el mundo.", "http://www.GlobalHealthDynamics.uy/info"));
    	usuarios.add(new DTEmpresa("ANTEL", "jarrington@ANTEL.com.uy", "Rocha", "Washington","https://itc.com.uy/wp-content/uploads/2018/09/ANTEL.png","ANTEL",  "En Antel te brindamos servicios de vanguardia en tecnología de comunicación en Telefonía Móvil, Fija, Banda Ancha y Datos", "ANTEL.com.uy"));
    	usuarios.add(new DTEmpresa("MIEM", "eldiez@MIEM.org.uy", "Bengoechea", "Pablo", "https://eficienciaenergetica.miem.gub.uy/Eficiencia2020-theme/images/eficiencia/logo-miem-blanco.png","MIEM",  "Balance Energético Nacional (BEN). La Dirección Nacional de Energía (DNE) del Ministerio de Industria, Energía y Minería (MIEM) presenta anualmente el BEN.", "MIEM.com.uy"));
    	usuarios.add(new DTEmpresa("TechSolutions", "Mercedes@TechSolutions.com.uy", "Venn", "Mercedes", "https://picsum.photos/616", "TechSolutions", "TechSolutions Inc. es una empresa líder en el sector de tecnología de la información y el software. Se especializa en el desarrollo de soluciones de software personalizadas para empresas de diversos tamaños y sectores. Su enfoque se centra en la creación de aplicaciones empresariales innovadoras que optimizan procesos, mejoran la eficiencia y brindan una ventaja competitiva a sus clientes.", "TechSolutions.com"));

		return usuarios;
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 // Obtiene las ofertas laborales
        List<DTUsuario> ofertas = getUsuarios();

        // Almacena las ofertas como un atributo en el objeto request
        request.setAttribute("usuarios", ofertas);

        // Reenvía la solicitud al JSP
        request.getRequestDispatcher("/WEB-INF/listar/usuarios.jsp").forward(request, response);
    
    }

}
