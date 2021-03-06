package br.com.camoleze.ordermanager.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.camoleze.ordermanager.domain.Request;
import br.com.camoleze.ordermanager.domain.RequestStage;
import br.com.camoleze.ordermanager.dto.RequestSaveDTO;
import br.com.camoleze.ordermanager.dto.RequestUpdateDTO;
import br.com.camoleze.ordermanager.model.PageModel;
import br.com.camoleze.ordermanager.model.PageRequestModel;
import br.com.camoleze.ordermanager.service.RequestService;
import br.com.camoleze.ordermanager.service.RequestStageService;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private RequestStageService stageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDTO requestDTO) {
		Request requestToSave = requestDTO.transformeToRequest();
		Request createdRequest = requestService.save(requestToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable(name = "id") Long id, @RequestBody @Valid RequestUpdateDTO requestDTO) {
		Request requestToUpdate = requestDTO.transformeToRequest();
		requestToUpdate.setId(id);		
		
		Request updateRequest = requestService.update(requestToUpdate);
		return ResponseEntity.ok(updateRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable(name = "id") Long id) {
		Request request = requestService.getById(id);
		return ResponseEntity.ok(request);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
												      @RequestParam(value = "size", defaultValue = "10") int size) {
		
		PageRequestModel prm = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyMode(prm);
		
		return ResponseEntity.ok(pm);
	}
	
	// seguindo a mesma ideia dos requests por usuários coloquei esse metodo aqui
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllStagesById(@PathVariable(name = "id") Long id,
																	 @RequestParam(value = "page" , defaultValue = "0") int page,
																	 @RequestParam(value = "size" , defaultValue = "10") int size) {
		PageRequestModel prm = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = stageService.listAllByRequestIdOnLazyMode(id, prm);
		
		return ResponseEntity.ok(pm);
	}
	
}
