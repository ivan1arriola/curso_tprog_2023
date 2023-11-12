
const cargarInfoPostulante = (nickname,  oferta) => {

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
            $("#nombrePostulanteLink a").attr("href",  "/ServidorWeb/consultarusuario?u=" + nickname);
            $("#curriculum").text(data.curriculum);
            $("#motivacion").text(data.motivacion);
            $("#fecha").text(data.fecha);
            if (data.video) {
                $("#video").html('<iframe width="100%" height="395" src="' + data.video + '" title="Video Postulacion" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>');
            } else {
                $("#video").html('<div class="alert alert-warning" role="alert">No se ha subido un video.</div>');
            }
        }, 
        error: function (error) {
            console.log("Error: ",  error);
        }
    });
}

/*

Quiero usar este iframe cuando exista data.video

 <iframe
                                        width="100%"
                                        height="395"
                                        src="<%=postulacion.getVideo()%>"
                                        title="Video Postulacion"
                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                        allowfullscreen>
                                </iframe>
 */