<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="main.java.logica.Datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.Datatypes.DTOfertaExtendido" %>
<%@ page import="java.util.Base64" %>

<%
	// Obtener el objeto de empresa y sus ofertas laborales desde los atributos de la solicitud
	DTEmpresa usuario = (DTEmpresa) request.getAttribute("usuario");
	Set<DTOfertaExtendido> ofertasLaborales = usuario.getOfertasLaborales();
%>

<!-- Tab de ofertas laborales -->
<div class="tab-pane fade" id="ofertas-panel" role="tabpanel" aria-labelledby="ofertas-tab">
    <%
    for (DTOfertaExtendido oferta : ofertasLaborales) {
    	String imagenUrl = "";
        if (oferta.getImagen() != null) {
            String base64Image = new String(Base64.getEncoder().encode(oferta.getImagen()), "UTF-8");
            imagenUrl = "data:image/jpeg;base64," + base64Image; // Asume que la imagen es en formato JPEG, ajusta según sea necesario
        } else {
            // Si no hay imagen, puedes establecer una imagen de reemplazo o un mensaje aquí
            imagenUrl = request.getContextPath() +  "/imagenNoFound.png";
        }
    %>
    <div class="card mb-3">
        <div class="row g-0">
            <div class="col-md-4">
                <img src="<%= imagenUrl %>" class="img-fluid rounded-start" alt="Imagen de oferta" />
            </div>
            <div class="col">
                <div class="card-body">
                    <h5 class="card-title"><%= oferta.getNombre() %></h5>
                    <p class="card-text"><%= oferta.getDescripcion() %></p>
                </div>
            </div>
            <div class="col-2">
                <a href="<%= request.getContextPath() %>/consultarofertalaboral?o=<%= oferta.getNombre() %>"
                    class="btn btn-info d-flex btn-block align-items-center justify-content-around">
                    <span>Leer más</span>
                </a>
            </div>
        </div>
    </div>
    <%
    }
    %>
</div>

            	
            	