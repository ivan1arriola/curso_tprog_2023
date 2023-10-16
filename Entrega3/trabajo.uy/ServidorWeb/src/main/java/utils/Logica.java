package utils;

import java.time.LocalDate;
import java.util.Set;

import interfaces.ILogica;
import javabeans.PaqueteBean;
import javabeans.UsuarioBean;
import main.java.excepciones.ExceptionUsuarioCorreoRepetido;
import main.java.excepciones.ExceptionUsuarioNickRepetido;
import main.java.excepciones.ExceptionUsuarioNickYCorreoRepetidos;
import main.java.logica.Fabrica;
import main.java.logica.datatypes.DTHorario;
import main.java.logica.datatypes.DTPaquete;
import main.java.logica.enumerados.DepUY;
import main.java.logica.enumerados.EstadoOL;
import main.java.logica.interfaces.ICtrlOferta;
import main.java.logica.interfaces.ICtrlUsuario;

public class Logica implements ILogica {
	
	ICtrlOferta ctrlOferta;
	ICtrlUsuario ctrlUsuario;
	
	public Logica(){
		Fabrica fabrica = Fabrica.getInstance();
		ctrlOferta = fabrica.getICtrlOferta();
		ctrlUsuario = fabrica.getICtrlUsuario();
	}

	@Override
	public void cargarDatos() {
		Fabrica.getInstance().getICtrlCargaDeDatos().cargarDatos();

	}

	@Override
	public boolean validarCredenciales(String identificador, String contraseña) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UsuarioBean obtenerDatosUsuario(String nickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarDatosUsuario(String nickname, UsuarioBean usuario) {
		// TODO Auto-generated method stub

	}

	@Override
	public Set<UsuarioBean> listarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> listarNicknamesUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> listarKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> listarPaquetesDeEmpresa(String nickname) {
		return ctrlOferta.listarComprasPaquete(nickname);
	}

	@Override
	public Set<String> listarTipoDePublicaciones() {
		return ctrlOferta.listarTipoDePublicaciones();
	}

	@Override
	public void altaEmpresa(String nick, String contraseña, String nombre, String apellido, String mail, String desc, String URL) throws ExceptionUsuarioCorreoRepetido, ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido {
        Fabrica.getInstance().getICtrlUsuario().altaEmpresaURLyImagen(nick, contraseña, nombre, apellido, mail, desc, URL, null);
    }
	

	@Override
	public void altaPostulante(String nick, String contraseña, String nombre, String apellido, String mail, LocalDate fecha_nac, String nacionalidad) throws ExceptionUsuarioNickYCorreoRepetidos, ExceptionUsuarioNickRepetido, ExceptionUsuarioCorreoRepetido {
        Fabrica.getInstance().getICtrlUsuario().altaPostulanteImagen(nick, contraseña, nombre, apellido, fecha_nac, mail, nacionalidad, null);
    }

	@Override
	public void altaOfertaLaboral(String nickname_e, String tipo, String nombre, String descripcion, DTHorario horario,
			float remun, String ciu, DepUY dep, LocalDate fechaA, Set<String> keys, EstadoOL estado, String img,
			String paquete) {
		ctrlOferta.altaOfertaLaboral(nickname_e, tipo, nombre, descripcion, horario, remun, ciu, dep, fechaA, keys, estado, img, paquete);
		
	}

	@Override
	public void compraPaquetes(String nickname, String paquete, LocalDate now, int valor) {
		ctrlOferta.compraPaquetes(nickname, paquete, now, valor);
		
	}

	@Override
	public PaqueteBean obtenerDatosPaquete(String paquete) {
		DTPaquete dtPaquete = ctrlOferta.obtenerDatosPaquete(paquete);
		PaqueteBean paqueteBean = new PaqueteBean();
		paqueteBean.setCosto(dtPaquete.getCosto());
		
		// Agregar mas atributos aqui en caso de necesitarlos
		
		return paqueteBean;
	}

}
