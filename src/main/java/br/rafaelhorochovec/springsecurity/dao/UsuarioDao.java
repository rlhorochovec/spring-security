package br.rafaelhorochovec.springsecurity.dao;
 
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.rafaelhorochovec.springsecurity.model.Usuario;
 
@Repository
@Transactional
public class UsuarioDao {
 
    @Autowired
    private EntityManager entityManager;
 
    public Usuario findUserAccount(String usuario) {
        try {
            String sql = "Select u from " + Usuario.class.getName() + " u " //
                    + " Where u.usuario = :usuario ";
 
            Query query = entityManager.createQuery(sql, Usuario.class);
            query.setParameter("usuario", usuario);
 
            return (Usuario) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
 
}