package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FabricaWeb;

import java.io.IOException;


@WebServlet("/cargardatos")
public class CargarDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CargarDatos() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FabricaWeb.getInstance().getLogica().cargarDatos();
            response.getWriter().append("Carga de datos exitosa");
            response.sendRedirect(request.getContextPath() + "/ofertaslaborales");
        } catch (Exception e) {
            response.getWriter().append("Error al cargar datos: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

   

}

