package br.com.camoleze.ordermanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.enums.RequestState;
import br.com.camoleze.ordermanager.exception.NotFoundException;
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
	
	public List<Request> listAllByOwnerId(Long ownerId) {
		List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
		return requests;
	}
	
}
