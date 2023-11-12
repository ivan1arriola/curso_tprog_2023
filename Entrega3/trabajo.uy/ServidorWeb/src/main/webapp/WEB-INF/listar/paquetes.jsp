<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.PaqueteBean" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Set" %>

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
                <%
                Set<PaqueteBean> paquetes = (Set<PaqueteBean>) request.getAttribute("paquetes");

                if (paquetes == null || paquetes.isEmpty()) {
                %>
                <div class="alert alert-secondary" role="alert">
                    No hay paquetes disponibles en este momento.
                </div>
                <%
                } else {
                    for (PaqueteBean paquete : paquetes) {
                        String imagenUrl = paquete.getImagen();
                        if (imagenUrl == null) {
                            imagenUrl = request.getContextPath() + "/nopicture.png";
                        }
                        String nombre = paquete.getNombre();
                        String descripcion = paquete.getDescripcion();
                        String enlace = request.getContextPath() + "/consultarpaquete?p=" + paquete.getNombre();
                %>
	                <div class="card mb-3">
					    <div class="row g-0">
					        <div class="col-md-4">
					            <img src="<%= imagenUrl %>" class="img-fluid rounded-start paquete" alt="Imagen de <%= nombre %>" />
					        </div>
					        <div class="col">
					            <div class="card-body">
					                <h5 class="card-title"><%= nombre %></h5>
					                <p class="card-text"><%= descripcion %></p>
					                <a href="<%= enlace %>" class="card-link">Leer más</a>
					            </div>
					        </div>
					    </div>
					</div>

                <%
                }
                }
                %>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</body>

</html>


