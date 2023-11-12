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

    const resetButton = $("#resetButton"); // Agrega el botón de reinicio

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

  // Agrega el manejador de eventos para restablecer todo
  resetButton.on("click", function () {
    // Restablecer la fecha
    $("#fechaAlta").val(formattedDate);

    // Restablecer las palabras clave seleccionadas y el select
    keywordsSeleccionadas.empty();
    keywordSelect.empty();
    keywordSelect.append(
      $("<option value='Palabra1'>Palabra1</option>"),
      $("<option value='Palabra2'>Palabra2</option>"),
      $("<option value='Palabra3'>Palabra3</option>")
    );
  });
});

document.addEventListener("DOMContentLoaded", function () {
  const formulario = document.getElementById("alta-oferta-laboral");
  const exitomodal = document.getElementById("exito");

  formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    const tipoOfers = document.getElementById("listadoOfertas").value;
    const nombre = document.getElementById("nombre").value;
    const descripcion = document.getElementById("descripcion").value;
    const horaInicio = document.getElementById("horaInicio").value;
    const horaFinal = document.getElementById("horaFinal").value;
    const remuneracion = document.getElementById("remuneracion").value;
    const ciudad = document.getElementById("ciudad").value;
    const pago = document.getElementById("listadoPagos").value;
    const fecha = document.getElementById("fechaAlta");
    const FechaA = document.getElementById("fechaAlta").value;
    const departamentos = document.getElementById("listadoDepartamentos").value;

    let campoFaltante = "";
    const esNumero = /^\d+$/.test(remuneracion);

    if (tipoOfers === "0") {
      campoFaltante = "Listado de Tipo de Oferta";
    } else if (nombre === "") {
      campoFaltante = "Nombre";
    } else if (descripcion === "") {
      campoFaltante = "Descripción";
    } else if (horaInicio === "") {
      campoFaltante = "Hora de Comienzo";
    } else if (horaFinal === "") {
      campoFaltante = "Hora de Final";
    } else if (remuneracion === "") {
      campoFaltante = "Remuneracion";
    } else if (departamentos === "0") {
      campoFaltante = "Departamento";
    } else if (ciudad === "") {
      campoFaltante = "Ciudad";
    } else if (pago === "0") {
      campoFaltante = "Pago";
    }

    if (campoFaltante !== "") {
      event.preventDefault();
      // Mostrar el modal de error y configurar el texto específico
      const errorModal = document.getElementById("faltanCampos");
      const modalBody = errorModal.querySelector(".modal-body");
      modalBody.textContent = `Por favor, complete el campo obligatorio: ${campoFaltante}`;
      $(errorModal).modal("show"); // Abre el modal
    } else if (!esNumero) {
      // Mostrar el modal de error y configurar el texto específico
      const errorModal = document.getElementById("faltanCampos");
      const modalBody = errorModal.querySelector(".modal-body");
      modalBody.textContent = `El campo remuneración debe ser un número.`;
      $(errorModal).modal("show"); // Abre el modal
    } else {
      // Mostrar el modal de éxito y configurar el texto
      const modalBody1 = exitomodal.querySelector(".modal-body");
      modalBody1.textContent = "El usuario se ha dado de alta exitosamente";
      $(exitomodal).modal("show"); // Abre el modal

      // Agregar un evento para detectar cuando se cierre el modal de éxito
      $(exitomodal).on("hidden.bs.modal", function () {
        var keywordsSeleccionadasElement = document.getElementById(
          "keywordsSeleccionadas"
        );
        while (keywordsSeleccionadasElement.firstChild) {
          keywordsSeleccionadasElement.removeChild(
            keywordsSeleccionadasElement.firstChild
          );
        }

        // Reiniciar el formulario
        formulario.reset();

        // Obtener la fecha actual
        const today = new Date();

        // Formatear la fecha en formato "dd/mm/yyyy"
        const formattedDate =
          today.getDate().toString().padStart(2, "0") +
          "/" +
          (today.getMonth() + 1).toString().padStart(2, "0") +
          "/" +
          today.getFullYear();

        // Establecer el valor en el campo de entrada
        document.getElementById("fechaAlta").value = formattedDate;
      });
    }
  });
});
