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

const validarTamanioImagen = () => {
  const maxFileSizeKB = 250;
  const maxFileSizeBytes = maxFileSizeKB * 1024;

  const fileInput = document.getElementById('image-input'); // Asegúrate de obtener el elemento por su ID
  const imageFile = fileInput.files[0];

  if (imageFile) {
    if (imageFile.size > maxFileSizeBytes) {
      fileInput.setCustomValidity('El archivo es demasiado grande. El tamaño máximo permitido es 250 KB.');
    } else {
      fileInput.setCustomValidity('');
    }
  }
}



document.addEventListener("DOMContentLoaded", function() {
	mostrarCamposEspeciales();
	
    (() => {
        "use strict";
    
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        const forms = document.querySelectorAll(".needs-validation");
        const password = document.getElementById("password-input");
        const passwordConfirm = document.getElementById("confirm-password-input");
        const fileInput = document.getElementById('image-input');
    
        passwordConfirm.addEventListener("input", validarContraseña);
        password.addEventListener("input", validarContraseña);
        fileInput.addEventListener("input", validarTamanioImagen);
    
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