package utils;

import logica.servidor.DtTipoOferta;

import java.util.Comparator;

public class ComparadorDtTipoOferta implements Comparator<DtTipoOferta> {

    @Override
    public int compare(DtTipoOferta tipoOferta1,  DtTipoOferta tipoOferta2) {
        return tipoOferta1.getNombre().compareTo(tipoOferta2.getNombre());
    }
}
