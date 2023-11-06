<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.PostulacionBean" %>
<%@ page import="javabeans.OfertaLaboralBean" %>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Info de Postulacion</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">


        <table class="table table-striped">
          <tbody>
          <tr>
            <td><b>Postulante:</b></td>
            <td id="nombrePostulanteLink"><a></a>
            </td>
          </tr>
          <tr>
            <td><b>Oferta Laboral</b></td>
            <td id="nombreOferta">
              <a href="<%= request.getContextPath()%> /consultarofertalaboral?o= <%=request.getParameter("oferta")%>">
                <%=request.getParameter("oferta")%>
              </a>
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
            <td><b>Video Postulacion:</b></td>
            <td id="video">

            </td>
          </tr>
          </tbody>
        </table>





      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>



