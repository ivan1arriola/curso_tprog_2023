<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="enumeration.TipoUsuario" %>

<%
UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
%>


                <!-- Tab de perfil -->
                <div class="tab-pane fade show active" id="perfil-panel">
                    <form action="consultarusuario" method="post">
                    	    <input type="hidden" name="nick"  placeholder="nick" value="<%= (String) request.getAttribute("usuarioConsultado") %>">


                            <div class="form-group">
                                    <label for="nombre">Nombre:</label>
                                    <input type="text" class="form-control" id="nombre" disabled
                                        value="<%= usuario.getNombre() %>" required />
                                </div>
                                <div class="form-group">
                                    <label for="apellido">Apellido:</label>
                                    <input type="text" class="form-control" id="apellido" disabled
                                        value="<%= usuario.getApellido() %>" required />
                                </div>
                                <div class="form-group">
                                    <label for="correo">Correo electrónico:</label>
                                    <input type="email" class="form-control" id="correo" disabled
                                        value="<%= usuario.getCorreoElectronico() %>" readonly required />
                                </div>
                                
                                <%if (usuario.getTipo() == TipoUsuario.Postulante) {  // Importar JSP de camposPostulante.jsp %>
                                    <jsp:include page="./camposPostulante.jsp" />
                                <% }%>
                                
                                <%if (usuario.getTipo() == TipoUsuario.Empresa) {  // Importar JSP de camposPostulante.jsp %>
                                    <jsp:include page="./camposEmpresa.jsp" />
                                <% }%>

                                


                    </form>
                </div>