package model.Clases;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import model.Clases.OfertaLaboral;

import model.Clases.*;
import model.Datatypes.DTHorario;
import model.Datatypes.DTUsuario;
import model.Datatypes.DTEmpresa;
import model.Enumerados.DepUY;

public class Empresa extends Usuario {

    private String nombreEmpresa;
    private String descripcion;
    private String url;
    private HashSet<OfertaLaboral> ofertasLaborales;
    
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String nombreE, String desc, String urlE) {
        super(nickname, nombre, apellido, correo_electronico);
        nombreEmpresa = nombreE;
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = urlE;
    }
    
    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String nombreE, String desc) {
        super(nickname, nombre, apellido, correo_electronico);
        nombreEmpresa = nombreE;
        descripcion = desc;
        ofertasLaborales = new HashSet<>();
        url = null;
    }    

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String urlE) {
        this.url = urlE;
    }

    @Override
    public boolean esEmpresa() {
        return true;
    }


    public HashSet<String> listarOfertasLaborales(){
        HashSet<String> lista = new HashSet<String>();
        
        if(ofertasLaborales.size() != 0) {
	        for( OfertaLaboral ol : ofertasLaborales){
	            lista.add(ol.getNombre());
	        }
        }

        return lista;
    }


    public OfertaLaboral altaOfertaLaboral(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords){
    	OfertaLaboral ol = new OfertaLaboral(atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA);
        ofertasLaborales.add(ol);
        return ol;
    }

    public DTUsuario obtenerDatosUsuario() {
    	String dire;
    	if (url!=null) {dire="No tiene";} else {dire=url;}
    	DTEmpresa empre = new DTEmpresa(this.getNickname(),this.getCorreo_electronico() , this.getApellido(), this.getNombre() ,nombreEmpresa, descripcion, url);
        return empre;
    }

}
