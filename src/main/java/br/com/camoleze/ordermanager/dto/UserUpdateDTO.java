package br.com.camoleze.ordermanager.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.RequestStage;
import br.com.camoleze.ordermanager.domain.User;
import lombok.Data;

@Data
public class UserUpdateDTO {

	@NotBlank(message = "Name required!")
	private String name;
	
	@Email(message = "Invalid email address!")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must be between 7 and 99.")
	private String password;
	
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();

	public User tranformerToUser() {
		User user = new User(null, this.name, this.email, this.password, null, this.requests, this.stages);
		return user;
	}

}
