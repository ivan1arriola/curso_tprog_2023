<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
  List<UsuarioBean> postulantes = (List<UsuarioBean>) request.getAttribute("postulantes");
%>

<div class="container">
  <div class="d-flex justify-content-between">

    <a href="<%=request.getContextPath()%>/ordenarpostulantes?oferta=<%=ofertaLaboral.getNombre()%>" class="btn btn-primary mr-2">Definir Orden de Selección</a>


    <jsp:include page="./finalizarOfertaConfirmacionModal.jsp" />


    <a type="button" class="btn btn-primary" href="<%=request.getContextPath()%>/consultarpostulantes?oferta=<%=ofertaLaboral.getNombre()%>">
      Ver Postulantes <span class="badge bg-secondary"><%= postulantes.size() %></span>
    </a>
  </div>
</div>
