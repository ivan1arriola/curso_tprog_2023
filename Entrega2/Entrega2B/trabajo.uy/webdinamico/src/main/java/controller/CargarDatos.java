package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTHora;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@WebServlet("/cargardatos")
public class CargarDatos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CargarDatos() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            cargarDatos();
            response.getWriter().append("Carga de datos exitosa");
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception e) {
            response.getWriter().append("Error al cargar datos: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void cargarDatos() {
        Fabrica F = Fabrica.getInstance();
        ICtrlUsuario ICU = F.getICtrlUsuario();
        ICtrlOferta ICO = F.getICtrlOferta();

        try {
            // Alta de tipos de publicación
            ICO.altaTipoPublicacionOL("TipoPub3", "Descripción TipoPub3", 45, 90, 120.0f, LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de paquetes
            ICO.altaPaqueteOL("Paquete3", "Descripción Paquete3", 45, LocalDate.now(), 15.0f, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de keywords
            ICO.altaKeyword("keyword4");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Compra de paquetes por parte de empresas
            ICO.compraPaquetes("empresa3", "Paquete3");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de ofertas laborales
            ICO.altaOfertaLaboral("empresa3", "TipoPub3", "Oferta3", "Descripción Oferta3", new DTHorario(new DTHora(10, 0), new DTHora(18, 0)), 2500.0f, "Ciudad3", DepUY.Montevideo, LocalDate.now(), new HashSet<>(Arrays.asList("keyword4", "keyword5")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de postulaciones
            ICO.altaPostulacion("postulante1", "empresa1", "CurriculumVitae1", "Motivación1", null, LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.altaTipoPublicacionOL("TipoPub4", "Descripción TipoPub4", 60, 120, 180.0f, LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.altaPaqueteOL("Paquete4", "Descripción Paquete4", 90, LocalDate.now(), 25.0f, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.altaKeyword("keyword5");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.altaKeyword("keyword6");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.compraPaquetes("empresa4", "Paquete4");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.altaOfertaLaboral("empresa4", "TipoPub4", "Oferta4", "Descripción Oferta4", new DTHorario(new DTHora(7, 0), new DTHora(15, 0)), 2200.0f, "Ciudad4", DepUY.Canelones, LocalDate.now(), new HashSet<>(Arrays.asList("keyword5", "keyword6")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            ICO.altaPostulacion("postulante2", "empresa2", "CurriculumVitae2", "Motivación2", null, LocalDate.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try {
            // Alta de postulantes
            ICU.altaPostulanteImagen("postulante1", "Contraseña1", "Nombre1", "Apellido1", LocalDate.of(1990, 1, 1), "correo1", "Nacionalidad1", null);
            ICU.altaPostulante("postulante2", "Contraseña2", "Nombre2", "Apellido2", "correo2", LocalDate.of(1990, 2, 2), "Nacionalidad2");
            ICU.altaPostulanteImagen("postulante3", "Contraseña3", "Nombre3", "Apellido3", LocalDate.of(1990, 3, 3), "correo3", "Nacionalidad3", null);
            ICU.altaPostulanteImagen("postulante4", "Contraseña4", "Nombre4", "Apellido4", LocalDate.of(1990, 4, 4), "correo4", "Nacionalidad4", null);
            ICU.altaPostulanteImagen("postulante5", "Contraseña5", "Nombre5", "Apellido5", LocalDate.of(1990, 5, 5), "correo5", "Nacionalidad5", null);
        } catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
            e.printStackTrace();
        }

        try {
            // Alta de empresas
        	ICU.altaEmpresa("empresa1", "Contraseña1", "Nombre Empresa 1", "Apellido Empresa 1", "correo_empresa1", "Descripción Empresa 1");
        	ICU.altaEmpresa("empresa2", "Contraseña2", "Nombre Empresa 2", "Apellido Empresa 2", "correo_empresa2", "Descripción Empresa 2");
        	ICU.altaEmpresa("empresa3", "Contraseña3", "Nombre Empresa 3", "Apellido Empresa 3", "correo_empresa3", "Descripción Empresa 3");
        	ICU.altaEmpresa("empresa4", "Contraseña4", "Nombre Empresa 4", "Apellido Empresa 4", "correo_empresa4", "Descripción Empresa 4");
        	ICU.altaEmpresa("empresa5", "Contraseña5", "Nombre Empresa 5", "Apellido Empresa 5", "correo_empresa5", "Descripción Empresa 5");
        } catch (ExceptionUsuarioNickYCorreoRepetidos | ExceptionUsuarioNickRepetido | ExceptionUsuarioCorreoRepetido e) {
            e.printStackTrace();
        }
        
        try {
            // Alta de ofertas laborales
            ICO.altaOfertaLaboral("empresa1", "TipoPub3", "Oferta1", "Descripción Oferta1", new DTHorario(new DTHora(9, 0), new DTHora(17, 0)), 3000.0f, "Ciudad1", DepUY.Montevideo, LocalDate.now(), new HashSet<>(Arrays.asList("keyword1", "keyword2")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de ofertas laborales
            ICO.altaOfertaLaboral("empresa2", "TipoPub4", "Oferta2", "Descripción Oferta2", new DTHorario(new DTHora(8, 0), new DTHora(16, 0)), 2800.0f, "Ciudad2", DepUY.Canelones, LocalDate.now(), new HashSet<>(Arrays.asList("keyword2", "keyword3")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de ofertas laborales
            ICO.altaOfertaLaboral("empresa3", "TipoPub3", "Oferta3", "Descripción Oferta3", new DTHorario(new DTHora(10, 0), new DTHora(18, 0)), 2500.0f, "Ciudad3", DepUY.Montevideo, LocalDate.now(), new HashSet<>(Arrays.asList("keyword4", "keyword5")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de ofertas laborales
            ICO.altaOfertaLaboral("empresa4", "TipoPub4", "Oferta4", "Descripción Oferta4", new DTHorario(new DTHora(7, 0), new DTHora(15, 0)), 2200.0f, "Ciudad4", DepUY.Canelones, LocalDate.now(), new HashSet<>(Arrays.asList("keyword5", "keyword6")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Alta de ofertas laborales
            ICO.altaOfertaLaboral("empresa5", "TipoPub3", "Oferta5", "Descripción Oferta5", new DTHorario(new DTHora(9, 0), new DTHora(17, 0)), 2700.0f, "Ciudad5", DepUY.Montevideo, LocalDate.now(), new HashSet<>(Arrays.asList("keyword6", "keyword7")), EstadoOL.Ingresada, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }




    }

}

