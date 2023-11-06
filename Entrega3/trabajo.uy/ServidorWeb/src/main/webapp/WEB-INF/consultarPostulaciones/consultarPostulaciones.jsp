<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<%
    List<String> postulantes = (List<String>) request.getAttribute("postulantes");
    String imagen = (String) request.getAttribute("imagenOferta");
%>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Postulaciones a Oferta Laboral</title>
    <style >
        .banner-container {
            background-image: url(<%= imagen %>);
            height: 25vh;
        }
    </style>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
</head>

<body>
<header>
    <jsp:include page="/WEB-INF/templates/header.jsp" />
</header>

<main class="container-fluid d-flex">
    <div class="container col-3">
        <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
    </div>

    <div class="container col-9">
        <div class="container">
            <div class="row banner-container banner-dark">
                <h1 class="text-center text-light fw-bolder">Desarrollador Frontend</h1>
            </div>

            <div class="row">
                <div class="col container m-2" id="informacionPostulante">
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

                <div class="col-4">
                    <div id="postulantes" class="container card m-2">
                        <div class="row align-items-center card-body">
                            <ol id="listaPostulantes" class="list-group list-group-numbered">
                                <% for(String postulante : postulantes){ %>
                                <li id="<%=postulante%>>" class="list-group-item">
                                    <div class="row">
                                        <div class="col">
                                            <button id="btn-<%=postulante%>>" class="btn btn-custom" onclick="cargarInfoPostulante('<%=postulante%>')">Ver</button>
                                        </div>
                                        <div class="col">
                                            <%=postulante%>
                                        </div>
                                    </div>
                                </li>
                                <%}%>

                            </ol>



                        </div>
                        <form class="text-center card-footer"
                              action="${pageContext.request.contextPath}/consultarpostulantes" method="POST">
                            <input type="hidden" name="orden" id="input-orden">
                            <input type="submit" value="Definir Orden" class="btn btn-primary">
                        </form>
                    </div>

                </div>


            </div>
        </div>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
    $(document).ready(function () {
        $("#listaPostulantes").sortable({
            update: function (event, ui) {
                // Actualiza el orden en el input oculto
                const order = $("#listaPostulantes").sortable("toArray");
                $("#input-orden").val(order);
                console.log(order);
            }
        });
        $("#listaPostulantes").disableSelection();
    });



    const cargarInfoPostulante = (nickname) => {
        // Deshabilita todos los botones
        $('button[id^="btn-"]').prop('disabled', true);

        $.ajax({
            url: "<%=request.getContextPath()%>/consultapostulacion",
            data: {
                postulante: nickname,
                oferta: "<%=request.getParameter("oferta")%>"
            },
            type: "POST",
            dataType: "json",
            success: function (data) {
                console.log(data);
                // Actualiza la tabla con los datos
                $("#nombrePostulanteLink a").text(data.nombre);
                $("#nombrePostulanteLink a").attr("href", "/ServidorWeb/consultarusuario?u=" + nickname);
                $("#nombreOferta a").attr("href", "/ServidorWeb/consultarofertalaboral?o=" + data.oferta);
                $("#curriculum").text(data.curriculum);
                $("#motivacion").text(data.motivacion);
                $("#fecha").text(data.fecha);
                if (data.video) {
                    $("#video").html('<a href="' + data.video + '">Ver Video</a>');
                } else {
                    $("#video").html('<div class="alert alert-warning" role="alert">No se ha subido un video.</div>');
                }
            },
            error: function (error) {
                console.log("Error: ", error);
            },
            complete: function () {
                // Habilita todos los botones nuevamente
                $('button[id^="btn-"]').prop('disabled', false);
                $(`#btn-${nickname}`).prop('disabled', true);

            }
        });
    }


</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
