<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>Inicio de Sesión</title>
    </head>

    <body>
        <header>
            <jsp:include page="/WEB-INF/templates/header.jsp" />
        </header>

       <main class="container">
    <div class="row justify-content-center">
      <div class="col-4">
        <div class="text-center mb-4">
          <h1>Iniciar Sesión</h1>
        </div>
        <form class="form-signin needs-validation" novalidate action="IniciarSesion">
          <div class="form-floating">
            <input type="text" class="form-control" id="identificador-input" placeholder="Nickname o Correo Electronico" required />
            <label for="identificador-input">Nickname o Correo Electronico</label>
            <div class="invalid-feedback">
              Nickname / Correo Electronico es un atributo requerido
            </div>
          </div>
          <div class="form-floating">
            <input type="password" class="form-control" id="password-input" placeholder="Contraseña" required />
            <label for="password-input">Contraseña</label>
            <div class="invalid-feedback">
              Contraseña es un atributo requerido
            </div>
          </div>
          <div class="link-secondary">
            <a href="<%= request.getContextPath() %>/altausuario" class="btn btn-link">Alta Usuario</a>
          </div>
          <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">
            Ingresar
          </button>
        </form>
      </div>
    </div>
  </main>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
    crossorigin="anonymous"></script>
    
    <script src="<%= request.getContextPath() %>/js/iniciosesion.js"></script>
    

</body>
</html>