<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javabeans.PaqueteBean" %>

<% boolean yaSeCompro = (boolean) request.getAttribute("yaSeCompro"); 
PaqueteBean paquete = (PaqueteBean) request.getAttribute("paquete");
%>

<% if (!yaSeCompro) { %>
    <div class="col-3">
        <a href="<%= request.getContextPath() %>/comprarpaquete?p=<%= paquete.getNombre()%>" class="btn btn-primary" data-toggle="modal" data-target="#verificarCompraModal">
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


