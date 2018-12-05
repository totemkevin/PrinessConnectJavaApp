package dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="team_member")
public class TeamMember {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String uuid;
	private Integer star;
	private Date createDtae;
	private Date modifyDtae;
	private Long teamId;
	private Long roleId;
}
