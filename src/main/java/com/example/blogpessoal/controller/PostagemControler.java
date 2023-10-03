package com.example.blogpessoal.controller;

import com.example.blogpessoal.model.Postagem;
import com.example.blogpessoal.repository.PostagemRepository;
import com.example.blogpessoal.repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private TemaRepository temaRepository;

    public PostagemControler(PostagemRepository postagemRepository, TemaRepository temaRepository) {
        this.postagemRepository = postagemRepository;
        this.temaRepository = temaRepository;
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

        if (temaRepository.existsById(postagem.getTema().getId())) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(postagemRepository.save(postagem));
        }
        throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,"Tema não existe");
    }


    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
        if (postagemRepository.existsById(postagem.getId())) {
            if (temaRepository.existsById(postagem.getTema().getId())) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem));
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O tema não existe.", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
