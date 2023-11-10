<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %>


<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Usuarios</title>
</head>

<body>
    <header>
        <jsp:include page="/WEB-INF/templates/header.jsp" />
    </header>

<main class="container-fluid d-flex">
        <div class="container col-md-3">
            <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
        </div>

        <div class="container col-md-9">
            <jsp:include page="/WEB-INF/templates/navbar.jsp" />

            <%
            	LinkedHashMap<OfertaLaboralBean, Integer> ofertasLab = (LinkedHashMap<OfertaLaboralBean, Integer>) request.getAttribute("ofertasLabOrdenadas");
                Integer cantOfer = (Integer) request.getAttribute("cantOfertasLaborales");
                LinkedHashMap<UsuarioBean, LocalDate> empresas = (LinkedHashMap<UsuarioBean, LocalDate>) request.getAttribute("empresasOrdenadas");
                Integer cantEmp = (Integer) request.getAttribute("cantEmpresas");
            %>
			<input type="hidden" name="input" style="display: none;" placeholder="nick" value="<%= (String) request.getAttribute("input") %>">
            <!-- Sección de ofertas laborales (primera columna) -->
            <div class="row">
                <div class="col-md-6">
                	<div class="sin-bordes text-center">
        				<h2>Ofertas Laborales</h2>
                    	<!-- Encuadre de texto de cantidad de resultados y menú de selección de orden -->
				        <div class="row mb-3" style="border: 1px solid #ccc; padding: 10px;">
				            <div class="col-md-3 d-flex align-items-center">
				                <!-- Texto de cantidad de resultados con tamaño más pequeño -->
				                <p id="resultados-ofertas" style="font-size: 14px;"><%=cantOfer.toString() + " resultados"%></p>
				            </div>
				            <div class="col-md-9 d-flex align-items-center">
				                <!-- Texto "Ordenar por" con tamaño más pequeño -->
				                <label for="orden-ofertas" style="margin-right: 10px; font-size: 14px;">Ordenar por:</label>
				                <!-- Menú de selección de orden -->
							    <form id="orden-ofertas-form" action="Home" method="POST">
								    <select id="orden-ofertas" name="ordenOfertaLab" class="form-select" onchange="submitForm()">
								        <option value="defectoOfertaLab">Por defecto</option>
								        <option value="alfabeticoOfertaLab">Alfabéticamente (A-Z a-z)</option>
								    </select>
								</form>
							 
								<script>
								    document.getElementById('orden-ofertas').addEventListener('change', function () {
								        if (isSelectionChanged1()) {
								            submitForm1();
								        }
								    });
								
								    function loadSelection1() {
								        var ordenGuardado1 = localStorage.getItem('ordenSeleccionado1');
								        if (!ordenGuardado1) {
								            ordenGuardado1 = "defectoOfertaLab";
								            localStorage.setItem('ordenSeleccionado1', ordenGuardado1);
								        }
								        document.getElementById('orden-ofertas').value = ordenGuardado1;
								    }
								
								    function isSelectionChanged1() {
								        var ordenSeleccionado1 = document.getElementById('orden-ofertas').value;
								        var ordenGuardado1 = localStorage.getItem('ordenSeleccionado1');
								        return ordenSeleccionado1 !== ordenGuardado1;
								    }
								
								    function submitForm1() {
								        var ordenSeleccionado1 = document.getElementById('orden-ofertas').value;
								        localStorage.setItem('ordenSeleccionado1', ordenSeleccionado1);
								        document.getElementById('orden-ofertas-form').submit();
								    }
								
								    // Llama a loadSelection después de que la página se ha cargado completamente
								    window.addEventListener('load', function () {
								        loadSelection1();
								    });
								</script>

				            </div>
				        </div>
                        <div class="row">
                            <% if (ofertasLab == null || ofertasLab.isEmpty()) { %>
                                <div class="alert alert-secondary" role="alert">
                                    <% if (ofertasLab == null) { %>
                                        No se encontraron resultados para la búsqueda.
                                    <% } else if (ofertasLab.isEmpty()) { %>
                                        No hay ofertas laborales disponibles en este momento.
                                    <% } %>
                                </div>
                            <% } else {
                                for (Map.Entry<OfertaLaboralBean, Integer> entry : ofertasLab.entrySet()) {
                                    String imagenUrl = entry.getKey().getImagen();

                                    if (imagenUrl == null){
                                        imagenUrl = request.getContextPath() + "/imagenNoFound.png";
                                    }

                                    String nombre = entry.getKey().getNombre();
                                    String empresa = entry.getKey().getNicknameEmpresa();
                                    String descripcion = entry.getKey().getDescripcion();
                                    String enlace = request.getContextPath() + "/consultarofertalaboral?o=" + entry.getKey().getNombre();
                            %>
                            <div class="card mb-3">
                                <div class="row g-0">
                                    <div class="col-md-4">
                                        <img src="<%=imagenUrl%>" class="img-fluid rounded-start" alt="<%=nombre%>" />
                                    </div>
                                    <div class="col">
                                        <div class="card-body">
                                            <h5 class="card-title"><%=nombre%></h5>
                                            <h6 class="card-subtitle mb-2 text-muted"><%=empresa%></h6>
                                            <p class="card-text"><%=descripcion%></p>
                                            <a href="<%=enlace%>" class="card-link">Leer más</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% } } %>
                        </div>
                    </div>
                </div>

                <!-- Sección de empresas (segunda columna) -->
                <div class="col-md-6">
                    <div class="sin-bordes text-center">
        				<h2>Empresas</h2>
				        <!-- Encuadre de texto de cantidad de resultados y menú de selección de orden -->
				        <div class="row mb-3" style="border: 1px solid #ccc; padding: 10px;">
				            <div class="col-md-3 d-flex align-items-center">
				                <!-- Texto de cantidad de resultados con tamaño más pequeño -->
				                <p id="resultados-ofertas" style="font-size: 14px;"><%=cantEmp.toString() + " resultados"%></p>
				            </div>
				            <div class="col-md-9 d-flex align-items-center">
				                <!-- Texto "Ordenar por" con tamaño más pequeño -->
				                <label for="orden-ofertas" style="margin-right: 10px; font-size: 14px;">Ordenar por:</label>
				                <!-- Menú de selección de orden -->
								<form id="orden-empresas-form" action="Home" method="POST">
								    <select id="orden-empresas" name="ordenEmp" class="form-select" onchange="submitForm()">
								        <option value="defectoEmp">Por defecto</option>
								        <option value="alfabeticoEmp">Alfabéticamente (A-Z a-z)</option>
								    </select>
								</form>
								
								<script>
								    document.getElementById('orden-empresas').addEventListener('change', function () {
								        if (isSelectionChanged()) {
								            submitForm();
								        }
								    });
								
								    function loadSelection() {
								        var ordenGuardado = localStorage.getItem('ordenSeleccionado');
								        if (!ordenGuardado) {
								            ordenGuardado = "defectoEmp";
								            localStorage.setItem('ordenSeleccionado', ordenGuardado);
								        }
								        document.getElementById('orden-empresas').value = ordenGuardado;
								    }
								
								    function isSelectionChanged() {
								        var ordenSeleccionado = document.getElementById('orden-empresas').value;
								        var ordenGuardado = localStorage.getItem('ordenSeleccionado');
								        return ordenSeleccionado !== ordenGuardado;
								    }
								
								    function submitForm() {
								        var ordenSeleccionado = document.getElementById('orden-empresas').value;
								        localStorage.setItem('ordenSeleccionado', ordenSeleccionado);
								        document.getElementById('orden-empresas-form').submit();
								    }
								
								    // Llama a loadSelection después de que la página se ha cargado completamente
								    window.addEventListener('load', function () {
								        loadSelection();
								    });
								</script>



				        	</div>
				        </div>
                        <div class="row">
                            <% if (empresas == null || empresas.isEmpty()) { %>
                                <div class="alert alert-secondary" role="alert">
                                    No hay usuarios dados de alta en este momento.
                                </div>
                            <% } else {
                                for (Map.Entry<UsuarioBean, LocalDate> entry : empresas.entrySet()) {
                                    String imagen = entry.getKey().getImagen();

                                    if (imagen == null) {
                                        imagen = request.getContextPath() + "/nopicture.png";
                                    }
                            %>
                            <div class="col-12">
                                <div class="card mb-3 bg-light">
                                    <img src="<%= imagen %>" class="card-img-top usuario" alt="Perfil de <%= entry.getKey().getNickname() %>" width="200" height="200" />
                                    <div class="card-body text-center">
                                        <h5 class="card-title">
                                            <a href="<%= request.getContextPath() %>/consultarusuario?u=<%= entry.getKey().getNickname() %>"><%= entry.getKey().getNickname() %></a>
                                        </h5>
                                        <p class="card-text mb-1">
                                            <%= entry.getKey().getNombre() + " " + entry.getKey().getApellido() %>
                                        </p>
                                        <p class="card-text mb-1 fw-bolder">
                                            <%= entry.getKey().getTipo() %>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <% } } %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</body>

</html>
