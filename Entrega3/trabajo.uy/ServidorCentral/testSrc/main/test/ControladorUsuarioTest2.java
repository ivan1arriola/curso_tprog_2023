
        
        
        

        


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

