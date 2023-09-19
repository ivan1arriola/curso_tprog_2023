const cerrarSesion = () => {
    const rutaActual = window.location.pathname;
    const rutaIndexHTML = rutaActual.substring(0, rutaActual.lastIndexOf('/')) + "/index.html";
    window.location.href = rutaIndexHTML;
}