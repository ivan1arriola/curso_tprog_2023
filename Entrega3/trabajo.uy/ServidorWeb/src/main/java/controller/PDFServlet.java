/*package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;

@WebServlet("/generar-pdf")
public class PDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=comprobante_postulacion.pdf");

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            // Contenido del comprobante (ejemplo estático)
            String nombrePostulante = "Juan Pérez";
            String nombreEmpresa = "Ejemplo Corp";
            String ofertaLaboral = "Desarrollador Java";
            int ordenSeleccion = 1; // PENDIENTE
            String fechaPostulacion = "01/01/2023"; // PENDIENTE
            String fechaResultado = "02/01/2023"; // PENDIENTE

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
        } catch (Exception e) {
            e.printStackTrace();
            // Manejo de errores: Puedes redirigir a una página de error o enviar un mensaje apropiado al usuario.
            response.getWriter().write("Error al generar el archivo PDF");
        }
    }
} */
