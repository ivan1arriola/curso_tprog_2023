<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<% boolean yaSeCompro = (boolean) request.getAttribute("yaSeCompro"); %>

<% if (!yaSeCompro) { %>
    <div class="col-3">
        <a href="#" class="btn btn-primary" data-toggle="modal" data-target="#verificarCompraModal">
            <i class="fas fa-shopping-cart"></i> Comprar
        </a>
    </div>
<% } else { %>
    <div class="col-3">
        <button class="btn btn-secondary" disabled>
            <i class="fas fa-shopping-cart"></i> Ya se compró
        </button>
    </div>
<% } %>




<!-- Modal de verificación de compra -->
<div class="modal fade" id="verificarCompraModal" tabindex="-1" role="dialog" aria-labelledby="verificarCompraModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="verificarCompraModalLabel">Confirmar Compra</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Cerrar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                ¿Estás seguro de que deseas realizar la compra?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                <a href="realizarCompra.jsp" class="btn btn-primary">Confirmar Compra</a>
            </div>
        </div>
    </div>
</div>
