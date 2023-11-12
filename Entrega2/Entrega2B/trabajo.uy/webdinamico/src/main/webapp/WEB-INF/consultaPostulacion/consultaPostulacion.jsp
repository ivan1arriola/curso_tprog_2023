<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>Postulacion</title>
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
      <!-- Agregar contenido nuevo aquí -->
      <div class="d-flex">
        <div class="contenedor-hijo col-10">
          <h3>Postulación a Oferta Laboral</h3>

          <p><b>Postulante:</b> <a href="<%= request.getContextPath() %>/consultarusuario?u=<%= request.getAttribute("nickname") %>"><%= request.getAttribute("postulante") %> </a> </p>

          <div class="label">
            <p><b>CV reducido:</b> <%=request.getAttribute("CVRed")%></p>
            <p><b>Motivación:</b> <%=request.getAttribute("CVMot")%></p>
            <p><b>Fecha Postulación:</b> <%=request.getAttribute("FechaPost")%></p>
          </div>
        </div>
        <!-- Columna 2 -->
        <div class="contenedor-hijo-derecha col-2">
          <img src="https://tinyurl.com/4n2vpurk" alt="Imagen 1" class="img-fluid mb-2"
            style="width: 160px; height: 120px">
          <p style="text-decoration: underline"><a href="<%= request.getContextPath() %>/consultarofertalaboral?o=<%= request.getAttribute("oferta") %>"> <%= request.getAttribute("oferta") %></a> </p>

        </div>
      </div>

      <!-- Hasta aqui-->

            </div>
        </main>

         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

            
            <!-- Otros scripts aqui -->
    </body>

    </html>