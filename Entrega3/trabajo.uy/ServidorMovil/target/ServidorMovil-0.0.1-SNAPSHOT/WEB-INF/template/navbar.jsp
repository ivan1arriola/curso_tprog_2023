<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<% String contextPath = request.getContextPath(); %>
<nav class="navbar">
<div class="container-fluid justify-content-between">
  <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
          aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="d-flex mx-auto">
    <h1 class="text-primary m-0 fw-bold">trabajo</h1>
    <h1 class="text-secondary m-0">.uy</h1>
  </div>
  <div class="d-flex align-items-center justify-content-between">
    <% if (session != null && session.getAttribute("tipoUsuario") != null) { %>
      <img src="https://tinyurl.com/4n2vpurk" alt="..." class="rounded-circle" width="60" height="60">
      <span>Nickname</span>
    <% } %>
  </div>
  <div class="collapse navbar-collapse order-lg-3" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <% if (session != null && session.getAttribute("tipoUsuario") != null) { %>
      <a class="nav-link" href="/ofertasLaborales.html">Ver Ofertas Laborales</a>
      <a class="nav-link" href="/mispostulaciones.html">Ver Postulaciones</a>
      <a class="nav-link" href="/salir.html">Salir</a>
      <% } else {%>
      <a class="nav-link" href="<%=contextPath%>/iniciarsesion">Iniciar Sesion</a>
      <%}%>
    </div>
  </div>
</div>
</nav>
