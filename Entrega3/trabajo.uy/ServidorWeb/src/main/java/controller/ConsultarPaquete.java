package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.PaqueteBean;
import jakarta.servlet.RequestDispatcher;

import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTPaquete;
import utils.FabricaWeb;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import enumeration.TipoUsuario;

@WebServlet("/consultarpaquete")
public class ConsultarPaquete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ConsultarPaquete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);
        
        String nombrePaquete = request.getParameter("p");
        request.setAttribute("mostrarComprar", false);

        HttpSession session = request.getSession(false);

        if (session != null) {
            String nickname = (String) session.getAttribute("nickname");
            if (nickname != null) {
                TipoUsuario tipo = (TipoUsuario) session.getAttribute("tipoUsuario");
                

                if (tipo == TipoUsuario.Empresa) {
                	Set<String> paquetes = FabricaWeb.getInstance().getLogica().listarPaquetesDeEmpresa(nickname);
                    if (!paquetes.contains(nombrePaquete)) {
                        request.setAttribute("mostrarComprar", true);
                        request.setAttribute("yaSeCompro", false);
                    } else {
                        request.setAttribute("mostrarComprar", true);
                        request.setAttribute("yaSeCompro", true);
                    }
                }   
            }
        }

        try {
            PaqueteBean paquete = FabricaWeb.getInstance().getLogica().obtenerDatosPaquete(nombrePaquete);

            request.setAttribute("paquete", paquete);

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

