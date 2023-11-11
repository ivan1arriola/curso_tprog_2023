<%--
  Created by IntelliJ IDEA.
  User: ivan1
  Date: 29/10/2023
  Time: 4:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
  <jsp:include page="/WEB-INF/template/head.jsp" />
  <title>trabajo.uy</title>
</head>

<body>
<header>
  <jsp:include page="/WEB-INF/template/navbar.jsp" />
</header>

<main>

  <h1>Bienvenido</h1>










</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


<script src="<%= request.getContextPath() %>/js/iniciosesion.js"></script>


</body>
</html>