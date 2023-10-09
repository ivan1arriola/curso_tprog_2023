package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTCompraPaquetes;
import main.java.logica.datatypes.DTEmpresaConCompras;
import main.java.logica.datatypes.DTHora;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import java.io.IOException;
import java.time.LocalDate;

import enumeration.TipoUsuario;

/**
 * Servlet implementation class AltaOfertaLaboral
 */
@WebServlet("/altaofertalaboral")
public class AltaOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
		} else {
			TipoUsuario tipo = (TipoUsuario) session.getAttribute("tipoUsuario");
			if( tipo == null || tipo== TipoUsuario.Postulante || tipo==TipoUsuario.Visitante) {
				response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
			} else {
				String nickname = (String) session.getAttribute("nickname");
				cargarTipoOferta(request, response);
				cargarKeywords(request, response);
				cargarPaquetes(request, response, nickname);
				request.getRequestDispatcher("/WEB-INF/altaOfertaLaboral/altaOfertaLaboral.jsp").forward(request, response);
			}
			
		}
		
	}
	
	
	private void cargarPaquetes(HttpServletRequest request, HttpServletResponse response, String nickname) {

		Set<String> paquetes = Fabrica.getInstance().getICtrlOferta().listarComprasPaquete(nickname);

		request.setAttribute("paquetes", paquetes);
	}

	private void cargarKeywords(HttpServletRequest request, HttpServletResponse response) {
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
		
	}

	private void cargarTipoOferta(HttpServletRequest request, HttpServletResponse response) {
		Set<String> tipoPublicaciones = Fabrica.getInstance().getICtrlOferta().listarTipoDePublicaciones();
		request.setAttribute("tipoPublicaciones", tipoPublicaciones);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 String tipoOferta = request.getParameter("tipoOferta");
		    String nombre = request.getParameter("nombre");
		    String descripcion = request.getParameter("descripcion");
		    String horaInicio = request.getParameter("horaInicio");
		    String horaFinal = request.getParameter("horaFinal");
		    String departamentoStr = request.getParameter("departamento");
		    String ciudad = request.getParameter("ciudad");
		    String formaPago = request.getParameter("formaPago");
		    String[] keywords = request.getParameterValues("keywords[]");
		    Set<String> keywordsSet = new HashSet<>(Arrays.asList(keywords));
		    
		    float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));
		    
		    
		    DepUY departamento = null;

		    if (departamentoStr != null && !departamentoStr.isEmpty()) {
		        try {
		            departamento = DepUY.valueOf(departamentoStr);
		        } catch (IllegalArgumentException e) {
		            // Manejar el caso en el que el valor no coincide con ninguno de los enumerados
		            // Puedes mostrar un mensaje de error o tomar otra acción adecuada
		        }
		    }

		    
			HttpSession session = request.getSession(false);
			String nickname = (String) session.getAttribute("nickname");
			
		
			try {
			    Fabrica.getInstance().getICtrlOferta().altaOfertaLaboral(
			        nickname, tipoOferta, nombre, descripcion, new DTHorario(obtenerDTHora(horaInicio), obtenerDTHora(horaFinal)),
			        remuneracion, ciudad, departamento, LocalDate.now(), keywordsSet, EstadoOL.Ingresada, null, formaPago
			    );
			    response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
			} catch (Exception e) {
			    // Manejar la excepción, mostrar un mensaje de error, redireccionar, etc.
			    e.printStackTrace(); // Opcionalmente, puedes registrar la excepción
			    response.sendRedirect(request.getContextPath() + "/altaofertalaboral");
			}

		    
}
	
	private DTHora obtenerDTHora(String horaStr) {
        DTHora dtHora = null; // Valor por defecto en caso de error

        // Dividir la cadena en dos partes usando ":"
        String[] partes = horaStr.split(":");

        if (partes.length == 2) {
            try {
                // Convertir las partes en enteros
                int hora = Integer.parseInt(partes[0]);
                int minutos = Integer.parseInt(partes[1]);

                // Crear un objeto DTHora con los valores convertidos
                dtHora = new DTHora(hora, minutos);
            } catch (NumberFormatException e) {
                // Manejar una conversión fallida aquí, si es necesario
                System.err.println("Error al convertir la hora y los minutos.");
            }
        } else {
            // La cadena no tiene el formato esperado, manejar esto según tus necesidades
            System.err.println("Formato de hora no válido.");
        }

        return dtHora;
    }
	
}
