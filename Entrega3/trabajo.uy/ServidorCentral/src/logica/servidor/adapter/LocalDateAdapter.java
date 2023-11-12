package logica.servidor.adapter;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String,  LocalDate> {

    public LocalDate unmarshal(String vvv) throws Exception {
        return LocalDate.parse(vvv);
    }

    public String marshal(LocalDate vvv) throws Exception {
        return vvv.toString();
    }

}