$(document).ready(function () {
    $("#listaPostulantes").sortable({
        items: "tr",
        update: function (event, ui) {
            // Actualiza el orden en el input oculto
            const order = $("#listaPostulantes").sortable("toArray");
            $("#input-orden").val(order);
            console.log(order);

            // Actualiza los números de las filas
            $("#listaPostulantes tr").each(function (index) {
                $(this).find(".row-index").text(index + 1);
            });
        }
    });
    $("#listaPostulantes").disableSelection();
});
