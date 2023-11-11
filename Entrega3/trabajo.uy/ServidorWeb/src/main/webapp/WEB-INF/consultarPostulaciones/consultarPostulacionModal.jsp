<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% String nombreOferta = request.getParameter("oferta"); %>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title text-center" id="exampleModalLabel">Información de Postulación</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table class="table table-striped">
          <tbody>
          <tr>
            <td><b>Postulante:</b></td>
            <td id="nombrePostulanteLink"><a></a></td>
          </tr>
          <tr>
            <td><b>Oferta Laboral</b></td>
            <td id="nombreOferta">
              <a href="<%= request.getContextPath()%>/consultarofertalaboral?o=<%=nombreOferta%>"><%=nombreOferta%></a>
            </td>
          </tr>
          <tr>
            <td><b>CV reducido:</b></td>
            <td id="curriculum"></td>
          </tr>
          <tr>
            <td><b>Motivación:</b></td>
            <td id="motivacion"></td>
          </tr>
          <tr>
            <td><b>Fecha Postulación:</b></td>
            <td id="fecha"></td>
          </tr>
          <tr>
            <td><b>Video Postulación:</b></td>
            <td id="video"></td>
          </tr>
          </tbody>
        </table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>



