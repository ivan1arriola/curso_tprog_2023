<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Lista para Ofertas Laborales y Paquetes -->

<div class="card mb-3">
    <div class="row g-0">
        <div class="col-md-4">
            <img src="${param.imagenUrl}" class="img-fluid rounded-start" alt="${param.nombre}" />
        </div>
        <div class="col">
            <div class="card-body">
                <h5 class="card-title">${param.nombre}</h5>
                <p class="card-text">${param.descripcion}</p>
                <a href="${param.enlace}" class="card-link">Leer más</a>      
            </div>
        </div>
    </div>
</div>