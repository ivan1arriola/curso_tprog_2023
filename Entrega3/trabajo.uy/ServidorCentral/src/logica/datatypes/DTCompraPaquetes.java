package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.LocalDateAdapter;
//import logica.servidor.adapter.SetDTOfertaExtendidoAdapter;

import java.time.LocalDate;
// import java.util.ArrayList; NO SE USA (CHECKSTYLE)
// import java.util.Set; NO SE USA (CHECKSTYLE)
@XmlAccessorType(XmlAccessType.FIELD)
public class DTCompraPaquetes {

    private String nombre;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechacompra;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechavencimiento;

    public DTCompraPaquetes(String nomb, LocalDate fechacomp, LocalDate fechavenc) {
        nombre = nomb;
        fechacompra = fechacomp;
        fechavencimiento = fechavenc;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaCompra() {
        return fechacompra;
    }

    public LocalDate getFechaVencimiento() {
        return fechavencimiento;
    }
}
