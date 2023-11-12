<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String nombreOferta = (String) request.getAttribute("nombreOferta");
  boolean hayOrdenDefinido = (boolean) request.getAttribute("hayOrdenDefinido");
%>



<!-- Modal -->
<div class="modal fade" id="finalizarOfertaModal" tabindex="-1" aria-labelledby="finalizarOfertaModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="finalizarOfertaModalLabel">Confirmar Finalizar Oferta</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <% if (hayOrdenDefinido) { %>
        <h2 class="card-title text-center mt-2">Lista Actual de Postulantes</h2>

        <table class="table table-bordered">
          <thead>
          <tr>
            <th>Lugar</th>
            <th>Nombre del Postulante</th>
          </tr>
          </thead>
          <tbody>
          <%
            int lugar = 0;
            for (String postulante : (List<String>) request.getAttribute("postulantesOrden")) {
              lugar++;
          %>
          <tr>
            <td>
              <%= lugar %>
            </td>

            <td>
              <%= postulante %>
            </td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
        <% } else { %>
        <div class="container mt-3">
          <div class="alert alert-warning" role="alert">
            <strong>Atención:</strong> No se ha definido aún un orden de postulantes. Si finalizas la
            oferta, se descartarán todas las postulaciones.
          </div>
        </div>
        <% } %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>

        <form action="<%=request.getContextPath()%>/finalizaroferta" method="POST">
          <input type="hidden" value="<%=nombreOferta%>" name="oferta">
          <button type="submit" class="btn btn-danger">Finalizar Oferta Laboral</button>
        </form>

        <%if(hayOrdenDefinido){%>
        <form action="<%=request.getContextPath()%>/descartarorden" method="POST">
          <input type="hidden" value="<%=nombreOferta%>" name="oferta">
          <button type="submit" class="btn btn-danger">Descartar Orden de Postulantes</button>
        </form>

        <% } %>


      </div>
    </div>
  </div>
</div>
