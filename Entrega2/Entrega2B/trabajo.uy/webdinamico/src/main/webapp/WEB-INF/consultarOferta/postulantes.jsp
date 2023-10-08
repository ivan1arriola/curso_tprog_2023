<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Set" %>
<%@ page import="auxiliar.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="main.java.logica.datatypes.DTPostulacion" %>
<%@ page import="java.util.Base64" %>

<%

	TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario"); // Se supone que no se visualiza para Visitante
	Set<DTPostulacion> postulaciones;
	
	if(tipoUsuario == TipoUsuario.Postulante){
		
	}
%>



<div>
    <div class="mt-4" id="postulaciones">
        <h1 class="text-center">Postulaciones</h1>
    </div>

    <div class="container">
        <!-- Imagen 1 -->
        <div class="d-flex align-items-center">
            <img src="https://tinyurl.com/yckek63e" alt="Imagen 1" class="img-fluid" style="width: 100px; height: 100px">
            <a href="../consultaPostulacion/lgarciaDesarrolloFrontEnd.html" target="_blank" class="ms-4">
                lgarcia
            </a>
        </div>

        <!-- Imagen 2 -->
        <div class="d-flex align-items-center mt-2">
            <img src="https://picsum.photos/100/100" alt="Imagen 2" class="img-fluid">
            <a href="../consultaPostulacion/maroDesarrolloFrontEnd.html" target="_blank" class="ms-4">
                maro
            </a>
        </div>
    </div>
</div>

