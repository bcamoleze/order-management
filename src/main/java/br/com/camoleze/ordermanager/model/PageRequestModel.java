package br.com.camoleze.ordermanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestModel { // me ajudara a requisitar uma página
	
	private int page;
	private int size;

}
