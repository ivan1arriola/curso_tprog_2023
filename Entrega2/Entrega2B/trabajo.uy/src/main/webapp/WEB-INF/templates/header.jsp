<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

  <nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="titulo">
      <a class="navbar-brand mx-4" href="home">
        <span class="text-primary m-0 fs-3 fw-bold">trabajo</span>
        <span class="text-secondary m-0 fs-3">.uy</span>
      </a>
    </div>

    <div class="container-fluid d-flex form justify-content-center">
      <form id="form-buscar" class="d-flex col-6" role="search">
        <input id="buscar-input" class="form-control me-2" type="search" placeholder="Ofertas Laborales, Empresas"
          aria-label="Buscar" />
        <button class="btn btn-success d-flex" type="submit">
          <span class="material-symbols-outlined"> search </span> Buscar
        </button>
      </form>
    </div>

    <% 
    // Obtén el valor de la variable de sesión usuarioLogueado
    Boolean usuarioLogueado = (Boolean) session.getAttribute("usuarioLogueado");
    
    // Comprueba si el usuario NO ha iniciado sesión (usuarioLogueado es null o false)
    if (usuarioLogueado == null || !usuarioLogueado) {
    %>
      
        <div id="sesion" class="usuario d-block col-2">
          <div class="d-inline-block">
            <!-- Coloca aquí los enlaces o contenido para el usuario no logueado -->
            <a href="visitante/inicioDeSesionNickname.html">Iniciar Sesión</a>
            <span> | </span>
            <a href="visitante/altaDeUsuario.html">Alta Usuario</a>
          </div>
        </div>
      
      <% } // Fin de la comprobación de inicio de sesión %>
    </nav>