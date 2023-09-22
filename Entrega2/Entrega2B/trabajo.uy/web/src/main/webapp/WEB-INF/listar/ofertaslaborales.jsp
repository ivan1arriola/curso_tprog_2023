<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Datatypes.DTOfertaLaboral" %>
<%@ page import="model.Datatypes.DTHorario" %>
<%@ page import="model.Datatypes.DTHora" %>


<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Ofertas Laborales</title>
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

                <%
                    List<DTOfertaLaboral> ofertasLaborales = (List<DTOfertaLaboral>) request.getAttribute("ofertasLaborales");
                    for (DTOfertaLaboral oferta : ofertasLaborales) {
                %>
                <div class="card mb-3">
	              <div class="row g-0">
	                <div class="col-md-4">
	                  <img src=<%= oferta.getImagen() %> class="img-fluid rounded-start" alt="..." />
	                </div>
	                <div class="col-md-8">
	                  <div class="card-body">
	                    <h5 class="card-title"><%= oferta.getNombre() %></h5>
	                    <p class="card-text">
	                      <%= oferta.getDescripcion() %>
	                    </p>
	                    <a href="<%= request.getContextPath() %>/consultarofertalaboral?ol=<%= oferta.getNombre() %>" class="card-link">Leer
	                      más</a>
	                  </div>
	                </div>
	              </div>
	            </div>
                <%
                    }
                %>

               
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
