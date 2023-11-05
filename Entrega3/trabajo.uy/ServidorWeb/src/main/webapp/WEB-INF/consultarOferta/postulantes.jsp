<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="javabeans.UsuarioBean" %>

<%
    List<UsuarioBean> postulantes = (List<UsuarioBean>) request.getAttribute("postulantes");
%>


<div>
    <div class="mt-4" id="postulaciones">
        <h4 class="text-center">Postulantes</h4>
    </div>

    <div class="mt-4">
        <%
            if (postulantes.isEmpty()) {
        %>
        <!-- Mensaje para Empresas -->
        <div class="alert alert-warning">
            No hay postulantes para esta oferta en este momento.
        </div>
        <%

        } else {
        %>
        <!-- Botón con número de postulantes -->
        <button type="button" class="btn btn-primary mb-3">
            Ver Postulantes <span class="badge bg-secondary"><%= postulantes.size() %></span>
        </button>
        <%
            }
        %>
    </div>
</div>
