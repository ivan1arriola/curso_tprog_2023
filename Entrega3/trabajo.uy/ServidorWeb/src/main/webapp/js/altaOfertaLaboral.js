const validarSelect = (selectElement) => {
  const valor = selectElement.value;

  if (valor === '0') {
    selectElement.setCustomValidity("Selecciona una opción");
  } else {
    selectElement.setCustomValidity("");
  }
}

const verificarNombreEnUso = (nombre) => {
  const xhr = new XMLHttpRequest();
  const url = host + "/validarnombreoferta?oferta=" + nombre;
  xhr.open('GET',  url); // 409 si el nickname ya está en uso
  return new Promise((resolve,  reject) => {
    xhr.onload = () => {
      if (xhr.status === 409) {
        reject("El nombre ya está en uso");
      } else {
        resolve();
      }
    };
    xhr.send();
  });
};

// Función para validar el nickname
const validarnombre = () => {
  console.log("validarnombre ejecutando");
  const nombreInput = document.getElementById("nombre");
  const divnombre = document.getElementById("div-nombre-input");
  const nombreInvalidFeedback = document.getElementById("nombre-invalid-feedback");

  divnombre.classList.add("was-validated");

  // Restablecer la validación y ocultar el mensaje de error
  nombreInput.setCustomValidity("");
  nombreInvalidFeedback.style.display = "none";

  // Validar que el nombre no esté vacío
  if (nombreInput.value === "") {
    nombreInput.setCustomValidity("El nombre no puede estar vacío");
    nombreInvalidFeedback.innerText = "El nombre no puede estar vacío";
    nombreInvalidFeedback.style.display = "block";
    return;
  }

  

  // Implementar el debouncing para verificar si el nombre está en uso
  if (validarnombre.timer) {
    clearTimeout(validarnombre.timer);
  }

  validarnombre.timer = setTimeout(() => {
    verificarNombreEnUso(nombreInput.value)
        .then(() => {
          // El nombre está disponible
          console.log("El nombre está disponible");
          nombreInput.setCustomValidity();
        })
        .catch((error) => {
          // El nombre está en uso
          nombreInput.setCustomValidity(error);
          nombreInvalidFeedback.innerText = error;
          nombreInvalidFeedback.style.display = "block";
        });
  },  500); // Espera 500 ms después de la última entrada para realizar la verificación
};



(() => {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')

  const selectElements = document.querySelectorAll('.custom-select-validation');

  selectElements.forEach(selectElement => {
    selectElement.addEventListener("input",  () => validarSelect(selectElement));
  });

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit',  event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      form.classList.add('was-validated');
    },  false);
  });
})();
