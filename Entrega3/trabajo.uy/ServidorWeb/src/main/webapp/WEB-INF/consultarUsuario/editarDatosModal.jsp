<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
UsuarioBean usuario = (UsuarioBean) request.getAttribute("usuario");
%>
    
<!-- Button trigger modal -->
<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  Editar Datos de Usuario
</button>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Editar Datos de Usuario</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">

        <form class="form-signup needs-validation" novalidate  action="ModificarUsuario" method="post" enctype="multipart/form-data">
          <!-- Secciï¿½n de Informaciï¿½n Personal -->
          <section>
            <h2 class="h4">Informaciï¿½n Personal</h2>

            <input type="hidden" name="nickname" value="<%=usuario.getNickname()%>">
            <input type="hidden" name="email" value="<%=usuario.getCorreoElectronico()%>">


            <div class="row">
              <div class="col">
                <!-- Input de Nombre -->
                <div class="form-floating mb-3 " id="div-nombre-input">
                  <input type="text" class="form-control" id="nombre-input"  name="nombre" placeholder=" " required value="<%=usuario.getNombre()%>">
                  <label for="nombre-input">Nombre *</label>
                  <div class="invalid-feedback" id="nombre-invalid-feedback">
                    Nombre es un atributo requerido
                  </div>
                </div>
              </div>
              <div class="col">
                <!-- Input de Apellido -->
                <div class="form-floating mb-3 " id="div-apellido-input">
                  <input type="text" class="form-control" id="apellido-input" name="apellido"  placeholder=" " required value="<%=usuario.getApellido()%>">
                  <label for="apellido-input">Apellido *</label>
                  <div class="invalid-feedback" id="apellido-invalid-feedback">
                    Apellido es un atributo requerido
                  </div>
                </div>
              </div>
            </div>
          </section>

          <!-- Secciï¿½n de Contraseï¿½a -->
          <section>
            <h2 class="h4">Contraseï¿½a</h2>

            <div class="row">

              <div class="col">
                <!-- Input de Contraseï¿½a -->
                <div class="form-floating mb-3 " id="div-password-input">
                  <input type="password" class="form-control" id="password-input" name="password" placeholder=" " required value="<%=usuario.getContrasenia()%>">
                  <label for="password-input">Contraseï¿½a *</label>
                  <div class="invalid-feedback" id="password-invalid-feedback">
                    Contraseï¿½a es un atributo requerido
                  </div>
                </div>
              </div>

              <div class="col">
                <!-- Input de Confirmar Contraseï¿½a -->
                <div class="form-floating mb-3 " id="div-confirm-password-input">
                  <input type="password" class="form-control" id="confirm-password-input" name="confirm-password"  placeholder=" " value="<%=usuario.getContrasenia()%>">
                  <label for="confirm-password-input">Confirmar Contraseï¿½a *</label>
                  <div class="invalid-feedback" id="confirm-password-invalid-feedback">
                    Las contraseï¿½as no coinciden
                  </div>
                </div>
              </div>

            </div>

          </section>



          <% if (usuario.getTipo().equals(TipoUsuario.Empresa)){ %>
          <!-- Campos adicionales para Empresa -->
          <div id="campos-empresa">
            <section>
              <h2 class="h4">Informaciï¿½n de la Empresa</h2>

              <div class="form-floating mb-3 " id="div-descripcion-empresa-input">
                <textarea class="form-control" id="descripcion-empresa-input" name="descripcion" placeholder=" " style="height: 150px" value="">
                  <%=usuario.getDescripcion()%>
                </textarea>

                <label for="descripcion-empresa-input">Descripciï¿½n de la Empresa *</label>
                <div class="invalid-feedback" id="descripcion-empresa-invalid-feedback">
                  Descripciï¿½n de la Empresa es un atributo requerido
                </div>
              </div>

              <div class="form-floating mb-3 " id="div-sitio-web-input">
                <input type="url" class="form-control" id="sitio-web-input" name="sitio-web"  placeholder=" " value=" <%=usuario.getUrl()%>">
                <label for="sitio-web-input">Sitio Web de la Empresa</label>
              </div>
            </section>
          </div>

          <% } else {%>

          <!-- Campos adicionales para Postulante -->
          <div id="campos-postulante">
            <section>
              <h2 class="h4">Informaciï¿½n del Postulante</h2>

              <div class="form-floating mb-3 " id="div-fecha-nacimiento-input">
                <input type="date" class="form-control" id="fecha-nacimiento-input" name="fecha-nacimiento"  required value="<%=usuario.getFechaNac()%>">
                <label for="fecha-nacimiento-input">Fecha de Nacimiento *</label>
                <div class="invalid-feedback" id="fecha-nacimiento-invalid-feedback">
                  Fecha de Nacimiento es un atributo requerido
                </div>
              </div>

              <div class="form-floating mb-3 " id="div-nacionalidad-input">
                <input type="text" class="form-control" id="nacionalidad-input" name="nacionalidad" placeholder=" " value="<%=usuario.getNacionalidad()%>">
                <label for="nacionalidad-input">Nacionalidad *</label>
                <div class="invalid-feedback" id="nacionalidad-invalid-feedback">
                  Nacionalidad es un atributo requerido
                </div>
              </div>
            </section>
          </div>

          <%}%>

          <!-- Secciï¿½n de Imagen de Perfil -->
          <section>
            <h2 class="h4">Imagen de Perfil</h2>

            <div class="form-group mb-3 " id="div-image-input">
              <label for="image-input">Subir una imagen de perfil (max 500 KB)</label>
              <input type="file" class="form-control" id="image-input" name="imagen" accept=".jpg" />
              <div class="invalid-feedback" id="image-invalid-feedback">
                La imagen no debe superar el tamaï¿½o mï¿½ximo permitido de 500 KB.
              </div>
            </div>
          </section>

          <!-- Botï¿½n de Registro -->
          <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">
            Actualizar Datos
          </button>

        </form>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>

<script src="<%= request.getContextPath() %>/js/modificarDatos.js"></script>