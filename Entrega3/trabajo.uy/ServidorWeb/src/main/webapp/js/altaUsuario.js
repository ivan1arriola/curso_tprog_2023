const empresaRadio = document.getElementById("empresa-radio");
const postulanteRadio = document.getElementById("postulante-radio");
const camposEmpresa = document.getElementById("campos-empresa");
const camposPostulante = document.getElementById("campos-postulante");
const fechaNacimientoInput = document.getElementById("fecha-nacimiento-input");
const nacionalidadInput = document.getElementById("nacionalidad-input");
const descripcionEmpresaInput = document.getElementById("descripcion-empresa-input");
const passwordInput = document.getElementById("password-input");
const confirmPasswordInput = document.getElementById("confirm-password-input");
const maxFileSizeKB = 250;
const maxFileSizeBytes = maxFileSizeKB * 1024;
const imageInput = document.getElementById('image-input');
const nombreInput = document.getElementById("nombre-input"); // Debes declarar nombreInput

const validarNombre = () => {
  console.log("validarNombre ejecutando");
  const nombre = document.getElementById("nombre-input");
  const divNombre = document.getElementById("div-nombre-input");
  const nombreInvalidFeedback = document.getElementById("nombre-invalid-feedback");

  // Validar que el nombre no esté vacío
  if (nombre.value === "") {
    nombre.setCustomValidity("El nombre no puede estar vacío");
    nombreInvalidFeedback.innerText = "El nombre no puede estar vacío";
  } else {
    nombre.setCustomValidity("");
    // Validar que el nombre no tenga números
    if (/\d/.test(nombre.value)) {
      nombre.setCustomValidity("El nombre no puede contener números");
      nombreInvalidFeedback.innerText = "El nombre no puede contener números";
    } else {
      nombre.setCustomValidity("");
    }

    // Validar que el nombre no tenga caracteres especiales
    if (/[!@#$%^&*(),.?":{}|<>]/g.test(nombre.value)) {
      nombre.setCustomValidity("El nombre no puede contener caracteres especiales");
      nombreInvalidFeedback.innerText = "El nombre no puede contener caracteres especiales";
    } else {
      nombre.setCustomValidity("");
    }

    // Validar que el nombre no tenga espacios
    if (/\s/.test(nombre.value)) {
      nombre.setCustomValidity("El nombre no puede contener espacios");
      nombreInvalidFeedback.innerText = "El nombre no puede contener espacios";
    } else {
      nombre.setCustomValidity("");
    }

    // Validar que el nombre no tenga más de 20 caracteres
    if (nombre.value.length > 20) {
      nombre.setCustomValidity("El nombre no puede contener más de 20 caracteres");
      nombreInvalidFeedback.innerText = "El nombre no puede contener más de 20 caracteres";
    } else {
      nombre.setCustomValidity("");
    }
  }



  divNombre.classList.add("was-validated");
}

const mostrarCamposEspeciales = () => {
  if (empresaRadio.checked) {
    camposEmpresa.style.display = "block";
    camposPostulante.style.display = "none";
    fechaNacimientoInput.required = false;
    nacionalidadInput.required = false;
    descripcionEmpresaInput.required = true;
  } else if (postulanteRadio.checked) {
    camposEmpresa.style.display = "none";
    camposPostulante.style.display = "block";
    fechaNacimientoInput.required = true;
    nacionalidadInput.required = true;
    descripcionEmpresaInput.required = false;
  } else {
    camposEmpresa.style.display = "none";
    camposPostulante.style.display = "none";
    fechaNacimientoInput.required = true;
    nacionalidadInput.required = true;
    descripcionEmpresaInput.required = true;
  }
}

const validarContraseña = () => {
  if (passwordInput.value !== confirmPasswordInput.value) {
    confirmPasswordInput.setCustomValidity("Las contraseñas no coinciden");
  } else {
    confirmPasswordInput.setCustomValidity("");
  }
}

const validarTamañoImagen = () => {
  const imageFile = imageInput.files[0];

  if (imageFile) {
    if (imageFile.size > maxFileSizeBytes) {
      imageInput.setCustomValidity('El archivo es demasiado grande. El tamaño máximo permitido es 250 KB.');
    } else {
      imageInput.setCustomValidity('');
    }
  }
}

document.addEventListener("DOMContentLoaded", () => {
  "use strict";
  mostrarCamposEspeciales();

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll(".needs-validation");

  confirmPasswordInput.addEventListener("input", validarContraseña);
  passwordInput.addEventListener("input", validarContraseña);
  imageInput.addEventListener("input", validarTamañoImagen);
  nombreInput.addEventListener("input", validarNombre);

  // Loop over them and prevent submission
  Array.from(forms).forEach((form) => {
    form.addEventListener(
      "submit",
      (event) => {
        if (!form.checkValidity()) {
          event.preventDefault();
          event.stopPropagation();
        }

        // Agrega la clase CSS de Bootstrap para mostrar los estilos de validación
        form.classList.add("was-validated");
      },
      false
    );
  });
});


