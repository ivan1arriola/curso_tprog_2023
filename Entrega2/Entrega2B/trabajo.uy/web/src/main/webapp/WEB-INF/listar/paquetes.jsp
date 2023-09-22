<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>Paquetes</title>
    </head>

    <body>
        <header>
            <jsp:include page="/WEB-INF/templates/header.jsp" />
        </header>

        <main class="container-fluid d-flex">
            <div class="container col-3">
            	<jsp:include page="/WEB-INF/templates/sidebar.jsp" />

            </div>

            <div class="container col-9">
                <jsp:include page="/WEB-INF/templates/navbar.jsp" />

                
                <div class="sin-bordes">
            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://shorturl.at/ceCD2" class="img-fluid rounded-start" alt="Paquete Basico" />
                </div>
                <div class="col">
                  <div class="card-body">
                    <h5 class="card-title">Básico</h5>
                    <p class="card-text">
                      Publica ofertas laborales en nuestra plataforma por un
                      período de 30 días
                    </p>
                    <a href="<%= request.getContextPath() %>/consultarpaquete?p=Basica" class="card-link">Leer más</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://picsum.photos/603" class="img-fluid rounded-start" alt="Paquete Destacado" />
                </div>
                <div class="col">
                  <div class="card-body">
                    <h5 class="card-title">Destacado</h5>
                    <p class="card-text">
                      Publica ofertas laborales destacadas que se mostrarán en
                      la parte superior de los resultados de búsqueda por 45
                      días
                    </p>
                    <a href="<%= request.getContextPath() %>/consultarpaquete?p=Destacado" class="card-link">Leer más</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://picsum.photos/602" class="img-fluid rounded-start" alt="Paquete Premium" />
                </div>
                <div class="col">
                  <div class="card-body">
                    <h5 class="card-title">Premium</h5>
                    <p class="card-text">
                      Publica ofertas laborales premium que incluye promoción
                      en nuestras redes sociales y listado en la sección
                      destacada por 60 días.
                    </p>
                    <a href="<%= request.getContextPath() %>/consultarpaquete?p=Premium" class="card-link">Leer más</a>
                  </div>
                </div>
              </div>
            </div>

            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://picsum.photos/601" class="img-fluid rounded-start" alt="Paquete Express" />
                </div>
                <div class="col">
                  <div class="card-body">
                    <h5 class="card-title">Express</h5>
                    <p class="card-text">
                      Publica ofertas laborales urgentes resaltadas en color y
                      se mostrarán en la sección de urgente por 15 días.
                    </p>
                    <a href="<%= request.getContextPath() %>/consultarpaquete?p=Express" class="card-link">Leer más</a>
                  </div>
                </div>
              </div>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
            crossorigin="anonymous"></script>

    </body>

    </html>