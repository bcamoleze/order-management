package br.com.camoleze.ordermanager.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.dto.UserLoginDTO;
import br.com.camoleze.ordermanager.dto.UserUpdateRoleDTO;
import br.com.camoleze.ordermanager.model.PageModel;
import br.com.camoleze.ordermanager.model.PageRequestModel;
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
	public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
												   @RequestParam(value = "size", defaultValue = "10") int size) {
		
		PageRequestModel prm = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(prm);
		
		return ResponseEntity.ok(pm);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody @Valid UserLoginDTO user) {
		User loggedUser = userService.login(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(loggedUser);
	}
	
	//lista todos os pedidos de um determinado usuário (por demanda)
	@GetMapping("/{id}/requests") 
	public ResponseEntity<PageModel<Request>> listAllRequestsById(@PathVariable(name = "id") Long id, 
																  @RequestParam(value = "page", defaultValue = "0") int page,
																  @RequestParam(value = "size", defaultValue = "10") int size) {
		PageRequestModel prm = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllByOwnerIdOnLazyModel(id, prm);
		
		return ResponseEntity.ok(pm);
		
	}
	
	@PatchMapping("/role/{id}") // muda parcialmente dados de usuários
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id,
										@RequestBody UserUpdateRoleDTO userDTO) {
		User user = new User();
		user.setId(id);
		user.setRole(userDTO.getRole());
		
		userService.updateRole(user);
		
		return ResponseEntity.ok().build();
		
	}

	
}
