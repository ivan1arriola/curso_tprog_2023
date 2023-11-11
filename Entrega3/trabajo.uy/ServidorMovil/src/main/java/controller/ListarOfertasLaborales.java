package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.DtOfertaExtendido;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;

import java.io.IOException;
import java.util.List;


@WebServlet("/ofertaslaborales")
public class ListarOfertasLaborales extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServidorService servidorService;
	private Servidor servidor;
       

    public ListarOfertasLaborales() {
        super();
        servidorService = new ServidorService();
        servidor = servidorService.getServidorPort();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DtOfertaExtendido> DtOfertas = servidor.obtenerDTOfertasLaboralesConfirmadas().getOfertasExtendido();
		request.setAttribute("ofertas", DtOfertas);
		request.getRequestDispatcher("/WEB-INF/listarOfertasLaborales/ofertasLaborales.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
