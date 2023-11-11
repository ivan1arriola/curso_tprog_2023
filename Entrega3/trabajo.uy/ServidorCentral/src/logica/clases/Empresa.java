package logica.clases;

//import excepciones.AsignarOrdenAOfertaFinalizada;
//import excepciones.AsignarOrdenAOfertaNoVencida;
//import excepciones.ErrorAgregarUsuario;
//import excepciones.ExcepcionKeywordVacia;
//import excepciones.ExcepcionTipoOfertaNoExistente;
//import excepciones.ExceptionCantidadPositivaDeTipoOfertaEnPaquete;
import excepciones.ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa;
//import excepciones.ExceptionCiudadInvalida;
import excepciones.ExceptionCompraPaqueteConValorNegativo;
import excepciones.ExceptionCostoPaqueteNoNegativo;
import excepciones.ExceptionDescuentoInvalido;
//import excepciones.ExceptionDuracionNegativa;
//import excepciones.ExceptionEmpresaInvalida;
//import excepciones.ExceptionExpoNegativa;
//import excepciones.ExceptionFechaInvalida;
import excepciones.ExceptionPaqueteNoVigente;
import excepciones.ExceptionRemuneracionOfertaLaboralNegativa;
//import excepciones.ExceptionUsuarioCorreoRepetido;
//import excepciones.ExceptionUsuarioNickRepetido;
//import excepciones.ExceptionUsuarioNickYCorreoRepetidos;
//import excepciones.ExceptionUsuarioNoEncontrado;
//import excepciones.ExceptionUsuarioSeSigueASiMismo;
import excepciones.ExceptionValidezNegativa;
//import excepciones.FaltaCvException;
//import excepciones.FaltaMotivaException;
//import excepciones.FinalizarOfertaNoVencida;
//import excepciones.NoExistePaquete;
//import excepciones.NoHayOrdenDefinidoDePostulantes;
import excepciones.OfertaLaboralNoEncontrada;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
//import excepciones.PostulaExistenteException;
//import excepciones.TipoUsuarioNoValido;
//import excepciones.UsuarioNoExisteException;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

//import jakarta.persistence.*;


import logica.datatypes.DTCantTO;
import logica.datatypes.DTCompraPaquetes;
import logica.datatypes.DTEmpresa;
import logica.datatypes.DTEmpresaConCompras;
//import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import logica.datatypes.DTOfertaExtendido;
//import logica.datatypes.DTOfertaExtendidoConKeywords;
//import logica.datatypes.DTOfertaExtendidoConKeywordsPostulante;
import logica.datatypes.DTOfertaExtendidoConKeywordsTit;
//import logica.datatypes.DTOfertaExtendidoSinPConK;
//import logica.datatypes.DTOfertaLaboral;
//import logica.datatypes.DTPaquete;
import logica.datatypes.DTPostulacion;
//import logica.datatypes.DTPostulante;
//import logica.datatypes.DTPostulanteExtendido;
//import logica.datatypes.DTTipoOferta;
import logica.datatypes.DTUsuario;
import logica.datatypes.DTUsuarioSinInfoSocial;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("E")
public class Empresa extends Usuario {

