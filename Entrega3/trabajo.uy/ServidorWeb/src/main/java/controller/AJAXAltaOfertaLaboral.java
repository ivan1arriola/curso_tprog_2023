package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import javabeans.CantTipoPublicacionBean;
import javabeans.PaqueteBean;
import logica.servidor.ExceptionEmpresaInvalida_Exception;
import logica.servidor.ExceptionRemuneracionOfertaLaboralNegativa_Exception;
import logica.servidor.ExceptionUsuarioNoEncontrado_Exception;
import logica.servidor.NoExistePaquete_Exception;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;
import utils.FabricaWeb;
import enumeration.TipoUsuario;
import interfaces.ILogica;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;

@WebServlet("/ajaxaltaofertalaboral")
public class AJAXAltaOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AJAXAltaOfertaLaboral() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String nickname = (String) session.getAttribute("nickname");
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();
        // Lógica para obtener el conjunto vinculado al valor seleccionado
        String selectedValue = request.getParameter("selectedValue");
        
		String s = servidor.obtenerCantPaquetesEmpresa(nickname);
		String[] paqueteYCant = s.split("-");
		String[] deseado = null;
        for (int i = 0; i < paqueteYCant.length; i++) {
        	System.out.println(paqueteYCant[i]);
            String[] intento = paqueteYCant[i].split(",");
            if(intento[0].equals(selectedValue)) {
            	deseado = intento;
            }
        }
        
        String[] buscado = Arrays.copyOfRange(deseado, 1, deseado.length);
		
		Set<String> res = new HashSet<>();
		if(buscado != null) {
			for(String elem : buscado) {
				String[] cantTip = elem.split(":");
				res.add(cantTip[0] + " (" + cantTip[1] + ")");
			}
		}
		
        // Convertir el conjunto a JSON y enviarlo como respuesta
        String conjuntoJson = new Gson().toJson(res);

        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(conjuntoJson);
			
    }

}
