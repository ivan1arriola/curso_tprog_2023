
        
        
        

        

        DTUsuario empresa1 = ICU.obtenerDatosUsuario("Kreves");
        DTEmpresa DTverdaderaempresa1 = (DTEmpresa) empresa1; // Casting;
        ICU.ingresarDatosEditadosEmpresa(DTverdaderaempresa1.getNickname(),
                DTverdaderaempresa1.getNombre(),
                DTverdaderaempresa1.getApellido(),
                DTverdaderaempresa1.getcorreoElectronico(),
                DTverdaderaempresa1.getcontrasenia(),
                DTverdaderaempresa1.getDescripcion());

        ICU.ingresarDatosEditadosEmpresaImg(DTverdaderaempresa1.getNickname(),
                DTverdaderaempresa1.getNombre(),
                DTverdaderaempresa1.getApellido(),
                DTverdaderaempresa1.getcorreoElectronico(),
                DTverdaderaempresa1.getcontrasenia(),
                img23,
                DTverdaderaempresa1.getDescripcion());

        ICU.ingresarDatosEditadosEmpresaURLImg(DTverdaderaempresa1.getNickname(),
                DTverdaderaempresa1.getNombre(),
                DTverdaderaempresa1.getApellido(),
                DTverdaderaempresa1.getcorreoElectronico(),
                DTverdaderaempresa1.getcontrasenia(),
                DTverdaderaempresa1.getUrl(),
                img23,
                DTverdaderaempresa1.getDescripcion());

        ICU.ingresarDatosEditadosEmpresaURL(DTverdaderaempresa1.getNickname(),
                DTverdaderaempresa1.getNombre(),
                DTverdaderaempresa1.getApellido(),
                DTverdaderaempresa1.getcorreoElectronico(),
                DTverdaderaempresa1.getcontrasenia(),
                DTverdaderaempresa1.getUrl(),
                DTverdaderaempresa1.getDescripcion());
        // si no tiene url no lo toma,   en este caso google tiene url
        if (!ICU.tieneURL("Google")) {
            assertFalse("The test for user in system failed", true);
        }


        String img233 = "url";
        // ------------------------- tipo oferta ---------------------------
//			boolean booleano;
        ICO.altaTipoPublicacionOL("Oferta normal", "visibilidad normal", 1, 19, 100.0f, LocalDate.now());
        DTTipoOferta tipoOfertaDT = null;
        try {
            tipoOfertaDT = ICO.obtenerDatosTO("Oferta normal");
        } catch (ExcepcionTipoOfertaNoExistente e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        tipoOfertaDT.getNombre();
        tipoOfertaDT.getFechaAlta();
        tipoOfertaDT.getCosto();
        tipoOfertaDT.getDuracion();
        tipoOfertaDT.getExposicion();
        tipoOfertaDT.getDescripcion();
//			booleano = 
        ICO.altaTipoPublicacionOL("Oferta destacada", "visibilidad destacada", 1, 19, 100.0f, LocalDate.now());
        ICO.altaTipoPublicacionOL("Oferta super destacada", "visibilidad super destacada", 1, 19, 100.0f, LocalDate.now());
        ICO.altaPaqueteOL("Paquete 1", "un paquete basico", 1, LocalDate.now(), 10.0f, img233);

        // los paquetes empiezan vacios,   se les va agregando tipos de oferta

        ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta normal", 20);
        ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta destacada", 12);
        ICO.agregarTipoOfertaPaq("Paquete 1", "Oferta super destacada", 1);

        DTPaquete nuevo = ICO.obtenerDatosPaquete("Paquete 1");
        nuevo.getCosto();
        nuevo.getDescripcion();
        nuevo.getDescuento();
        nuevo.getFechaAlta();
        nuevo.getNombre();
        nuevo.getTiposDePub();
        nuevo.getValidez();
        // coloco todas keywors del sistema
        Set<String> listaKeywords = (HashSet<String>) ICO.listarKeywords();

        DTHora hora1 = new DTHora(8, 0);
        DTHora hora2 = new DTHora(1, 0);
        DTHorario horario = new DTHorario(hora1, hora2);
        String TIPOOFERTASELECCIONADA = "Oferta normal";


        String Nick = "Google";
        String desc = "investigador IA";
        String titulo = "Investigador de IA";
        String ciudad = "montevideo1";
        DepUY departamento = DepUY.Montevideo;
        LocalDate fecha = LocalDate.now();
        float sueldo = 100222.0f;
        String paquete = "Paquete 1";
        EstadoOL estado = EstadoOL.Confirmada;
        ICO.altaOfertaLaboral(Nick,
                TIPOOFERTASELECCIONADA,
                desc,
                titulo,
                horario,
                sueldo,
                ciudad,
                departamento,
                fecha,
                listaKeywords,
                estado,
                img233,
                paquete);

        DTOfertaLaboral temporal3 = new DTOfertaLaboral("Google",
                "investigador IA",
                LocalDate.now(),
                100222.0f,
                100222.0f,
                horario,
                departamento,
                ciudad,
                estado,
                img233);
        temporal3.getCiudad();
        temporal3.getCosto();
        temporal3.getDepartamento();
        temporal3.getDescripcion();
        temporal3.getestado();
        temporal3.getFechaDeAlta();
        temporal3.getHorario();
        temporal3.getImagen();
        temporal3.getNombre();
        temporal3.getRemuneracion();
        temporal3.toString();
//			Set<String> auxiliar = (HashSet<String>) 
        ICO.listarOfertasLaboralesConfirmadas("Google");
        // obtener nombres de los postulantes
//			Set<String> nombres = (HashSet<String>) 
        ICU.obtenerNicknamesPostulantes();


//			boolean existePostulacion(String nickname,   String nombre) {
//				UsuarioHandler UHan = UsuarioHandler.getInstance();
//				Postulante p = (Postulante) UHan.buscarNick(nickname);
//				if (p != null) 
//					return p.existePostulacion(nombre);
//				else
//					// throw new IllegalArgumentException("Usuario " + nick + " no existe");
//					return false;
//			}
//
//			public Postulacion crearPostulacion(String nick,   String cv,   String motivacion,   LocalDate fecha,   String URLDocExtras,   OfertaLaboral OferLab) 
    }

}

