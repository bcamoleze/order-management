package br.com.camoleze.ordermanager.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.camoleze.ordermanager.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
	
	private Long id;
	private String subject;
	private String description;
	private Date creationDate;
	private RequestState state;
	private User user;
	private List<RequestStage> stages = new ArrayList<RequestStage>();

}
