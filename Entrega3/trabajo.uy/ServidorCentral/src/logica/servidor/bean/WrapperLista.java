package logica.servidor.bean;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTOfertaExtendido;
import logica.datatypes.DTPostulacion;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperLista {

    private List<String> listaString;
    private List<DTOfertaExtendido> ofertasExtendido;
    private List<DTPostulacion> postulaciones;

    
    public WrapperLista() {
        listaString = new ArrayList<>();
        ofertasExtendido = new ArrayList<>();
        postulaciones = new ArrayList<>();
    }

    public List<String> getListaString() {
        return listaString;
    }

    public void setListaString(List<String> listaString) {
        this.listaString = listaString;
    }

    public List<DTOfertaExtendido> getOfertasExtendido() {
        return ofertasExtendido;
    }

    public void setOfertasExtendido(List<DTOfertaExtendido> ofertasExtendido) {
        this.ofertasExtendido = ofertasExtendido;
    }
    
    public List<DTPostulacion> getPostulaciones() {
        return postulaciones;
    }
    
    public void setPostulaciones(List<DTPostulacion> postu) {
        this.postulaciones = postu;
    }


}
