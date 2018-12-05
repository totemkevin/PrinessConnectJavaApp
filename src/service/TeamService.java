package service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import dao.Role;
import dao.Team;
import entity.TeamEntity;
import util.HibernateUtil;

public class TeamService {
	public List<Team> findAll(int start, int limit) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Team> criteriaQuery = builder.createQuery(Team.class);
		criteriaQuery.from(Team.class);
		List<Team> list = session.createQuery(criteriaQuery)
				.setFirstResult(start)
				.setMaxResults(start+limit)
				.getResultList();
		
		return list;
		
	}
	public long createTeam(TeamEntity teamEntity) {
		Team team = EntityToDAO(teamEntity);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(team);
		session.getTransaction().commit();
		
		return 0;
		
	}
	
	protected Team EntityToDAO(TeamEntity teamEntity) {
		Team team = new Team();
		team.setUuid(UUID.randomUUID().toString());
		team.setType(teamEntity.getType());
		team.setNote(teamEntity.getNote());
		team.setCreateDtae(new Date());
		team.setModifyDtae(new Date());
		
		return team;
	}
}
