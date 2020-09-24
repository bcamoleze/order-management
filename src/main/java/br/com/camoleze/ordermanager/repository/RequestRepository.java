package br.com.camoleze.ordermanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	// busca todos os pedidos dado um determinado usuário.
	public List<Request> findAllByOwnerId(Long id);
	
	@Query("UPDATE request SET state = ?2 WHERE id = ?1")
	public Request updateRequest(Long id, RequestState state);
	
}
