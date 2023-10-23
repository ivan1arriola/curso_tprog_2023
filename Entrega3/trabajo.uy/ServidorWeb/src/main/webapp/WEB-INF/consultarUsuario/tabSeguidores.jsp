<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Base64" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.UsuarioSinInfoSocialBean" %>

<%
	UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
	Set<UsuarioSinInfoSocialBean> seguidores = usuario.getSeguidores();
%>

<!-- Tab de Seguidores -->
<div class="tab-pane fade sin-bordes p-2" id="ofertas-panel" role="tabpanel" aria-labelledby="ofertas-tab">

<%
    if(seguidores.isEmpty()){
    	
    	%>
    	
    	<div class="alert alert-secondary" role="alert">
			El usuario no es seguido por ningún otro usuario.
		</div>

    	<%
    	
    } else{
    	
   
    %>

    <%
    for (UsuarioSinInfoSocialBean user : seguidores) {
    	String imagen = user.getImagen();
        if (imagen== null) {
            // Si no hay imagen, puedes establecer una imagen de reemplazo o un mensaje aquí
            imagen = request.getContextPath() +  "/imagenNoFound.png";
        }
        
        String nombre_completo = user.getNombre() + " " + user.getApellido();
        String correo = user.getCorreoElectronico();
        String enlace = request.getContextPath() + "/consultarusuario?o=" + user.getNickname();
    %>
    <div class="card mb-3">
        <div class="row g-0">
            		<div class="card mb-3">
					    <div class="row g-0">
					        <div class="col-md-4">
					            <img src="<%=imagen%>" class="img-fluid rounded-start" alt="<%=nombre_completo%>" />
					        </div>
					        <div class="col">
					            <div class="card-body">
					                <h5 class="card-title"><%=nombre_completo%></h5>
					                <p class="card-text"><%=correo%></p>
					                <a href="<%=enlace%>" class="card-link">Leer más</a>      
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
