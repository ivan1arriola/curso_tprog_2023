package enumeration;

public enum EstadoOfertaLaboral {
    Ingresada("bg-primary"),
    Confirmada("bg-success"),
    Rechazada("bg-danger"),
    Finalizada("bg-info");    // Otra nueva constante

    private String cssClass;

    EstadoOfertaLaboral(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getCssClass() {
        return cssClass;
    }
}