package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import logica.datatypes.DTOfertaExtendido;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetDTOfertaExtendidoAdapter extends XmlAdapter<ArrayList<DTOfertaExtendido>, Set<DTOfertaExtendido>> {

    @Override
    public ArrayList<DTOfertaExtendido> marshal(Set<DTOfertaExtendido> set) throws Exception {
        if (set == null) {
            return null;
        }
        return new ArrayList<>(set);
    }

    @Override
    public Set<DTOfertaExtendido> unmarshal(ArrayList<DTOfertaExtendido> list) throws Exception {
        if (list == null) {
            return null;
        }
        return new HashSet<>(list);
    }
}