    @Lob
    private String descripcion;
    private String url;
    @OneToMany(mappedBy = "empresaPublicadora", cascade = CascadeType.PERSIST)
    private Set<OfertaLaboral> ofertasLaborales;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.PERSIST)
    private Set<InfoCompra> infoCompras;

    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, byte[] img, String desc, String urlE) {
        super(nickname, nombre, apellido, correo_electronico, contrasena, img);
        this.descripcion = desc;
        this.url = urlE;
        this.ofertasLaborales = new HashSet<OfertaLaboral>();
        this.infoCompras = new HashSet<InfoCompra>();
    }

    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, String desc) {
        this(nickname, nombre, apellido, correo_electronico, contrasena, null, desc, null);
    }

    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, byte[] img, String desc) {
        this(nickname, nombre, apellido, correo_electronico, contrasena, img, desc, null);
    }

    public Empresa(String nickname, String nombre, String apellido, String correo_electronico, String contrasena, String desc, String urlE) {
        this(nickname, nombre, apellido, correo_electronico, contrasena, null, desc, urlE);
    }

    public Empresa() {

    }

    public Set<InfoCompra> getInfoCompmras() {
        return this.infoCompras;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
    public void setofertasLaborales(Set<OfertaLaboral> OL) {
    	ofertasLaborales =  OL; 
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String geturl() {
        return url;
    }

    public Set<OfertaLaboral> getofertasLaborales() {
        return ofertasLaborales;
    }

    public void seturl(String urlE) {
        this.url = urlE;
    }

    public Set<String> listarOfertasLaborales() {
        Set<String> lista = new HashSet<String>();

        if (ofertasLaborales.size() != 0) {
            for (OfertaLaboral ol : ofertasLaborales) {
                lista.add(ol.getNombre());
            }
        }

        return lista;
    }

    @Override
    public boolean esEmpresa() {
        return true;
    }

    public OfertaLaboral altaOfertaLaboral(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords, EstadoOL estado, byte[] img, Paquete paq) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        try {
            if (remun >= 0) {
                OfertaLaboral ofertaLab = new OfertaLaboral(this, atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA, estado, img, paq);
                ofertasLaborales.add(ofertaLab);

                if (paq != null) { //actualiza cantidad del tipo Oferta
                    Set<OfertaPaquete> restantes = paq.getOfertaPaquete();
                    
                    OfertaPaquete ofertaAux = null;
                    for (OfertaPaquete offer : restantes) {

                        if (offer.getDTCantTO().getNombre().equals(tipoOferta.getNombre())) {
                        	ofertaAux = offer;
                        }
                    } //cierra for
                    if (ofertaAux != null) {
                    	int cantidadAsociada = ofertaAux.getDTCantTO().getCantidad();
                        if (cantidadAsociada >= 1) {
                            cantidadAsociada = cantidadAsociada - 1;
                            OfertaPaquete oferPaq = new OfertaPaquete(tipoOferta, cantidadAsociada);
                            restantes.remove(ofertaAux);
                            restantes.add(oferPaq);
                        }
                    }

                }

                return ofertaLab;
            } else if (remun<0){
                throw new ExceptionRemuneracionOfertaLaboralNegativa("La remuneración de la oferta laboral es negativa.");
            } else {
            	throw new NumberFormatException("La remuneración debe ser un número");
            }
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            throw exc;

        }

    }

    public OfertaLaboral altaOfertaLaboralForzado(TipoOferta tipoOferta, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> atrkeywords, EstadoOL estado, byte[] img, Paquete paq) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido {
        if (remun >= 0) {
            OfertaLaboral ofertaLab = new OfertaLaboral(true, this, atrkeywords, tipoOferta, nombre, descripcion, ciu, dep, horario, remun, fechaA, estado, img, paq);
            ofertasLaborales.add(ofertaLab);
            return ofertaLab;
        } else {
            throw new ExceptionRemuneracionOfertaLaboralNegativa("La remuneración de la oferta laboral es negativa.");
        }

    }


    public OfertaLaboral altaOfertaLaboralImagen(TipoOferta tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> keyw, EstadoOL estado, byte[] img) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        try {
            if (remun >= 0) {
                OfertaLaboral ofertaLab = new OfertaLaboral(this, keyw, tipo, nombre, descripcion, ciu, dep, horario, remun, fechaA, estado, img);
                ofertasLaborales.add(ofertaLab);
                return ofertaLab;
            } else {
                throw new ExceptionRemuneracionOfertaLaboralNegativa("La remuneración de la oferta laboral es negativa.");
            }

        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            throw exc;

        }
    }

    public OfertaLaboral altaOfertaLaboralImagenPaquete(TipoOferta tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate fechaA, List<Keyword> keyw, EstadoOL estado, byte[] img, Paquete paquete) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        try {
            if (remun >= 0) {
                OfertaLaboral ofertaLab = new OfertaLaboral(this, keyw, tipo, nombre, descripcion, ciu, dep, horario, remun, fechaA, estado, img, paquete);
                ofertasLaborales.add(ofertaLab);
                return ofertaLab;
            } else {
                throw new ExceptionRemuneracionOfertaLaboralNegativa("La remuneración de la oferta laboral es negativa.");
            }
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            throw exc;

        }
    }

    public DTUsuario obtenerDatosUsuario() { // obtenerDatosUsuario(): DTUsuario

        Set<DTUsuarioSinInfoSocial> sdores = new HashSet<DTUsuarioSinInfoSocial>();
        Set<DTUsuarioSinInfoSocial> sdos = new HashSet<DTUsuarioSinInfoSocial>();

        for (Usuario elemento : getSeguidores()) {
        	DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(), elemento.getcorreoElectronico(), elemento.getApellido(), elemento.getNombre(), elemento.getcontrasenia(), elemento.getImagen());
            sdores.add(dtp);
        }

        for (Usuario elemento : getSeguidos()) {
            DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(), elemento.getcorreoElectronico(), elemento.getApellido(), elemento.getNombre(), elemento.getcontrasenia(), elemento.getImagen());
            sdos.add(dtp);
        }

        String nickname = getNickname();
        String nombre = getNombre();
        String apellido = getApellido();
        String correoElectronico = getcorreoElectronico();
        String contraseña = getcontrasenia();
        String descripcion = getDescripcion();
        String url = geturl();
        byte[] imagen = getImagen();

        Set<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();

        for (OfertaLaboral oferta : ofertasLaborales) {
            DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
            dtOfertas.add(dtOferta);
        }

        return new DTEmpresa(nickname, correoElectronico, apellido, nombre, contraseña, descripcion, url, dtOfertas, imagen, sdos, sdores);

    }

    public Set<String> listarOfertasLaboralesConfirmadas() {
        Set<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ofertaLab = iterator.next();
            if (ofertaLab.getEstado() == EstadoOL.Confirmada) {
                res.add(ofertaLab.getNombre());
            }
        }
        return res;
    }

    public Set<String> listarOfertasLaboralesConfirmadasKeyword(String keywords) {
        Set<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ofertaLab = iterator.next();
            if (ofertaLab.getEstado() == EstadoOL.Confirmada && ofertaLab.tieneKeyword(keywords)) {
                res.add(ofertaLab.getNombre());
            }
        }
        return res;
    }

    public Set<String> listarOfertasLaboralesIngresadas() {
        Set<String> res = new HashSet<String>();
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ofertaLab = iterator.next();
            if (ofertaLab.getEstado() == EstadoOL.Ingresada) {
                res.add(ofertaLab.getNombre());
            }
        }
        return res;
    }

    public boolean existeOfertaLaboral(String nombre_oferta) {
        Iterator<OfertaLaboral> iterator = ofertasLaborales.iterator();

        // Recorremos el HashSet usando el Iterator
        while (iterator.hasNext()) {
            OfertaLaboral ofertaLab = iterator.next();
            if (ofertaLab.getNombre().equals(nombre_oferta)) {
                return true;
            }
        }

        return false;
    }

    public boolean tieneURL() {
        return url != null;
    }

    public boolean compraPaquetes(Paquete paq, LocalDate fecha, int valor) throws ExceptionCompraPaqueteConValorNegativo, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa, ExceptionValidezNegativa {
        if (valor >= 0) {
            for (InfoCompra ic : infoCompras) {
                if ((ic.getPaquete()).getNombre().equals(paq.getNombre())) {
                    return false;
                }
            }
            // int val = paq.getValidez();
            Set<DTCantTO> ConjuntoS = paq.obtenerDTSCantTO();

            InfoCompra infoComp = new InfoCompra(fecha, valor, paq, this, ConjuntoS);
            infoCompras.add(infoComp);
            return true;
        } else {
            throw new ExceptionCompraPaqueteConValorNegativo("El valor de la compra de un paquete no puede ser negativo.");
        }

    }

    @Override
    // corregido,   se pasan mas parametros para la ejecucion
    public DTUsuario obtenerDatosUsuarioEspecial(String UsuarioRegistradoActual, String UsuarioQueSeHaceConsulta) {

        Set<DTUsuarioSinInfoSocial> sdores = new HashSet<DTUsuarioSinInfoSocial>();
        Set<DTUsuarioSinInfoSocial> sdos = new HashSet<DTUsuarioSinInfoSocial>();

        for (Usuario elemento : getSeguidores()) {
        	DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(), elemento.getcorreoElectronico(), elemento.getApellido(), elemento.getNombre(), elemento.getcontrasenia(), elemento.getImagen());
            sdores.add(dtp);
        }

        for (Usuario elemento : getSeguidos()) {
            DTUsuarioSinInfoSocial dtp = new DTUsuarioSinInfoSocial(elemento.getNickname(), elemento.getcorreoElectronico(), elemento.getApellido(), elemento.getNombre(), elemento.getcontrasenia(), elemento.getImagen());
            sdos.add(dtp);
        }

        DTEmpresa empre;
        if (UsuarioRegistradoActual.equals(UsuarioQueSeHaceConsulta)) {
            String nickname = getNickname();
            String nombre = getNombre();
            String apellido = getApellido();
            String correoElectronico = getcorreoElectronico();
            String contraseña = getcontrasenia();
            byte[] imagen = getImagen();
            Set<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();

            for (OfertaLaboral oferta : ofertasLaborales) {
                DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
                dtOfertas.add(dtOferta);
                // muestro toda oferta laboral 
            }

            Set<DTCompraPaquetes> paquetesComp = new HashSet<DTCompraPaquetes>();
            for (InfoCompra compra : infoCompras) {
                DTCompraPaquetes dtcp = new DTCompraPaquetes(compra.getPaquete().getNombre(), compra.getfCompra(), compra.getFechaVencimiento());
                paquetesComp.add(dtcp);
            }

            empre = new DTEmpresaConCompras(nickname, correoElectronico, apellido, nombre, contraseña, imagen, descripcion, url, dtOfertas, paquetesComp, sdos, sdores);
        } else {
            String nickname = getNickname();
            String nombre = getNombre();
            String apellido = getApellido();
            String correoElectronico = getcorreoElectronico();
            String contraseña = getcontrasenia();
            byte[] imagen = getImagen();
            Set<DTOfertaExtendido> dtOfertas = new HashSet<DTOfertaExtendido>();

            for (OfertaLaboral oferta : ofertasLaborales) {
                if (oferta.getEstado() == EstadoOL.Confirmada) {
                    DTOfertaExtendido dtOferta = oferta.obtenerDatosOferta();
                    dtOfertas.add(dtOferta);
                }// si oferta laboral confirmada se muestra
            }
            empre = new DTEmpresa(nickname, correoElectronico, apellido, nombre, contraseña, descripcion, url, dtOfertas, imagen, sdos, sdores);
        }
        return empre;
    }

    public Set<String> listarPaquetesNoVencidos() {
        Set<String> res = new HashSet<String>();
        for (InfoCompra infoCompra : infoCompras) {
            // Accede a cada elemento 'infoCompra' y realiza las operaciones necesarias
            if (!infoCompra.estaVencido()) {
                res.add(infoCompra.obtenerDatosPaquete().getNombre());
            }
        }
        return res;
    }

    // ============================================================

    public Set<DTPostulacion> obtenerPostulacionesOfertaLaboral(String nombre_oferta_laboral) {
        Set<OfertaLaboral> OFEmpresa = getofertasLaborales();
        OfertaLaboral auxiliar = null;
        for (OfertaLaboral OLe : OFEmpresa) {
            if (nombre_oferta_laboral.equals(OLe.getNombre())) {
                auxiliar = OLe;
                break;
            }
        }
        Set<DTPostulacion> stringSet = auxiliar.obtenerPostulacionesOfertaLaboral();
        return stringSet;
    }


    private OfertaLaboral encontrarOfertaPorNombre(String nombreOferta) throws OfertaLaboralNoEncontrada {
        for (OfertaLaboral oferta : getofertasLaborales()) {
            if (nombreOferta.equals(oferta.getNombre())) {
                return oferta;
            }
        }
        throw new OfertaLaboralNoEncontrada(
                "Empresa " + getNickname()+ " no tiene una oferta laboral llamada " + nombreOferta);
    }


    public Set<DTOfertaExtendidoConKeywordsTit> listarOfertasLaboralesNoVigentesConfirmadas() {
        Set<OfertaLaboral> OFEmpresa = getofertasLaborales();

        EstadoOL estado = EstadoOL.Confirmada;

        Set<DTOfertaExtendidoConKeywordsTit> stringSet = new HashSet<>();
        DTOfertaExtendidoConKeywordsTit nuevo;

        for (OfertaLaboral OLe : OFEmpresa) {
            if (estado.equals(OLe.getEstado()) && OLe.estaVencida()) {
                nuevo = OLe.infoOfertaLaboralPropietario();
                stringSet.add(nuevo);
            }
        }

        return stringSet;
    }


    public LocalDate obtenerFechaDeCompra(String paq) {
		System.out.println(paq);
		for (InfoCompra ic : infoCompras) {
			if (ic.getPaquete().getNombre().equals(paq)) {
				return ic.getfCompra();
			}
		}
		return null;
	}

}

