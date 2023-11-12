/**
 * 
 */

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
          } 
          //else {
          //  event.preventDefault();
          //  event.stopPropagation();
          //}

          // Agrega la clase CSS de Bootstrap para mostrar los estilos de validación
          form.classList.add("was-validated");
        },
        false
      );
    });
  })();
});