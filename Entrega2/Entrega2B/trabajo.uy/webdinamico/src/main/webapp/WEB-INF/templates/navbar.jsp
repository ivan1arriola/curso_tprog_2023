<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Links de navegacion -->
<ul class="nav nav-pills nav-fill my-1">
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/ofertaslaborales.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/ofertaslaborales">Ofertas Laborales</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/usuarios.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/usuarios">Usuarios</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/tipospublicacion.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/tipospublicacion">Tipo de Publicacion de Oferta Laboral</a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/paquetes.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/paquetes">Paquetes</a>
    </li>
</ul>

<hr class="mt-1 mb-4"/>