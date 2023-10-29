package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import logica.datatypes.DTCompraPaquetes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



public class SetDTCompraPaquetesAdapter extends XmlAdapter<ArrayList<DTCompraPaquetes>, Set<DTCompraPaquetes>> {

    @Override
    public ArrayList<DTCompraPaquetes> marshal(Set<DTCompraPaquetes> set) throws Exception {
        if (set == null) {
            return null;
        }
        return new ArrayList<>(set);
    }

    @Override
    public Set<DTCompraPaquetes> unmarshal(ArrayList<DTCompraPaquetes> list) throws Exception {
        if (list == null) {
            return null;
        }
        return new HashSet<>(list);
    }
}
