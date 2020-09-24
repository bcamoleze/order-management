package br.com.camoleze.ordermanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	// busca todos os pedidos dado um determinado usuário.
	public List<Request> findAllByOwnerId(Long id);
	
	@Transactional(readOnly = false) // o atributo estara disponivel para leitura e gravação
	@Modifying   // indica que a consulta modificara a entidade 
	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	public int updateStatus(Long id, RequestState state);  // metodo retorna o numero de
														   // linhas afetadas pelo update
	
}
