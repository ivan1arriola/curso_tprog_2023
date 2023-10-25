package logica.servidor.bean;

import logica.datatypes.DTHorario;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.servidor.UsuarioBean;

import java.util.ArrayList;

public class DTOfertaLaboralCompleta {
    private String nombre;
    private String descripcion;
    private DateBean fechaDeAlta;
    private float costo;
    private float remuneracion;
    private DTHorario horario;
    private DepUY departamento;
    private String ciudad;
    private EstadoOL estado;
    private ArrayList<UsuarioBean> postulantes;
    private String imagen;
    private String paq;
    private PaqueteBean paquete;
    private ArrayList<String> keywords;
    private String nicknameEmpresa;
}
