package controller;

import interfaces.ILogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FabricaWeb;

import java.io.IOException;

public class ConsultarPostulantes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public ConsultarPostulantes() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // tiene que mostrar todos los postulantes de la pagina
    }


}
