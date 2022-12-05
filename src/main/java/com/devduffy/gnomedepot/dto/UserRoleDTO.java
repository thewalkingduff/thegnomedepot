package com.devduffy.gnomedepot.dto;

import com.devduffy.gnomedepot.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDTO {
    
    private Integer id;
    private Integer userId;
    private String roleName;
	
	public UserRole toModel () {
		return new UserRole(id, userId, roleName);
	}
}
 