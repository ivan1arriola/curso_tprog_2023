<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="enumeration.TipoUsuario" %>



<!DOCTYPE html>
<html>

<%
UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
boolean consultaSuPerfil = (boolean) request.getAttribute("consultaSuPerfil");
boolean seguir = (boolean) request.getAttribute("seguir");
TipoUsuario tu = (TipoUsuario) request.getAttribute("tipoU");

boolean ofertasTabAbierto = Boolean.parseBoolean(request.getParameter("ofertas"));
%>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Información de <%= usuario.getNombre() %></title>
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

            <div class="row mb-2">
                    <div class="col-3">
                        <%
                        // Mostrar la imagen del usuario si está disponible
                        String imagenUsuario = usuario.getImagen();
                        if (imagenUsuario != null) {
                        %>
                            <img id="imagenUsuario" src="<%= imagenUsuario %>"
                                alt="Imagen de Usuario" class="img-fluid w-100 perfil rounded" />
                        <%
                        } else {
                        %>
                            <img id="imagenUsuario" src="<%= request.getContextPath() + "/nopicture.png" %>"
                                alt="Imagen de Usuario por Defecto"
                                class="img-fluid mb-3 rounded-circle img-thumbnail perfil" />
                        <%
                        }
                        %>
                    </div>
                    <div class="col-9 text-center">

                        <div class="row">

                            <div class="col">
                                <h1 class='fw-bold'><%= usuario.getNickname() %></h1>
                                <h2><%= usuario.getNombre() + ' ' + usuario.getApellido() %></h2>
                                <h2><%= usuario.getCorreoElectronico() %> </h2>
                            </div>

                            <% if (!consultaSuPerfil && (tu == TipoUsuario.Postulante || tu == TipoUsuario.Empresa)) {
                                if (seguir) { %>

                            <div class="col-4">
                                <form action="consultarusuario" method="post">
                                    <input type="hidden" name="nick" placeholder="nick" value="<%= (String) request.getAttribute("usuarioConsultado") %>">

                                    <button class="btn btn-success mt-2" name="btnSeguir" type="submit">
                                        Seguir
                                    </button>
                                </form>
                            </div>
                                    <% } else { %>
                            <div class="col-4">
                                <form action="consultarusuario" method="post">
                                    <input type="hidden" name="nick"  placeholder="nick" value="<%= (String) request.getAttribute("usuarioConsultado") %>">

                                    <button class="btn btn-danger mt-2" name="btnDejarDeSeguir" type="submit">
                                        Dejar de seguir
                                    </button>

                                </form>
                            </div>
                            <% }
                            } %>


                        </div>
                    </div>


                </div>


            <div class="row d-flex align-items-start">


                <div class="col-3">
                    <ul class="nav nav-pills flex-column" id="myTab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link <%= !ofertasTabAbierto ? "active" : "" %>" data-bs-toggle="tab" data-bs-target="#perfil-panel" type="button"
                            role="tab" aria-controls="perfil-panel">Perfil</a>
                    </li>
                    <%if (consultaSuPerfil && usuario.getTipo() == TipoUsuario.Empresa) { %>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link <%= ofertasTabAbierto ? "active" : "" %>" data-bs-toggle="tab" data-bs-target="#ofertas-panel" type="button" role="tab"
                                aria-controls="ofertas-panel">Ofertas Laborales</a>
                        </li>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" data-bs-target="#paquete-panel" type="button" role="tab"
                                aria-controls="paquete-panel">Paquete</a>
                        </li>
                    <%} %>

                    <%if (!consultaSuPerfil && usuario.getTipo() == TipoUsuario.Empresa) { %>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" data-bs-target="#ofertas-panel" type="button" role="tab"
                                aria-controls="ofertas-panel">Ofertas Laborales</a>
                        </li>
                    <%} %>
                    <%if (consultaSuPerfil && usuario.getTipo() == TipoUsuario.Postulante) { %>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" data-bs-target="#postulaciones-panel" type="button" role="tab"
                                aria-controls="postulaciones-panel">Postulaciones</a>
                        </li>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" data-bs-target="#favoritos-panel" type="button" role="tab"
                                aria-controls="favoritos-panel">Favoritos</a>
                        </li>
                    <%} %>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" data-bs-target="#seguidos-panel" type="button" role="tab"
                                aria-controls="seguidos-panel">Seguidos</a>
                        </li>
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" data-bs-toggle="tab" data-bs-target="#seguidores-panel" type="button" role="tab"
                                aria-controls="seguidores-panel">Seguidores</a>
                        </li>
                </ul>
                </div>
                <div class="col-9">
                    <div class="tab-content" id="myTabsContent">
                        <!-- Contenido de las pestañas -->
                        <% if (consultaSuPerfil) { %>
                            <% if (usuario.getTipo() == TipoUsuario.Empresa) { %>
                                <jsp:include page="./tabPerfil.jsp" />
                                <jsp:include page="./tabOfertasLaborales.jsp" />
                                <jsp:include page="./tabPaquetes.jsp" />
                            <% } else if (usuario.getTipo() == TipoUsuario.Postulante) { %>
                                <jsp:include page="./tabPerfil.jsp" />
                                <jsp:include page="./tabPostulaciones.jsp" />
                                <jsp:include page="./tabFavoritos.jsp" />
                            <% } else { %>
                                <jsp:include page="./tabPerfil.jsp" />
                            <% } %>
                        <% } else { %>
                            <jsp:include page="./tabPerfil.jsp" />
                            <% if (usuario.getTipo() == TipoUsuario.Empresa) { %>
                                <jsp:include page="./tabOfertasLaborales.jsp" />
                                <jsp:include page="./tabPaquetes.jsp" />
                            <% } %>
                        <% } %>
                        <jsp:include page="./tabSeguidos.jsp" />
                        <jsp:include page="./tabSeguidores.jsp" />
                    </div>
                </div>
            </div>
		    

        </div>
    </main>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            // Obtén el parámetro 'ofertas' de la URL
            const urlParams = new URLSearchParams(window.location.search);
            const ofertasParam = urlParams.get('ofertas');

            // Si el parámetro 'ofertas' es 'true', activa el tab correspondiente
            if (ofertasParam && ofertasParam.toLowerCase() === 'true') {
                $('#myTabs a[href="#ofertas-panel"]').tab('show');
            } else {
                // Puedes cambiar 'tabPerfil' por el ID del tab que quieres que esté activo por defecto
                $('#myTabs a[href="#tabPerfil"]').tab('show');
            }
        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>

</html>
