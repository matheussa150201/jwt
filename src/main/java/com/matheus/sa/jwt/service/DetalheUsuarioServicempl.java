package com.matheus.sa.jwt.service;

import com.matheus.sa.jwt.data.DetalheUsuarioData;
import com.matheus.sa.jwt.model.UsuarioModel;
import com.matheus.sa.jwt.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServicempl implements UserDetailsService {

    private final UsuarioRepository repository;

    public DetalheUsuarioServicempl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> usuario = repository.findByLogin(username);
        if(usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usúario [" + username + "] não encontrado");
        }
        return new DetalheUsuarioData(usuario);
    }
}
