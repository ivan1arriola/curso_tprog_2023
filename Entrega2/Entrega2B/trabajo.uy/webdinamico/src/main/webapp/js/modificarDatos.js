document.addEventListener("DOMContentLoaded", function () {
  // Ocultar los botones de aceptar y cancelar
  const aceptarBtn = document.getElementById("aceptarBtn");
  const cancelarBtn = document.getElementById("cancelarBtn");
  const passwordSec = document.getElementById("PasswordSection");

  aceptarBtn.style.display = "none";
  cancelarBtn.style.display = "none";
  passwordSec.style.display="none";

  // Vincular la función al botón de modificar
  const modificarBtn = document.getElementById("modificarBtn");
  modificarBtn.addEventListener("click", modificarDatos);
  aceptarBtn.addEventListener("click", aceptarCambios);
  cancelarBtn.addEventListener("click", cancelarCambios);
});

function modificarDatos() {
  // Habilitar los botones de aceptar y cancelar
  const aceptarBtn = document.getElementById("aceptarBtn");
  const cancelarBtn = document.getElementById("cancelarBtn");
  const modificarBtn = document.getElementById("modificarBtn");
  const passwordSec = document.getElementById("PasswordSection");

  aceptarBtn.style.display = "inline-block";
  cancelarBtn.style.display = "inline-block";
  modificarBtn.style.display = "none";
  passwordSec.style.display="block";

  // Habilitar los campos del formulario
  document.getElementById("nombre").disabled = false;
  document.getElementById("apellido").disabled = false;
  
  
  
  	const fechanacimientoInput = document.getElementById("fechanacimiento");
	const nacionalidadInput = document.getElementById("nacionalidad");
	const descripcionInput = document.getElementById("descripcion");
	const linkInput = document.getElementById("link");

	if (fechanacimientoInput && nacionalidadInput) {
	    fechanacimientoInput.disabled = false;
	    nacionalidadInput.disabled = false;
	} else if (descripcionInput && linkInput) {
	    descripcionInput.disabled = false;
	    linkInput.disabled = false;
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
                    } else {
						document.getElementById("nickname").disabled = false;
						document.getElementById("correo").disabled = false;
					}
    
                    // Agrega la clase CSS de Bootstrap para mostrar los estilos de validación
                    form.classList.add("was-validated");
                },
                false
            );
        });
    })();
   
});
