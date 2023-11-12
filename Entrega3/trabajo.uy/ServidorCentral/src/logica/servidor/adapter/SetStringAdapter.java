package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SetStringAdapter extends XmlAdapter<ArrayList<String>,  Set<String>> {

    @Override
    public ArrayList<String> marshal(Set<String> set) throws Exception {
        if (set == null) {
            return null;
        }
        return new ArrayList<>(set);
    }

    @Override
    public Set<String> unmarshal(ArrayList<String> list) throws Exception {
        if (list == null) {
            return null;
        }
        return new HashSet<>(list);
    }
}