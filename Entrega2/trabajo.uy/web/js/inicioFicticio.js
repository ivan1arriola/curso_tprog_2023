function validarCredencialesNickname() {
    var nickname = document.getElementById("nickname-input").value;
    var password = document.getElementById("password-input").value;

    // Verificar las credenciales
    if (nickname === "bloodymary" && password === "bm1234") {
        alert("Inicio de sesión exitoso.");
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", true);
        return true;
    } else if (nickname === "asd" && password === "asd") {

        alert("Inicio de sesión exitoso.");
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", true);
        localStorage.setItem("postulanteLogeado", false);
        return true;
    } else {
        alert("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        localStorage.setItem("usuarioLogeado", false);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", false);
        return false;
    }
}

const validarCredencialesCorreo = () => {
    const correo = document.getElementById("correo-input").value;
    const password = document.getElementById("password-input").value;

    // Verificar las credenciales
    if (correo === "bloodymary@fing.edu.uy" && password === "bm1234") {
        alert("Inicio de sesión exitoso.");
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", true);
        return true;
    } else if (correo === "asd@asd.com" && password === "asd") {

        alert("Inicio de sesión exitoso.");
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", true);
        localStorage.setItem("postulanteLogeado", false);
        return true;
    } else {
        alert("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        localStorage.setItem("usuarioLogeado", false);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", false);
        return false;
    }
}

// Cambios en DOM si el usuario está logeado

const usuarioLogeado = localStorage.getItem("usuarioLogeado");
const empresaLogeada = localStorage.getItem("empresaLogeada");
const postulanteLogeado = localStorage.getItem("postulanteLogeado");

document.addEventListener("DOMContentLoaded", () => {
    if (usuarioLogeado === "true") {
        inicioFalso();
    } 
});

const inicioFalso = () => {
    const sesion = document.getElementById("sesion");
    if (sesion && postulanteLogeado === "true") {
        // sobrescribir el contenido de sesion
        // tiene que mostrar un usuario logeado
        // nickname bloodymary
        // foto de perfil /img/bloodymary.jpg
        sesion.innerHTML = `
        <div class="usuario-logeado d-flex align-items-center">
            <img src="img/bloodymary.jpg" alt="Bloody Mary" class="rounded-circle" style="width: 60px; height: 60px;">
            <a href="/miPerfil" class="ml-3 mb-0">Bloody Mary</a>
        </div>
        `;

    }

    const miPerfil = document.getElementsByClassName("mi-perfil")[0];
    const miPerfilEmpresa = document.getElementsByClassName("empresa")[0];
    const miPerfilPostulante = document.getElementsByClassName("postulante")[0];

    if (miPerfil && postulanteLogeado === "true") {
        // mostrar seccion mi perfil
        miPerfil.style.display = "block";
        miPerfilEmpresa.style.display = "none";
        miPerfilPostulante.style.display = "block";
    } else if (miPerfil && empresaLogeada === "true") {
        miPerfil.style.display = "block";
        miPerfilEmpresa.style.display = "block";
        miPerfilPostulante.style.display = "none";
    }


    console.log("Inicio de sesión exitoso.");
}