package logica.servidor.bean;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.datatypes.DTOfertaExtendido;

import java.util.ArrayList;


@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperLista {

    private ArrayList<String> listaString;
    private ArrayList<DTOfertaExtendido> ofertasExtendido;

    public WrapperLista() {
        listaString = new ArrayList<>();
        ofertasExtendido = new ArrayList<>();
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


}
