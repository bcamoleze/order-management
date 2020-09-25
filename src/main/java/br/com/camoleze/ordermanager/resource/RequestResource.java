package br.com.camoleze.ordermanager.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.service.RequestService;

public class RequestResource {
	
	@Autowired
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody Request request) {		
		Request createdRequest = requestService.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody Request request) {
		request.setId(id);		
		Request updateRequest = requestService.update(request);
		return ResponseEntity.ok(updateRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping
	public ResponseEntity<List<Request>> listAll() {
		List<Request> requests = requestService.listAll();
		return ResponseEntity.ok(requests);
	}
	
	
}
