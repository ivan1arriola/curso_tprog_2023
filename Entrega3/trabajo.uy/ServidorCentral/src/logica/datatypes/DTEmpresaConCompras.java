package logica.datatypes;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.servidor.adapter.SetDTCompraPaquetesAdapter;

import java.util.Set;
@XmlAccessorType(XmlAccessType.FIELD)
public class DTEmpresaConCompras extends DTEmpresa {
    @XmlJavaTypeAdapter(SetDTCompraPaquetesAdapter.class)
    private Set<DTCompraPaquetes> compraPaquetes;

    public DTEmpresaConCompras(String nick, String mail, String apellido, String nombre, String contraseña, byte[] img, String desc, String URL, Set<DTOfertaExtendido> ols, Set<DTCompraPaquetes> dtcp, Set<DTUsuarioSinInfoSocial> seguidos, Set<DTUsuarioSinInfoSocial> seguidores) {
        super(nick, mail, apellido, nombre, contraseña, desc, URL, ols, img, seguidos, seguidores);
        compraPaquetes = dtcp;
    }

    // Getters y setters
    public Set<DTCompraPaquetes> getCompraPaquetes() {
        return compraPaquetes;
    }

}
