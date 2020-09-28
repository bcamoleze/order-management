package br.com.camoleze.ordermanager.dto;

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
public class RequestStageSaveDTO {

	private String description;
	
	@NotNull(message = "State required!")
	private RequestState state;
	
	@NotNull(message = "Request required!")
	private Request request;
	
	@NotNull(message = "Owner required!")
	private User owner;
	
	public RequestStage transformeToRequestStage() {
		RequestStage stage = new RequestStage(null, this.description, null, this.state, this.request, this.owner);
		return stage;
	}
	
}
