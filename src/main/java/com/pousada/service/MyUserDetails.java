//package com.pousada.service;
//
//import java.util.*;
//
//import com.pousada.domain.entity.PermissaoEntity;
//import com.pousada.domain.entity.UsuarioEntity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class MyUserDetails implements UserDetails {
//
//    private UsuarioEntity user;
//
//    public MyUserDetails(UsuarioEntity user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<PermissaoEntity> roles = user.getPermissoes();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (PermissaoEntity role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getNome()));
//        }
//
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getSenha();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsuario();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return user.isAtivo();
//    }
//
//}