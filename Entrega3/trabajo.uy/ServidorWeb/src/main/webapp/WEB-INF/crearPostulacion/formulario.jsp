<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="form-signin needs-validation" novalidate action="crearpostulacion" method="post">

      <!-- CV Abreviado -->
      <div class="form-group">
        <label for="cvAbreviado" class="form-label">CV Abreviado:</label>
        <textarea class="form-control" name="cvAbreviado" id="cvAbreviado" rows="4" placeholder="CV Abreviado"></textarea>
      </div>

      <!-- Motivación -->
      <div class="form-group">
        <label for="motivacion" class="form-label">Motivación:</label>
        <textarea class="form-control" name="motivacion" id="motivacion" rows="4" placeholder="Motivación"></textarea>
      </div>

        <!-- Video de YouTube -->
        <div class="form-group">
            <label for="videoYouTube" class="form-label">Enlace de Video de YouTube:</label>
            <input type="text" class="form-control" name="videoYouTube" id="videoYouTube" placeholder="Enlace de Video de YouTube">
        </div>


      <input type="hidden" name="nombreOferta" value="<%= request.getAttribute("oferta") %>">
      <input type="hidden" name="nombrePostulante" value="<%= session.getAttribute("nickname") %>">
      <input type="hidden" name="fechaPostulacion" id="fechaPostulacion" readonly disabled />

      <button class="btn btn-primary" id="btnPostularse" type="submit">
        Postularse
      </button>

</form>

<script src="<%= request.getContextPath() %>/js/crearPostulacion.js"></script>
