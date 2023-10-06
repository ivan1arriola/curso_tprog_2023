<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>Informacion de Usuario</title>
    </head>

    <body>
        <header>
            <jsp:include page="/WEB-INF/templates/header.jsp" />
        </header>

        <main class="container-fluid d-flex">
            <div class="container col-3">
            	<jsp:include page="/WEB-INF/templates/sidebar.jsp" />

            </div>

            <div class="container container col-9">

      <!-- Agregar contenido nuevo aquí -->
      <div class="container">
        <div class="row">
          <div class="col-4">
            <img src="https://tinyurl.com/yckek63e" alt="Imagen de Usuario"
              class="img-fluid mb-3 rounded-circle img-thumbnail perfil" />
          </div>
          <div class="col-8">
            <h1>Lucía Garcia</h1>
            <h2>lgarcia85@gmail.com</h2>

          </div>
        </div>
      </div>

      <!-- Agrega las pestañas (tabs) -->
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
          <a class="nav-link active" data-bs-toggle="tab" data-bs-target="#perfil-panel" type="button" role="tab"
            aria-controls="perfil-panel" aria-selected="true">Perfil</a>
        </li>
      </ul>

      <!-- Contenido de las pestañas -->
      <div class="tab-content" id="myTabsContent">
        <!-- Tab de perfil -->
        <div class="tab-pane fade show active" id="perfil-panel" style="margin-top: 20px">
          <form>
            <div class="row">
              <div class="col-8">
                <div class="form-group">
                  <label for="nombre">Nombre:</label>
                  <input type="text" class="form-control" id="nombre" disabled value="Lucía" required />
                </div>
                <div class="form-group">
                  <label for="apellido">Apellido:</label>
                  <input type="text" class="form-control" id="apellido" disabled value="Garcia" required />
                </div>
                <div class="form-group">
                  <label for="correo">Correo electrónico:</label>
                  <input type="email" class="form-control" id="correo" disabled value="lgarcia85@gmail.com" readonly
                    required />
                </div>
                <div class="form-group">
                  <label for="fecha_nac">Fecha de nacimiento:</label>
                  <input type="text" class="form-control" id="link" disabled value="15/03/1985" />
                </div>
                <div class="form-group">
                  <label for="link">Nacionalidad:</label>
                  <input type="text" class="form-control" id="link" disabled value="Uruguaya" />
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>

    </div>
        </main>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
            crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
            integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
            crossorigin="anonymous"></script>
            
            <!-- Otros scripts aqui -->
    </body>

    </html>