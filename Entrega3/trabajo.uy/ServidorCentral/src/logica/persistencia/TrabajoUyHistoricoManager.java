package logica.persistencia;

import excepciones.PersistirOfertaNoFinalizada;
import logica.clases.OfertaLaboral;
import logica.clases.Postulacion;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.EntityTransaction;

import logica.dto.OfertaLaboralDTO;
import logica.dto.UsuarioDTO;
import logica.enumerados.EstadoOL;

import java.util.List;

public class TrabajoUyHistoricoManager {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public TrabajoUyHistoricoManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TrabajoUyHistorico");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void persistirOfertaFinalizada(OfertaLaboral ofertaLaboral) throws PersistirOfertaNoFinalizada {
        if (!ofertaLaboral.getEstado().equals(EstadoOL.Finalizada))
            throw new PersistirOfertaNoFinalizada("No se puede persistir una oferta laboral no finalizada en esta base de datos");

        OfertaLaboralDTO ofertaLaboralDTO = ofertaLaboral.getDTO();

        List<Postulacion> postulaciones = ofertaLaboral.getPostulaciones();

        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            // Persistir la oferta laboral
            entityManager.persist(ofertaLaboralDTO);

            // Persistir las postulaciones
            for (Postulacion postulacion : postulaciones) {
                entityManager.persist(postulacion.getDTO(ofertaLaboralDTO));
            }

            transaction.commit();
            System.out.println(ofertaLaboral.getNombre() + " se persistio en TrabajoUyHistorico");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
        } finally {
            entityManager.close();
        }
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
            UsuarioDTO usuarioDTO = entityManager.createQuery(queryString, UsuarioDTO.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();

            // Confirmar la transacción
            entityManager.getTransaction().commit();

            return usuarioDTO;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
           // e.printStackTrace(); // Manejar la excepción de manera adecuada en tu aplicación
            return null;
        } finally {
            entityManager.close();
        }
    }





}


