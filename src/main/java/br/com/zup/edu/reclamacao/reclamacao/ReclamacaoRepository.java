package br.com.zup.edu.reclamacao.reclamacao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamacaoRepository extends JpaRepository<Reclamacao, Long>{

	public boolean existsByHashDoTelefoneAndTexto(String hashDoTelefone, String texto);

}
