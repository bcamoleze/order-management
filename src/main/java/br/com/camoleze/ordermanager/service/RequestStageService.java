package br.com.camoleze.ordermanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.camoleze.ordermanager.domain.RequestStage;
import br.com.camoleze.ordermanager.domain.User;
import br.com.camoleze.ordermanager.domain.enums.RequestState;
import br.com.camoleze.ordermanager.exception.NotFoundException;
import br.com.camoleze.ordermanager.model.PageModel;
import br.com.camoleze.ordermanager.model.PageRequestModel;
import br.com.camoleze.ordermanager.repository.RequestRepository;
import br.com.camoleze.ordermanager.repository.RequestStageRepository;

@Service
public class RequestStageService {

	@Autowired
	private RequestStageRepository requestStageRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public RequestStage save(RequestStage stage) {
		stage.setRealizationDate(new Date());
		
		RequestStage createdStage = requestStageRepository.save(stage);
		
		Long requestId = stage.getRequest().getId();
		RequestState state = stage.getState();
		
		// para atualizar o estado do pedido
		requestRepository.updateStatus(requestId, state);
		
		return createdStage;
	}
	
	public RequestStage getById(Long id) {
	  	Optional<RequestStage> result = requestStageRepository.findById(id);
	  	
	  	return result.orElseThrow(()-> new NotFoundException("ORDER MANAGEMENT: There are not request stage with id = " + id));
	}
	
	public List<RequestStage> listAllByRequestId(Long requestId) {
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(requestId);
		return stages;		
	}
	
	public PageModel<RequestStage> listAllByRequestIdOnLazyMode(Long requestId, PageRequestModel prm) {
		// abaixo é instanciado um objeto da interface Pageable e não da Class
		Pageable pageable = PageRequest.of(prm.getPage(), prm.getSize());		
		Page<RequestStage> page = requestStageRepository.findAllByRequestId(requestId, pageable);
		
		// a classe PageModel foi definida no pacote <<model>> do projeto
		PageModel<RequestStage> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent()); 
		
		return pm;	
	}

	
	
}
