package entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeamEntity {
	private String type;
	private String note;
	
	private List<TeamMemberEntity> teamMemberEntitys = new ArrayList<TeamMemberEntity>();
}
