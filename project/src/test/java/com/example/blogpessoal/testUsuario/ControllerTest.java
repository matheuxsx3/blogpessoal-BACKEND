package com.example.blogpessoal.testUsuario;

import com.example.blogpessoal.model.Usuario;
import com.example.blogpessoal.repository.UsuarioRepository;
import com.example.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //classe inicial que geralmente é usado para fazer autenticação
    // antes de utilizar os metodos
    //testando requisição de post (criar usuario)

    @BeforeAll
    void start() {
        usuarioRepository.deleteAll();
        usuarioService.cadastrarUsuario(new Usuario(0L, "rootroot", "rootroot", "root@gmail.com", "rootsenha", " "));
    }

    @Test
    @DisplayName("cadastrar um usuario")
    public void deveCriarUsuario() {
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0L, "aleatorio1", "aleatorio2", "aleatorio1@gmail.com", "aleatoriosenha", "-"));
        ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("não deve permitir duplicar usuarios")
    public void deveRejeitarCadastroUsuarioDuplicado() {
        usuarioService.cadastrarUsuario(new Usuario(0L, "aleatorio2", "aleatorio2", "aleatorio2@gmail.com", "aleatorio2", " "));
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0L,"aleatorio2", "aleatorio2", "aleatorio2@gmail.com", "aleatorio2", " "));
        ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar",HttpMethod.POST, corpoRequisicao,Usuario.class);
        assertEquals(HttpStatus.BAD_REQUEST,corpoResposta.getStatusCode());
    }
    @Test
    @DisplayName("atualizar um usuario")
    public  void deveAtualizarUsuario(){
        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L,"aleatorio3", "aleatorio3", "aleatorio3@gmail.com", "aleatorio3", " "));
        Usuario usuarioAtualizar = new Usuario(usuarioCadastrado.get().getId(), "aleatorio3Atualizado", "aleatorio3", "aleatorio3@gmail.com", "aleatorio3","");
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(usuarioAtualizar);
        ResponseEntity<Usuario> corpoResposta = testRestTemplate
                .withBasicAuth("rootroot", "rootsenha")
                .exchange("/usuarios/atualizar",HttpMethod.PUT,corpoRequisicao,Usuario.class);
        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());

    }
    @Test
    @DisplayName("listar todos os usuarios")
    public void deveListarTodosUsuarios(){
        usuarioService.cadastrarUsuario(new Usuario(0L,"aleatorio4", "aleatorio4", "aleatorio4@gmail.com", "aleatorio4", " "));
        usuarioService.cadastrarUsuario(new Usuario(0L,"aleatorio5", "aleatorio5", "aleatorio5@gmail.com", "aleatorio5", " "));
        ResponseEntity<String> resposta = testRestTemplate
                .withBasicAuth("rootroot", "rootsenha")
                .exchange("/usuarios/all",HttpMethod.GET, null,String.class);
        assertEquals(HttpStatus.OK, resposta.getStatusCode());
    }

}
