package br.com.camoleze.ordermanager.dto;

import javax.validation.constraints.NotNull;

import br.com.camoleze.ordermanager.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// objeto de transferencia de dados de atualização de ROLE do usuário
public class UserUpdateRoleDTO {
	@NotNull(message = "Role required!")
	private Role role;

}
