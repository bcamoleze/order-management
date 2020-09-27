package br.com.camoleze.ordermanager.dto;

import br.com.camoleze.ordermanager.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// objeto de transferencia de dados de atualização de ROLE do usuário
public class UserUpdateRoleDTO {
	
	private Role role;

}
