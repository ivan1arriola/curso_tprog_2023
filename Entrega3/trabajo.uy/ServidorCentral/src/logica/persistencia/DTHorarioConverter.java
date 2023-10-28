package logica.persistencia;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import logica.datatypes.DTHorario;

@Converter
public class DTHorarioConverter implements AttributeConverter<DTHorario, String> {
    @Override
    public String convertToDatabaseColumn(DTHorario horario) {
        return horario != null ? horario.toString() : null;
    }

    @Override
    public DTHorario convertToEntityAttribute(String horarioString) {
        return new DTHorario(horarioString);
    }
}