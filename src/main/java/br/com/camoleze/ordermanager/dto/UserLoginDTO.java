package br.com.camoleze.ordermanager.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {
	
	@Email(message = "Invalid email address!")
	private String email;
	
	@NotBlank(message = "Password required!")
	private String password;
	
}
