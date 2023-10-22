package utils;

import java.time.LocalDate;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enumeration.TipoUsuario;
import javabeans.UsuarioBean;
import logica.datatypes.DTHora;
import logica.datatypes.DTHorario;
import webservice.DateBean;
import webservice.DtHora;
import webservice.DtHorario;
import webservice.ListaBean;
import webservice.UsuarioBeanServidor;

public class Convertidor {
	
		public static DateBean fromLocalDate(LocalDate localDate) {
	        DateBean dateBean = new DateBean();
	        dateBean.setDia(localDate.getDayOfMonth());
	        dateBean.setMes(localDate.getMonthValue());
	        dateBean.setAnio(localDate.getYear());
	        return dateBean;
	    }
		
		public static String imagenAString(byte[] bytes) {
	        if (bytes != null) {
	            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bytes);
	        } else {
	            return null;
	        }
	    }
		
		
		
		public static DTHorario dtHorarioFromServidor( DtHorario horarioServidor) {
			DTHora desde = Convertidor.dtHorafromServidor(horarioServidor.getDesde());
			DTHora hasta = Convertidor.dtHorafromServidor(horarioServidor.getHasta());
			return new DTHorario(desde, hasta);
		}



	    private static DTHora dtHorafromServidor(DtHora hora) {
			return new DTHora(hora.getHora(), hora.getMinutos());
		}


	    public static TipoUsuario convertTipoUsuario(webservice.TipoUsuario tipoServidor) {
	        switch (tipoServidor) {
	            case VISITANTE:
	                return TipoUsuario.Visitante;
	            case EMPRESA:
	                return TipoUsuario.Empresa;
	            case POSTULANTE:
	                return TipoUsuario.Postulante;
	            default:
	                return TipoUsuario.Visitante; // Valor por defecto
	        }
	    }
	    
	    
	    public static LocalDate toLocalDate(DateBean dateBean) {
	        if (dateBean != null) {
	            return LocalDate.of(dateBean.getAnio(), dateBean.getMes(), dateBean.getDia());
	        }
	        return null;
	    }
	    
	    public static Set<String> obtenerListaString(ListaBean bean){
			List<String> lista = bean.getListaString();
			Set<String> setString = new HashSet<>(lista);
			return setString;
		}
	    
	    


}
