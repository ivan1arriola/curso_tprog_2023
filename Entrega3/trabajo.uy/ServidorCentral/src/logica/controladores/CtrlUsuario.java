package logica.controladores;

import excepciones.*;
import logica.clases.*;
import logica.datatypes.*;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;
import logica.interfaces.ICtrlUsuario;
import logica.manejadores.*;

import java.time.LocalDate;
import java.util.*;

// import main.java.logica.Controladores.*; NO SE USA (CHECKSTYLE)

public class CtrlUsuario implements ICtrlUsuario {
    // empresa con URL y sin imagen
    public boolean altaEmpresaURL(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        boolean existeNick = UsuarioH.existeNick(nick);
        boolean existeCorreo = UsuarioH.existeCorreo(mail);

        if (existeNick && existeCorreo) {
            throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
        } else {
            if (existeNick) {
                throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
            } else if (existeCorreo) {
                throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
            }
        }

        if (!existeNick && !existeCorreo) {
            Empresa empresa = new Empresa(nick, nombre, apellido, mail, contraseña, desc, URL);
            UsuarioH.agregar(empresa);
        }

        return !existeNick && !existeCorreo;
    }

    // empresa sin URL ni imagen
    public boolean altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        boolean existeNick = UsuarioH.existeNick(nick);
        boolean existeCorreo = UsuarioH.existeCorreo(mail);

        if (existeNick && existeCorreo) {
            throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
        } else {
            if (existeNick) {
                throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
            } else if (existeCorreo) {
                throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
            }
        }

        if (!existeNick && !existeCorreo) {
            Empresa empresa = new Empresa(nick, nombre, apellido, mail, contraseña, desc);
            UsuarioH.agregar(empresa);
        }

