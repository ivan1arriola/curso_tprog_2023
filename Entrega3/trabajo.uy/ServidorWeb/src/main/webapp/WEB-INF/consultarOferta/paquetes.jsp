<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javabeans.PaqueteBean" %>




<%
    PaqueteBean paquete = (PaqueteBean) request.getAttribute("paquete");
    if (paquete != null){
        String imagenPaquete = paquete.getImagen();

%>

<div class="mb-2">
    <div class="row">
        <div class="col">
            <img src="<%= imagenPaquete %>" alt="Imagen Paquete" class="img-fluid">
        </div>
    </div>
    <div class="row">
        <div class="col text-center">
            <h5> Paquete:
                <a href="<%= request.getContextPath() %>/consultarpaquete?p=<%= paquete.getNombre() %>" class="card-link">
                    <%= paquete.getNombre() %>
                </a>
            </h5>
        </div>
    </div>
</div>


<%} else {%>
<div class="mb-2">

<div class="row">
    <h4>Paquete</h4>
</div>
<div class="alert alert-warning">
    No hay paquete asociado a esta oferta laboral.
</div>


</div>

<%}%>