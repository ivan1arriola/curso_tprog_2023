<div class="usuario col-2 d-flex align-items-center">
    <div class="d-inline-block">
        <%
            String imagen = (String) session.getAttribute("imagen"); 
            if (imagen == null) imagen = request.getContextPath() + "/nopicture.png";
            
            String usuario = (String) session.getAttribute("nickname"); 
            if (usuario == null) usuario = "";
        %>
        <div class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                <img src="<%= imagen %>" alt="<%= usuario %>" class="rounded" style="width: 60px; height: 60px" />
                <span class="ml-3 mb-0"><%= usuario %></span>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/consultarusuario?u=<%= usuario %>">
                        <span class="material-symbols-outlined align-middle mr-2">account_circle</span>
                        <span class="align-middle">Mi Perfil</span>
                    </a>
                </li>
                <li>
                    <a class="dropdown-item" href="<%= request.getContextPath() %>/cerrarsesion" class="btn btn-danger">
                        <span class="material-symbols-outlined align-middle mr-2">logout</span>
                        <span class="align-middle">Cerrar Sesi�n</span>
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
