<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="main.java.logica.datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.datatypes.DTPostulante" %>
<%@ page import="main.java.logica.datatypes.DTUsuario" %>
<%
DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
%>

                <!-- Tab de perfil -->
                <div class="tab-pane fade show active" id="perfil-panel" style="margin-top: 20px">
                    <form>
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
                                        value="<%= usuario.getcorreoElectronico() %>" readonly required />
                                </div>
                                
                                <%if (usuario instanceof DTPostulante) {  // Importar JSP de camposPostulante.jsp %>
                                
                                <jsp:include page="./camposPostulante.jsp" />
                                
                                <% }%>
                                
                                <%if (usuario instanceof DTEmpresa) {  // Importar JSP de camposPostulante.jsp %>
                                
                                <jsp:include page="./camposEmpresa.jsp" />
                                
                                <% }%>
                                
                                
                            </div>
                        </div>
                    </form>
                </div>