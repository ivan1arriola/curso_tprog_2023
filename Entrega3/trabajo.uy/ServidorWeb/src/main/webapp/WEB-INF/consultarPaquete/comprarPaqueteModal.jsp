<%@ page import="javabeans.PaqueteBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  PaqueteBean paquete = (PaqueteBean) request.getAttribute("paquete");
%>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#verificarCompraModal">
  Comprar Paquete
</button>

<!-- Modal -->
<div class="modal fade" id="verificarCompraModal" tabindex="-1" aria-labelledby="verificarCompraModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="verificarCompraModalLabel">Confirmar Compra Paquete</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>¿Está seguro de que desea comprar este paquete?</p>

        <!-- Formulario para confirmar la compra -->
        <form id="compraForm" action="/comprarpaquete" method="post">
          <input name="paquete" value="<%=paquete.getNombre()%>">
          <button type="submit" class="btn btn-primary">Comprar</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        </form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>