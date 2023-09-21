document.addEventListener("DOMContentLoaded", function () {
  (() => {
    "use strict";
    const forms = document.getElementById("form-buscar");

    // Loop over them and prevent submission
    forms.addEventListener("submit", (event) => {
      event.preventDefault();
      event.stopPropagation();
      buscar();
    });
  })();
});

const buscar = () => {
  const buscar = document.getElementById("buscar-input").value.toLowerCase();
  if (buscar === "ecotech") {
    reemplazarContenidoEcotech();
  } else if (buscar === "globalhealth") {
    reemplazarContenidoGlobalHealth();
  } else {
    // Crear alert bootstrap para mostrar el error
    const alerta = document.createElement("div");
    alerta.classList.add(
      "alert",
      "alert-danger",
      "alert-dismissible",
      "fade",
      "show"
    );
    alerta.setAttribute("role", "alert");
    alerta.innerHTML = `
        <strong>Atención:</strong> No se encontraron resultados para la búsqueda <strong>${buscar}</strong>.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        `;
    const contenedor = document.getElementById("ofertas-laborales-panel");
    contenedor.innerHTML = "";
    contenedor.appendChild(alerta);
  }
};

const reemplazarContenidoEcotech = () => {
  const contenedor = document.getElementById("ofertas-laborales-panel");
  contenedor.innerHTML = `
    <div class="sin-bordes">
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://tinyurl.com/45nsf34m" class="img-fluid rounded-start" alt="..." />
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Desarrollador Frontend</h5>
                        <p class="card-text">
                            Unete a nuestro equipo de desarrollo fronted y crea
                            experiencias de usuario excepcionales.
                        </p>
                        <a href="visitante/consultarOfertaLaboral/DesarrolladorFrontEnd.html" class="card-link">Leer más</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-4">
                  <img src="https://tinyurl.com/4n2vpurk" class="img-fluid rounded-start" alt="..." />
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">A. de Marketing Digital</h5>
                        <p class="card-text">
                            Unete a nuestro equipo de marketing y trabaja en
                            estrategias digitales innovadoras.
                        </p>
                        <a href="visitante/consultarOfertaLaboral/A. de Marketing Digital.html" class="card-link">Leer más</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;
};

const reemplazarContenidoGlobalHealth = () => {
  const contenedor = document.getElementById("ofertas-laborales-panel");
  contenedor.innerHTML = `
    <div class="sin-bordes">
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="https://www.coherdi.mx/wp-content/uploads/2017/05/esrategias-inversion.jpg"
                        class="img-fluid rounded-start" alt="..." />
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Estratega de Negocios</h5>
                        <p class="card-text">
                            Forma parte de nuestro equipo de estategia y contribuye
                            al crecimiento de las empresas clientes.
                        </p>
                        <a href="visitante/consultarOfertaLaboral/Estratega de Negocios.html" class="card-link">
                            Leer más
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;
};

const limpiarBusqueda = () => {
  const contenedor = document.getElementById("ofertas-laborales-panel");
  contenedor.innerHTML = `
    <div class="sin-bordes">
    <div class="card mb-3">
    <div class="row g-0">
        <div class="col-md-4">
          <img src="https://tinyurl.com/45nsf34m" class="img-fluid rounded-start" alt="..." />
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title">Desarrollador Frontend</h5>
                <p class="card-text">
                    Unete a nuestro equipo de desarrollo fronted y crea
                    experiencias de usuario excepcionales.
                </p>
                <a href="visitante/consultarOfertaLaboral/DesarrolladorFrontEnd.html" class="card-link">Leer más</a>
            </div>
        </div>
    </div>
</div>
<div class="card mb-3">
    <div class="row g-0">
        <div class="col-md-4">
          <img src="https://tinyurl.com/4n2vpurk" class="img-fluid rounded-start" alt="..." />
        </div>
        <div class="col-md-8">
            <div class="card-body">
                <h5 class="card-title">A. de Marketing Digital</h5>
                <p class="card-text">
                    Unete a nuestro equipo de marketing y trabaja en
                    estrategias digitales innovadoras.
                </p>
                <a href="visitante/consultarOfertaLaboral/A. de Marketing Digital.html" class="card-link">Leer más</a>
            </div>
        </div>
    </div>
</div>
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-4">
                    <img src="https://www.coherdi.mx/wp-content/uploads/2017/05/esrategias-inversion.jpg"
                        class="img-fluid rounded-start" alt="..." />
                </div>
                <div class="col-md-8">
                    <div class="card-body">
                        <h5 class="card-title">Estratega de Negocios</h5>
                        <p class="card-text">
                            Forma parte de nuestro equipo de estategia y contribuye
                            al crecimiento de las empresas clientes.
                        </p>
                        <a href="visitante/consultarOfertaLaboral/Estratega de Negocios.html" class="card-link">
                            Leer más
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;
};
