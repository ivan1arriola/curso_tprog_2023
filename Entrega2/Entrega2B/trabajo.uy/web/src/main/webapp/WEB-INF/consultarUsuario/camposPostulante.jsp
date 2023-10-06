<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Datatypes.DTPostulante" %>

<%
DTPostulante usuario = (DTPostulante) request.getAttribute("usuario");
String fechaFormateada = usuario.getFecha_nac().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
%>

<div class="form-group">
  <label for="fecha_nac">Fecha de nacimiento:</label>
  <input type="text" class="form-control" id="fecha_nac" disabled value="<%= fechaFormateada %>" />
</div>
<div class="form-group">
  <label for="link">Nacionalidad:</label>
  <input type="text" class="form-control" id="link" disabled value="<%= usuario.getNacionalidad() %>" />
</div>
