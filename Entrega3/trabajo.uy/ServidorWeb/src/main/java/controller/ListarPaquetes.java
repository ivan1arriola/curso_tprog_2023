package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.PaqueteBean;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.Set;

import interfaces.ILogica;

/**
 * Servlet implementation class ListarPaquetes
 */
@WebServlet("/paquetes")
public class ListarPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ILogica logica;
       

    public ListarPaquetes() {
        super();
        logica = FabricaWeb.getLogica();
    }
    
    


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FabricaWeb.getKeywordsLoader().cargarKeywords(request, response);

        try {
            Set<PaqueteBean> paquetes = logica.obtenerPaquetes();
            request.setAttribute("paquetes", paquetes);
            request.getRequestDispatcher("/WEB-INF/listar/paquetes.jsp").forward(request, response);
        } catch (Exception e) {
            String mensajeError = "Ocurrió un error: " + e.getMessage();
            request.setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }
}

package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.PaqueteBean;
import utils.FabricaWeb;

import java.io.IOException;
import java.util.Set;

import interfaces.ILogica;

/**
 * Servlet implementation class ListarPaquetes
 */
@WebServlet("/paquetes")
public class ListarPaquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ILogica logica;
       

    public ListarPaquetes() {
        super();
        logica = FabricaWeb.getLogica();
    }
    
    


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FabricaWeb.getKeywordsLoader().cargarKeywords(request, response);

        try {
            Set<PaqueteBean> paquetes = logica.obtenerPaquetes();
            request.setAttribute("paquetes", paquetes);
            request.getRequestDispatcher("/WEB-INF/listar/paquetes.jsp").forward(request, response);
        } catch (Exception e) {
            String mensajeError = "Ocurrió un error: " + e.getMessage();
            request.setAttribute("mensajeError", mensajeError);
            request.getRequestDispatcher("/WEB-INF/errorPage.jsp").forward(request, response);
        }
    }
}
