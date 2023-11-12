<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/template/head.jsp" />
        <title>Inicio de Sesión</title>
    </head>

    <body>
        <header>
            <jsp:include page="/WEB-INF/template/navbar.jsp" />
        </header>

       <main class="container">
    <div class="row justify-content-center">
      <div class="col-sm-10 m-5">
        <div class="text-center mb-4">
          <h1>Iniciar Sesión</h1>
        </div>
        
        <% String mensajeError = (String) request.getAttribute("mensajeError");
                   if (mensajeError != null && !mensajeError.isEmpty()) { %>
                    <div class="alert alert-danger"><%= mensajeError %></div>
        <% } %>
        
        <form class="form-signin needs-validation" novalidate action="IniciarSesion" method="post">
          <div class="form-floating">
			<input type="text" class="form-control" name="identificador-input" id="identificador-input" 
				placeholder="Nickname o Correo Electronico" required 
				value="<%= request.getAttribute("identificador") != null ? request.getAttribute("identificador") : "" %>"/>
            <label for="identificador-input">Nickname o Correo Electronico</label>
            <div class="invalid-feedback">
              Nickname / Correo Electronico es un atributo requerido
            </div>
          </div>
          
          <div class="form-floating">
            <input type="password" class="form-control" name="password-input" id="password-input" placeholder="Contraseña" required />
            <label for="password-input">Contraseña</label>
            <div class="invalid-feedback">
              Contraseña es un atributo requerido
            </div>
          </div>
          <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">
            Ingresar
          </button>
        </form>
      </div>
    </div>
  </main>

   <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    
    <script src="<%= request.getContextPath() %>/js/iniciosesion.js"></script>
    

</body>
</html>