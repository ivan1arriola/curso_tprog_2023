<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.Base64" %>
<% String contextPath = request.getContextPath(); %>
<nav class="navbar">
<div class="container-fluid justify-content-between">
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
          aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  
  <div class="d-flex mx-auto">
  <a href="<%= request.getContextPath() %>/home" style="text-decoration: none;">
    <h1 class="text-primary m-0 fw-bold" style="display: inline;">trabajo</h1>
    <h1 class="text-secondary m-0" style="display: inline;">.uy</h1>
   </a>
  </div>
  
  
  <div class="d-flex align-items-center justify-content-between">
    <% if (session != null && session.getAttribute("tipoUsuario") != null) { 
    	String nickname = (String) session.getAttribute("nickname");    
    	byte[] imagenUsr = (byte[]) session.getAttribute("imagen"); 
    	String imagen = null;
    	
    	if (imagenUsr!=null) {
        	imagen = "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(imagenUsr);
        }

         if (imagenUsr == null) imagen = request.getContextPath() + "/nopicture.png";
        
    %>
      <img src="<%=imagen%>" alt="Foto <%= nickname %>" class="rounded-circle" width="60" height="60">
      <span> <%=nickname%> </span>
    <% } %>
  </div>
  <div class="collapse navbar-collapse order-lg-3" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <% if (session != null && session.getAttribute("tipoUsuario") != null) { %>
      <a class="nav-link" href="<%=contextPath%>/ofertaslaborales">Ver Ofertas Laborales</a>
      <a class="nav-link" href="<%=contextPath%>/verpostulacion">Ver Postulaciones</a>
      <a class="nav-link" href="/salir.html">Salir</a>
      <% } else {%>
      <a class="nav-link" href="<%=contextPath%>/iniciarsesion">Iniciar Sesion</a>
      <%}%>
    </div>
  </div>
</div>
</nav>
