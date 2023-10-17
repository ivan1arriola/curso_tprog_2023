<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="javabeans.PaqueteBean" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Base64" %>
<%@ page import="javabeans.UsuarioBean" %>

<!-- Tab de paquetes -->
<div class="tab-pane fade" id="paquete-panel" role="tabpanel" aria-labelledby="paquete-tab">
    <div class="card mb-3">
        <%
        UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
        Set<PaqueteBean> paquetes = usuario.getPaquetes();

        if (paquetes == null || paquetes.isEmpty()) {
        %>
        <div class="alert alert-secondary" role="alert">
            No tiene paquetes disponibles en este momento.
        </div>
        <%
        } else {
            for (PaqueteBean paquete : paquetes) {
                String imagen = paquete.getImagen();
                if (imagen == null)
                    imagen = request.getContextPath() + "/imagenNoFound.png";
                String nombre = paquete.getNombre();
                String descripcion = paquete.getDescripcion();
                String enlace = request.getContextPath() + "/consultarpaquete?p=" + paquete.getNombre();
        %>
        <jsp:include page="/WEB-INF/templates/lista1.jsp">
            <jsp:param name="imagen" value="<%= imagen %>" />
            <jsp:param name="nombre" value="<%= nombre %>" />
            <jsp:param name="descripcion" value="<%= descripcion %>" />
            <jsp:param name="enlace" value="<%= enlace %>" />
        </jsp:include>
        <%
            }
        }
        %>
    </div>
</div>
