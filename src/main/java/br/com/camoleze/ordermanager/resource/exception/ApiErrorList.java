package br.com.camoleze.ordermanager.resource.exception;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApiErrorList extends ApiError{

	private static final long serialVersionUID = 1L;

	private List<String> errors;
	
	public ApiErrorList(int code, String msg, Date date, List<String> errors) {
		
		super(code, msg, date);
		this.errors = errors;
		
	}
	
	
	
}
