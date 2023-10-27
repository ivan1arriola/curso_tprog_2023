<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="javabeans.PaqueteBean" %>

<% OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
	PaqueteBean paquete = ofertaLaboral.getPaquete();
	if (paquete !=null){


%>


<div>
    <div class="mt-4" id="paquete">
        <h1 class="text-center">Paquete</h1>
    </div>

    <div class="container">
        <div class="d-flex align-items-center mt-6">
            <img src="https://shorturl.at/ceCD2" alt="Imagen 2" class="img-fluid" width="110" height="110">
            <a href="<%= request.getContextPath() %>/consultarpaquete?p=<%= paquete.getNombre() %>" target="_blank" class="ms-4">
                <%= paquete.getNombre() %>
            </a>
        </div>
    </div>
</div>

<%}%>