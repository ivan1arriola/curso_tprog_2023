<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Base64" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.UsuarioSinInfoSocialBean" %>
<%@ page import="javabeans.OfertaLaboralBean" %>

<%
	UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
	Set<OfertaLaboralBean> oferFavs = usuario.getOferFavs();
%>

<!-- Tab de Seguidores -->
<div class="tab-pane fade sin-bordes p-2" id="favoritos-panel" role="tabpanel" aria-labelledby="seguidores-tab">

<%

	
    if (oferFavs.isEmpty()){
    	
    	%>
    	
    	<div class="alert alert-secondary" role="alert">
			No existen ofertas laborales marcadas como favoritas.
		</div>

    	<%
    	
    } else{
    	
   
    %>

    <%
    for (OfertaLaboralBean ofer : oferFavs) {
    	String imagenOferta = ofer.getImagen();
        
        String nombreOferta = ofer.getNombre();
        String descripcion = ofer.getDescripcion();
        String enlace = request.getContextPath() + "/consultarofertalaboral?o=" + ofer.getNombre();
    %>
    <div class="card mb-3">
        <div class="row g-0">
            		<div class="card mb-3">
					    <div class="row g-0">
					    <%
                        if (imagenOferta != null) {
                        %>
                            <img src="<%= imagenOferta %>" alt="Imagen de Oferta" class="img-fluid mb-3 perfil rounded" style="width: 250px; height: 150px;" />
                        <%
                        } else {
                        %>
                            <img src="<%= request.getContextPath() + "/nopicture.png" %>"
                                alt="Imagen de Usuario por Defecto"
                                class="img-fluid mb-3 rounded-circle img-thumbnail perfil" />
                        <%
                        }
                        %>
					        <div class="col">
					            <div class="card-body">
					                <h5 class="card-title"><%=nombreOferta%></h5>
					                <p class="card-text"><%=descripcion%></p>
					                <a href="<%=enlace%>" class="card-link">Visitar Oferta Laboral</a>      
					            </div>
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
