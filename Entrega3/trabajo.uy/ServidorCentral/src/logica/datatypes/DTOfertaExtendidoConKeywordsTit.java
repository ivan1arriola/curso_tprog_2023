package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
//import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
//import logica.servidor.adapter.SetStringAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
//import java.util.TreeSet;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTOfertaExtendidoConKeywordsTit extends DTOfertaExtendidoSinPConK {
    @XmlElement(nillable = true)
    private ArrayList<String> postulaciones;
    private DTPaquete paq;

    public DTOfertaExtendidoConKeywordsTit(String nicknameEmpresa, String nomb, String desc, LocalDate fechaA, float cost, float remu, DTHorario horario,
                                           DepUY dep, String ciu, EstadoOL estado, byte[] img, Set<String> keys, DTPaquete paq, Set<String> postulaciones, Integer cantF, Integer cantV, String tipoOfer) {
        super(nicknameEmpresa, nomb, desc, fechaA, cost, remu, horario, dep, ciu, estado, img, keys, cantF, cantV, tipoOfer);
        this.postulaciones = new ArrayList<>(postulaciones);
        this.paq = paq;
    }

    public DTPaquete getPaquete() {
        return paq;
    }



    public ArrayList<String> getpostulaciones() {

        return postulaciones;
    }
}
