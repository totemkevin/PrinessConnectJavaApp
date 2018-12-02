package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="team")
public class Team {
	
	
	public Team(long id, String uuid, String type, String note, Date createDtae, Date modifyDtae,
			List<TeamMember> teamMembers) {
		super();
		this.id = id;
		this.uuid = uuid;
		this.type = type;
		this.note = note;
		this.createDtae = createDtae;
		this.modifyDtae = modifyDtae;
		this.teamMembers = teamMembers;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String uuid;
	private String type;
	private String note;
	private Date createDtae;
	private Date modifyDtae;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
	private List<TeamMember> teamMembers = new ArrayList<TeamMember>();

}
