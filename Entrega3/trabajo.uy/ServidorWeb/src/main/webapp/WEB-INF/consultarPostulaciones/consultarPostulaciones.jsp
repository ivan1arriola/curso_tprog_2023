<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<%
  List<String> postulantes = (List<String>) request.getAttribute("nicknamesPostulantes");
  String imagen = (String) request.getAttribute("imagenOferta");
  String nombreOferta = request.getParameter("oferta");
  boolean estaFinalizada = (boolean) request.getAttribute("estaFinalizada");
  boolean hayOrdenDefinido = (boolean) request.getAttribute("hayOrdenDefinido");

  boolean finalizoSinOrden = estaFinalizada && !hayOrdenDefinido;
%>

<head>
  <jsp:include page="/WEB-INF/templates/head.jsp" />
  <title>Postulaciones a Oferta Laboral</title>
  <style >
    .banner-container {
      background-image: url(<%= imagen %>);
      height: 25vh;
    }
  </style>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
    <div class="container">
      <div class="row banner-container banner-dark">
        <h1 class="text-center text-light fw-bolder"><%=nombreOferta%>></h1>
      </div>

      <div class="row">
        <div class="col">
          <div id="postulantes" class="container  text-center card m-2">
            <h2 class="card-title p-3">Lista de Postulantes</h2>
            <% if(estaFinalizada){ %> <h3>Oferta Finalizada</h3> <%}%>
            <% if(finalizoSinOrden){ %> <h3 class="text-danger">Finalizo sin orden de postulantes</h3> <%}%>
            <% if(hayOrdenDefinido){ %> <h3>Orden de Postulantes</h3> <%}%>
            <div class="row align-items-center card-body">
              <table class="table table-bordered">
                <thead>
                <tr>
                  <% if(hayOrdenDefinido){ %> <th>Lugar</th> <%}%>
                  <th>Nombre del Postulante</th>
                  <th>Acción</th>
                </tr>
                </thead>
                <tbody>
                <%
                  int lugar = 0;
                  for(String postulante : postulantes){
                    lugar++;
                %>
                <tr>
                  <% if(hayOrdenDefinido){ %>
                  <td>
                    <%=lugar%>
                  </td>
                  <%}%>


                  <td>
                    <%=postulante%>
                  </td>
                  <td>

                      <button id="btn-<%=postulante%>" type="button" onclick="cargarInfoPostulante('<%=postulante%>', '<%=nombreOferta%>')" class="btn btn btn-info" data-bs-toggle="modal" data-bs-target="#exampleModal">
                        Ver Postulacion
                      </button>

                  </td>
                </tr>
                <% } %>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>



    </div>
  </div>
</main>


<jsp:include page="./consultarPostulacionModal.jsp" />


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  const contextPath = "<%=request.getContextPath()%>";
</script>

<script src="<%=request.getContextPath() + "/js/consultarPostulaciones.js"%>">
</script>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
