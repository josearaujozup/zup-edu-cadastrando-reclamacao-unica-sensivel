package br.com.zup.edu.reclamacao.reclamacao;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reclamacoes")
public class ReclamacaoController {
	
	private final ReclamacaoRepository repository;

	public ReclamacaoController(ReclamacaoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ReclamacaoRequest request, UriComponentsBuilder uriComponentsBuilder){
		
		String hashDoTelefone = Encriptador.hash(request.getTelefone()); // encripta telefone informado
		if(repository.existsByHashDoTelefoneAndTexto(hashDoTelefone,request.getTexto())) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Reclamaçao já existe para o telefone no sistema");
		}
		
        Reclamacao reclamacao = request.toModel();

        repository.save(reclamacao);

        URI location = uriComponentsBuilder.path("/reclamacoes/{id}")
                .buildAndExpand(reclamacao.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
	
	@ExceptionHandler
	public ResponseEntity<?> handleUniqueConstraintErrors(ConstraintViolationException e){
		
		Map<String, Object> body = Map.of(
				"message", "reclamcao já existente no sistema para esse telefone",
				"timestamp", LocalDateTime.now()
		);
		
		return ResponseEntity.unprocessableEntity().body(body);
	}

}
