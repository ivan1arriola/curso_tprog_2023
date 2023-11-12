package controller;

import interfaces.ILogica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import javabeans.UsuarioBean;
import utils.FabricaWeb;


import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/modificarusuario")
@MultipartConfig
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }


    private byte[] procesarImagen(Part imagenPart) throws IOException {
        if (imagenPart == null) {
            return null;
        }

        try (InputStream input = imagenPart.getInputStream()) {
            byte[] imagenBytes = input.readAllBytes();
            if (imagenBytes.length == 0) {
                return null;
            }
            return imagenBytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public static LocalDate convertirCadenaAFecha(String fechaEnCadena) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(fechaEnCadena,  formatter);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
	protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
		
        // Campos obligatorios,  no editables
        String nickname = request.getParameter("nickname");
        String correo = request.getParameter("email");

        // Campos editables
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String password = request.getParameter("password");
        String fechanacimiento = request.getParameter("fecha-nacimiento");
        String nacionalidad = request.getParameter("nacionalidad");
        String descripcion = request.getParameter("descripcion");
        String link = request.getParameter("sitio-web");

        Part imagenPart = request.getPart("imagen");
        byte[] imagenBytes  = procesarImagen(imagenPart);

        ILogica logica = FabricaWeb.getLogica();
        
        try {
	        if (fechanacimiento != null && descripcion == null) {
	        	// es postulante
	        	LocalDate fecha = convertirCadenaAFecha(fechanacimiento);
	        	logica.modificarPostulante(nickname,  nombre,  apellido,  correo,  password,  imagenBytes,  fecha,  nacionalidad);
	        } else if (fechanacimiento == null && descripcion != null) {
                logica.modificarEmpresa(nickname,  nombre,  apellido,  correo,  password,  imagenBytes,  descripcion,  link);
	        }
	        response.sendRedirect(request.getContextPath() + "/consultarusuario?u=" + nickname);
        } catch (Exception e) {
        	String mensajeError = "Ocurrió un error al actualizar los datos del usuario";
            request.setAttribute("mensajeError",  mensajeError);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/errorPage.jsp");
            dispatcher.forward(request,  response);
        }


        HttpSession session = request.getSession();
        UsuarioBean usuario = logica.obtenerDatosUsuario(nickname);

        String imagen = usuario.getImagen();
        if (imagen != null) {
            session.setAttribute("imagen",  imagen);
        }

        
        
		
	}

}
