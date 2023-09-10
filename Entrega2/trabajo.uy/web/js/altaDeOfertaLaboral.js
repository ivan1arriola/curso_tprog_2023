$(document).ready(() => {
  const today = new Date();

  // Formatea la fecha en el formato deseado (por ejemplo, DD/MM/AAAA)
  const formattedDate =
    today.getDate().toString().padStart(2, "0") +
    "/" +
    (today.getMonth() + 1).toString().padStart(2, "0") +
    "/" +
    today.getFullYear();

  // Establece la fecha formateada como el valor por defecto del campo de entrada
  $("#fechaAlta").val(formattedDate);

  $(document).ready(function () {
    const keywordSelect = $("#keywordSelect");
    const agregarKeywordButton = $("#agregarKeyword");
    const keywordsSeleccionadas = $("#keywordsSeleccionadas");

    agregarKeywordButton.on("click", function () {
      const selectedKeyword = keywordSelect.val();
      if (selectedKeyword) {
        // Crea el elemento de la keyword con la estructura deseada
        const keywordElement = $("<div></div>").addClass(
          "d-flex justify-content-between align-items-center bg-light p-2 mt-2"
        );

        const keywordText = $("<span></span>").text(selectedKeyword);

        const eliminarKeywordButton = $("<button></button>")
          .addClass("btn btn-danger")
          .text("X")
          .on("click", function () {
            keywordElement.remove();
            // Habilitar la opción nuevamente en el select
            keywordSelect.append(
              $("<option></option>")
                .attr("value", selectedKeyword)
                .text(selectedKeyword)
            );
          });

        keywordElement.append(keywordText, eliminarKeywordButton);
        keywordsSeleccionadas.append(keywordElement);

        // Deshabilitar la opción en el select
        keywordSelect.find(`option[value="${selectedKeyword}"]`).remove();
      }
    });
  });
});
