<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Base64" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.EstadoOfertaLaboral" %>

<%
	// Obtener el objeto de empresa y sus ofertas laborales desde los atributos de la solicitud
	UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
	Set<OfertaLaboralBean> ofertasLaborales = usuario.getOfertasLaborales();
	boolean ofertasTabAbierto = Boolean.parseBoolean(request.getParameter("ofertas"));
%>

<!-- Tab de ofertas laborales -->
<div class="tab-pane fade sin-bordes <%= ofertasTabAbierto ? "show active" : "" %>" id="ofertas-panel" role="tabpanel" aria-labelledby="ofertas-tab">

<%
    if (ofertasLaborales == null || ofertasLaborales.isEmpty()){
    	
    	%>
    	
    	<div class="alert alert-secondary" role="alert">
			No tiene ofertas laborales vigentes
		</div>

    	<%
    	
    } else{
    	
   
    %>

    <%
    for (OfertaLaboralBean oferta : ofertasLaborales) {
    	String imagen = oferta.getImagen();
        if (imagen== null) {
            // Si no hay imagen,  puedes establecer una imagen de reemplazo o un mensaje aquí
            imagen = request.getContextPath() +  "/imagenNoFound.png";
        }
 
        String nombre = oferta.getNombre();
        String descripcion = oferta.getDescripcion();
        String enlace = request.getContextPath() + "/consultarofertalaboral?o=" + nombre;
        EstadoOfertaLaboral estado = oferta.getEstado();
    %>
    <div class="card mb-3">
        <div class="row g-0">
            		<div class="card mb-3">
					    <div class="row g-0">
					        <div class="col-md-4">
					            <img src="<%=imagen%>" class="img-fluid rounded-start" alt="<%=nombre%>" />
					        </div>
					        <div class="col">
					            <div class="card-body">
					                <h5 class="card-title"><%=nombre%></h5>
					                <p class="card-text"><%=descripcion%></p>
					                <a href="<%=enlace%>" class="card-link">Leer más</a>      
					            </div>
					        </div>
					        
					        <div class="col-2">
					        	<span class="badge <%=estado.getCssClass()%>"><%=estado%></span>
					        </div>
					    </div>
					</div>
        </div>
    </div>
    <%
    }
    }
    %>
    
</div>

            	
            	