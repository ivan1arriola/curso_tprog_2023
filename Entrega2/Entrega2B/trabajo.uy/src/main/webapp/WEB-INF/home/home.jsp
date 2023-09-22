<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <title>trabajo.uy</title>
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
                <!-- Tabs de navegación -->
                <ul class="nav nav-tabs" id="myTabs" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" id="ofertas-laborales-tab" data-bs-toggle="tab"
                            href="#ofertas-laborales-panel" role="tab" aria-controls="ofertas-laborales-panel"
                            aria-selected="true">Ofertas Laborales
                        </a>
                    </li>

                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="usuarios-tab" data-bs-toggle="tab" href="#usuarios-panel" role="tab"
                            aria-controls="usuarios-panel" aria-selected="false">Usuarios</a>
                    </li>

                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="tipoPublicacion-tab" data-bs-toggle="tab" href="#tipoPublicacion-panel"
                            role="tab" aria-controls="tipoPublicacion-panel" aria-selected="false">Tipo de Publicacion
                            de Oferta Laboral</a>
                    </li>

                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="paquetes-tab" data-bs-toggle="tab" href="#paquetes-panel" role="tab"
                            aria-controls="paquetes-panel" aria-selected="false">Paquetes</a>
                    </li>
                </ul>

                <!-- Contenido de las pestañas -->
                <div class="tab-content" id="myTabContent">

                    <!--- Contenido de la pestaña de ofertas laborales -->
                    <div class="tab-pane fade show active" id="ofertas-laborales-panel" role="tabpanel"
                        aria-labelledby="ofertas-laborales-tab">

                        <jsp:include page="/WEB-INF/home/listarOfertasLaborales.jsp" />
                    
                    </div>

                    <!--- Contenido de la pestaña de usuarios -->
                    <div class="tab-pane fade" id="usuarios-panel" role="tabpanel" aria-labelledby="usuarios-tab">

                        <jsp:include page="/WEB-INF/home/listarUsuarios.jsp" />

                    </div>

                    <!--- Contenido de la pestaña de Tipo de publicacion -->
                    <div class="tab-pane fade" id="tipoPublicacion-panel" role="tabpanel"
                        aria-labelledby="tipoPublicacion-tab">

                        <jsp:include page="/WEB-INF/home/listarTipoPublicacion.jsp" />

                    </div>

                    <!-- Contenido de la pestaña de Paquete -->
                    <div class="tab-pane fade" id="paquetes-panel" role="tabpanel" aria-labelledby="paquetes-tab">

                        <jsp:include page="/WEB-INF/home/listarPaquetes.jsp" />

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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
            integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
            crossorigin="anonymous"></script>

    </body>

    </html>