$(document).ready(function () {
  // Ocultar los botones de aceptar y cancelar
  $("#aceptarBtn").hide();
  $("#cancelarBtn").hide();

  // Vincular la función al botón de modificar
  $("#modificarBtn").on("click", modificarDatos);
  $("#aceptarBtn").on("click", aceptarCambios);
  $("#cancelarBtn").on("click", cancelarCambios);
});

const modificarDatos = () => {
  // Habilitar los botones de aceptar y cancelar
  $("#aceptarBtn").show();
  $("#cancelarBtn").show();
  $("#modificarBtn").hide();

  // Habilitar los campos del form
  $("#nombre").prop("disabled", false);
  $("#apellido").prop("disabled", false);
  $("#descripcion").prop("disabled", false);
  $("#link").prop("disabled", false);
};

const aceptarCambios = () => {
  // Deshabilitar los botones de aceptar y cancelar
  $("#aceptarBtn").hide();
  $("#cancelarBtn").hide();
  $("#modificarBtn").show();

  // Deshabilitar los campos del form
  $("#nombre").prop("disabled", true);
  $("#apellido").prop("disabled", true);
  $("#descripcion").prop("disabled", true);
  $("#link").prop("disabled", true);

  // Obtener los valores de los campos
  let nombre = $("#nombre").val();
  let apellido = $("#apellido").val();
  let descripcion = $("#descripcion").val();
  let link = $("#link").val();

  // Validar que no estén vacíos
  if (nombre === "" || apellido === "" || descripcion === "" || link === "") {
    alert("Todos los campos son obligatorios");
    return;
  }

  // Actualizar los datos del perfil
  //   $("#nombrePerfil").text(nombre + " " + apellido);
  //   $("#descripcionPerfil").text(descripcion);
  //   $("#linkPerfil").text(link);
};

const cancelarCambios = () => {
  window.location.reload();
};
