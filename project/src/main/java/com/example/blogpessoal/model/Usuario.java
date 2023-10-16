package com.example.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
    @Size(min = 6, max = 255, message = "nomeUsuario deve conter de 6 a 255 caracteres.")
    private String nome;
    @NotBlank(message = "apelidoUsuario não pode ser nula")
    @Size(min = 6, max = 255, message = "apelidoUsuario deve conter de 6 a 255 caracteres.")
    private String usuario;
    @Email
    @NotNull(message = "emailUsuario não pode ser nula")
    private String email;
    @NotBlank(message = "senha não pode ser nula")
    @Size(min = 6, max = 255, message = "Senha deve conter de 6 a 255 caracteres.")
    private String senha;
    @Size(max = 5000, message = "a foto não pode ultrapassar 5000 caracteres")
    private String foto;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idUsuario",cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("idUsuario")
    private List<Postagem> postagens;





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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
