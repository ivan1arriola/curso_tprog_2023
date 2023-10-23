<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Base64" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.UsuarioSinInfoSocialBean" %>

<%
	UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
	Set<UsuarioSinInfoSocialBean> seguidos = usuario.getSeguidos();
%>

<!-- Tab de Postulaciones -->
<div class="tab-pane fade sin-bordes" id="postulaciones-panel" role="tabpanel" aria-labelledby="postulaciones-tab">
    <%
    if (seguidos.isEmpty()) {
    %>
    <div class="alert alert-info" role="alert">
        El usuario no sigue a ningún otro usuario.
    </div>
    <%
    } else {
        for (UsuarioSinInfoSocialBean user : seguidos) {
    %>
    <div class="card mb-3">
        <div class="card-body">
            <div class="row">
                <div class="col-4">
                    <h5 class="card-title"><%= user.getNombre() + " " + user.getApellido() %></h5>
                </div>
                <div class="col-4 text-center">
                    <p class="card-text"><%= user.getDescripcion() %></p>
                </div>
                <div class="col-4 text-end">
                    <a href="<%= request.getContextPath() %>/consultarusuario?id=<%= user.getNickname() %>"
                        class="">
                        Leer más
                    </a>
                </div>
            </div>
        </div>
    </div>
    <%
        }
    }
    %>
</div>
