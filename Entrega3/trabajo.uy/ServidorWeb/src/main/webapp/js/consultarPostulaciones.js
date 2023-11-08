
const cargarInfoPostulante = (nickname, oferta) => {

    $.ajax({
        url: contextPath + "/consultapostulacion",
        data: {
            postulante: nickname,
            oferta: oferta,
        },
        type: "POST",
        dataType: "json",
        success: function (data) {
            console.log(data);
            // Actualiza la tabla con los datos
            $("#nombrePostulanteLink a").text(data.nombre);
            $("#nombrePostulanteLink a").attr("href", "/ServidorWeb/consultarusuario?u=" + nickname);
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
        }
    });
}

