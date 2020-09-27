package br.com.camoleze.ordermanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.exception.NotFoundException;
import br.com.camoleze.ordermanager.model.PageModel;
import br.com.camoleze.ordermanager.model.PageRequestModel;
import br.com.camoleze.ordermanager.repository.UserRepository;
import br.com.camoleze.ordermanager.service.util.HashUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	
	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);
		
		User updateUser = userRepository.save(user);
		return updateUser;		
	}
	
	public User getUserById(Long id) {
		Optional<User> result = userRepository.findById(id);
		
		return result.orElseThrow(()-> new NotFoundException("ORDER MANAGEMENT: There are not user with id = " + id));
	}
	
	public List<User> listAll(){
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public PageModel<User> listAllOnLazyMode(PageRequestModel prm) {
		// abaixo é instanciado um objeto da interface Pageable e não da Class
		Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());		
		Page<User> page = userRepository.findAll(pageable);
		
		// a classe PageModel foi definida no pacote <<model>> do projeto
		PageModel<User> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent()); 
		
		return pm;	
	}
	
	public User login(String email, String password) {		
		password = HashUtil.getSecureHash(password);
				
		Optional<User> result = userRepository.login(email, password);
		return result.get();
	}
	
}
