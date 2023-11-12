package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import logica.datatypes.DTCantTO;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SetDTCantTOAdapter extends XmlAdapter<ArrayList<DTCantTO>, Set<DTCantTO>> {

    @Override
    public ArrayList<DTCantTO> marshal(Set<DTCantTO> set) throws Exception {
        if (set == null) {
            return null;
        }
        return new ArrayList<>(set);
    }

    @Override
    public Set<DTCantTO> unmarshal(ArrayList<DTCantTO> list) throws Exception {
        if (list == null) {
            return null;
        }
        return new HashSet<>(list);
    }
}
