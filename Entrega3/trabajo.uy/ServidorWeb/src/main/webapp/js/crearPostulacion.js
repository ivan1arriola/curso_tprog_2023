document.addEventListener("DOMContentLoaded",  () => {
    const fechaInput = document.getElementById("fechaPostulacion");

    // Obtener la fecha actual
    const fechaActual = new Date();

    // Obtener el día,  mes y año
    const dia = fechaActual.getDate();
    const mes = fechaActual.getMonth() + 1; // Los meses en JavaScript se cuentan desde 0 (enero) hasta 11 (diciembre)
    const anio = fechaActual.getFullYear();

    // Formatear la fecha en el formato ISO 8601 (yyyy-MM-dd)
    // Asignar la fecha formateada al campo de entrada
    fechaInput.value = `${anio}-${mes.toString().padStart(2,  '0')}-${dia.toString().padStart(2,  '0')}`;
});
