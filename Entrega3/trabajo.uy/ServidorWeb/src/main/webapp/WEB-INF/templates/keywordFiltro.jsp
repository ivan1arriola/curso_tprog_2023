<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
    String keyword = request.getParameter("key");
%>
<!-- Filtros -->
<div class="mb-3 container-fluid d-flex align-content-center">
    <span class="badge rounded-pill text-bg-info align-items-center">
        <div class="d-flex justify-content-between">
            <span class="mt-1 ms-3"><%= keyword %></span>
            <a href="<%= request.getContextPath() %>/ofertaslaborales" class="ms-2 material-symbols-outlined closeBtn" style="text-decoration: none; color: inherit;">close</a>
        </div>
    </span>
</div>
