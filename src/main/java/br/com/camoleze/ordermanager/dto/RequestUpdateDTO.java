package br.com.camoleze.ordermanager.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.RequestStage;
import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateDTO {
	
	@NotBlank(message = "Subject required!")
	private String subject;
	private String description;
	
	@NotNull(message = "State required!")
	private RequestState state;
	
	@NotNull(message = "Owner required!")
	private User owner;
	
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
	public Request transformeToRequest() {
		Request request = new Request(null, this.subject, this.description, null, this.state, this.owner, this.stages);
		return request;
	}

	
}
