<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="main.java.logica.datatypes.DTPaquete" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Base64" %>


       <!-- Tab de paquetes -->
        <div class="tab-pane fade" id="paquete-panel" role="tabpanel" aria-labelledby="paquete-tab">
          <div class="card mb-3">
          
          
            <%
                	Set<DTPaquete> paquetes = (Set<DTPaquete>) request.getAttribute("paquetes");
                
                if (paquetes == null || paquetes.isEmpty()) {
                    // Si ofertasLaborales es null o está vacío, muestra un mensaje
                %>
                    <div class="alert alert-secondary" role="alert">
					        No hay paquetes disponibles en este momento.
					</div>

                <%
                } else {

                    // Itera sobre la lista de paquetes y muestra cada paquete
                    for (DTPaquete paquete : paquetes) {
                    	
                    	String imagen = paquete.getImagen();
                    if(imagen == null)
                    	imagen = request.getContextPath() +  "/imagenNoFound.png";
                       // }
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