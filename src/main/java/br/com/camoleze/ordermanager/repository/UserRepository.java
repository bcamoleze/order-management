package br.com.camoleze.ordermanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.camoleze.ordermanager.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//Tambem pode ser usado findByEmailAndPassword porém dessa forma fica mais profissional
	@Query("SELECT u FROM user u WHERE email = ?1 AND password = ?2")
	public Optional<User> login(String email, String password);	
	
}
