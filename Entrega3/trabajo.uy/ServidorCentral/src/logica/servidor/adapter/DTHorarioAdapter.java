package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import logica.datatypes.DTHorario;

public class DTHorarioAdapter extends XmlAdapter<String,  DTHorario> {
    
	public DTHorario unmarshal(String dtHorarioString) throws Exception {
        return new DTHorario(dtHorarioString);
    }

    public String marshal(DTHorario dtHorario) throws Exception {
        return dtHorario.toString();
    }
}
