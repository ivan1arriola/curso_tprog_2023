package controller;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTPaquete;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import enumeration.TipoUsuario;


/**
 * Servlet implementation class ConsultarPaquete
 */
@WebServlet("/consultarpaquete")
public class ConsultarPaquete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	
    	
		Set<String> keys = Fabrica.getInstance().getICtrlOferta().listarKeywords();
		request.setAttribute("keys", keys);
        String nombrePaquete = request.getParameter("p");
        request.setAttribute("mostrarComprar", false);
        
		HttpSession session = request.getSession(false);
		if( session == null) {
			
		} else {
			
			
			String nickname = (String) session.getAttribute("nickname");
			if(nickname != null) {
				
				TipoUsuario tipo = (TipoUsuario) session.getAttribute("tipoUsuario");
				Set<String> paquetes = Fabrica.getInstance().getICtrlOferta().listarComprasPaquete(nickname);

				if(tipo == TipoUsuario.Empresa && !paquetes.contains(nombrePaquete)) {
					
					request.setAttribute("mostrarComprar", true);
					request.setAttribute("yaSeCompro", false);
					
				} else if(tipo == TipoUsuario.Empresa ) {
					
					request.setAttribute("mostrarComprar", true);
					request.setAttribute("yaSeCompro", true);	
					
				}
				
			}
			
			
			
			
			
			
			
		}
		
		
		
		
		
		
		

		
		
		
		
		
		
		

		
		
		
        
        

        try {
            DTPaquete paquete = obtenerPaquetePorNombre(nombrePaquete);

      
                request.setAttribute("nombrePaquete", paquete.getNombre());
                request.setAttribute("imagenPaquete", paquete.getImagen());// TODO: Reemplaza con el atributo imagen de DTPaquete
                request.setAttribute("costoPaquete", paquete.getCosto());
                request.setAttribute("descuentoPaquete", paquete.getDescuento());
                request.setAttribute("validezPaquete", paquete.getValidez() + " días");
                request.setAttribute("descripcionPaquete", paquete.getDescripcion());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String fechaFormateada = paquete.getFechaAlta().format(formatter);

                request.setAttribute("fechaPaquete", fechaFormateada);

                request.setAttribute("tiposDePublicacion", paquete.getTiposDePub());
            

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/consultarPaquete/paquete.jsp");
            dispatcher.forward(request, response);
            
        } catch (Exception e) {
            // Manejar la excepción
            request.setAttribute("mensajeError", "Error al obtener el paquete: " + e.getMessage());

            // Redirigir a una página de error o mostrar un mensaje de error en el JSP
            RequestDispatcher errorDispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            errorDispatcher.forward(request, response);
        }
    }


    private DTPaquete obtenerPaquetePorNombre(String nombrePaquete) {
    	return Fabrica.getInstance().getICtrlOferta().obtenerDatosPaquete(nombrePaquete);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
