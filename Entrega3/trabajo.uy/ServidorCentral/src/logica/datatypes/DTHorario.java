package logica.datatypes;

public class DTHorario {
    private DTHora desde;
    private DTHora hasta;

    public DTHorario(DTHora desde, DTHora hasta) {
        this.desde = desde;
        this.hasta = hasta;
    }

    public DTHora getDesde() {
        return desde;
    }

    public DTHora getHasta() {
        return hasta;
    }

    @Override
    public String toString() {
        return "Desde: " + desde + " - Hasta: " + hasta;
    }

    public DTHorario(String horarioString) {
        // Suponemos que el formato del horario en horarioString es "Desde: [desde] - Hasta: [hasta]"
        String[] partes = horarioString.split(" - ");
        if (partes.length == 2) {
            String desdeString = partes[0].replace("Desde: ", "");
            String hastaString = partes[1].replace("Hasta: ", "");

            // Crear objetos DTHora a partir de las cadenas de hora
            DTHora desde = crearDTHora(desdeString);
            DTHora hasta = crearDTHora(hastaString);

            this.desde = desde;
            this.hasta = hasta;
        } else {
            // Manejar un formato incorrecto o lanzar una excepción si es necesario
            throw new IllegalArgumentException("Formato de horario incorrecto: " + horarioString);
        }
    }

    private DTHora crearDTHora(String horaString) {
        // Suponemos que el formato de horaString es "HH:mm"
        String[] partes = horaString.split(":");
        if (partes.length == 2) {
            try {
                int hora = Integer.parseInt(partes[0]);
                int minutos = Integer.parseInt(partes[1]);
                return new DTHora(hora, minutos);
            } catch (NumberFormatException e) {
                // Manejar error de formato si no se pueden convertir los números
                throw new IllegalArgumentException("Formato de hora incorrecto: " + horaString);
            }
        } else {
            // Manejar error de formato si no se dividen en hora y minutos
            throw new IllegalArgumentException("Formato de hora incorrecto: " + horaString);
        }
    }

} 

