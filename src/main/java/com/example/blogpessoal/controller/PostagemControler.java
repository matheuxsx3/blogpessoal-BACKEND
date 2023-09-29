package com.example.blogpessoal.controller;

import com.example.blogpessoal.model.postagens.Postagem;
import com.example.blogpessoal.repository.PostagemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemControler {
    private final PostagemRepository postagemRepository;

    public PostagemControler(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    @GetMapping
    public ResponseEntity<List<Postagem>> getAll() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable long id) {
        return postagemRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
