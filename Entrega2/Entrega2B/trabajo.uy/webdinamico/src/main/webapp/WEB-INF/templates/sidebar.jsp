<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div class="card mb-4 keywords">
    <div class="card-header">
        <h4 class="card-title">Keywords</h4>
    </div>
    <div class="card-body">
        <ul class="list-group">
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=TiempoCompleto"
                class="list-group-item list-group-item-action border-0 py-1">Tiempo completo</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=MedioTiempo"
                class="list-group-item list-group-item-action border-0 py-1">Medio Tiempo</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Remoto"
                class="list-group-item list-group-item-action border-0 py-1">Remoto</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Freelance"
                class="list-group-item list-group-item-action border-0 py-1">Freelance</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Temporal"
                class="list-group-item list-group-item-action border-0 py-1">Temporal</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Permanente"
                class="list-group-item list-group-item-action border-0 py-1">Permanente</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Computacion"
                class="list-group-item list-group-item-action border-0 py-1">Computacion</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Administración"
                class="list-group-item list-group-item-action border-0 py-1">Administración</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Logistica"
                class="list-group-item list-group-item-action border-0 py-1">Logistica</a>
            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=Contabilidad"
                class="list-group-item list-group-item-action border-0 py-1">Contabilidad</a>
        </ul>
    </div>
</div>