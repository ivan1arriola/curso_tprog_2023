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
const nombreInput = document.getElementById("nombre-input");
const apellidoInput = document.getElementById("apellido-input");
const emailInput = document.getElementById("email-input");
const nicknameInput = document.getElementById("nickname-input");


const host = contextPath || "http://localhost:8080/ServidorWeb";



// Función para validar el formato del nickname
const validarFormatoNickname = (nickname) => {
  return /^[A-Za-zÁÉÍÓÚÑáéíóúñÀ-ÿĄ-ź0-9]*$/.test(nickname);
};

// Función para verificar si el nickname está en uso
const verificarNicknameEnUso = (nickname) => {
  const xhr = new XMLHttpRequest();
  const url = host + "/validarNickname?nickname=" + nickname;
  xhr.open('GET', url); // 409 si el nickname ya está en uso
  return new Promise((resolve, reject) => {
    xhr.onload = () => {
      if (xhr.status === 409) {
        reject("El nickname ya está en uso");
      } else {
        resolve();
      }
    };
    xhr.send();
  });
};

// Función para validar el nickname
const validarNickname = () => {
  console.log("validarNickname ejecutando");
  const nicknameInput = document.getElementById("nickname-input");
  const divNickname = document.getElementById("div-nickname-input");
  const nicknameInvalidFeedback = document.getElementById("nickname-invalid-feedback");

  divNickname.classList.add("was-validated");

  // Restablecer la validación y ocultar el mensaje de error
  nicknameInput.setCustomValidity("");
  nicknameInvalidFeedback.style.display = "none";

  // Validar que el nickname no esté vacío
  if (nicknameInput.value === "") {
    nicknameInput.setCustomValidity("El nickname no puede estar vacío");
    nicknameInvalidFeedback.innerText = "El nickname no puede estar vacío";
    nicknameInvalidFeedback.style.display = "block";
    return;
  }

  // Validar que el nickname no tenga espacios
  if (/\s/.test(nicknameInput.value)) {
    nicknameInput.setCustomValidity("El nickname no puede contener espacios");
    nicknameInvalidFeedback.innerText = "El nickname no puede contener espacios";
    nicknameInvalidFeedback.style.display = "block";
    return;
  }

  // Validar que el nickname no tenga más de 20 caracteres
  if (nicknameInput.value.length > 20) {
    nicknameInput.setCustomValidity("El nickname no puede contener más de 20 caracteres");
    nicknameInvalidFeedback.innerText = "El nickname no puede contener más de 20 caracteres";
    nicknameInvalidFeedback.style.display = "block";
    return;
  }

  // Validar que el nickname cumple con el formato
  if (!validarFormatoNickname(nicknameInput.value)) {
    nicknameInput.setCustomValidity("El nickname solo puede contener letras, números y caracteres especiales específicos");
    nicknameInvalidFeedback.innerText = "El nickname solo puede contener letras, números y caracteres especiales específicos";
    nicknameInvalidFeedback.style.display = "block";
    return;
  }

  // Implementar el debouncing para verificar si el nickname está en uso
  if (validarNickname.timer) {
    clearTimeout(validarNickname.timer);
  }

  validarNickname.timer = setTimeout(() => {
    verificarNicknameEnUso(nicknameInput.value)
        .then(() => {
          // El nickname está disponible
          console.log("El nickname está disponible");
        })
        .catch((error) => {
          // El nickname está en uso
          nicknameInput.setCustomValidity(error);
          nicknameInvalidFeedback.innerText = error;
          nicknameInvalidFeedback.style.display = "block";
        });
  }, 500); // Espera 500 ms después de la última entrada para realizar la verificación
};

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

  // Implementar el debouncing
  if (validarCorreoElectronico.timer) {
    clearTimeout(validarCorreoElectronico.timer);
  }

  validarCorreoElectronico.timer = setTimeout(() => {
    // valida con AJAX la disponibilidad del email
    const url = host + "/validarEmail?email=" + emailInput.value; // TODO: cambiar por la URL del servidor
    const xhr = new XMLHttpRequest();
    xhr.open('GET', url);
    xhr.onload = () => {
      if (xhr.status === 409) {
        emailInput.setCustomValidity("El email ya está en uso");
        emailInvalidFeedback.innerText = "El email ya está en uso, elige otro";
        emailInvalidFeedback.style.display = "block";
      }
    };
    xhr.send();
  }, 500); // Espera 500 ms después de la última entrada para realizar la verificación
};

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


  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  const forms = document.querySelectorAll(".needs-validation");

  confirmPasswordInput.addEventListener("input", validarContraseña);
  passwordInput.addEventListener("input", validarContraseña);
  imageInput.addEventListener("input", validarTamañoImagen);
  nombreInput.addEventListener("input", validarNombre);
  apellidoInput.addEventListener("input", validarApellido);
  emailInput.addEventListener("input", validarCorreoElectronico);
  nicknameInput.addEventListener("input", validarNickname);


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


