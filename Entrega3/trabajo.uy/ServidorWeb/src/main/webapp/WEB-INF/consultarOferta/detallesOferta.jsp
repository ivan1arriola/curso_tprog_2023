<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.Set" %>    
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="javabeans.UsuarioBean" %>

<%
    // Obtener el objeto de usuario desde los atributos de la solicitud
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
	Set<String> keywords = ofertaLaboral.getKeywords();
	String contextPath = request.getContextPath();
%>

    <div class="row align-items-center mt-2">
        <table class="table">
            <tbody>
				<button class="btn btn-primary mt-2">
				    <span class="float-right"> <!-- Clase de Bootstrap para alinear a la derecha -->
				        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-heart" viewBox="0 0 16 16"> <!-- Icono de corazón de Bootstrap -->
				            <path d="M8 14s6-3.5 6-7a3.5 3.5 0 0 0-7 0C8 10.5 8 14 8 14z"/>
				            <path fill-rule="evenodd" d="M3.906 2.293a.5.5 0 0 1 .708 0L8 6.793l3.387-4.5a.5.5 0 0 1 .763.647l-4 5a.5.5 0 0 1-.75-.001l-4-5a.5.5 0 0 1 .003-.647z"/>
				        </svg>
				    </span>
				</button>

                <tr>
                    <th>Nombre:</th>
                    <td><%= ofertaLaboral.getNombre() %></td>
                </tr>
                <tr>
                    <th>Empresa:</th>
                    <td><%= ofertaLaboral.getNicknameEmpresa() %></td>
                </tr>
                <tr>
                    <th>Descripción:</th>
                    <td><%= ofertaLaboral.getDescripcion() %></td>
                </tr>
                <tr>
                    <th>Remuneración:</th>
                    <td><%= ofertaLaboral.getRemuneracionString() %> pesos uruguayos</td>
                </tr>
                <tr>
                    <th>Horario:</th>
                    <td><%= ofertaLaboral.getHorario()%></td>
                </tr>
                <tr>
                    <th>Departamento:</th>
                    <td><%= ofertaLaboral.getDepartamento() %></td>
                </tr>
                <tr>
                    <th>Ciudad:</th>
                    <td><%= ofertaLaboral.getCiudad() %></td>
                </tr>
                <tr>
                    <th>Fecha de Alta:</th>
                    <td><%= ofertaLaboral.getFechaDeAltaString() %></td>
                </tr>
                <tr>
                    <th>Costo:</th>
                    <td><%= ofertaLaboral.getCostoString() %>  pesos uruguayos</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div>
        <p class="fw-bold">
            Keywords:
        </p>
        <ul class="list-group list-group-horizontal">
            <%  if (keywords.isEmpty()) { %>
                <div class="alert alert-warning" role="alert">
                    No hay palabras clave disponibles
                </div>
            <% } else {
               for (String keyword : keywords) { %>
                <a href="<%= contextPath %>/ofertaslaborales?key=<%= keyword %>" class="list-group-item">
                    <%= keyword %>
                </a>
            <%   }
               } %>
        </ul>
    </div>
