function validarCredencialesNickname() {
    const nickname = document.getElementById("nickname-input").value;
    const password = document.getElementById("password-input").value;

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
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", true);
        return true;
    } else if (correo === "asd@asd.com" && password === "asd") {

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

// Cambia a carpeta postulante o empresa según corresponda

const usuarioLogeado = localStorage.getItem("usuarioLogeado");
const empresaLogeada = localStorage.getItem("empresaLogeada");
const postulanteLogeado = localStorage.getItem("postulanteLogeado");

document.addEventListener("DOMContentLoaded", () => {
    if (usuarioLogeado === "true") {
        inicioFalso();
    } 
});

const inicioFalso = () => {
    if (postulanteLogeado === "true") {
        window.location.href = "/postulante";
    } else if (empresaLogeada === "true") {
        window.location.href = "/empresa";
    } else {
        console.log("No hay usuario logeado.");
    }
}

