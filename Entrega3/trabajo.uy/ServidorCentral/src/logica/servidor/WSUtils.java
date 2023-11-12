package logica.servidor;

import logica.datatypes.DTOfertaExtendido;
import logica.servidor.bean.WrapperLista;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import excepciones.NoExisteArchivoDeConfiguacion;

public class WSUtils {
	

    public static WrapperLista envolverLista(List<String> strings) {
        WrapperLista listaBean = new WrapperLista();
        listaBean.setListaString(strings);
        return listaBean;
    }

    public static int obtenerPuerto() {
    	Configuracion config;
		try {
			config = new Configuracion();
			String valor = config.obtenerValor("servidor.puerto");
			if (valor != null) return  Integer.parseInt(valor);
			else return 9128;
		} catch (NoExisteArchivoDeConfiguacion e) {
			e.printStackTrace();
			return 9128;
		}
    }

    public static String obtenerIp(){
    	Configuracion config;
		try {
			config = new Configuracion();
			String valor = config.obtenerValor("servidor.ip");
			if (valor != null) return  valor;
			else return "localhost";
		} catch (NoExisteArchivoDeConfiguacion e) {
			e.printStackTrace();
			return "localhost";
		}
    }


    public static WrapperLista envolverLista(Set<String> strings) {
        return envolverLista(new ArrayList<>(strings));
    }

    public static String imagenAString(byte[] bytes) {
        if (bytes != null) {
            return "data:image/jpeg;base64, " + Base64.getEncoder().encodeToString(bytes);
        } else {
            return null;
        }
    }


    public static WrapperLista envolverDTOfertaExtendido(Set<DTOfertaExtendido> dtOfertaExtendidos) {
        WrapperLista listaBean = new WrapperLista();
        listaBean.setOfertasExtendido(new ArrayList<>(dtOfertaExtendidos));
        return listaBean;
    }
}
