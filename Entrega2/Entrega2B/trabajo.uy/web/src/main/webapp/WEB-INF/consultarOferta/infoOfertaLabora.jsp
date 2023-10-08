<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Base64" %>
<%@ page import="main.java.logica.datatypes.DTOfertaExtendido" %>

<%
	// Obtener el objeto de usuario desde los atributos de la solicitud
	DTOfertaExtendido ofertaLaboral = (DTOfertaExtendido) request.getAttribute("ofertaLaboral");
%>


    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title><%= ofertaLaboral.getNombre() %></title>
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
                
                <div class="container">
        <div class="row">
          <div class="col-9 d-flex">
            <div class="mt-4" id="detalleOferta">
              <div class="container">
                <div class="row">
                  <div class="col-md-6">
                    <div class="d-flex align-items-center mt-2"> <!-- Aumentamos el margen superior aquí -->
                      <img src="https://tinyurl.com/45nsf34m" alt="Descripción de la imagen" class="img-fluid">
                      <div style="margin-left: 20px;"> <!-- Agregamos un margen a la izquierda de 20px -->
                        <h2><%= ofertaLaboral.getNombre() %></h2>
                      </div>
                    </div>
                  </div>
                </div>
              </div>


       

              <h3 class="text-center mt-3">Información Básica de la Oferta Laboral</h3>
              <p><span class="fw-bold" id="nombre"><%= ofertaLaboral.getNombre() %></span> <span id="descripcion"><%= ofertaLaboral.getDescripcion() %></span></p>

              <p class="mb-05"><span class="fw-bold">Remuneración:</span> <span id="remuneracionOferta"><%= ofertaLaboral.getRemuneracion() %> pesos
                  uruguayos</span></p>
              <p class="mb-05"><span class="fw-bold">Horario:</span> <span id="horarioOferta"><%= ofertaLaboral.getHorario() %></span></p>
              <p class="mb-05"><span class="fw-bold">Departamento:</span> <span
                  id="departamentoOferta"><%= ofertaLaboral.getDepartamento() %></span></p>
              <p class="mb-3"><span class="fw-bold">Ciudad:</span> <span id="ciudadOferta"><%= ofertaLaboral.getCiudad() %></span></p>

              <p class="mb-05"><span class="fw-bold">Fecha de Alta:</span> <span id="fechaAltaOferta"><%= ofertaLaboral.getFechaDeAlta() %></span></p>
              <p class="mb-3"><span class="fw-bold">Costo:</span> <span id="costoOferta"><%= ofertaLaboral.getCosto() %></span></p>
              
             
              <div>
                <p class="fw-bold">
                  Keywords:
                </p>
                <ul class="list-group list-group-horizontal">
                  <li class="list-group-item">Tiempo completo</li>
                  <li class="list-group-item">Medio Tiempo</li>
                  <li class="list-group-item">Remoto</li>
                  <li class="list-group-item">Freelance</li>
                  <li class="list-group-item">Temporal</li>
                  <li class="list-group-item">Permanente</li>
                </ul>
              </div>
            </div>
          </div>

          <div class="col-3 text-center">
            <div class="mt-4" id="postulaciones">
              <h1 class="text-center">Postulaciones</h1>
            </div>

            <div class="container">
              <!-- Imagen 1 -->
              <div class="d-flex align-items-center">
                <img src="https://tinyurl.com/yckek63e" alt="Imagen 1" class="img-fluid"
                  style="width: 100px; height: 100px"> <!-- Aumentamos el margen inferior aquí -->
                <a href="../consultaPostulacion/lgarciaDesarrolloFrontEnd.html" target="_blank" class="ms-4">
                  <!-- Aumentamos el margen derecho y el margen superior aquí -->
                  lgarcia
                </a>
              </div>

              <!-- Imagen 2 -->
              <div class="d-flex align-items-center mt-2"> <!-- Aumentamos el margen superior aquí -->
                <img src="https://picsum.photos/100/100" alt="Imagen 2" class="img-fluid">
                <!-- Aumentamos el margen inferior aquí -->
                <a href="../consultaPostulacion/maroDesarrolloFrontEnd.html" target="_blank" class="ms-4">
                  <!-- Aumentamos el margen derecho y el margen superior aquí -->
                  maro
                </a>
              </div>
            </div>

            <div class="mt-4" id="paquete">
              <h1 class="text-center">Paquete</h1> <!-- Aumentamos el margen superior aquí -->
            </div>

            <div class="container">
              <div class="d-flex align-items-center mt-6"> <!-- Aumentamos el margen superior aquí -->
                <img src="https://shorturl.at/ceCD2" alt="Imagen 2" class="img-fluid" width="110" height="110">

                <!-- Aumentamos el margen inferior aquí -->
                <a href="../consultaPaquete/Basica.html" target="_blank" class="ms-4">
                  <!-- Aumentamos el margen derecho y el margen superior aquí -->
                  Básico
                </a>
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
            
            <!-- Otros scripts aqui -->
    </body>

    </html>