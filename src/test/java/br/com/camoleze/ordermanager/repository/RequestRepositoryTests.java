package br.com.camoleze.ordermanager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {

	@Autowired private RequestRepository requestRepository;
	
	@Test
	public void aSaveTest() {
		User owner = new User();
		owner.setId(1L);
		
		Request request = new Request(null, "Compra de Notebook", "Solicito a compra de notebook DELL para departamento de TI", new Date(), RequestState.OPEN, owner, null);
		
		Request createdRequest = requestRepository.save(request);
		
		assertThat(createdRequest.getId()).isEqualTo(3L);
		
	}
	
	@Test
	public void updateTest() {
		
		User owner = new User();
		owner.setId(2L);
		
		Request request = new Request(null, "Compra de PC", "Solicito a compra de micro computador HP 400 G5 MINI para departamento comercial - minimo de 8 GB de RAM", null, RequestState.OPEN, owner, null);
		
		Request updateRequest = requestRepository.save(request);
		
		assertThat(updateRequest.getDescription()).isEqualTo("Solicito a compra de micro computador HP 400 G5 MINI para departamento comercial - minimo de 8 GB de RAM");
	}
	
	@Test
	public void getByIdTest() {
		
		Optional<Request> result = requestRepository.findById(1L);
		
		Request request = result.get();
		
		assertThat(request.getSubject()).isEqualTo("Compra de PC");
		
	}
	
	@Test
	public void listTest() {
		
		List<Request> requests = requestRepository.findAll();
		assertThat(requests.size()).isEqualTo(1);
		
	}
	
	@Test
	public void listByOwnerIdTest() {
		
		List<Request> requests = requestRepository.findAllByOwnerId(2L);
		assertThat(requests.size()).isEqualTo(1);
		
	}
}
