<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="enumeration.TipoUsuario" %>

<%
UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
boolean seguir = (boolean) request.getAttribute("seguir");
boolean consultaSuPerfil = (boolean) request.getAttribute("consultaSuPerfil");
TipoUsuario tu = (TipoUsuario) request.getAttribute("tipoU");
%>


                <!-- Tab de perfil -->
                <div class="tab-pane fade show active" id="perfil-panel" style="margin-top: 20px">
                    <form action="consultarusuario" method="post">
                    	<input type="text" name="nick" placeholder="nick" value="<%= (String) request.getAttribute("usuarioConsultado") %>">
                        <div class="row">
                            <div class="col-8">
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
                                
							    <!-- ... otros campos del formulario ... -->
							
							    <% if(!consultaSuPerfil && (tu == TipoUsuario.Postulante || tu == TipoUsuario.Empresa)) {
							        if (seguir) { %>
							            <button class="btn btn-primary mt-2" name="btnSeguir" type="submit">
							                Seguir
							            </button>
							        <% } else { %>
							            <button class="btn btn-primary mt-2" name="btnDejarDeSeguir" type="submit">
							                Dejar de seguir
							            </button>
							        <% }
							    } %>

                                
                            </div>
                        </div>
                    </form>
                </div>