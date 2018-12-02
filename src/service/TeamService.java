package service;

import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;

import dao.Team;
import entity.TeamEntity;
import util.HibernateUtil;

public class TeamService {
//	public long createTeam(TeamEntity teamEntity) {
//		Team team = EntityToDAO(teamEntity);
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		session.save(team);
//		session.getTransaction().commit();
//		
//		return 0;
//		
//	}
//	
//	protected Team EntityToDAO(TeamEntity teamEntity) {
//		Team team = new Team();
//		team.setUuid(UUID.randomUUID().toString());
//		team.setType(teamEntity.getType());
//		team.setNote(teamEntity.getNote());
//		team.setCreateDtae(new Date());
//		team.setModifyDtae(new Date());
//		
//		return team;
//	}
}
