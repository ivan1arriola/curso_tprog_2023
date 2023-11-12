<%--
  Created by IntelliJ IDEA.
  User: ivan1
  Date: 29/10/2023
  Time: 0:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="jumbotron text-center">
    <h1>
        Bienvenido a
        <span class="text-primary m-0 fw-bold">trabajo</span><span class="text-secondary m-0">.uy</span>
    </h1>
    <p>Encuentra tu próximo empleo o talento aquí</p>
    <p>
        <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/iniciarsesion" role="button">Iniciar sesión</a>
        <a class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/altausuario" role="button">Registrarse</a>
    </p>
</div>