package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.servidor.Servidor;
import logica.servidor.ServidorService;
import javabeans.OfertaLaboralBean;
import javabeans.PostulacionBean;
import javabeans.UsuarioBean;
import utils.FabricaWeb;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import interfaces.ILogica;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/generar-pdf")
public class PDFServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ILogica logica;

    public PDFServlet() {
        super();
        logica = FabricaWeb.getLogica();
    }
    
    protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException,  IOException {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition",  "attachment; filename=comprobante_postulacion.pdf");

            String nicknameUsuarioLogueado = (String) request.getSession().getAttribute("nickname");

            String OfertaLaboral = request.getParameter("o");
            
            ServidorService SS = new ServidorService();
            Servidor servidor = SS.getServidorPort();
            
            UsuarioBean user = logica.obtenerDatosUsuario(nicknameUsuarioLogueado);
            
            
            List<String> empresas = servidor.listarEmpresas().getListaString();
            String empresaPostul = "";
            
            for (String emp : empresas) {
            	Set<String> ofertas_lab = logica.listarOfertasLaboralesDeEmpresa(emp);
            	
	    		for (String olb : ofertas_lab) {
	        		if (olb.equals(OfertaLaboral)) {
	        			empresaPostul = emp;
	        			break;
	        		}
	        	}
            }
            
            PostulacionBean postulacion = logica.obtenerDatosPostulacionW(nicknameUsuarioLogueado,  OfertaLaboral);
            
           
            
            // Utiliza ServletOutputStream en lugar de response.getOutputStream()
            ServletOutputStream outputStream = response.getOutputStream();
            Document document = new Document();
            
            
            
            // Pasar el ServletOutputStream al constructor de PdfWriter
            PdfWriter writer = PdfWriter.getInstance(document,  outputStream);

            
            
            document.open();

            // Contenido del comprobante (ejemplo estático)
            String nombrePostulante = user.getNombre() + " " + user.getApellido();
            String nombreEmpresa = empresaPostul;
            String ofertaLaboral = OfertaLaboral;
            String ordenSeleccion = "No se ha realizado una clasificación";
            int ordenSeleccionInt = postulacion.getClasificacion();
            if (ordenSeleccionInt > 0) {
            	ordenSeleccion = String.valueOf(ordenSeleccionInt);
            }
            String fechaPostulacion = postulacion.getFechaString(); // PENDIENTE
            String fechaResultado = postulacion.getFechaResuString(); // PENDIENTE

            // Crear el contenido del comprobante
            Paragraph title = new Paragraph("Comprobante de Postulación");
            title.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph info = new Paragraph();
            info.add(new Paragraph("Nombre del Postulante: " + nombrePostulante));
            info.add(new Paragraph("Empresa: " + nombreEmpresa));
            info.add(new Paragraph("Oferta Laboral: " + ofertaLaboral));
            info.add(new Paragraph("Resultado de Selección: " + ordenSeleccion));
            info.add(new Paragraph("Fecha de Postulación: " + fechaPostulacion));
            info.add(new Paragraph("Fecha del Resultado: " + fechaResultado));

            // Agregar contenido al documento
            document.add(title);
            document.add(info);

            document.close();
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores: Puedes redirigir a una página de error o enviar un mensaje apropiado al usuario.
            response.getWriter().write("Error al generar el archivo PDF");
        }
    }
}

