package service;

import dao.Role;
import entity.RoleEntity;
public class RoleService {
	
	public Role findById(long id) {
		return null;
	
	}
	
	public long createRole(RoleEntity roleEntity) {
		return 0;
		
	}
	
	private Role EntityToDAO(RoleEntity roleEntity) {
		Role role = new Role();
		role.setId(roleEntity.getId());
		role.setName(roleEntity.getName());
		role.setImg(roleEntity.getImg());
		
		return role;
	}
}
