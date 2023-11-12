<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Links de navegacion -->
<ul class="nav nav-pills nav-fill my-1">
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/ofertaslaborales.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/ofertaslaborales">
            Ofertas Laborales <i class="bi bi-wallet-fill"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/usuarios.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/usuarios">
            Usuarios <i class="bi bi-people-fill"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/tipospublicacion.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/tipospublicacion">
            Tipo de Publicacion de Oferta Laboral <i class="bi bi-card-heading"></i>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link <%= request.getRequestURI().endsWith("/paquetes.jsp") ? "active" : "" %>" href="<%= request.getContextPath() %>/paquetes">
            Paquetes <i class="bi bi-box-fill"></i>
        </a>
    </li>
</ul>

<hr class="mt-1 mb-4"/>