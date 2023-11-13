<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Base64" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.UsuarioSinInfoSocialBean" %>

<%
	UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
	Set<UsuarioBean> seguidos= (Set<UsuarioBean>) request.getAttribute("seguidos");
%>

<!-- Tab de Seguidos -->
<div class="tab-pane fade sin-bordes p-2" id="seguidos-panel" role="tabpanel" aria-labelledby="seguidos-tab">

<%
    if (seguidos.isEmpty()){
    	
    	%>
    	
    	<div class="alert alert-secondary" role="alert">
			El usuario no sigue a ningún otro usuario.
		</div>

    	<%
    	
    } else{
    	
   
    %>

    <%
    for (UsuarioBean user : seguidos) {
    	String imagenUsuario = user.getImagen();
        
        String nombre_completo = user.getNombre() + " " + user.getApellido();
        String correo = user.getCorreoElectronico();
        String enlace = request.getContextPath() + "/consultarusuario?u=" + user.getNickname();
    %>
    <div class="card mb-3">
        <div class="row g-0">
            		<div class="card mb-3">
					    <div class="row g-0">
					    <%
                        if (imagenUsuario != null) {
                        %>
                            <img src="<%= imagenUsuario %>" alt="Imagen de Usuario" class="img-fluid mb-3 perfil rounded" style="width: 250px; height: 150px;" />
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
					                <h5 class="card-title"><%=nombre_completo%></h5>
					                <p class="card-text"><%=correo%></p>
					                <a href="<%=enlace%>" class="card-link">Visitar perfil</a>      
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

