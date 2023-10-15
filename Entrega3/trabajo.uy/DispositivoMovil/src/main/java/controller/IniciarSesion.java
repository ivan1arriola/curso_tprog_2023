package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import main.java.logica.datatypes.DTEmpresa;
import main.java.logica.datatypes.DTPostulante;
import main.java.logica.datatypes.DTUsuario;

import java.io.IOException;

import enumeration.TipoUsuario;


@WebServlet("/iniciarsesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public IniciarSesion() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("nickname") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.getRequestDispatcher("/WEB-INF/IniciarSesion/iniciarsesion.jsp").forward(request, response);
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response, String identificador) throws Exception {
        HttpSession session = request.getSession();
        DTUsuario usuario = obtenerUsuario(identificador);
        session.setAttribute("nickname", usuario.getNickname());

        TipoUsuario tipo;

        if (usuario instanceof DTPostulante) {
            tipo = TipoUsuario.Postulante;
        } else if (usuario instanceof DTEmpresa) {
            tipo = TipoUsuario.Empresa;
        } else {
            throw new Exception("El tipo de usuario no es reconocido: " + usuario.getClass().getName());
        }

        session.setAttribute("tipoUsuario", tipo);

        String imagen = usuario.getImagen();
        if (imagen != null) {
            session.setAttribute("imagen", imagen);
        }

        response.sendRedirect(request.getContextPath() + "/home");
    }

}
