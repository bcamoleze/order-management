package br.com.camoleze.ordermanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.domain.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//Tambem pode ser usado findByEmailAndPassword porém dessa forma fica mais profissional
	@Query("SELECT u FROM user u WHERE email = ?1 AND password = ?2")
	public Optional<User> login(String email, String password);	
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE user SET role = ?2 WHERE id = ?1")
	public int updateRole(Long id, Role role);
	
}
