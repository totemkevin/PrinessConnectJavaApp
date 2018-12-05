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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String uuid;
	private String type;
	private String note;
	private Date createDtae;
	private Date modifyDtae;
}
