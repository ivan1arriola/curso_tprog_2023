<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<% String contextPath = request.getContextPath(); %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <div class="d-flex col justify-content-center align-items-center">
      <a class="navbar-brand" href="<%= contextPath %>">
        <span class="text-primary m-0 fs-3 fw-bold">trabajo</span>
        <span class="text-secondary m-0 fs-3">.uy</span>
      </a>
    </div>
    <% if (session != null && session.getAttribute("tipoUsuario") != null) { %>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="SupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="<%= contextPath %>/ofertaslaborales">Ver Ofertas Laborales</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%= contextPath %>/mispostulaciones">Ver Postulaciones</a>
          </li>
        </ul>
      </div>
    <% } %>
  </div>
</nav>
