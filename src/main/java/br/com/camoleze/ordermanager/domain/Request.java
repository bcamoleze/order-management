package br.com.camoleze.ordermanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.camoleze.ordermanager.domain.enums.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "request")
public class Request implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String subject;
	
	@Column(columnDefinition = "text")
	private String description;
	
	@Column(name = "creation_date" , nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	@Column(length = 12, nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestState state;
	
	@ManyToOne // Muitos pedidos podem pertencer a 1 usuário
	@JoinColumn(name = "owner_id", nullable = false)
	@Getter(onMethod = @__({@JsonIgnore})) 
	@Setter(onMethod = @__({@JsonProperty}))
	private User owner;
	
	
	@OneToMany(mappedBy = "request") // Um pedido pode ter vários estagios
	@Getter(onMethod = @__({@JsonIgnore})) 
	@Setter(onMethod = @__({@JsonProperty}))
	private List<RequestStage> stages = new ArrayList<RequestStage>();

}
