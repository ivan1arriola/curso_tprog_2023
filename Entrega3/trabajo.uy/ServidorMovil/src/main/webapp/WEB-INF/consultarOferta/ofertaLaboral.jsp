<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="java.util.Base64" %>
<%
	DtOfertaExtendido oferta = (DtOfertaExtendido) request.getAttribute("oferta");

	byte[] imagenBytes = oferta.getImagen();
	String imagen = "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(imagenBytes);
	
	
%>


<!DOCTYPE html>
<html>

<head>
  <jsp:include page="/WEB-INF/template/head.jsp" />
  <title>trabajo.uy</title>
  <style type="text/css">
    	.banner-container {
		    background-image: url("<%=imagen%>");
		}
  </style>
</head>

<body>
<header>
  <jsp:include page="/WEB-INF/template/navbar.jsp" />
</header>

<main>

	

        <div class="container container col-12">

            <div class="container p-3">

                    <div class="row banner-container banner-dark">
                        <h1 class="text-center text-light fw-bolder"><%= oferta.getNombre() %></h1>
                    </div>

                    <div class="row align-items-center mt-2">
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>Nombre:</th>
                                    <td><%= oferta.getNombre() %></td>
                                </tr>
                                <tr>
                                    <th>Empresa:</th>
                                    <td>
                                        HAY QUE AGREGAR EMPRESA
                                    </td>
                                </tr>
                                <tr>
                                    <th>Descripción:</th>
                                    <td><%= oferta.getDescripcion() %></td>
                                </tr>
                                <tr>
                                    <th>Remuneración:</th>
                                    <td><%= oferta.getRemuneracion() %> pesos uruguayos</td>
                                </tr>
                                <tr>
                                    <th>Horario:</th>
                                    <td><%= oferta.getHorario()%></td>
                                </tr>
                                <tr>
                                    <th>Departamento:</th>
                                    <td><%= oferta.getDepartamento() %>
                                </tr>
                                <tr>
                                    <th>Ciudad:</th>
                                    <td><%= oferta.getCiudad() %></td>
                                </tr>
                                <tr>
                                    <th>Fecha de Alta:</th>
                                    <td><%= oferta.getFechaDeAlta() %> </td>
                                </tr>
                                <tr>
                                    <th>Costo:</th>
                                    <td><%= oferta.getCosto() %> pesos uruguayos</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                    <div>
                        <p class="fw-bold">
                            Keywords:
                        </p>
                        <ul class="list-group list-group-horizontal">

                            <div class="alert alert-warning" role="alert">
                                No hay palabras clave disponibles
                            </div>

                        </ul>
                    </div>

                    <div id="postulacion">
                        <div class="row align-items-center mt-2">
                            <h2>Postulaciones</h2>
                        </div>

                        <div class="row align-items-center mt-2">
                            <!-- Link para ver postulacion en caso de existir-->
                            <a href="/postulacion/DesarrolladorFrontEnd.html" class="btn btn-primary btn-lg">Ver Postulación</a>
                        </div>

                        <div class="row align-items-center mt-2">
                            <!-- Link para ver postulacion en caso de existir-->
                            <a href="/postulacion/postularse.html" class="btn btn-primary btn-lg">Postularse</a>

                        </div>


                    </div>



                
            </div>

        </div>


    </main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>
</html>