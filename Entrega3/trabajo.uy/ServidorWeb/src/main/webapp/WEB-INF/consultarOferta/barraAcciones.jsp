<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="java.util.List" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="enumeration.EstadoOfertaLaboral" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
  EstadoOfertaLaboral estado = ofertaLaboral.getEstado();
  boolean ocultarPostulante = estado == EstadoOfertaLaboral.Ingresada || estado.equals(EstadoOfertaLaboral.Finalizada);
  boolean vigente = (boolean) request.getAttribute("vigente");
  boolean estaFinalizada = (boolean) request.getAttribute("estaFinalizada");
  TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
  boolean duenioOfertaLaboral = (boolean) request.getAttribute("duenio");
  int cantFavs = (int) request.getAttribute("cantFavs");
  boolean estaFav = (boolean) request.getAttribute("estaFav");
%>

<div class="container-fluid">

  <div class="d-flex flex-column justify-content-between  pt-3 pb-2 mb-3 border-bottom">

    <% if (!vigente && !estaFinalizada) { %>

      <h5 class="mb-2">
        <span class="badge bg-danger">Oferta No Vigente</span>
      </h5>

    <% } %>

    <% if (!ocultarPostulante && !vigente && !estaFinalizada && tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral) { %>
    <a href="<%=request.getContextPath()%>/ordenarpostulantes?oferta=<%=ofertaLaboral.getNombre()%>" class="btn btn-primary">Definir Orden de Selección</a>

    <!-- Item de menú para finalizar oferta -->
    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#finalizarOfertaModal">
      Finalizar Oferta Laboral
    </button>
    <% } %>

    <!-- Puedes agregar más acciones aquí como elementos de menú -->

    <% if (tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral) { %>

      <jsp:include page="./postulantes.jsp" />

    <% } else if (tipoUsuario == TipoUsuario.Postulante && !estaFinalizada && vigente) { %>

      <jsp:include page="./postular.jsp" />

    <% } %>
  </div>

  <ul class="list-group list-group-flush">
    <li id="estadoOferta" class="list-group-item">
      <h4></h4>
    </li>



    <% if (!ocultarPostulante && TipoUsuario.Postulante == tipoUsuario) { %>
    <li class="list-group-item" id="accion-favorito">
      <form action="consultarofertalaboral" method="post">
        <input type="hidden" name="nombreOferta" value="<%= ofertaLaboral.getNombre() %>">
        <% if (!estaFav) { %>
        <h5> Marcar favorito</h5>
        <button id="corazonDesm" name="corazonDesm" type="submit" class="btn" aria-label="Like">
          <span class="heart-icon" aria-hidden="true">&#10084;</span>
        </button>
        <% } else { %>
        <h5> Desmarcar favorito</h5>
        <button id="corazonMarc" name="corazonMarc" type="submit" class="btn" aria-label="Like">
          <span class="heart-icon text-danger" aria-hidden="true">&#10084;</span>
        </button>
        <% } %>
      </form>
    </li>
    <% } %>


    <%if (!ocultarPostulante){ %>
    <li class="list-group-item" id="cantidad-favoritos">
      <h4>Cantidad de favoritos: <span class="badge bg-success"><%= cantFavs %></span></h4>
    </li>
    <%} %>

    <% if (tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral) { %>
    <li class="list-group-item" id="paquete">
      <jsp:include page="./paquetes.jsp" />
    </li>
    <% } %>
  </ul>

</div>

<jsp:include page="./finalizarOfertaConfirmacionModal.jsp" />
