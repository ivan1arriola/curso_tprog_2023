package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javabeans.OfertaLaboralBean;
import logica.Fabrica;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTUsuario;
import utils.FabricaWeb;
import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import enumeration.TipoUsuario;
import interfaces.ILogica;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/generar-pdf")
public class PDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// Nombre del archivo PDF
        String fileName = "comprobante_postulacion.pdf";
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=comprobante_postulacion.pdf");

        // Crear un nuevo documento
        Document document = new Document();
    	
        try {
        	
        	HttpSession session = request.getSession(false);
    		String nickname = (String) session.getAttribute("nickname");
    		Fabrica fabrica = Fabrica.getInstance();
    		DTUsuario usuario = fabrica.getICtrlUsuario().obtenerDatosUsuario(nickname);
    		String nombreOferta = request.getParameter("id");
    		FabricaWeb FW = FabricaWeb.getInstance();
    		ILogica IL = FW.getLogica();
    		OfertaLaboralBean olb = IL.obtenerDatosOfertaLaboral(nombreOferta);
    		
        	// Establecer la ruta de salida del archivo PDF
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            // Abrir el documento para escribir contenido
            document.open();

            // Contenido del comprobante
            String nombrePostulante = usuario.getNombre() + " " + usuario.getApellido();
            String nombreEmpresa = olb.getNicknameEmpresa();
            String ofertaLaboral = nombreOferta;
            int ordenSeleccion = 1; // PENDIENTE
            String fechaPostulacion = "01/01/2023"; // PENDIENTE
            String fechaResultado = "02/01/2023"; // PENDIENTE

            // Crear el contenido del comprobante
            Paragraph title = new Paragraph("Comprobante de Postulación");
            title.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph info = new Paragraph();
            info.add(new Phrase("Nombre del Postulante: " + nombrePostulante + "\n"));
            info.add(new Phrase("Empresa: " + nombreEmpresa + "\n"));
            info.add(new Phrase("Oferta Laboral: " + ofertaLaboral + "\n"));
            info.add(new Phrase("Resultado de Selección: " + ordenSeleccion + "\n"));
            info.add(new Phrase("Fecha de Postulación: " + fechaPostulacion + "\n"));
            info.add(new Phrase("Fecha del Resultado: " + fechaResultado + "\n"));

            // Agregar contenido al documento
            document.add(title);
            document.add(info);

            // Cerrar el documento
            document.close();
            
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
