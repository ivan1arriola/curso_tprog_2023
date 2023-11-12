<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%
  List<String> postulantes = (List<String>) request.getAttribute("postulantes");
  String imagen = (String) request.getAttribute("imagenOferta");
  String nombreOferta = request.getParameter("oferta");
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

      <div class="col">
        <div id="postulantes" class="container card m-2">
          <div class="text-center m-2">
            <h2 class="card-title">Lista de Postulantes</h2>
            <h4>Seleccionar Orden Postulantes:</h4>
          </div>

          <table class="table">
            <thead>
            <tr>
              <th >#</th>
              <th class="col-6">Postulante</th>
              <th class="col-3">Acciones</th>
            </tr>
            </thead>
            <tbody id="listaPostulantes">
            <% int rowIndex = 1; %>
            <% for (String postulante : postulantes) { %>
            <tr id="<%=postulante%>"
                class="postulante-row tr-draggable"
            >
              <th scope="row" class="row-index" id="index-<%=postulante%>"><%= rowIndex++ %></th>
              <td>
                <%=postulante%>
              </td>
              <td>
                <button
                        id="btn-<%=postulante%>"
                        class="btn btn-info"
                        data-bs-toggle="modal"
                        data-bs-target="#exampleModal"
                        onclick="cargarInfoPostulante('<%=postulante%>', '<%=nombreOferta%>' )"
                >
                  Ver Postulacion
                </button>
              </td>
            </tr>
            <% } %>
            </tbody>
          </table>



          <form class="text-center card-footer" action="${pageContext.request.contextPath}/ordenarpostulantes" method="POST">
            <input type="hidden" name="orden" id="input-orden" value="<%=postulantes.toString()%>">
            <input type="hidden" name="oferta" value="<%=request.getParameter("oferta")%>">
            <button type="submit" class="btn btn-success">Definir Orden de Postulantes</button>
          </form>

        </div>
      </div>



    </div>
  </div>
  </div>
</main>

<jsp:include page="/WEB-INF/consultarPostulaciones/consultarPostulacionModal.jsp" />

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script>
  const contextPath = "<%=request.getContextPath()%>";
</script>

<script src="<%=request.getContextPath() + "/js/consultarPostulaciones.js"%>"></script>


<script src="<%=request.getContextPath() + "/js/listaArrastrable.js" %>"></script>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
