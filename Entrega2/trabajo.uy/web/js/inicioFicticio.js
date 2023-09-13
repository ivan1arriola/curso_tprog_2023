function validarCredencialesNickname() {
    const nickname = document.getElementById("nickname-input").value;
    const password = document.getElementById("password-input").value;

    // Verificar las credenciales
    // Postulante
    if (nickname === "lgarcia" && password === "awdrg543") {
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", true);
        return true;

    // Empresa
    } else if (nickname === "EcoTech" && password === "qsxcdw43") {

        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", true);
        localStorage.setItem("postulanteLogeado", false);
        return true;
    // Else
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
    // Postulante
    if (correo === "lgarcia85@gmail.com" && password === "awdrg543") {
        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", false);
        localStorage.setItem("postulanteLogeado", true);
        return true;

    // Empresa
    } else if (correo === "info@EcoTech.com" && password === "qsxcdw43") {

        localStorage.setItem("usuarioLogeado", true);
        localStorage.setItem("empresaLogeada", true);
        localStorage.setItem("postulanteLogeado", false);
        return true;

    // Else
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

