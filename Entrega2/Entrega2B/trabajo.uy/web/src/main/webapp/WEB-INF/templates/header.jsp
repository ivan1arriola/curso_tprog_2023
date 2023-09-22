<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="titulo">
    <a class="navbar-brand mx-4" href="home">
      <span class="text-primary m-0 fs-3 fw-bold">trabajo</span>
      <span class="text-secondary m-0 fs-3">.uy</span>
    </a>
  </div>
  
  <% 
    // Obtén la URL de la solicitud actual
    String requestURI = request.getRequestURI();
    
    // Comprueba si la URL es una de las que deseas excluir
    if (!requestURI.endsWith("/iniciarsesion.jsp") && !requestURI.endsWith("/altaUsuario.jsp")) {
  %>
  
  <jsp:include page="/WEB-INF/templates/header/search.jsp"></jsp:include>
  <jsp:include page="/WEB-INF/templates/header/links.jsp"></jsp:include>
  
  <% } %>
</nav>
