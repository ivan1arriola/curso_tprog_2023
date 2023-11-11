package logica.persistencia;

import excepciones.PersistirOfertaNoFinalizada;
import logica.clases.OfertaLaboral;
import logica.clases.Postulacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;
import logica.dto.EmpresaDTO;
import logica.dto.OfertaLaboralDTO;
import logica.dto.PostulacionDTO;
import logica.dto.PostulanteDTO;
import logica.dto.UsuarioDTO;
import logica.enumerados.EstadoOL;
import logica.manejadores.OfertaLaboralHandler;

import java.util.List;

public class TrabajoUyHistoricoManager {


    private static TrabajoUyHistoricoManager TrabajoHistoricoHandler = null;
    private final EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public TrabajoUyHistoricoManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TrabajoUyHistorico");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static TrabajoUyHistoricoManager getInstance()  {
        if (TrabajoHistoricoHandler == null) {
            TrabajoHistoricoHandler = new TrabajoUyHistoricoManager();
        }
        return TrabajoHistoricoHandler;
    }

    public static void setBaseDatos(EntityManager emm) {
        entityManager = emm;
    }

    public void persistirOfertaFinalizada(OfertaLaboral ofertaLaboral) throws PersistirOfertaNoFinalizada {
//        if (!ofertaLaboral.getEstado().equals(EstadoOL.Finalizada))
//            throw new PersistirOfertaNoFinalizada("No se puede persistir una oferta laboral no finalizada en esta base de datos");
//
//        OfertaLaboralDTO ofertaLaboralDTO = ofertaLaboral.getDTO();
//
//        List<Postulacion> postulaciones = ofertaLaboral.getPostulaciones();
//
//        EntityTransaction transaction = entityManager.getTransaction();
//
//        try {
//            transaction.begin();
//
//            // Persistir la oferta laboral
//            entityManager.persist(ofertaLaboralDTO);
//
//            // Persistir las postulaciones
//            for (Postulacion postulacion : postulaciones) {
//                entityManager.persist(postulacion.getDTO(ofertaLaboralDTO));
//            }
//
//            transaction.commit();
//            System.out.println(ofertaLaboral.getNombre() + " se persistio en TrabajoUyHistorico");
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
//        } finally {
//            entityManager.close();
//        }
    }

    /** Busca si ya esta registrado el usuario en la base de datos
     * Si existe el registro, devuelve el DTO, si no existe, devulve null
     * @param nickname
     * @return
     */
    public UsuarioDTO obtenerUsuarioDT(String nickname) {

        try {
            // Iniciar transacción
            entityManager.getTransaction().begin();

            // Consulta para obtener el UsuarioDTO por su nickname
            String queryString = "SELECT u FROM UsuarioDTO u WHERE u.nickname = :nickname";
            List<UsuarioDTO> resultList = entityManager.createQuery(queryString, UsuarioDTO.class)
                    .setParameter("nickname", nickname)
                    .getResultList();

            // Confirmar la transacción
            entityManager.getTransaction().commit();

            if (!resultList.isEmpty()) {
                UsuarioDTO usuarioDTO = resultList.get(0);
//                System.out.println("EL NUMERO USUARIO ES: " + usuarioDTO.getNickname());
                return usuarioDTO;
            } else {
//                System.out.println("Usuario no encontrado para el nickname: " + nickname);
                return null; // Or handle this case according to your application's logic
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            // e.printStackTrace(); // Handle the exception appropriately in your application
            return null;
        }
    }


    public void GuardarOfertaFinalizada(OfertaLaboralDTO ofertaLaboraldto,EmpresaDTO Empresa){
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            
            // esta es la manera de vincularlo con empresa existente
            ofertaLaboraldto.setEmpresa(Empresa);
            entityManager.persist(ofertaLaboraldto);


            transaction.commit();
            System.out.println(ofertaLaboraldto.getNombre() + " se persistio en TrabajoUyHistorico");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
        } 
    }

    public void GuardarPostulacion(PostulacionDTO postulacion,PostulanteDTO postulante){
    	EntityTransaction transaction = entityManager.getTransaction();
    	try {
            transaction.begin();
            
            postulacion.setPostulante(postulante);
            
            entityManager.persist(postulacion);

            
            transaction.commit();
            System.out.println(" se persistio en TrabajoUyHistorico una postulacion");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
        } 
    }	
    
    public void GuardarPostulante(PostulanteDTO postulantetransformado) {
    	EntityTransaction transaction = entityManager.getTransaction();
    	try {
            transaction.begin();

            
            entityManager.persist(postulantetransformado);

            
            transaction.commit();
            System.out.println(" se persistio en TrabajoUyHistorico el postulante " + postulantetransformado.getNickname());
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
        } 
    }
    
    
    public void GuardarEmpresa(EmpresaDTO empresa) {
    	EntityTransaction transaction = entityManager.getTransaction();
    	try {
            transaction.begin();

            // Persistir la oferta laboral
            entityManager.persist(empresa);

            
            transaction.commit();
//            System.out.println(" se persistio en TrabajoUyHistorico la empresa " + empresa.getNickname());
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
        }
    }
    
    public void cerrarBaseDatos() {
    	entityManager.close();
    }

}


