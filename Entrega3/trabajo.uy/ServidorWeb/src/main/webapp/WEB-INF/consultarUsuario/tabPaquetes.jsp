<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.PaqueteBean" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="javabeans.UsuarioBean" %>

<!-- Tab de paquetes -->
<div class="tab-pane fade sin-bordes" id="paquete-panel" role="tabpanel" aria-labelledby="paquete-tab">

        <%
        UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
        Set<PaqueteBean> paquetes = usuario.getPaquetes();
        Set<String> paquetesVencidos = (Set<String>) request.getAttribute("paquetesVencidos");
        Map<String,  LocalDate> fechasAsocPaq = (Map<String,  LocalDate>) request.getAttribute("mapaStringFecha");
        
        if (paquetes == null || paquetes.isEmpty()) {
        %>
        <div class="alert alert-secondary" role="alert">
            No tiene paquetes disponibles en este momento.
        </div>
        <%
        } else {
            for (PaqueteBean paquete : paquetes) {
                String imagen = paquete.getImagen();
                if (imagen == null)
                    imagen = request.getContextPath() + "/imagenNoFound.png";
                String nombre = paquete.getNombre();
                String descripcion = paquete.getDescripcion();
                String enlace = request.getContextPath() + "/consultarpaquete?p=" + paquete.getNombre();
                
        %>
	        		<div class="card mb-3">
					    <div class="row g-0">
					        <div class="col-md-4">
					            <img src="<%= imagen %>" class="img-fluid rounded-start paquete" alt="Imagen de <%= nombre %>" />
					        </div>
					        <div class="col">
					            <div class="card-body">
					                <h5 class="card-title"><%= nombre %></h5>
					                <p class="card-text"><%= descripcion %></p>
					                <a href="<%= enlace %>" class="card-link">Leer más</a>
					                <p class="card-text">Fecha de Compra: <%= fechasAsocPaq.get(paquete.getNombre()).toString() %></p>
					            </div>
					        </div>
					        
							<% if (!paquetesVencidos.contains(paquete.getNombre())) { %>
							    <div class="col-2">
							        <span class="badge bg-success"><%= "Vigente" %></span>
							    </div>
							<% } else { %>
							    <div class="col-2">
							        <span class="badge bg-danger"><%= "Vencido" %></span>
							    </div>
							<% } %>

					    </div>
					</div>
        <%
            }
        }
        %>
  
</div>
