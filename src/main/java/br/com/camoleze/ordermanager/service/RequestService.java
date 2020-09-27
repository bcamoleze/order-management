package br.com.camoleze.ordermanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.domain.enums.RequestState;
import br.com.camoleze.ordermanager.exception.NotFoundException;
import br.com.camoleze.ordermanager.model.PageModel;
import br.com.camoleze.ordermanager.model.PageRequestModel;
import br.com.camoleze.ordermanager.repository.RequestRepository;

@Service
public class RequestService {

	@Autowired
	RequestRepository requestRepository;
	
	public Request save(Request request) {
		
		System.out.println("Service: " + request);
		
		request.setCreationDate(new Date());
		request.setState(RequestState.OPEN);
		
		Request createdRequest = requestRepository.save(request);
		
		return createdRequest;
		
	}
	
	public Request update(Request request) {
		Request updateRequest = requestRepository.save(request);
		return updateRequest;
	}
	
	public Request getById(Long id) {		
		Optional<Request> result = requestRepository.findById(id);

		return result.orElseThrow(()-> new NotFoundException("ORDER MANAGEMENT: There are not REQUEST with id = " + id));
	}
	
	public List<Request> listAll() {
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public PageModel<Request> listAllOnLazyMode(PageRequestModel prm) {
		Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());		
		Page<Request> page = requestRepository.findAll(pageable);
		
		// a classe PageModel foi definida no pacote <<model>> do projeto
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent()); 
		
		return pm;	
	}

	
	public List<Request> listAllByOwnerId(Long ownerId) {
		List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
		return requests;
	}
	
	// retorna os pedidos por usuários no modo paginado 
	public PageModel<Request> listAllByOwnerIdOnLazyModel(Long ownerId, PageRequestModel prm) {
		Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
		
		PageModel<Request> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		return pm;
	}
}
