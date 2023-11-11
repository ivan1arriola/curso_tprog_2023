<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>Alta de Usuario</title>
    </head>

    <body>
        <header>
            <jsp:include page="/WEB-INF/templates/header.jsp" />
        </header>

       <main class="container">
    <div class="row justify-content-center">
    
    	<div class="col-9">
        <div class="text-center mb-4">
          <h1 class="h3 mb-3 fw-normal">Alta de Usuario</h1>
        </div>
        
        <%
        String nickname = request.getParameter("nickname");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String tipoUsuario = request.getParameter("tipo-usuario");

        String descripcionEmpresa = null;
        String sitioWebEmpresa = null;
        String fechaNacimiento = null;
        String nacionalidad = null;
        
        if ("empresa".equals(tipoUsuario)) {
            descripcionEmpresa = request.getParameter("descripcion");
            sitioWebEmpresa = request.getParameter("sitio-web");
        } else {
            fechaNacimiento = request.getParameter("fecha-nacimiento");
            nacionalidad = request.getParameter("nacionalidad");
        }
        
        %>

        <form class="form-signup needs-validation" novalidate  action="altausuario" method="POST" enctype="multipart/form-data">
          <!-- Sección de Información Personal -->
          <section>
            <h2 class="h4">Información Personal</h2>
            
         <% String mensajeError = (String) request.getAttribute("alert");
                   if (mensajeError != null && !mensajeError.isEmpty()) { %>
                    <div class="alert alert-danger"><%= mensajeError %></div>
        <% } %>




                      <!-- Input de Nickname -->
                      <div class="form-floating mb-3" id="div-nickname-input">
                          <input type="text" class="form-control" id="nickname-input" name="nickname" placeholder=" " required value="">
                          <label for="nickname-input">Nickname *</label>
                          <div class="invalid-feedback" id="nickname-invalid-feedback">
                              Elije otro nickname
                          </div>
                      </div>

                      <div class="row">
                          <div class="col">
                              <!-- Input de Nombre -->
                              <div class="form-floating mb-3 " id="div-nombre-input">
                                  <input type="text" class="form-control" id="nombre-input"  name="nombre" placeholder=" " required value="">
                                  <label for="nombre-input">Nombre *</label>
                                  <div class="invalid-feedback" id="nombre-invalid-feedback">
                                      Nombre es un atributo requerido
                                  </div>
                              </div>
                          </div>
                          <div class="col">
                              <!-- Input de Apellido -->
                              <div class="form-floating mb-3 " id="div-apellido-input">
                                  <input type="text" class="form-control" id="apellido-input" name="apellido"  placeholder=" " required value="">
                                  <label for="apellido-input">Apellido *</label>
                                  <div class="invalid-feedback" id="apellido-invalid-feedback">
                                      Apellido es un atributo requerido
                                  </div>
                              </div>
                          </div>
                      </div>
                  </section>

                  <!-- Sección de Contraseña -->
                  <section>
                      <h2 class="h4">Contraseña</h2>

                      <div class="row">

                          <div class="col">
                              <!-- Input de Contraseña -->
                              <div class="form-floating mb-3 " id="div-password-input">
                                  <input type="password" class="form-control" id="password-input" name="password" placeholder=" " required>
                                  <label for="password-input">Contraseña *</label>
                                  <div class="invalid-feedback" id="password-invalid-feedback">
                                      Contraseña es un atributo requerido
                                  </div>
                              </div>
                          </div>

                          <div class="col">
                              <!-- Input de Confirmar Contraseña -->
                              <div class="form-floating mb-3 " id="div-confirm-password-input">
                                  <input type="password" class="form-control" id="confirm-password-input" name="confirm-password"  placeholder=" ">
                                  <label for="confirm-password-input">Confirmar Contraseña *</label>
                                  <div class="invalid-feedback" id="confirm-password-invalid-feedback">
                                      Las contraseñas no coinciden
                                  </div>
                              </div>
                          </div>

                      </div>

                  </section>

                  <!-- Sección de Correo Electrónico -->
                  <section>
                      <h2 class="h4">Correo Electrónico</h2>

                      <!-- Input de Correo Electrónico -->
                      <div class="form-floating mb-3 " id="div-email-input">
                          <input type="email" class="form-control" id="email-input" placeholder=" " name="email" required value="">
                          <label for="email-input">Correo Electrónico *</label>
                          <div class="invalid-feedback" id="email-invalid-feedback">
                              Correo Electrónico es un atributo requerido
                          </div>
                      </div>
                  </section>

                  <!-- Sección de Tipo de Usuario -->
                  <section class="mb-3" id="tipo-usuario">
                      <h2 class="h4">Tipo de Usuario</h2>

                      <!-- Radio buttons para el tipo de usuario -->
                      <div class="form-check ">
                          <input class="form-check-input" type="radio" name="tipo-usuario" id="empresa-radio" value="empresa"
                                 onchange="mostrarCamposEspeciales()" required >
                          <label class="form-check-label" for="empresa-radio">Empresa</label>
                      </div>

                      <div class="form-check">
                          <input class="form-check-input" type="radio" name="tipo-usuario" id="postulante-radio" value="postulante"
                                 onchange="mostrarCamposEspeciales()" required >
                          <label class="form-check-label" for="postulante-radio">Postulante</label>
                          <div class="invalid-feedback">
                              Debe seleccionar un tipo de usuario
                          </div>
                      </div>
                  </section>

                  <!-- Campos adicionales para Empresa -->
                  <div id="campos-empresa">
                      <section>
                          <h2 class="h4">Información de la Empresa</h2>

                          <div class="form-floating mb-3 " id="div-descripcion-empresa-input">
                              <textarea class="form-control" id="descripcion-empresa-input" name="descripcion" placeholder=" " style="height: 150px" value=""></textarea>

                              <label for="descripcion-empresa-input">Descripción de la Empresa *</label>
                              <div class="invalid-feedback" id="descripcion-empresa-invalid-feedback">
                                  Descripción de la Empresa es un atributo requerido
                              </div>
                          </div>

                          <div class="form-floating mb-3 " id="div-sitio-web-input">
                              <input type="url" class="form-control" id="sitio-web-input" name="sitio-web"  placeholder=" " value="">
                              <label for="sitio-web-input">Sitio Web de la Empresa</label>
                          </div>
                      </section>
                  </div>

                  <!-- Campos adicionales para Postulante -->
                  <div id="campos-postulante">
                      <section>
                          <h2 class="h4">Información del Postulante</h2>

                          <div class="form-floating mb-3 " id="div-fecha-nacimiento-input">
                              <input type="date" class="form-control" id="fecha-nacimiento-input" name="fecha-nacimiento"  required value="">
                              <label for="fecha-nacimiento-input">Fecha de Nacimiento *</label>
                              <div class="invalid-feedback" id="fecha-nacimiento-invalid-feedback">
                                  Fecha de Nacimiento es un atributo requerido
                              </div>
                          </div>

                          <div class="form-floating mb-3 " id="div-nacionalidad-input">
                              <input type="text" class="form-control" id="nacionalidad-input" name="nacionalidad" placeholder=" " value="">
                              <label for="nacionalidad-input">Nacionalidad *</label>
                              <div class="invalid-feedback" id="nacionalidad-invalid-feedback">
                                  Nacionalidad es un atributo requerido
                              </div>
                          </div>
                      </section>
                  </div>

                  <!-- Sección de Imagen de Perfil -->
                  <section>
                      <h2 class="h4">Imagen de Perfil</h2>

                      <div class="form-group mb-3 " id="div-image-input">
                          <label for="image-input">Subir una imagen de perfil (max 500 KB)</label>
                          <input type="file" class="form-control" id="image-input" name="imagen" accept=".jpg" />
                          <div class="invalid-feedback" id="image-invalid-feedback">
                              La imagen no debe superar el tamaño máximo permitido de 500 KB.
                          </div>
                      </div>
                  </section>

                  <!-- Botón de Registro -->
                  <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">
                      Registrarse
                  </button>

                  <div class="link-secondary text-center">
                      <!-- mensaje para iniciar sesión en vez de darse de alta -->
                      <a href="/ServidorWeb/iniciarsesion" class="btn btn-link">Iniciar Sesión</a>
                  </div>
              </form>
      </div>
      
    </div>
  </main>

 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

        <script>
            const contextPath = '<%= request.getContextPath() %>';
        </script>

        <script src="<%= request.getContextPath() %>/js/altaUsuario.js"></script>
    

</body>
</html>