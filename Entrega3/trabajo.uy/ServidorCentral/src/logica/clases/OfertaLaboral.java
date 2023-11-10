package logica.clases;

import excepciones.*;
import jakarta.persistence.*;

import logica.Utils;
import logica.datatypes.*;
import logica.enumerados.DepUY;
import logica.enumerados.EstadoOL;


import java.time.LocalDate;
import java.util.*;


@Entity
public class OfertaLaboral {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // atributos
    private String nombre;
    private String descripcion;
    private LocalDate fechaAlta;
    private Float costo; // atributo calculado
    private Float remuneracion;

    private String horario;
    private DepUY departamento;
    private String ciudad;
    private EstadoOL estado;

    private Integer cantFavs;
    
    private Integer cantVisitas;
    
    @Lob
    private String imagen;



    private boolean hayOrdenDefinido = false;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private TipoOferta tOferta;

    @OneToMany (cascade = CascadeType.PERSIST)
    private List<Keyword> keywords;
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Paquete paqueteAsoc;
    @OneToMany(mappedBy = "oferLab", cascade = CascadeType.PERSIST)
    private List<Postulacion> postulaciones;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "empresa_id")
    private Empresa empresaPublicadora;
    
    



    public OfertaLaboral(
            Empresa empresaPublicadora,
            List<Keyword> atrkeywords,
            TipoOferta atrtOferta,
            String atrnombre,
            String atrdescripcion,
            String atrciudad,
            DepUY atrdepartamento,
            DTHorario atrhorario,
            Float atrremuneracion,
            LocalDate atrfechaAlta,
            EstadoOL estadoNuevo,
            byte[] imagennueva,
            Paquete paq
    ) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {

        this.nombre = atrnombre;
        this.descripcion = atrdescripcion;
        this.ciudad = atrciudad;
        this.departamento = atrdepartamento;
        this.setHorario(atrhorario);
        
        cantVisitas = 0;

        try {
        	if (Float.compare(atrremuneracion, 0.0f) <= 0) {
                throw new ExceptionRemuneracionOfertaLaboralNegativa("La remuneración debe ser mayor que 0");
            } else {
                this.remuneracion = atrremuneracion;
            }
        } catch (ExceptionRemuneracionOfertaLaboralNegativa exc) {
            System.out.println(exc);
            throw exc;
        }

        this.tOferta = atrtOferta;
        this.estado = estadoNuevo;
        this.cantFavs = 0;
        this.setImagen(imagennueva);




        try {
            if (paq != null) {
                if (paq.getfechaAlta().plusDays(paq.getValidez()).isBefore(LocalDate.now())) {
                    throw new ExceptionPaqueteNoVigente("El paquete no se encuentra vigente");
                } else {
                    this.paqueteAsoc = paq;
                }

            }
        } catch (ExceptionPaqueteNoVigente exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;
        }


        this.empresaPublicadora = empresaPublicadora;

        if (this.paqueteAsoc != null) {
            float costodadoPaq = 0;
            float descuento = 0;


            try {
                if (Float.compare(tOferta.getCosto(), 0.0f) <= 0) {
                    throw new ExceptionCostoPaqueteNoNegativo("El costo del paquete debe ser mayor que 0");
                }
                costodadoPaq = tOferta.getCosto();

                if (Float.compare(paqueteAsoc.getDescuento(), 0.0f) < 0) {
                    throw new ExceptionDescuentoInvalido("El descuento debe ser mayor o igual a 0");
                }

                descuento = paqueteAsoc.getDescuento();

                Set<DTCantTO> restantes = this.paqueteAsoc.obtenerDTSCantTO();

                int cantidadAsociada = 0;
                for (DTCantTO offer : restantes) {

                    if (offer.getNombre().equals(this.tOferta.getNombre())) {
                        cantidadAsociada = offer.getCantidad();
                        break;
                    }
                } //cierra for
                if (cantidadAsociada == 0) {
                    throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("No hay disponibilidad del Tipo de Oferta seleccionado en el Paquete Elegido");
                }


            } catch (ExceptionCostoPaqueteNoNegativo excCosto) {
                System.err.println("Error: " + excCosto.getMessage());
                throw excCosto;
            } catch (ExceptionDescuentoInvalido excDesc) {
                System.err.println("Error: " + excDesc.getMessage());
                throw excDesc;
            } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa excCant) {
                System.err.println("Error: " + excCant.getMessage());
                throw excCant;
            }


            if (Float.compare(paqueteAsoc.getDescuento(), 0.0f) == 0) {
                this.costo = costodadoPaq;
            } else {
                //this.costo = costodadoPaq - costodadoPaq * (1 / descuento);
                this.costo = costodadoPaq * (1 - descuento / 100);

            }

        } else {
            this.costo = tOferta.getCosto();
        }

        this.fechaAlta = atrfechaAlta;
        this.keywords = atrkeywords; // la lista de keywords
        this.postulaciones = new ArrayList<>(); // originalmente vacío

        System.out.println("Se ha creado una Oferta Laboral. - " + nombre);

    }


    public OfertaLaboral(
            boolean forzado,
            Empresa empresaPublicadora,
            List<Keyword> atrkeywords,
            TipoOferta atrtOferta,
            String atrnombre,
            String atrdescripcion,
            String atrciudad,
            DepUY atrdepartamento,
            DTHorario atrhorario,
            Float atrremuneracion,
            LocalDate atrfechaAlta,
            EstadoOL estadoNuevo,
            byte[] imagennueva,
            Paquete paq
    ) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido {

        this.nombre = atrnombre;
        this.descripcion = atrdescripcion;
        this.ciudad = atrciudad;
        this.departamento = atrdepartamento;
        this.setHorario(atrhorario);

        cantVisitas = 0;
        try {
            if (atrremuneracion <= 0) {
                throw new ExceptionRemuneracionOfertaLaboralNegativa("La remuneración debe ser mayor que 0");
            } else {
                this.remuneracion = atrremuneracion;
            }
        } catch (ExceptionRemuneracionOfertaLaboralNegativa exc) {
            System.out.println(exc);
            throw exc;
        }

        this.tOferta = atrtOferta;
        this.estado = estadoNuevo;

        
        this.cantFavs = 0;

        this.setImagen(imagennueva);



        this.paqueteAsoc = paq;


        this.empresaPublicadora = empresaPublicadora;

        if (this.paqueteAsoc != null) {
            float costodadoPaq = 0;
            float descuento = 0;

            try {
                if (tOferta.getCosto() <= 0) {
                    throw new ExceptionCostoPaqueteNoNegativo("El costo del paquete debe ser mayor que 0");
                }
                costodadoPaq = tOferta.getCosto();

                if (paqueteAsoc.getDescuento() < 0) {
                    throw new ExceptionDescuentoInvalido("El descuento debe ser mayor o igual a 0");
                }

                descuento = paqueteAsoc.getDescuento();

            } catch (ExceptionCostoPaqueteNoNegativo excCosto) {
                System.err.println("Error: " + excCosto.getMessage());
                throw excCosto;
            } catch (ExceptionDescuentoInvalido excDesc) {
                System.err.println("Error: " + excDesc.getMessage());
                throw excDesc;
            }


            if (Float.compare(paqueteAsoc.getDescuento(), 0.0f) == 0) {
                this.costo = costodadoPaq;
            } else {
                //this.costo = costodadoPaq - costodadoPaq * (1 / descuento);
                this.costo = costodadoPaq * (1 - descuento / 100);

            }

        } else {
            this.costo = tOferta.getCosto();
        }

        this.fechaAlta = atrfechaAlta;
        this.keywords = atrkeywords; // la lista de keywords
        this.postulaciones = new ArrayList<>(); // originalmente vacío

        System.out.println("Se ha creado una Oferta Laboral (forzado). - " + nombre);
    }

    public void setHorario(DTHorario atrhorario) {
        this.horario = atrhorario.toString();
    }

    // Constructor sin imagen ni paquete
    public OfertaLaboral(
            Empresa empresaPublicadora,
            List<Keyword> atrkeywords,
            TipoOferta atrtOferta,
            String atrnombre,
            String atrdescripcion,
            String atrciudad,
            DepUY atrdepartamento,
            DTHorario atrhorario,
            Float atrremuneracion,
            LocalDate atrfechaAlta,
            EstadoOL estadoNuevo
    ) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        this(
                empresaPublicadora,
                atrkeywords,
                atrtOferta,
                atrnombre,
                atrdescripcion,
                atrciudad,
                atrdepartamento,
                atrhorario,
                atrremuneracion,
                atrfechaAlta,
                estadoNuevo,
                null,   // Imagen nula
                null // Paquete nulo
        );
    }

    // Constructor sin imagen pero con paquete
    public OfertaLaboral(
            Empresa empresaPublicadora,
            List<Keyword> atrkeywords,
            TipoOferta atrtOferta,
            String atrnombre,
            String atrdescripcion,
            String atrciudad,
            DepUY atrdepartamento,
            DTHorario atrhorario,
            Float atrremuneracion,
            LocalDate atrfechaAlta,
            EstadoOL estadoNuevo,
            Paquete paq
    ) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        this(
                empresaPublicadora,
                atrkeywords,
                atrtOferta,
                atrnombre,
                atrdescripcion,
                atrciudad,
                atrdepartamento,
                atrhorario,
                atrremuneracion,
                atrfechaAlta,
                estadoNuevo,
                null,    // Imagen nula
                paq
        );
    }

    // Constructor sin paquete y con imagen
    public OfertaLaboral(
            Empresa empresaPublicadora,
            List<Keyword> atrkeywords,
            TipoOferta atrtOferta,
            String atrnombre,
            String atrdescripcion,
            String atrciudad,
            DepUY atrdepartamento,
            DTHorario atrhorario,
            Float atrremuneracion, 
            LocalDate atrfechaAlta,
            EstadoOL estadoNuevo,
            byte[] imagennueva
    ) throws ExceptionRemuneracionOfertaLaboralNegativa, ExceptionPaqueteNoVigente, ExceptionCostoPaqueteNoNegativo, ExceptionDescuentoInvalido, ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        this(
                empresaPublicadora,
                atrkeywords,
                atrtOferta,
                atrnombre,
                atrdescripcion,
                atrciudad,
                atrdepartamento,
                atrhorario,
                atrremuneracion,
                atrfechaAlta,
                estadoNuevo,
                imagennueva,
                null  // Paquete nulo
        );
    }

    public OfertaLaboral() {
        
    }


    // Getters
    public String getNombre() {
        return nombre;
    }

    // Setters
    public void setNombre(String nomb) {
        nombre = nomb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Desc) {
        descripcion = Desc;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String Ciud) throws ExceptionCiudadInvalida {
        try {
            if (Ciud.matches("^[a-zA-Z][a-zA-Z\\s+]*$")) {
                ciudad = Ciud;
            } else {
                throw new ExceptionCiudadInvalida("Ciudad incorrecta");
            }
        } catch (ExceptionCiudadInvalida exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;
        }
    }

    public DepUY getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepUY Departa) {
        departamento = Departa;
    }

    public DTHorario getHorario() {
        return new DTHorario(horario);
    }

    public Float getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(Float Remunera) throws ExceptionRemuneracionOfertaLaboralNegativa {
        try {
            if (Float.compare(Remunera, 0.0f) <= 0) {
                throw new ExceptionRemuneracionOfertaLaboralNegativa("Remuneración debe ser mayor que 0");
            } else {
                remuneracion = Remunera;
            }
        } catch (ExceptionRemuneracionOfertaLaboralNegativa exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;
        }
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fecha) throws ExceptionPaqueteNoVigente {
        try {
            if (this.paqueteAsoc != null) {
                LocalDate paqAlta = this.paqueteAsoc.getfechaAlta();
                LocalDate fechaLimite = paqAlta.plusDays(this.paqueteAsoc.getValidez());
                if (LocalDate.now().isAfter(fechaLimite)) {
                    throw new ExceptionPaqueteNoVigente("En la fecha seleccionada,   el paquete no está vigente");
                }
            }
            fechaAlta = fecha;
        } catch (ExceptionPaqueteNoVigente exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;
        }
    }
    



    public boolean isHayOrdenDefinido() {
        return hayOrdenDefinido;
    }

    public void setHayOrdenDefinido(boolean hayOrdenDefinido) {
        this.hayOrdenDefinido = hayOrdenDefinido;
    }
    
    public Float getCosto() {
        return costo;
    }

    public void setCosto(float cost) throws ExceptionCostoPaqueteNoNegativo {
        try {
            if (Float.compare(cost, 0.0f) <= 0) {
                throw new ExceptionCostoPaqueteNoNegativo("El costo debe ser mayor que 0");
            }
            costo = cost;
        } catch (ExceptionCostoPaqueteNoNegativo exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;

        }
    }

    public List<Postulacion> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(List<Postulacion> posts) {
        postulaciones = posts;
    }

    public TipoOferta getTipoOferta() {
        return tOferta;
    }

    public void setTipoOferta(TipoOferta tipoOfer) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {
        try {
            if (this.paqueteAsoc != null) {
                Set<OfertaPaquete> restantes = this.paqueteAsoc.getOfertaPaquete();

                for (OfertaPaquete offer : restantes) {

                    if (offer.getDTCantTO().getNombre().equals(tipoOfer.getNombre())) {
                        int cantidadAsociada = offer.getDTCantTO().getCantidad();
                        if (cantidadAsociada == 0) {
                            throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("No hay disponibilidad del Tipo de Oferta seleccionado en el Paquete Elegido");
                        }

                    }
                } //cierra for

            }

            tOferta = tipoOfer;
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;
        }
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keys) {
        keywords = keys;
    }

    public EstadoOL getEstado() {
        return estado;
    }

    public void setEstado(EstadoOL estad) {
        estado = estad;
    }

    public byte[] getImagen() {
        if (imagen == null) return null;
        return Base64.getDecoder().decode(imagen);
    }

    public void setImagen(byte[] imagen) {
        if (imagen == null) return;
        byte[] base64EncodedBytes = Base64.getEncoder().encode(imagen);
        // Convert the byte array to a Base64 string

        this.imagen = new String(base64EncodedBytes);
    }

    public Paquete getPaquete() {
        return paqueteAsoc;
    }

    public void setPaquete(Paquete paqueteA) throws ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa {

        try {
            Set<OfertaPaquete> restantes = paqueteA.getOfertaPaquete();

            for (OfertaPaquete offer : restantes) {

                if (offer.getDTCantTO().getNombre().equals(this.getTipoOferta().getNombre())) {
                    int cantidadAsociada = offer.getDTCantTO().getCantidad();
                    if (cantidadAsociada == 0) {
                        throw new ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa("No hay disponibilidad del Tipo de Oferta seleccionado en el Paquete Elegido");
                    }

                }
            } //cierra for

            paqueteAsoc = paqueteA;
        } catch (ExceptionCantidadRestanteDeUnTipoDeOfertaEnUnPaqueteEsNegativa exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;
        }
    }

    // -------------- funciones ---------------------

    public void registrarPostulacion(Postulacion post) throws ExceptionFechaInvalida {
        try {
            int dura = this.getTipoOferta().getDuracion();
            LocalDate altaOferta = this.getTipoOferta().getFechaAlta();
            if (altaOferta.plusDays(dura).isAfter(LocalDate.now())) {
                throw new ExceptionFechaInvalida("Oferta no vigente");
            }

            postulaciones.add(post);
        } catch (ExceptionFechaInvalida exc) {
            System.err.println("Error: " + exc.getMessage());
            throw exc;

        }
    } // registra postulacion a la lista de postulaciones

    public void registrarPostulacionForzado(Postulacion post) throws ExceptionFechaInvalida {
        //int dura = this.getTipoOferta().getDuracion();
        //LocalDate altaOferta = this.getTipoOferta().getFechaAlta();
        postulaciones.add(post);

    } // registra postulacion a la lista de postulaciones

    public DTOfertaExtendido obtenerDatosOferta() {
        Set<DTPostulacion> posts = new HashSet<>();
        // muestro todas las postulaciones
        for (int i = 0; i < postulaciones.size(); i++) {
            Postulacion elem = postulaciones.get(i);
            // obtengo DTPostulacion para cada una
            posts.add(elem.obtenerDT());
        }
        Paquete paq = getPaquete();
        String paq_nomb = null;
        if (paq != null) {
            paq_nomb = paq.getNombre();
        }
        DTOfertaExtendido dtoe = new DTOfertaExtendido(getEmpresaPublicadora().getNickname(), getNombre(), getDescripcion(), getFechaAlta(), getCosto(), getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), posts, getImagen(), paq_nomb, getCantFav(), getCantVisitas(), getTipoOferta().getNombre(), estaVencida(), hayOrdenDefinido);
        return dtoe;
    }

    public boolean tieneKeyword(String keyword) {
        for (Keyword item : keywords) {
            if (item.getNombre().equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    public DTOfertaExtendidoSinPConK infoOfertaLaboralVisitante() {
        List<Keyword> keys = getKeywords();
        Set<String> nuevo = new HashSet<>();
        for (Keyword item : keys) {
            nuevo.add(item.getNombre());
        }
        DTOfertaExtendidoSinPConK dtoe = new DTOfertaExtendidoSinPConK(getEmpresaPublicadora().getNickname(), getNombre(), getDescripcion(), getFechaAlta(), getCosto(), getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), getImagen(), nuevo, getCantFav(), getCantVisitas(), getTipoOferta().getNombre());
        return dtoe;
    }


    public boolean existePostulacion(String nombre_postulante) {
        for (Postulacion item : postulaciones) {
            if (nombre_postulante.equals(item.obtenerNicknamePostulante())) {
                return true;
            }
        }
        return false;
    }

    public DTOfertaExtendidoConKeywordsTit infoOfertaLaboralPropietario() {
        List<Keyword> keys = getKeywords();
        Set<String> nicknamesPostulantes = new TreeSet<>();
        for (Postulacion item : postulaciones) {
            nicknamesPostulantes.add(item.obtenerNicknamePostulante());
        }
        DTOfertaExtendidoConKeywordsTit dtoe;
        if (getPaquete() != null) {
            dtoe = new DTOfertaExtendidoConKeywordsTit(getEmpresaPublicadora().getNickname(), getNombre(), getDescripcion(), getFechaAlta(), getCosto(), getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), getImagen(), nicknamesPostulantes, getPaquete().getDTPaquete(), nicknamesPostulantes, getCantFav(), getCantVisitas(), getTipoOferta().getNombre());
        } else {
            dtoe = new DTOfertaExtendidoConKeywordsTit(getEmpresaPublicadora().getNickname(), getNombre(), getDescripcion(), getFechaAlta(), getCosto(), getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), getImagen(), nicknamesPostulantes, null, nicknamesPostulantes, getCantFav(), getCantVisitas(), getTipoOferta().getNombre());
        }
        return dtoe;
    }

    public DTPostulacion obtenerDatosPostulacion(String nombre_empresa) {
        List<Postulacion> lista = getPostulaciones();
        for (Postulacion item : lista) {
            item.obtenerNicknamePostulante();
        }
        return null; // FALTA ENCONTRAR EL DCOM
    }

    // ----------------------------------------------------

    public Set<DTPostulacion> ObtenerPostulacionesOfertaLaboral() {
        List<Postulacion> lista = getPostulaciones();
        Set<DTPostulacion> stringSet = new HashSet<>();
        for (Postulacion item : lista) {
            DTPostulacion post = item.obtenerDT();
            stringSet.add(post);
        }
        return stringSet;
    }

    // ----------------------------------------------------


    public DTOfertaExtendidoConKeywordsPostulante infoOfertaLaboralPost(String nombre_postulante) {
        int indicebuscado = 0;
        boolean salir = false;
        for (int i = 0; i < postulaciones.size() && !salir; i++) {
            if (postulaciones.get(i).obtenerNicknamePostulante().equals(nombre_postulante)) {
                indicebuscado = i;
                salir = true;
            }
        }
        DTPostulacion dtPost = postulaciones.get(indicebuscado).obtenerDT();
        Set<String> keys = new HashSet<String>();
        for (int i = 0; i < keywords.size() && !salir; i++) {
            keys.add(keywords.get(i).getNombre());
        }
        DTOfertaExtendidoConKeywordsPostulante entregar = new DTOfertaExtendidoConKeywordsPostulante(getEmpresaPublicadora().getNickname(), getNombre(), getDescripcion(), getFechaAlta(), getCosto(), getRemuneracion(), getHorario(), getDepartamento(), getCiudad(), getEstado(), getImagen(), keys, dtPost, getCantFav(), getCantVisitas(), getTipoOferta().getNombre());


        return entregar;
    }

    public Empresa getEmpresaPublicadora() {
        return empresaPublicadora;
    }

    public void setEmpresaPublicadora(Empresa empresaPublicadora) {
        this.empresaPublicadora = empresaPublicadora;
    }

    public boolean estaVencida() {
    	return LocalDate.now().isAfter(fechaAlta.plusDays(tOferta.getDuracion()));
    }

    // ===============================================================
    public void establecerPosicion(List<String> nickPostulantes) throws AsignarOrdenAOfertaFinalizada, AsignarOrdenAOfertaNoVencida {
        if (getEstado() == EstadoOL.Finalizada) throw new AsignarOrdenAOfertaFinalizada("No se reasignar el orden de postulantes porque la oferta ya finalizo");
        if (!estaVencida()) throw new AsignarOrdenAOfertaNoVencida("No se puede asignar un orden a una oferta que no vencio");

        List<Postulacion> postulacionesOferta = getPostulaciones();

        // Ordena la lista de postulaciones según el orden de los nicknames
        postulacionesOferta.sort(Comparator.comparingInt(p -> nickPostulantes.indexOf(p.obtenerNicknamePostulante())));

        // Setea la lista ordenada
        setPostulaciones(postulacionesOferta);
        setHayOrdenDefinido(true);

    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
    public Integer getCantFav() {
    	return cantFavs;
    }
    
    public void setCantFav(Integer cantF) {
    	cantFavs = cantF;
    }
    
    public void marcadaFav() {
    	cantFavs = cantFavs + 1;
    }
    
    public void desmarcadaFav() {
    	cantFavs = cantFavs - 1;
    }


    public Integer getCantVisitas() {
		return cantVisitas;
	}


	public void setCantVisitas(Integer cantVisitas) {
		this.cantVisitas = cantVisitas;
	}

    public List<String> getOrdenPostulantes() throws NoHayOrdenDefinidoDePostulantes {
        if (!isHayOrdenDefinido()) throw new NoHayOrdenDefinidoDePostulantes("La oferta laboral " + getNombre() + " aun no tiene un orden de postulantes definido");

        List<String> nicknamesPostulantes = new ArrayList<>();

        for (Postulacion postulacion : postulaciones){
            nicknamesPostulantes.add(postulacion.obtenerNicknamePostulante());
        }

        return nicknamesPostulantes;
    }

    public void finalizarOferta() {
        if (hayOrdenDefinido) {
            List<Postulacion> postulacionesOferta = getPostulaciones();
            int posicion = 0;
            for (Postulacion postulacion : postulacionesOferta) {
                posicion++;
                postulacion.setClasificacion(posicion);
            }
        }
        setEstado(EstadoOL.Finalizada);
    }

    public void descartarOrden() {
        setHayOrdenDefinido(false);
    }
}
