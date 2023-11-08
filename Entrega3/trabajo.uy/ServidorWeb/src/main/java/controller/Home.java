package controller;

import interfaces.ILogica;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javabeans.OfertaLaboralBean;
import javabeans.UsuarioBean;
import logica.servidor.*;
import utils.FabricaWeb;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Servlet implementation class Home
 */

@WebServlet("/home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public Home() {
        super();
        logica = FabricaWeb.getInstance().getLogica();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FabricaWeb.getKeywordsLoader().cargarKeywords(request, response);

        request.getRequestDispatcher("/WEB-INF/home/home.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String input = (String) request.getParameter("buscar-input");

        RequestDispatcher dispatcher;
        ServidorService SS = new ServidorService();
        Servidor servidor = SS.getServidorPort();

        try {
            // OBTENER TODAS LAS OFERTAS LABORALES Y EMPRESAS
            Set<OfertaLaboralBean> ofertas_laborales = logica.listarDatosOfertas();
            List<String> empresas = servidor.listarEmpresas().getListaString();

            Integer cantEmpresas = 0; // las empresas que cumplen con el resultado

            // OBTENER OFERTAS LABORALES y ORDENAR CON EL PREFIJO
            Integer cantOfertasLaborales = 0; // las ofertas laborales que cumplen con el resultado
            Map<OfertaLaboralBean, Integer> ofertasLab = new HashMap<>();

            for(OfertaLaboralBean ol : ofertas_laborales) {
                if(ol.getNombre().toLowerCase().contains(input.toLowerCase()) || ol.getDescripcion().toLowerCase().contains(input.toLowerCase())) {
                    ofertasLab.put(ol, servidor.obtenerDatosTO(ol.getTipoOferta()).getExposicion());
                    cantOfertasLaborales++;
                }
            }

            // Ordenar el mapa por los valores y, en caso de empate, por la fechaAlta de más reciente a más antiguo
            LinkedHashMap<OfertaLaboralBean, Integer> ofertasLabOrdenadas = ofertasLab.entrySet()
                    .stream()
                    .sorted(Map.Entry.<OfertaLaboralBean, Integer>comparingByValue()
                            .thenComparing((e1, e2) -> e2.getKey().getFechaDeAlta().compareTo(e1.getKey().getFechaDeAlta())))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            request.setAttribute("ofertasLabOrdenadas", ofertasLabOrdenadas);
            request.setAttribute("cantOfertasLaborales", cantOfertasLaborales);

            // OBTENER EMPRESAS
            LinkedHashMap<UsuarioBean, LocalDate> empresasOrdenadas = new LinkedHashMap<>();

            for(String emp : empresas) {
                UsuarioBean userDatos = logica.obtenerDatosUsuario(emp);
                if(emp.toLowerCase().contains(input.toLowerCase()) || userDatos.getDescripcion().contains(input.toLowerCase())) {
                    Set<String> ofertasLaborales = logica.listarOfertasConfirmadasDeEmpresa(emp);
                    LocalDate fechaMasActual = LocalDate.MIN;
                    for(String ol : ofertasLaborales) {
                        LocalDate fecha = logica.obtenerDatosOfertaLaboral(ol).getFechaDeAlta();
                        if(fecha.isAfter(fechaMasActual)) {
                            fechaMasActual = fecha;
                        }
                    }
                    empresasOrdenadas.put(userDatos,fechaMasActual);
                    cantEmpresas++;
                }
            }

            LinkedHashMap<UsuarioBean, LocalDate> empresasOrdenadasPorFecha = empresasOrdenadas.entrySet()
                    .stream()
                    .sorted(Map.Entry.<UsuarioBean, LocalDate>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

            request.setAttribute("empresasOrdenadas", empresasOrdenadasPorFecha);
            request.setAttribute("cantEmpresas", cantEmpresas);

            dispatcher = request.getRequestDispatcher("/WEB-INF/busqueda/buscarOfertaLabYEmpresas.jsp");
            dispatcher.forward(request, response);
        } catch (OfertaLaboralNoEncontrada_Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExcepcionTipoOfertaNoExistente_Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExceptionUsuarioNoEncontrado_Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }




}
