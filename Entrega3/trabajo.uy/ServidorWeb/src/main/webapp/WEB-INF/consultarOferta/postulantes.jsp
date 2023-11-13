<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="javabeans.UsuarioBean" %>

<%
    List<UsuarioBean> postulantes = (List<UsuarioBean>) request.getAttribute("postulantes");
    String nombreOferta = (String) request.getAttribute("nombreOferta");
%>



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
        <!-- Botï¿½n con nï¿½mero de postulantes -->
        <a type="button" class="btn btn-primary" href="<%=request.getContextPath()%>/consultarpostulantes?oferta=<%=nombreOferta%>">
            Ver Postulantes <span class="badge bg-secondary"><%= postulantes.size() %></span>
        </a>
        <%
            }
        %>

