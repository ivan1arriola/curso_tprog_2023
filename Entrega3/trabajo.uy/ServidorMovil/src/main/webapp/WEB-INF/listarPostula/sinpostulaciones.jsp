<%@page import="logica.servidor.TipoUsuarioNoValido_Exception"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="logica.servidor.DtPostulacion" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>

<head>
  <jsp:include page="/WEB-INF/template/head.jsp" />
  <title>Postulaciones</title>
</head>

<body>
<header>
  <jsp:include page="/WEB-INF/template/navbar.jsp" />
</header>

<main class="text-center">

        <h1>Postulaciones</h1>

        <h5>No hay postulaciones para este usuario</h5>


    </main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>
</html>