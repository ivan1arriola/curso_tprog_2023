package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import logica.datatypes.DTPostulacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SetDTPostulacionAdapter extends XmlAdapter<ArrayList<DTPostulacion>, Set<DTPostulacion>> {

    @Override
    public ArrayList<DTPostulacion> marshal(Set<DTPostulacion> set) throws Exception {
        if (set == null) {
            return null;
        }
        return new ArrayList<>(set);
    }

    @Override
    public Set<DTPostulacion> unmarshal(ArrayList<DTPostulacion> list) throws Exception {
        if (list == null) {
            return null;
        }
        return new HashSet<>(list);
    }
}