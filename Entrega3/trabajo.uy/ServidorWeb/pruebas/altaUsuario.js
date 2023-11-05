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
const apellidoInput = document.getElementById("apellido-input"); // Debes declarar apellidoInput

const validarNombre = () => {
    console.log("validarNombre ejecutando");
    
    const nombre = document.getElementById("nombre-input");
    const divNombre = document.getElementById("div-nombre-input");
    const nombreInvalidFeedback = document.getElementById("nombre-invalid-feedback");
  
    divNombre.classList.add("was-validated");
    // Restablecer la validación y ocultar el mensaje de error
    nombre.setCustomValidity("");
    nombreInvalidFeedback.style.display = "none";
  
    // Validar que el nombre no esté vacío
    if (nombre.value === "") {
      nombre.setCustomValidity("El nombre no puede estar vacío");
      nombreInvalidFeedback.innerText = "El nombre no puede estar vacío";
      nombreInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el nombre no tenga números
    if (/\d/.test(nombre.value)) {
      nombre.setCustomValidity("El nombre no puede contener números");
      nombreInvalidFeedback.innerText = "El nombre no puede contener números";
      nombreInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el nombre no tenga caracteres especiales
    if (!/^[A-Za-zÁÉÍÓÚÑáéíóúñÀ-ÿĄ-ź]*$/.test(nombre.value)) {
        nombre.setCustomValidity("El nombre solo puede contener letras y caracteres especiales específicos");
        nombreInvalidFeedback.innerText = "El nombre solo puede contener letras y caracteres especiales específicos";
        nombreInvalidFeedback.style.display = "block";
        return;
    }
      
  
    // Validar que el nombre no tenga espacios
    if (/\s/.test(nombre.value)) {
      nombre.setCustomValidity("El nombre no puede contener espacios");
      nombreInvalidFeedback.innerText = "El nombre no puede contener espacios";
      nombreInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el nombre no tenga más de 20 caracteres
    if (nombre.value.length > 20) {
      nombre.setCustomValidity("El nombre no puede contener más de 20 caracteres");
      nombreInvalidFeedback.innerText = "El nombre no puede contener más de 20 caracteres";
      nombreInvalidFeedback.style.display = "block";
      return;
    }
  }
  

  const validarApellido = () => {
    console.log("validarApellido ejecutando");
    const apellido = document.getElementById("apellido-input");
    const divApellido = document.getElementById("div-apellido-input");
    const apellidoInvalidFeedback = document.getElementById("apellido-invalid-feedback");
  
    divApellido.classList.add("was-validated");
    // Restablecer la validación y ocultar el mensaje de error
    apellido.setCustomValidity("");
    apellidoInvalidFeedback.style.display = "none";
  
    // Validar que el apellido no esté vacío
    if (apellido.value === "") {
      apellido.setCustomValidity("El apellido no puede estar vacío");
      apellidoInvalidFeedback.innerText = "El apellido no puede estar vacío";
      apellidoInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el apellido no tenga números
    if (/\d/.test(apellido.value)) {
      apellido.setCustomValidity("El apellido no puede contener números");
      apellidoInvalidFeedback.innerText = "El apellido no puede contener números";
      apellidoInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el apellido no tenga caracteres especiales
    if (!/^[A-Za-zÁÉÍÓÚÑáéíóúñÀ-ÿĄ-ź]*$/.test(apellido.value)) {
      apellido.setCustomValidity("El apellido solo puede contener letras y caracteres especiales específicos");
      apellidoInvalidFeedback.innerText = "El apellido solo puede contener letras y caracteres especiales específicos";
      apellidoInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el apellido no tenga espacios
    if (/\s/.test(apellido.value)) {
      apellido.setCustomValidity("El apellido no puede contener espacios");
      apellidoInvalidFeedback.innerText = "El apellido no puede contener espacios";
      apellidoInvalidFeedback.style.display = "block";
      return;
    }
  
    // Validar que el apellido no tenga más de 20 caracteres
    if (apellido.value.length > 20) {
      apellido.setCustomValidity("El apellido no puede contener más de 20 caracteres");
      apellidoInvalidFeedback.innerText = "El apellido no puede contener más de 20 caracteres";
      apellidoInvalidFeedback.style.display = "block";
      return;
    }
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
    console.log("validarContraseña ejecutando");
    const passwordInput = document.getElementById("password-input");
    const confirmPasswordInput = document.getElementById("confirm-password-input");
    const passwordInvalidFeedback = document.getElementById("password-invalid-feedback");
    const confirmPasswordInvalidFeedback = document.getElementById("confirm-password-invalid-feedback");
    const divPassword = document.getElementById("div-password-input");
    const divConfirmPassword = document.getElementById("div-confirm-password-input");

    divPassword.classList.add("was-validated");
    divConfirmPassword.classList.add("was-validated");
  
    // Restablecer la validación y ocultar los mensajes de error
    passwordInput.setCustomValidity("");
    confirmPasswordInput.setCustomValidity("");
    passwordInvalidFeedback.style.display = "none";
    confirmPasswordInvalidFeedback.style.display = "none";
  
    // Validar si la contraseña no está vacía
    if (passwordInput.value === "") {
      passwordInput.setCustomValidity("La contraseña no puede estar vacía");
      passwordInvalidFeedback.innerText = "La contraseña no puede estar vacía";
      passwordInvalidFeedback.style.display = "block";
      return;
    }

  
    // Validar si las contraseñas coinciden
    if (passwordInput.value !== confirmPasswordInput.value) {
      passwordInput.setCustomValidity("Las contraseñas no coinciden");
      confirmPasswordInput.setCustomValidity("Las contraseñas no coinciden");
      passwordInvalidFeedback.innerText = "Las contraseñas no coinciden";
      confirmPasswordInvalidFeedback.innerText = "Las contraseñas no coinciden";
      passwordInvalidFeedback.style.display = "block";
      confirmPasswordInvalidFeedback.style.display = "block";
    }
  }
  
const validarCorreoElectronico = () => {
    console.log("validarCorreoElectronico ejecutando");
    const emailInput = document.getElementById("email-input");
    const emailInvalidFeedback = document.getElementById("email-invalid-feedback");
    const divEmail = document.getElementById("div-email-input");
    
    divEmail.classList.add("was-validated");
    
    // Restablecer la validación y ocultar el mensaje de error
    emailInput.setCustomValidity("");
    emailInvalidFeedback.style.display = "none";
    
    // Validar si el correo electrónico no está vacío
    if (emailInput.value === "") {
        emailInput.setCustomValidity("El correo electrónico no puede estar vacío");
        emailInvalidFeedback.innerText = "El correo electrónico no puede estar vacío";
        emailInvalidFeedback.style.display = "block";
        return;
    }
    
    // Validar si el correo electrónico tiene un formato válido
    if (!/\S+@\S+\.\S+/.test(emailInput.value)) {
        emailInput.setCustomValidity("El correo electrónico no tiene un formato válido");
        emailInvalidFeedback.innerText = "El correo electrónico no tiene un formato válido";
        emailInvalidFeedback.style.display = "block";
        return;
    }

    // valida con AJAX la disponibilidad del email
    
}
  
const validarTipoUsuario = () => {
    const empresaRadio = document.getElementById("empresa-radio");
    const postulanteRadio = document.getElementById("postulante-radio");
    const tipoUsuarioSection = document.getElementById("tipo-usuario");
  
    // Restablecer la validación
    tipoUsuarioSection.classList.remove("was-validated");
  
    // Validar que se haya seleccionado un tipo de usuario
    if (!empresaRadio.checked && !postulanteRadio.checked) {
      tipoUsuarioSection.classList.add("was-validated");
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
  const emailInput = document.getElementById("email-input");

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll(".needs-validation");

  confirmPasswordInput.addEventListener("input", validarContraseña);
  passwordInput.addEventListener("input", validarContraseña);
  imageInput.addEventListener("input", validarTamañoImagen);
  nombreInput.addEventListener("input", validarNombre);
    apellidoInput.addEventListener("input", validarApellido);
    emailInput.addEventListener("input", validarCorreoElectronico);

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


