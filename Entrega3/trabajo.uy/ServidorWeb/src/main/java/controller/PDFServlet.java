package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import utils.FabricaWeb;
import enumeration.Departamento;
import enumeration.EstadoOfertaLaboral;
import enumeration.TipoUsuario;
import interfaces.ILogica;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/generar-pdf")
public class PDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Configurar la respuesta HTTP
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=comprobante_postulacion.pdf");

            // Crear un archivo PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            // Abre el documento
            document.open();

            // Agregar contenido al PDF
            document.add(new Paragraph("¡Hola, mundo!"));

            // Cerrar el documento
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
