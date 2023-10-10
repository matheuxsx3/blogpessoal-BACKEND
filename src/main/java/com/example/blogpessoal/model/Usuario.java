package com.example.blogpessoal.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "nomeUsuario não pode ser nula")
    @Size(min = 6, max = 255, message = "nomeUsuario deve conter de 6 a 255 caracteres.")
    private String nome;
    @DateTimeFormat
    private Date dataNascimentoUsuario;
    @NotBlank(message = "apelidoUsuario não pode ser nula")
    @Size(min = 6, max = 255, message = "apelidoUsuario deve conter de 6 a 255 caracteres.")
    private String apelidoUsuario;
    @Email
    @NotNull(message = "emailUsuario não pode ser nula")
    private String emailUsuario;
    @NotBlank(message = "senha não pode ser nula")
    @Size(min = 6, max = 255, message = "Senha deve conter de 6 a 255 caracteres.")
    private String senhaUsuario;

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

    public Date getDataNascimentoUsuario() {
        return dataNascimentoUsuario;
    }

    public void setDataNascimentoUsuario(Date dataNascimentoUsuario) {
        this.dataNascimentoUsuario = dataNascimentoUsuario;
    }

    public String getApelidoUsuario() {
        return apelidoUsuario;
    }

    public void setApelidoUsuario(String apelidoUsuario) {
        this.apelidoUsuario = apelidoUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }
}
