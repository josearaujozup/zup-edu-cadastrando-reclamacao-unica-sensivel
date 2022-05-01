package br.com.zup.edu.reclamacao.reclamacao;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReclamacaoRequest {
	
	@NotBlank
    private String nomeUsuario;
	
	@NotBlank
	@Email
    private String emailUsuario;
	
	@NotBlank
    @Size(max = 4000)
	private String texto;
	
	@NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}")
    private String telefone;

	public ReclamacaoRequest(@NotBlank String nomeUsuario, @NotBlank @Email String emailUsuario,
			@NotBlank @Size(max = 4000) String texto,
			@NotBlank @Pattern(regexp = "^\\+[1-9][0-9]\\d{1,14}") String telefone) {
		this.nomeUsuario = nomeUsuario;
		this.emailUsuario = emailUsuario;
		this.texto = texto;
		this.telefone = telefone;
	}
	
	public ReclamacaoRequest() {
		
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public String getTexto() {
		return texto;
	}

	public String getTelefone() {
		return telefone;
	}

	public Reclamacao toModel() {
		return new Reclamacao(nomeUsuario, emailUsuario, texto, telefone);
	}
	
}
