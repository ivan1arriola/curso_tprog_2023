package logica.servidor.adapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class SetAdapter<T> extends XmlAdapter<ArrayList<T>, Set<T>> {

	  @Override
	    public ArrayList<T> marshal(Set<T> set) throws Exception {
	        if (set == null) {
	            return null;
	        }
	        return new ArrayList<>(set);
	    }

	    @Override
	    public Set<T> unmarshal(ArrayList<T> list) throws Exception {
	        if (list == null) {
	            return null;
	        }
	        return new HashSet<>(list);
	    }
}