        return !existeNick && !existeCorreo;
    }

    public boolean altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fechanac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        boolean existeNick = UsuarioH.existeNick(nick);
        boolean existeCorreo = UsuarioH.existeCorreo(mail);

        if (existeNick && existeCorreo) {
            throw new ExceptionUsuarioNickYCorreoRepetidos("Existe un usuario con el nickname indicado y existe un usuario con el correo electrónico indicados.");
        } else {
            if (existeNick) {
                throw new ExceptionUsuarioNickRepetido("Existe un usuario con el nickname indicado.");
            } else if (existeCorreo) {
                throw new ExceptionUsuarioCorreoRepetido("Existe un usuario con el correo electrónico indicado.");
            }
        }

        if (!existeNick && !existeCorreo) {
            Postulante postulante;
            try {
                postulante = new Postulante(nick, contraseña, nombre, apellido, mail, fechanac, nacionalidad);
                UsuarioH.agregar(postulante);
            } catch (ExceptionFechaInvalida e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }

        }

        return !existeNick && !existeCorreo;
    }

    public Set<String> listarEmpresas() {
        Set<String> res = new HashSet<>();
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario user = entry.getValue();
            boolean esEmp = user.esEmpresa();
            if (esEmp) {
                res.add(user.getNickname());
            }
        }
        return res;
    }

    public DTOfertaExtendido consultaOfertaLaboral(String nombre) throws OfertaLaboralNoEncontrada {
        CtrlOferta CtrlOfer = new CtrlOferta();
        return CtrlOfer.obtenerOfertaLaboral(nombre);
    }

    public DTUsuario obtenerDatosUsuario(String nick) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Usuario user = UsuarioH.buscarNick(nick);
        return user.obtenerDatosUsuario();
    }

    public Set<String> listarNicknamesUsuarios() {
        Set<String> res = new HashSet<>();
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            // Usuario u = entry.getValue(); NO SE USA (CHECKSTYLE)
            res.add(entry.getKey());
        }
        return res;
    }

    public boolean existePostulacion(String nickname, String nombre) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);
        if (postulante != null) {
            return postulante.existePostulacion(nombre);
        } else {
            // throw new IllegalArgumentException("Usuario " + nick + " no existe");
            return false;
        }
    }

    public Postulacion crearPostulacion(String nick, String curriculumVitae, String motivacion, LocalDate fecha, String URLDocExtras, OfertaLaboral OferLab, String urlVideo) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();

        Postulante postulante = (Postulante) UsuarioH.buscarNick(nick);
        if (postulante == null) {
            throw new IllegalArgumentException("Usuario " + nick + " no existe");
        }
        try {
            return postulante.crearPostulacion(curriculumVitae, motivacion, fecha, URLDocExtras, OferLab, urlVideo);
        } catch (ExceptionValidezNegativa e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Set<String> obtenerNicknamesPostulantes() {
        Set<String> res = new HashSet<>();
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario user = entry.getValue();
            boolean esEmp = user.esEmpresa();
            if (!esEmp) {
                res.add(entry.getKey());
            }
        }
        return res;
    }


    public boolean altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario, float remun, String ciu, DepUY dep, LocalDate FechaA, List<String> keys, EstadoOL estado, byte[] img, String paquete) throws ExceptionUsuarioNoEncontrado, ExceptionEmpresaInvalida,
            ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        List<Keyword> keywords = new ArrayList<>();

        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        KeywordHandler KeywordH = KeywordHandler.getInstance();
        TipoOfertaHandler TOH = TipoOfertaHandler.getInstance();
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();

        Map<String, Keyword> keyw = KeywordH.obtener();
        for (Map.Entry<String, Keyword> entry : keyw.entrySet()) {
            if (keys.contains(entry.getKey())) {
                keywords.add(entry.getValue());
            }
        }

        if (UsuarioH.existeNick(nickname_e)) {
            Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);

            if (empresa != null) {
                CtrlOferta CtrlOfer = new CtrlOferta();
                boolean ofer = CtrlOfer.existeOferta(nombre);
                if (!ofer) {
                    PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
                    Paquete paq;
                    if (paquete != null) {
                        paq = PaqueteH.buscar(paquete);
                    } else {
                        paq = null;
                    }

                    OfertaLaboral oferL;
                    try {
                        oferL = empresa.altaOfertaLaboral(TOH.buscar(tipo), nombre, descripcion, horario, remun, ciu, dep, FechaA, keywords, estado, img, paq);
                        OLH.agregar(oferL);
                    } catch (ExceptionRemuneracionOfertaLaboralNegativa exc) {

                        exc.printStackTrace();
                        throw exc;
                    } catch (ExceptionPaqueteNoVigente exc) {

                        exc.printStackTrace();
                        throw exc;
                    } catch (ExceptionDescuentoInvalido exc) {

                        exc.printStackTrace();
                        throw exc;
                    } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
                        exc.printStackTrace();
                        throw exc;
                    } catch (NumberFormatException exc) {
                    	throw exc;
                    }

                }
                return !ofer;
            } else {
                throw new ExceptionEmpresaInvalida("No existe una empresa con el nickname indicado.");
            }
        } else {
            throw new ExceptionUsuarioNoEncontrado("No existe un usuario con el nickname indicado.");
        }


    }


    public Set<String> listarOfertasLaborales(String nickname) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Usuario user = UsuarioH.buscarNick(nickname);
        return user.listarOfertasLaborales();
    }


    // -------------------------------------------------------------------------------------
    // ################################  NUEVAS OPERACIONES ################################
    // -------------------------------------------------------------------------------------

    public Set<String> listarKeywords(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        OfertaLaboralHandler OLH = OfertaLaboralHandler.getInstance();
        OfertaLaboral oferLab = OLH.buscar(nombre_oferta);
        List<Keyword> keywords = oferLab.getKeywords();
        Set<String> res = new HashSet<String>();
        for (int i = 0; i < keywords.size(); i++) {
            res.add(keywords.get(i).getNombre());
        }
        return res;
    }

    public DTUsuario obtenerDatosUsuarioEspecial(String UsuarioNickname, String nick) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Usuario usuario = UsuarioH.buscarNick(nick);
        if (nick.equals(UsuarioNickname)) {
            DTUsuario user = usuario.obtenerDatosUsuario();
            return user;
        } else {
            DTUsuario userEsp = usuario.obtenerDatosUsuarioEspecial(UsuarioNickname, nick);
            return userEsp;
        }
    }

    public DTUsuario obtenerDatosUsuarioVisitantes(String nick) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Usuario usuario = UsuarioH.buscarNick(nick);
        return usuario.obtenerDatosUsuario();
    }


    // public void cerrarSesion(String nickname) {    } NO EXISTE


    public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante(String nombre_oferta) throws OfertaLaboralNoEncontrada {
        CtrlOferta COL = new CtrlOferta();
        DTOfertaExtendidoSinPConK infoOLVisitante = COL.infoOfertaLaboralVisitante(nombre_oferta);
        return infoOLVisitante;
    }


    public DTPostulacion obtenerDatosPostulacionW(String postulante_nick, String ofer) throws ExceptionUsuarioNoEncontrado, TipoUsuarioNoValido {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        try {
            Postulante user = (Postulante) UsuarioH.buscarNick(postulante_nick);
            return user.obtenerDatosPostulacion(postulante_nick, ofer);
        } catch (Exception e){
            throw new TipoUsuarioNoValido("Se esperaba un Postulante y llego una Empresa");
        }


    }


    public DTPaquete obtenerDatosPaquete(String paq) {
        PaqueteHandler PaqueteH = PaqueteHandler.getInstance();
        Paquete paquete = PaqueteH.buscar(paq);
        DTPaquete datosPaq = paquete.getDTPaquete();
        return datosPaq;

    }




    public boolean validarCredenciales(String identificador, String contraseña) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Usuario user;
        // Verificar si 'id' es un correo electrónico. Poner la er que sigue el correo
        if (identificador.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            user = UsuarioH.buscarCorreo(identificador);
            if (user.getcontrasenia().equals(contraseña)) {
                return true;
            } else {
                return false;
            }
        } else {
            user = UsuarioH.buscarNick(identificador);
            if (user.getcontrasenia().equals(contraseña)) {
                return true;
            } else {
                return false;
            }

        }

    }


    public void ingresarDatosEditadosPostulanteImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, LocalDate fechanac, String nacionalidad) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);
        postulante.setNombre(nombre);
        postulante.setApellido(apellido);
        postulante.setCorreoElectronico(correo);
        postulante.setContrasenia(contraseña);
        postulante.setImagen(imagen);
        try {
            postulante.setFechaNac(fechanac);
        } catch (ExceptionFechaInvalida e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        postulante.setNacionalidad(nacionalidad);
    }

    public void ingresarDatosEditadosPostulante(String nickname, String nombre, String apellido, String correo, String contraseña, LocalDate fechanac, String nacionalidad) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);
        postulante.setNombre(nombre);
        postulante.setApellido(apellido);
        postulante.setCorreoElectronico(correo);
        postulante.setContrasenia(contraseña);
        try {
            postulante.setFechaNac(fechanac);
        } catch (ExceptionFechaInvalida e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        postulante.setNacionalidad(nacionalidad);
    }


    public void ingresarDatosEditadosEmpresaURL(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, String descripcion) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
        empresa.setNombre(nombre);
        empresa.setApellido(apellido);
        empresa.setContrasenia(contraseña);
        empresa.setCorreoElectronico(correo);
        empresa.seturl(URL);
        empresa.setDescripcion(descripcion);

    }

    public void ingresarDatosEditadosEmpresa(String nickname, String nombre, String apellido, String correo, String contraseña, String descripcion) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
        empresa.setNombre(nombre);
        empresa.setApellido(apellido);
        empresa.setCorreoElectronico(correo);
        empresa.setContrasenia(contraseña);
        empresa.setDescripcion(descripcion);
    }

    public void ingresarDatosEditadosEmpresaURLImg(String nickname, String nombre, String apellido, String correo, String contraseña, String URL, byte[] imagen, String descripcion) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
        empresa.setNombre(nombre);
        empresa.setApellido(apellido);
        empresa.setContrasenia(contraseña);
        empresa.setCorreoElectronico(correo);
        empresa.seturl(URL);
        empresa.setImagen(imagen);
        empresa.setDescripcion(descripcion);
    }

    public void ingresarDatosEditadosEmpresaImg(String nickname, String nombre, String apellido, String correo, String contraseña, byte[] imagen, String descripcion) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
        empresa.setNombre(nombre);
        empresa.setApellido(apellido);
        empresa.setCorreoElectronico(correo);
        empresa.setContrasenia(contraseña);
        empresa.setImagen(imagen);
        empresa.setDescripcion(descripcion);
    }

    public boolean tieneURL(String nickname) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname);
        boolean tiene = empresa.tieneURL();
        return tiene;
    }

    public boolean hayPostulacionW(String postulante_nick, String ofer) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Postulante postulante = (Postulante) UsuarioH.buscarNick(postulante_nick);
        boolean existe = postulante.existePostulacion(ofer);
        return existe;
    }


    public boolean altaEmpresaURLyImagen(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL, byte[] imagen) {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        boolean existe = UsuarioH.existeNick(nick) || UsuarioH.existeCorreo(mail);
        if (!existe) {
            Empresa empresa = new Empresa(nick, nombre, apellido, mail, contraseña, imagen, desc, URL); // falta agregarle el parametro img
            UsuarioH.agregar(empresa);
            return true;
        } else {
            return false;
        }

    }


    // alta postulante con imagen
    public boolean altaPostulanteImagen(String nick, String contraseña, String nombre, String apellido, LocalDate fechanac, String mail, String nacionalidad, byte[] imagen) {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        boolean existe = UsuarioH.existeNick(nick) || UsuarioH.existeCorreo(mail);
        if (!existe) {
            Postulante postulante;
            try {
                postulante = new Postulante(nick, contraseña, nombre, apellido, mail, fechanac, nacionalidad, imagen);
                UsuarioH.agregar(postulante);
            } catch (ExceptionFechaInvalida e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } // falta agregarle el parametro img

            return true;
        } else {
            return false;
        }
    }

    // necesito otro constructor?
    public boolean altaEmpresaImagen(String nick, String contraseña, String nombre, String apellido, String mail, String desc, byte[] imagen) {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        boolean existe = UsuarioH.existeNick(nick) || UsuarioH.existeCorreo(mail);
        if (!existe) {
            Empresa empresa = new Empresa(nick, nombre, apellido, mail, contraseña, imagen, desc); //  agregarle el parametro img
            UsuarioH.agregar(empresa);
            return true;
        } else {
            return false;
        }

    }


    public Set<String> listarPostulantesDeOfertas(String nickname_e, String oferta) {
        Set<String> res = new HashSet<>();
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Map<String, Usuario> usuarios = UsuarioH.obtenerNick();
        for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
            Usuario user = entry.getValue();
            if (!(user.esEmpresa())) {
                Postulante postulante = (Postulante) user;
                boolean esta = postulante.existePostulacion(oferta); // CUIDADO CON ESTA OPERACION DEVUELVE BOOLEANO!!
                if (esta) {
                    res.add(postulante.getNickname());
                }
            }
        }
        return res;

    }

    public Set<String> listarOfertasLaboralesConfirmadas(String nickname_e) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UsuarioH.buscarNick(nickname_e);
        Set<String> OLConfirmadas = empresa.listarOfertasLaboralesConfirmadas();
        return OLConfirmadas;
    }

    public boolean modificarPostulacion(String nombre, String nick, String cvAbreviado, String motivacion) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Postulante postulante = (Postulante) UsuarioH.buscarNick(nick);
        boolean edito = postulante.editarPostulacion(nombre, cvAbreviado, motivacion);
        return edito;
    }

    @Override
    public Set<String> listarPostulacionesPostulante(String nickname) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        Postulante postulante = (Postulante) UsuarioH.buscarNick(nickname);

        return postulante.listarPostulaciones();
    }

    public boolean existeUsuarioConNickname(String nickname) {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        return UsuarioH.existeNick(nickname);
    }

    public boolean existeUsuarioConEmail(String correo) {
        UsuarioHandler UsuarioH = UsuarioHandler.getInstance();
        return UsuarioH.existeCorreo(correo);
    }

    public void seguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo, ExceptionUsuarioNoEncontrado {    	
        if (usuario != usuario_seguido) {
            UsuarioHandler UHan = UsuarioHandler.getInstance();
            Usuario usr1 = UHan.buscarNick(usuario);
            Usuario usr2 = UHan.buscarNick(usuario_seguido);
            if(usr1 == null || usr2 == null) {
            	throw new ExceptionUsuarioNoEncontrado("El usuario no se ha encontrado.");
            }
            usr1.seguirUsuario(usr2);
            usr2.loSigue(usr1);
        } else {
            throw new ExceptionUsuarioSeSigueASiMismo("No es posible que un usuario se siga a si mismo.");
        }
    }

    public void dejarDeseguirUsuario(String usuario, String usuario_seguido) throws ExceptionUsuarioSeSigueASiMismo, ExceptionUsuarioNoEncontrado {
        if (usuario != usuario_seguido) {
            UsuarioHandler UHan = UsuarioHandler.getInstance();
            Usuario usr = UHan.buscarNick(usuario);
            Usuario usrseg = UHan.buscarNick(usuario_seguido);
            usr.dejarDeSeguirUsuario(usrseg);
            usrseg.noLoSigue(usr);
        } else {
            throw new ExceptionUsuarioSeSigueASiMismo("No es posible que un usuario deje de seguirse a si mismo.");
        }
    }


    public Set<DTPostulacion> obtenerPostulacionesOfertaLaboral(String nickname_empresa, String nombre_oferta) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UHan.buscarNick(nickname_empresa);
        return empresa.ObtenerPostulacionesOfertaLaboral(nombre_oferta);
    }


    public void establecerPosicion(String nickname_empresa, String nombre_oferta, String nickPostulante, Integer posicion) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UHan.buscarNick(nickname_empresa);
        empresa.establecerPosicion(nombre_oferta, nickPostulante, posicion);
    }

    public Set<DTOfertaExtendidoConKeywordsTit> listarOfertasLaboralesNoVigentesConfirmadas(String nickname_empresa) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UHan.buscarNick(nickname_empresa);
        Set<DTOfertaExtendidoConKeywordsTit> auxiliar = empresa.listarOfertasLaboralesNoVigentesConfirmadas();
        return auxiliar;
    }

    public void finalizarOfertaLaboral(String nickname_empresa, String nombre_oferta) throws ExceptionUsuarioNoEncontrado {
        UsuarioHandler UHan = UsuarioHandler.getInstance();
        Empresa empresa = (Empresa) UHan.buscarNick(nickname_empresa);
        empresa.finalizarOfertaLaboral(nombre_oferta);
    }

	@Override
	public HashSet<String> obtenerSeguidoresUsuario(String nickname) throws ExceptionUsuarioNoEncontrado {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		HashSet<String> res = new HashSet<String>();
		Set<Usuario> seg = UH.buscarNick(nickname).getSeguidores();
		for (Usuario usuario : seg) {
			res.add(usuario.getNickname());
		}
		return res;
	}

	@Override
	public HashSet<String> obtenerSeguidosUsuario(String nickname) throws ExceptionUsuarioNoEncontrado {
		UsuarioHandler UH = UsuarioHandler.getInstance();
		HashSet<String> res = new HashSet<String>();
		Set<Usuario> seg = UH.buscarNick(nickname).getSeguidos();
		for (Usuario usuario : seg) {
			res.add(usuario.getNickname());
		}
		return res;
	}
}

