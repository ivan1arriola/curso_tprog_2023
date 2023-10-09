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
                    	
                    	String imagenUrl = ""; /*
                        if (paquete.getImagen() != null) {
                            String base64Image = new String(Base64.getEncoder().encode(paquete.getImagen()), "UTF-8");
                            imagenUrl = "data:image/jpeg;base64," + base64Image; // Asume que la imagen es en formato JPEG, ajusta según sea necesario
                        } else { */
                            // Si no hay imagen, puedes establecer una imagen de reemplazo o un mensaje aquí
                            imagenUrl = request.getContextPath() +  "/imagenNoFound.png";
                       // }
                		String nombre = paquete.getNombre();
                        String descripcion = paquete.getDescripcion();
                        String enlace = request.getContextPath() + "/consultarpaquete?p=" + paquete.getNombre();
                %>
                <jsp:include page="/WEB-INF/templates/lista1.jsp">
                    <jsp:param name="imagenUrl" value="<%= imagenUrl %>" />
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