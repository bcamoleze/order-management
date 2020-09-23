package br.com.camoleze.ordermanager.domain;

import java.util.ArrayList;
import java.util.List;

import br.com.camoleze.ordermanager.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Long id;
	private String name;
	private String email;
	private String password;
	private Role role;
	private List<Request> requests = new ArrayList<Request>();
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
}
