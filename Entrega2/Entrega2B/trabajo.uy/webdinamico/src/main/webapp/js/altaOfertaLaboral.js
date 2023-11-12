const validarSelect = (selectElement) => {
  const valor = selectElement.value;

  if (valor === '0') {
    selectElement.setCustomValidity("Selecciona una opción");
  } else {
    selectElement.setCustomValidity("");
  }
}

(() => {
  'use strict'

  const forms = document.querySelectorAll('.needs-validation')

  const selectElements = document.querySelectorAll('.custom-select-validation');

  selectElements.forEach(selectElement => {
    selectElement.addEventListener("input", () => validarSelect(selectElement));
  });

  // Loop over them and prevent submission
  Array.from(forms).forEach(form => {
    form.addEventListener('submit', event => {
      if (!form.checkValidity()) {
        event.preventDefault();
        event.stopPropagation();
      }

      form.classList.add('was-validated');
    }, false);
  });
})();
