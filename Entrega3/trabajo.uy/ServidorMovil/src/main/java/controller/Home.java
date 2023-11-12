package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.servidor.*;

import java.io.IOException;


@WebServlet("/")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ServidorService servidorService;
    private Servidor servidor;


    public Home() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }


    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        HttpSession session = request.getSession(false);
        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request,  response);

    }


    protected void doPost(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {

        doGet(request,  response);
    }


}
