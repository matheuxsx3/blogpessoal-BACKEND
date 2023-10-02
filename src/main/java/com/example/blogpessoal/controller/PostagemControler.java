package com.example.blogpessoal.controller;

import com.example.blogpessoal.model.postagens.Postagem;
import com.example.blogpessoal.repository.PostagemRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));
    }

    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
        return postagemRepository.findById(postagem.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        Optional<Postagem> optional = postagemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        postagemRepository.deleteById(id);
    }
}
