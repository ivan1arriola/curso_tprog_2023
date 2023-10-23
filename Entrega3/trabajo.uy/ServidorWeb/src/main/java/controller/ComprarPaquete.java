package controller;

import interfaces.ILogica;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import utils.FabricaWeb;

@WebServlet("/comprarpaquete")
public class ComprarPaquete extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ComprarPaquete() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FabricaWeb.getInstance().getKeywordsLoader().cargarKeywords(request, response);

        String paquete = request.getParameter("p");
        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");

        ILogica logica = FabricaWeb.getInstance().getLogica();

        int valor = (int) logica.obtenerDatosPaquete(paquete).getCosto();

        logica.compraPaquetes(nickname, paquete, LocalDate.now(), valor);

        response.sendRedirect(request.getContextPath() + "/consultarpaquete?p=" + paquete);
    }
}