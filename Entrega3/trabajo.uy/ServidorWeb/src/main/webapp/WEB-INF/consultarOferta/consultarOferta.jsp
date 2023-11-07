<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>


<%
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
    String imagen = ofertaLaboral.getImagen();
    if (imagen == null) {
        imagen = request.getContextPath() + "/imagenNoFound.png";
    }
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
    boolean duenioOfertaLaboral = (boolean) request.getAttribute("duenio");
    boolean mostrarContenido = tipoUsuario == TipoUsuario.Empresa || tipoUsuario == TipoUsuario.Postulante;
    boolean estaFav = (boolean) request.getAttribute("estaFav");
    boolean vigente = (boolean) request.getAttribute("vigente");
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title><%= ofertaLaboral.getNombre() %></title>

    <style>
        .banner-container {
            background-image: url(<%= imagen %>);
        }
    </style>
    
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
                <h1 class="text-center text-light fw-bolder"><%= ofertaLaboral.getNombre() %></h1>
            </div>

            <div class="row">
                <div class="col">
                    <div id="detalleOferta">
                        <jsp:include page="./detallesOferta.jsp" />
                    </div>
                </div>

                <% if (mostrarContenido) { %>


                <div class="col-4 mt-2 container text-center" id="acciones">
                    <form action="consultarofertalaboral" method="post">
                        <input type="hidden" name="nombreOferta" value="<%= ofertaLaboral.getNombre() %>">
		            <% if (TipoUsuario.Postulante == tipoUsuario && !estaFav) { %>
			            <h4> Marcar favorito</h4>
						<button id="corazonDesm"  name="corazonDesm" type="submit" class="btn btn-light" aria-label="Like">
						  <span class="heart-icon" aria-hidden="true">&#10084;</span>
						</button>
					<%} else if (TipoUsuario.Postulante == tipoUsuario) {%>
						<h4> Desmarcar favorito</h4>
						<button id="corazonMarc" name="corazonMarc" type="submit" class="btn btn-light" aria-label="Like">
						  <span class="heart-icon.text-danger" aria-hidden="true">&#10084;</span>
						</button>
					<%}%>
                    </form>
                    <h4> Cantidad de favoritos:  </h4>
                    <h4 class="text-center fw-bolder"><%= request.getAttribute("cantFavs") %></h4>

                    <% if(tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral){ %>
                    <jsp:include page="./postulantes.jsp" />
                    <jsp:include page="./paquetes.jsp" />
                    <%
                    if (!vigente){
                    %>
                        <form action="<%=request.getContextPath()%>/finalizaroferta" method="POST">
                            <input type="hidden" value="<%=ofertaLaboral.getNombre()%>" name="oferta">
                            <button type="submit" class="btn btn-danger">
                                Finalizar Oferta Laboral
                            </button>
                        </form>

                    <%}
                    } else if (tipoUsuario == TipoUsuario.Postulante){%>
                    <jsp:include page="./postular.jsp" />
                    <%}%>

                </div>
                <% } %>
            </div>
        </div>
    </div>

</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjvP/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>

</html>
