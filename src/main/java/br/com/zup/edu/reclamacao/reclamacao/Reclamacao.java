package br.com.zup.edu.reclamacao.reclamacao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = {
		@UniqueConstraint(name = "UK_telefone_texto", columnNames = {"hashDoTelefone", "texto"})
})
@Entity
public class Reclamacao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String nomeUsuario;
	
	@Column(nullable = false)
    private String emailUsuario;
	
	@Column(nullable = false, length = 4000)
    private String texto;
	
	@Column(nullable = false, length = 64) // unico
    private String hashDoTelefone;
	
	@Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacaoRegistro = LocalDateTime.now();

	public Reclamacao(String nomeUsuario, String emailUsuario, String texto, String telefone) {
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.texto = texto;
		this.hashDoTelefone = Encriptador.hash(telefone);
	}
	
	/**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
	public Reclamacao() {
		
	}
	
	public Long getId() {
		return id;
	}
	
}
