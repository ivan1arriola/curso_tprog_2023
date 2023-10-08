<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.logica.Datatypes.DTPostulante" %>

<%
DTPostulante usuario = (DTPostulante) request.getAttribute("usuario");
%>

<div class="form-group">
  <label for="fecha_nac">Fecha de nacimiento:</label>
  <input type="date"  class="form-control" id="fechanacimiento" disabled value="<%= usuario.getFecha_nac() %>"  name="fechanacimiento"/>
</div>
<div class="form-group">
  <label for="link">Nacionalidad:</label>
  <input type="text" class="form-control" id="nacionalidad" disabled value="<%= usuario.getNacionalidad() %>" name="nacionalidad"/>
</div>
