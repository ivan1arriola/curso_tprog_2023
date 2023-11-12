<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="javabeans.PaqueteBean" %>

<% boolean yaSeCompro = (boolean) request.getAttribute("yaSeCompro"); 
PaqueteBean paquete = (PaqueteBean) request.getAttribute("paquete");
//Codificar el nombre del paquete para asegurar una URL válida
String nombrePaqueteCodificado = URLEncoder.encode(paquete.getNombre(), "UTF-8");
%>
<% if (!yaSeCompro) { %>
	
    <div class="col-3">
        <a href="<%= request.getContextPath() %>/comprarpaquete?p=<%= nombrePaqueteCodificado %>" class="btn btn-primary" data-toggle="modal" data-target="#verificarCompraModal">
            <i class="fas fa-shopping-cart"></i> Comprar
        </a>
    </div>
<% } else { %>
    <div class="col-3">
        <button class="btn btn-secondary" disabled>
            <i class="fas fa-shopping-cart"></i> Ya se compró
        </button>
    </div>
<% } %>


