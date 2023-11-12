<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Base64" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.PostulacionBean" %>

<%
	UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
	Set<PostulacionBean> postulaciones = usuario.getPostulaciones();
%>

<!-- Tab de Postulaciones -->
<div class="tab-pane fade sin-bordes" id="postulaciones-panel" role="tabpanel" aria-labelledby="postulaciones-tab">
    <%
    if (postulaciones.isEmpty()) {
    %>
    <div class="alert alert-info" role="alert">
        No hay postulaciones para mostrar.
    </div>
    <%
    } else {
        for (PostulacionBean postulacion : postulaciones) {
        	if (postulacion.getEstado() == "Vigente"){
      		  %>
    		  <div class="card mb-3">
    		      <div class="card-body">
    		          <div class="row">
    		              <div class="col-3">
    		                  <h5 class="card-title"><%= postulacion.getNombreOfertaLaboral() %></h5>
    		              </div>
    		              <div class="col-3 text-center">
    		                  <p class="card-text"><%= postulacion.getFechaString() %></p>
    		              </div>
    		              <div class="col-3 text-center">
    		                  <p class="card-text"><%= postulacion.getEstado() %></p>
    		              </div>
    		              <div class="col-3 text-end">
    		                  <a href="<%= request.getContextPath() %>/consultapostulacion?id=<%= postulacion.getNombreOfertaLaboral() %>"
    		                      class="">
    		                      Leer más
    		                  </a>
    		              </div>
    		          </div>
    		      </div>
    		  </div>
    		  <%        		
        	} else {
        		  %>
        		  <div class="card mb-3">
        		      <div class="card-body">
        		          <div class="row">
        		              <div class="col-3">
        		                  <h5 class="card-title"><%= postulacion.getNombreOfertaLaboral() %></h5>
        		              </div>
        		              <div class="col-3 text-center">
        		                  <p class="card-text"><%= postulacion.getFechaString() %></p>
        		              </div>
        		              <div class="col-3 text-center">
        		                  <p class="card-text"><%= postulacion.getEstado() %></p>
								  <a href="">Descargar PDF</a>
        		              </div>
        		              <div class="col-3 text-end">
        		                  <a href="<%= request.getContextPath() %>/consultapostulacion?id=<%= postulacion.getNombreOfertaLaboral() %>"
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
    }
    %>
</div>
