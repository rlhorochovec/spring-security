package br.rafaelhorochovec.springsecurity.dao;
 
import java.util.List;
 
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.rafaelhorochovec.springsecurity.model.UsuarioGrupo;

 
@Repository
@Transactional
public class GrupoDao {
 
    @Autowired
    private EntityManager entityManager;
 
    @SuppressWarnings("unchecked")
	public List<String> getGruposUsuario(Long usuarioId) {
        String sql = "Select ug.grupo.nome from " + UsuarioGrupo.class.getName() + " ug " //
                + " where ug.usuario.id = :usuarioId ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }
}