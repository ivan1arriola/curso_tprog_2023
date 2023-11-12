function validarCredenciales() {
  const identificador = document.getElementById("identificador-input").value;
  const password = document.getElementById("password-input").value;

  // Verificar las credenciales
  if (identificador === "lgarcia" || identificador === "lgarcia85@gmail.com") {
    if (password === "awdrg543") {
      window.location.href = "../postulante/index.html";
      return false; // false para evitar la recarga de la página
    }
  } else if (
    identificador === "EcoTech" ||
    identificador === "info@EcoTech.com"
  ) {
    if (password === "qsxcdw43") {
      window.location.href = "../empresa/index.html";
      return false; // false para evitar la recarga de la página
    }
  }

  alert("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
  return false;
}

document.addEventListener("DOMContentLoaded", function () {
  (() => {
    "use strict";
    const forms = document.querySelectorAll(".needs-validation");

    // Loop over them and prevent submission
    Array.from(forms).forEach((form) => {
      form.addEventListener(
        "submit",
        (event) => {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          } else {
            event.preventDefault();
            event.stopPropagation();
            validarCredenciales();
          }

          // Agrega la clase CSS de Bootstrap para mostrar los estilos de validación
          form.classList.add("was-validated");
        },
        false
      );
    });
  })();
});
