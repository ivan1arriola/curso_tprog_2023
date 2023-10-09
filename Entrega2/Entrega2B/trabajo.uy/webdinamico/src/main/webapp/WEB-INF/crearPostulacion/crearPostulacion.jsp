<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>[Nombre de la pagina]</title>
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
                <!-- Contenido aqui -->
                
                <div class="row">
        <h1 class="">Postulación a Oferta Laboral </h1>
      </div>

<form class="form-signin needs-validation" novalidate action="crearpostulacion" method="post">
      <div class="row">
		
        <div class="col-9">
          <!-- Nombre del Postulante -->
          <div class="row">
            <label for="nombrePostulante" class="col-2 col-form-label">Postulantes</label>
            <div class="col-10">
              <div class="card-body mt-1">
                <div class="d-inline">
                 <a href="<%= request.getContextPath() %>/consultarusuario?u=<%= request.getAttribute("nickname") %>" class="text mt-4"><%= request.getAttribute("postulante") %></a>
                </div>
              </div>
            </div>
          </div>
         

          <!-- CV Abreviado -->
          <div class="mb-3">
            <label for="cvAbreviado" class="form-label">CV Abreviado:</label>
            <textarea class="form-control" name="cvAbreviado" id="cvAbreviado" rows="4" placeholder="CV Abreviado"></textarea>
          </div>

          <!-- Motivación -->
          <div class="mb-3">
            <label for="motivacion" class="form-label">Motivación:</label>
            <textarea class="form-control" name="motivacion" id="motivacion" rows="4" placeholder="Motivación"></textarea>
          </div>

          <!-- Fecha de Postulación (automática) -->
          <div class="mb-4">
            <label for="fechaPostulacion" class="form-label">Fecha de Postulación:</label>
            <input type="text" class="form-control" id="fechaPostulacion" readonly disabled />
          </div>


		<input type="text" name="nombreOferta" placeholder="nombreDeLaOferta" value="<%= request.getAttribute("oferta") %>">

          <script>
            // Obtener la fecha actual
            var fechaActual = new Date();

            // Obtener el día, mes y año
            var dia = fechaActual.getDate();
            var mes = fechaActual.getMonth() + 1; // Los meses en JavaScript se cuentan desde 0 (enero) hasta 11 (diciembre)
            var anio = fechaActual.getFullYear();

            // Formatear la fecha en el formato deseado (por ejemplo, "dd/mm/yyyy")
            var fechaFormateada = dia + '/' + mes + '/' + anio;

            // Asignar la fecha formateada al campo de entrada
            document.getElementById("fechaPostulacion").value = fechaFormateada;
          </script>


          <button class="btn btn-primary" id="btnPostularse" type="submit">
            Postularse
          </button>
        </div>
        </form>


        <div class="col-3">
          <div class="card">
            <img src="https://www.coherdi.mx/wp-content/uploads/2017/05/esrategias-inversion.jpg"
              class="card-img-top" />
            <div class="card-body text-center">
            
                        
            <a href="<%=request.getContextPath() %>/consultarofertalaboral?o=<%= request.getAttribute("oferta") %>" class="text"><%= request.getAttribute("oferta") %></a> 
               
            </div>
          </div>
        </div>
      </div>

            </div>
        </main>

         <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

            
            <!-- Otros scripts aqui -->
    </body>

    </html>