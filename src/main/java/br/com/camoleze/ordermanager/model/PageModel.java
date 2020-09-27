package br.com.camoleze.ordermanager.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageModel<T> implements Serializable{ //Modelo de página
	
	private int totalElements;
	private int pageSize;
	private int totalPages;
	private List<T> elements;

}
