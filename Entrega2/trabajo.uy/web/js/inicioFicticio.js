function validarCredencialesNickname() {
    const nickname = document.getElementById("nickname-input").value;
    const password = document.getElementById("password-input").value;

    // Verificar las credenciales
    // Postulante
    if (nickname === "lgarcia" && password === "awdrg543") {
        window.location.href = "/postulante";
        return false; // false para evitar la recarga de la página
    // Empresa
    } else if (nickname === "EcoTech" && password === "qsxcdw43") {
        window.location.href = "/empresa";
        return false; // false para evitar la recarga de la página
    // Else
    } else {
        alert("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        return false;
    }
}

function validarCredencialesCorreo() {
    const correo = document.getElementById("correo-input").value;
    const password = document.getElementById("password-input").value;

    // Verificar las credenciales
    // Postulante
    if (correo === "lgarcia85@gmail.com" && password === "awdrg543") {
        window.location.href = "/postulante";
        return false; // false para evitar la recarga de la página
    // Empresa
    } else if (correo === "info@EcoTech.com" && password === "qsxcdw43") {
        window.location.href = "/empresa";
        return false; // false para evitar la recarga de la página
    // Else
    } else {
        alert("Credenciales incorrectas. Por favor, inténtalo de nuevo.");
        return false;
    }
}