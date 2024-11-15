//package com.pousada.domain.entity;
//
//import java.util.*;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "usuario")
//public class UsuarioEntity {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String usuario;
//    private String senha;
//    private boolean ativo;
//
//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "usuario_permissao",
//            joinColumns = @JoinColumn(name = "id_usuario"),
//            inverseJoinColumns = @JoinColumn(name = "id_permissao")
//    )
//    private Set<PermissaoEntity> permissoes = new HashSet<>();
//
//}
