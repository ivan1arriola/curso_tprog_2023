<div class="usuario d-block col-2">
    <div class="d-inline-block">
        <% 
            String imagen = (String) session.getAttribute("imagen");
            String usuario = (String) session.getAttribute("usuario");
        %>
        <img src="<%= imagen != null ? imagen : "" %>" alt="<%= usuario != null ? usuario : "" %>" class="rounded-circle"
            style="width: 60px; height: 60px" />
        <% if (usuario != null) { %>
        <a href="<%= request.getContextPath() %>/consultarusuario?u=<%= usuario %>"
            class="ml-3 mb-0">
            <%= usuario %>
        </a>
        <% } %>
    </div>
</div>
