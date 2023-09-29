package com.example.blogpessoal.model.postagens;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O atributo título é obrigatório!")
    @Size(min = 1, max = 100, message = "O atributo título deve ter no mínimo 1 caractere e no maximo 100!")
    private String ttulo;
    @NotBlank(message = "O atributo texto é obrigatório!")
    @Size(min = 1, max = 1000, message = "O atributo titulo deve ter no mínimo 1 caractere e no maximo 1000!")
    private String texto;
    @UpdateTimestamp
    private LocalDateTime data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTtulo() {
        return ttulo;
    }

    public void setTtulo(String ttulo) {
        this.ttulo = ttulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}

