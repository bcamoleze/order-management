package br.com.camoleze.ordermanager.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.camoleze.ordermanager.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String name;
	
	@Column(length = 75, nullable = false, unique = true)
	private String email;
	
	@Getter(onMethod = @__({@JsonIgnore}))  // configuração para não exibir esses objetos no JSON
	@Setter(onMethod = @__({@JsonProperty})) // Durante o set ele deve aceitar a password
	@Column(length = 100, nullable = false)
	private String password;
	
	@Column(length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Getter(onMethod = @__({@JsonIgnore})) // configuração para não exibir esses objetos no JSON
	@OneToMany(mappedBy = "owner") // um usuário pode ser dono de vários pedidos
	private List<Request> requests = new ArrayList<Request>();
	
	@Getter(onMethod = @__({@JsonIgnore})) // configuração para não exibir esses objetos no JSON
	@OneToMany(mappedBy = "owner") // um usuário pode ser dono de vários estagios de pedido
	private List<RequestStage> stages = new ArrayList<RequestStage>();
	
}
