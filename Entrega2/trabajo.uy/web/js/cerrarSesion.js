const cerrarSesion = () => {
    localStorage.setItem("usuarioLogeado", false);
    localStorage.setItem("empresaLogeada", false);
    localStorage.setItem("postulanteLogeado", false);
    window.location.href = "/";
}