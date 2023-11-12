<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.logica.datatypes.DTEmpresa" %>

<%
DTEmpresa usuario = (DTEmpresa) request.getAttribute("usuario");
%>

<div class="form-group">
    <label for="descripcion">Descripción:</label>
    <textarea class="form-control" id="descripcion" rows="6" disabled  name="descripcion" required><%= usuario.getDescripcion() %></textarea>
</div>
<div class="form-group">
    <label for="link">Link de la empresa:</label>
    <input type="text" class="form-control" id="link" disabled name="link"  value="<%= usuario.getUrl() %>" />
</div>
