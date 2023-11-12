package logica.servidor.bean;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPostulacion;
import java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperLista {

    private ArrayList<String> listaString;
    private ArrayList<DTOfertaExtendido> ofertasExtendido;
    private ArrayList<DTPostulacion> postulaciones;

    
    public WrapperLista() {
        listaString = new ArrayList<>();
        ofertasExtendido = new ArrayList<>();
        postulaciones = new ArrayList<>();
    }

    public ArrayList<String> getListaString() {
        return listaString;
    }

    public void setListaString(ArrayList<String> listaString) {
        this.listaString = listaString;
    }

    public ArrayList<DTOfertaExtendido> getOfertasExtendido() {
        return ofertasExtendido;
    }

    public void setOfertasExtendido(ArrayList<DTOfertaExtendido> ofertasExtendido) {
        this.ofertasExtendido = ofertasExtendido;
    }
    
    public ArrayList<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }
    
    public void setPostulaciones(ArrayList<DTPostulacion> postu) {
        this.postulaciones = postu;
    }


}
