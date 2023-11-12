<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.Set" %>    
<%@ page import="javabeans.OfertaLaboralBean" %>

<%
    // Obtener el objeto de usuario desde los atributos de la solicitud
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
	Set<String> keywords = ofertaLaboral.getKeywords();
	String contextPath = request.getContextPath();
    boolean estaFinalizada = (boolean) request.getAttribute("estaFinalizada");
    boolean duenioOfertaLaboral = (boolean) request.getAttribute("duenio");
%>

    <div class="row align-items-center mt-2">
        <table class="table">
            <tbody>
                <%if (duenioOfertaLaboral){ %>
                <tr>
                    <th>
                        Estado de Oferta:
                    </th>
                    <td>
                        <span class="badge rounded-pill <%=ofertaLaboral.getEstado().getCssClass()%>">
                            <%=ofertaLaboral.getEstado()%>
                          </span>
                    </td>
                </tr>
                <% } %>
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
