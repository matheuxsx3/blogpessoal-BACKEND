package com.example.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nomeUsuario não pode ser nula")
    private String nome;
    @Schema(example = "email@email.com")
    @NotNull(message = "usuario não pode ser nula")
    private String usuario;
    @NotBlank(message = "senha não pode ser nula")
    private String senha;
    @Size(max = 5000, message = "a foto não pode ultrapassar 5000 caracteres")
    private String foto;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idUsuario",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("idUsuario")
    private List<Postagem> postagens;

    public Usuario(Long id, String nome, String usuario, String senha, String foto) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
    }
    public Usuario(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }
}
