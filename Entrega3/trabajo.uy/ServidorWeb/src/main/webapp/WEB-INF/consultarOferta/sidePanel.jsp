<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>


<%
  OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
  TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
  boolean duenioOfertaLaboral = (boolean) request.getAttribute("duenio");
  boolean estaFav = (boolean) request.getAttribute("estaFav");
  boolean vigente = (boolean) request.getAttribute("vigente");
  boolean estaFinalizada = (boolean) request.getAttribute("estaFinalizada");
  int cantFavs = (int) request.getAttribute("cantFavs");
%>


<!-- Barra lateral derecha -->
<div class="text-center">
  <ul class="list-group list-group-flush">
  <li id="estadoOferta" class="list-group-item">
    <h4>
      <span class="badge rounded-pill <%=ofertaLaboral.getEstado().getCssClass()%>">
        <%=ofertaLaboral.getEstado()%>
      </span>
    </h4>

  </li>

      <% if (!vigente && !estaFinalizada) { %>
    <li id="estadoOferta" class="list-group-item">
      <h5>
        <span class="badge bg-danger">Oferta No Vigente</span>

      </h5>

    </li>


      <% } %>

  <%
    if (!vigente && !estaFinalizada) {
  %>
  <li class="list-group-item"  id="finalizar-oferta">
    <form action="<%=request.getContextPath()%>/finalizaroferta" method="POST">
      <input type="hidden" value="<%=ofertaLaboral.getNombre()%>" name="oferta">
      <button type="submit" class="btn btn-danger">Finalizar Oferta Laboral</button>
    </form>
  </li>
  <%
    }
  %>

      <%
        if (TipoUsuario.Postulante == tipoUsuario ) {
      %>
  <li class="list-group-item" id="accion-favorito">
    <form action="consultarofertalaboral" method="post">
      <input type="hidden" name="nombreOferta" value="<%= ofertaLaboral.getNombre() %>">
      <%
        if (!estaFav) {
      %>
      <h5> Marcar favorito</h5>
      <button id="corazonDesm" name="corazonDesm" type="submit" class="btn" aria-label="Like">
        <span class="heart-icon" aria-hidden="true">&#10084;</span>
      </button>
      <%
      } else {
      %>
      <h5> Desmarcar favorito</h5>
      <button id="corazonMarc" name="corazonMarc" type="submit" class="btn" aria-label="Like">
        <span class="heart-icon text-danger" aria-hidden="true">&#10084;</span>
      </button>
      <%
        }
      %>
    </form>
  </li>
      <%
        }
      %>

  <li class="list-group-item" id="cantidad-favoritos">
    <h4>Cantidad de favoritos: <span class="badge bg-success"><%= cantFavs %></span></h4>
  </li>

  <%
    if (tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral) {
  %>
  <li class="list-group-item" id="postulantes">
    <jsp:include page="./postulantes.jsp" />
  </li>
  <li class="list-group-item" id="paquete">
    <jsp:include page="./paquetes.jsp" />
  </li>
  <%
  } else if (tipoUsuario == TipoUsuario.Postulante && !estaFinalizada && vigente) {
  %>
  <li class="list-group-item" id="postular">
    <jsp:include page="./postular.jsp" />
  </li>
  <%
    }
  %>
</div>
