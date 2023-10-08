<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.java.logica.Datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.Datatypes.DTPostulante" %>
<%@ page import="main.java.logica.Datatypes.DTUsuario" %>

<%
DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
%>

<div class="container">


    <!-- Formulario de Modificación -->
    <form class="form-signin needs-validation" novalidate action="ModificarUsuario" method="post">
        <div class="row">
            <div class="col-md-8">
            
                <div class="form-group">
                    <label for="nickname">Nickname:</label>
                    <input type="text" class="form-control" id="nickname" name="nickname" disabled readonly
                        value="<%= usuario.getNickname() %>" required />
                </div>
                <div class="form-group">
                    <label for="nombre">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" disabled
                        value="<%= usuario.getNombre() %>" required />
                </div>
                <div class="form-group">
                    <label for="apellido">Apellido:</label>
                    <input type="text" class="form-control" id="apellido" name="apellido" disabled
                        value="<%= usuario.getApellido() %>" required />
                </div>
                <div class="form-group">
                    <label for="correo">Correo electrónico:</label>
                    <input type="email" class="form-control" id="correo" name="correo" disabled
                        value="<%= usuario.getCorreo_electronico() %>" readonly required />
                </div>

                <!-- Sección de Contraseña -->
                <section id="PasswordSection">
                    <div class="form-group">
                        <label for="password-input">Contraseña:</label>
                        <input type="password" class="form-control" id="password-input" name="password" placeholder=" " required>
                    </div>
                    <div class="form-group">
                        <label for="confirm-password-input">Confirmar Contraseña:</label>
                        <input type="password" class="form-control" id="confirm-password-input" name="confirm-password" placeholder=" ">
                    </div>
                </section>

                <!-- Importar JSP de acuerdo al tipo de usuario -->
                <% if (usuario instanceof DTPostulante) { %>
                <jsp:include page="./camposPostulante.jsp" />
                <% } else if (usuario instanceof DTEmpresa) { %>
                <jsp:include page="./camposEmpresa.jsp" />
                <% } %>

                <div class="form-group mt-3">
                    <button type="submit" class="btn btn-success" id="aceptarBtn">
                        Aceptar
                    </button>
                    <a href="<%= request.getContextPath() %>/consultarusuario?u=<%=usuario.getNickname() %>" class="btn btn-danger" id="cancelarBtn">
                        Cancelar
                    </a>
                </div>
            </div>
        </div>
    </form>

    <!-- Botón para Modificar Datos -->
    <div class="form-group mt-3">
        <button type="button" class="btn btn-primary btn-sm" id="modificarBtn">
            Modificar Datos de Usuario
        </button>
    </div>
</div>

<script src="<%= request.getContextPath() %>/js/modificarDatos.js"></script>
