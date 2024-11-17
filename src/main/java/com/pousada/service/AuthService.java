//package com.pousada.service;
//
//import com.pousada.domain.entity.UsuarioEntity;
//import com.pousada.domain.repository.UsuarioRepository;
//import com.pousada.dto.UsuarioDTO;
//import com.pousada.exception.UsuarioJaExistenteException;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final ModelMapper modelMapper;
//    private final UsuarioRepository usuarioRepository;
//
//    public UsuarioDTO criarUsuario(UsuarioDTO usuarioDTO) {
//        if (usuarioRepository.existsByUsuario(usuarioDTO.getUsuario())) {
//            throw new UsuarioJaExistenteException("O nome de usuário já está sendo utilizado!");
//        }
//        UsuarioEntity usuarioEntity = modelMapper.map(usuarioDTO, UsuarioEntity.class);
//        UsuarioEntity usuarioEntitySalvo = usuarioRepository.save(usuarioEntity);
//
//        return modelMapper.map(usuarioEntitySalvo, UsuarioDTO.class);
//    }
//
//    public boolean login(UsuarioDTO usuarioDTO) {
//        Optional<UsuarioEntity> user = usuarioRepository.findByUsuario(usuarioDTO.getUsuario());
//        return user.isPresent() && user.get().getSenha().equals(usuarioDTO.getSenha());
//    }
//
//}
