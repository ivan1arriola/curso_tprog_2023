package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import logica.servidor.OfertaLaboralNoEncontrada_Exception;
import utils.FabricaWeb;
import interfaces.ILogica;
import java.io.IOException;


@WebServlet("/finalizaroferta")
public class FinalizarOferta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public FinalizarOferta() {
        super();
        logica = FabricaWeb.getLogica();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nickname = (String) session.getAttribute("nickname");

        String nombreOferta = request.getParameter("oferta");

        try {
            OfertaLaboralBean oferta = logica.obtenerDatosOfertaLaboral(nombreOferta);
            boolean esDueño = oferta.getNicknameEmpresa().equals(nickname);
            if (esDueño){
                logica.finalizarOferta(nombreOferta);
                request.getRequestDispatcher()

            } else {
                //Pagina Error
            }


        } catch (OfertaLaboralNoEncontrada_Exception e) {
            //Pagina de error
        }


    }
}
