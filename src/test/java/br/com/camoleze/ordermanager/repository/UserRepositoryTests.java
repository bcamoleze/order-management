package br.com.camoleze.ordermanager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // ordena a execução dos metodos (ordem alfabetica)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void aSaveTest() {

		User user = new User(null, "Bruno", "bruno.camoleze@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);

		// atentar-se ao import - usar do pacote "org.assertj.core.api"
		assertThat(createdUser.getId()).isEqualTo(1L);

	}

	@Test
	public void updateTest() {

		User user = new User(1L, "Bruno Camoleze", "bruno.camoleze@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User updateUser = userRepository.save(user);

		assertThat(updateUser.getName()).isEqualTo("Bruno Camoleze");

	}

	@Test
	public void getByIdTest() {

		Optional<User> result = userRepository.findById(1L);
		User user = result.get();

		assertThat(user.getId()).isEqualTo(1L);

	}

	@Test
	public void listTest() {

		List<User> users = userRepository.findAll();

		assertThat(users.size()).isEqualTo(1);

	}

	@Test
	public void login() {

		Optional<User> result = userRepository.login("bruno.camoleze@gmail.com", "123");
		User loggedUser = result.get();

		assertThat(loggedUser.getId()).isEqualTo(1L);
		
	}
}
