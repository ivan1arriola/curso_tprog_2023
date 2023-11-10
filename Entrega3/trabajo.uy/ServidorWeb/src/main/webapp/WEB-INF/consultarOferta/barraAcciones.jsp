<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="java.util.List" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
  boolean vigente = (boolean) request.getAttribute("vigente");
  boolean estaFinalizada = (boolean) request.getAttribute("estaFinalizada");
  TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
  boolean duenioOfertaLaboral = (boolean) request.getAttribute("duenio");
%>

<div class="nav nav-pills flex-column">



    <!-- Botón desplegable para otras acciones -->
    <div class="btn-group" role="group">
      <button id="accionesMenu" type="button" class="btn btn-secondary btn-lg dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        Acciones
      </button>
      <div class="dropdown-menu" aria-labelledby="accionesMenu">

        <!-- Botón de definir orden -->

        <%
          if (!vigente && !estaFinalizada) {
        %>

        <a href="<%=request.getContextPath()%>/ordenarpostulantes?oferta=<%=ofertaLaboral.getNombre()%>" class="btn btn-primary">Definir Orden de Selección</a>


        <!-- Item de menú para finalizar oferta -->
        <button type="button" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#finalizarOfertaModal">
          Finalizar Oferta Laboral
        </button>


        <%
          }
        %>



        <!-- Puedes agregar más acciones aquí como elementos de menú -->

        <%
          if (tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral) {
        %>
        <li class="list-group-item" id="postulantes">
          <jsp:include page="./postulantes.jsp" />
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
    </div>
</div>

<jsp:include page="./finalizarOfertaConfirmacionModal.jsp" />
