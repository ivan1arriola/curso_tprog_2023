package enumeration;

public enum EstadoOfertaLaboral {
	INGRESADA("bg-primary"),
	CONFIRMADA("bg-success"),
	RECHAZADA("bg-danger");

	private String cssClass;

	EstadoOfertaLaboral(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssClass() {
		return cssClass;
	}
}