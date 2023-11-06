<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<%
  List<String> postulantes = (List<String>) request.getAttribute("postulantesFinal");
  String imagen = (String) request.getAttribute("imagenOferta");
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
        <h1 class="text-center text-light fw-bolder">Desarrollador Frontend</h1>
      </div>

      <div class="row">
        <div class="col">
          <div id="postulantes" class="container card m-2">
            <h2 class="card-title text-center mt-2">Lista Final de Postulantes</h2>
            <div class="row align-items-center card-body">
              <table class="table table-bordered">
                <thead>
                <tr>
                  <th>Lugar</th>
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
                  <td>
                     <%=lugar%>
                  </td>

                  <td>
                    <%=postulante%>
                  </td>
                  <td>
                    <form action="<%=request.getContextPath()%>/consultapostulacion" method="get">
                      <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                      <input type="hidden" name="postulante" value="<%=postulante%>">
                      <button id="btn-<%=postulante%>" type="submit" class="btn btn-custom">Ver Postulación</button>
                    </form>

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

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
