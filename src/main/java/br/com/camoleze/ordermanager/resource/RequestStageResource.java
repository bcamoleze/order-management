package br.com.camoleze.ordermanager.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.camoleze.ordermanager.domain.RequestStage;
import br.com.camoleze.ordermanager.dto.RequestStageSaveDTO;
import br.com.camoleze.ordermanager.service.RequestStageService;

@RestController
@RequestMapping(value = "request-stages")
public class RequestStageResource {

	@Autowired
	private RequestStageService stageService;
	
	@PostMapping
	public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDTO requestStageDTO) {			
		
		RequestStage stageToSave = requestStageDTO.transformeToRequestStage();
		RequestStage createdRequestStage = stageService.save(stageToSave);		
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable(name = "id") Long id) {
		RequestStage stage = stageService.getById(id);
		return ResponseEntity.ok(stage);
	}
	
}
