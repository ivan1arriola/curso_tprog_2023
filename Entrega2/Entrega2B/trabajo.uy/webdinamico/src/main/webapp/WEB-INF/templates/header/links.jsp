<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <div id="sesion" class="usuario d-block col-2">
    <div class="d-inline-block">
      <!-- Coloca aquí los enlaces o contenido para el usuario no logueado -->
      <a href="<%= request.getContextPath() %>/iniciarsesion">Iniciar Sesión</a>
      <span> | </span>
      <a href="<%= request.getContextPath() %>/altausuario">Alta Usuario</a>
    </div>
  </div>