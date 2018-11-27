package dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vs")
public class VS {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long myTeam;
	private long enemyTeam;
	private Integer win;
	private Integer lose;
	private Date createDtae;
	private Date modifyDtae;
}
