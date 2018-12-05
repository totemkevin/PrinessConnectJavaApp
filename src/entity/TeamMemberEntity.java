package entity;

import dao.Role;
import lombok.Data;

@Data
public class TeamMemberEntity {
	private long teamId;
	private long roleId;
	private Role role;
}
