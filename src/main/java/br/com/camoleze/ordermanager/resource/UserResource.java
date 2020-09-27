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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.dto.UserLoginDTO;
import br.com.camoleze.ordermanager.service.RequestService;
import br.com.camoleze.ordermanager.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private RequestService requestService;
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		User createdUser = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @RequestBody User user) {
		user.setId(id);
		User updateUser = userService.update(user);
		return ResponseEntity.ok(updateUser);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable(name = "id") Long id) {
		User user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listAll() {
		List<User> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody UserLoginDTO user) {
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	//lista todos os pedidos de um determinado usuário
	@GetMapping("/{id}/requests")
	public ResponseEntity<List<Request>> listAllRequestsById(@PathVariable(name = "id") Long id) {
		
		System.out.println("Opa! Tô aqui.");
		List<Request> requests = requestService.listAllByOwnerId(id);
		return ResponseEntity.ok(requests);
		
	}

	
}