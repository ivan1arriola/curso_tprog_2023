const mostrarCamposEspeciales = () => {
  const empresaRadio = document.getElementById("empresa-radio");
  const postulanteRadio = document.getElementById("postulante-radio");
  const camposEmpresa = document.getElementById("campos-empresa");
  const camposPostulante = document.getElementById("campos-postulante");

  if (empresaRadio.checked) {
    camposEmpresa.style.display = "block";
    camposPostulante.style.display = "none";
    document.getElementById("fecha-nacimiento-input").required = false;
    document.getElementById("nacionalidad-input").required = false;
    document.getElementById("descripcion-empresa-input").required = true;
  } else if (postulanteRadio.checked) {
    camposEmpresa.style.display = "none";
    camposPostulante.style.display = "block";
    document.getElementById("fecha-nacimiento-input").required = true;
    document.getElementById("nacionalidad-input").required = true;
    document.getElementById("descripcion-empresa-input").required = false;
  } else {
    camposEmpresa.style.display = "none";
    camposPostulante.style.display = "none";
    document.getElementById("fecha-nacimiento-input").required = true;
    document.getElementById("nacionalidad-input").required = true;
    document.getElementById("descripcion-empresa-input").required = true;
  }
}

const validarContraseña = () => {
    const password = document.getElementById("password-input");
    const passwordConfirm = document.getElementById("confirm-password-input");

    if (password.value !== passwordConfirm.value) {
        passwordConfirm.setCustomValidity("Las contraseñas no coinciden");
    } else {
        passwordConfirm.setCustomValidity("");
    }
}

document.addEventListener("DOMContentLoaded", function() {

    (() => {
        "use strict";
    
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll(".needs-validation");
        const password = document.getElementById("password-input");
        const passwordConfirm = document.getElementById("confirm-password-input");
    
        passwordConfirm.addEventListener("input", validarContraseña);
        password.addEventListener("input", validarContraseña);
    
        // Loop over them and prevent submission
        Array.from(forms).forEach((form) => {
            form.addEventListener(
                "submit",
                (event) => {
                    if (!form.checkValidity() ) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
    
                    // Agrega la clase CSS de Bootstrap para mostrar los estilos de validación
                    form.classList.add("was-validated");
                },
                false
            );
        });
    })();
   
});