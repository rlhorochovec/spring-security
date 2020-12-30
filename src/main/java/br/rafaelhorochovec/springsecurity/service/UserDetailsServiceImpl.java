package br.rafaelhorochovec.springsecurity.service;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.rafaelhorochovec.springsecurity.dao.GrupoDao;
import br.rafaelhorochovec.springsecurity.dao.UsuarioDao;
import br.rafaelhorochovec.springsecurity.model.Usuario;
 
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
 
    @Autowired
    private UsuarioDao usuarioDao;
 
    @Autowired
    private GrupoDao grupoDao;
 
    @Override
    public UserDetails loadUserByUsername(String _usuario) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioDao.findUserAccount(_usuario);
 
        if (usuario == null) {
            System.out.println("User not found! " + _usuario);
            throw new UsernameNotFoundException("User " + _usuario + " was not found in the database");
        }
 
        System.out.println("Found User: " + usuario.getUsuario());
 
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> grupos = this.grupoDao.getGruposUsuario(usuario.getId());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (grupos != null) {
            for (String grupo : grupos) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(grupo);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(usuario.getUsuario(), //
                usuario.getSenha(), grantList);
        return userDetails;
    }
}