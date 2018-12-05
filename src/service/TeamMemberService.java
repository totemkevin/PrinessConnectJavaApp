package service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import dao.Team;
import dao.TeamMember;
import util.HibernateUtil;

public class TeamMemberService {
	public TeamMember getById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<TeamMember> criteriaQuery = builder.createQuery(TeamMember.class);
		Root<TeamMember> root = criteriaQuery.from(TeamMember.class);
		criteriaQuery.where(builder.equal(root.get("id"), id));
		List<TeamMember> list = session.createQuery(criteriaQuery).getResultList();
		if(list.size()==1) {
			return list.get(0);
		}
		
		return null;
	}

	public List<TeamMember> getByTeamId(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<TeamMember> query = builder.createQuery(TeamMember.class);
		Root<TeamMember> root = query.from(TeamMember.class);
		query.select(root);
		query.where(builder.equal(root.get("teamId"), id));
		Query<TeamMember> q=session.createQuery(query);
		List<TeamMember> list=q.getResultList();
		
		return list;
	}

	public List<TeamMember> getByTeam(Team team) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<TeamMember> criteriaQuery = builder.createQuery(TeamMember.class);
		Root<TeamMember> root = criteriaQuery.from(TeamMember.class);
		criteriaQuery.where(builder.equal(root.get("team"), team));
		List<TeamMember> list = session.createQuery(criteriaQuery).getResultList();
		
		return list;
	}
	
	public List<TeamMember> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
	
		CriteriaQuery<TeamMember> query = builder.createQuery(TeamMember.class);

		Root<TeamMember> root = query.from(TeamMember.class);
		
		query.select(root);
	
		Query<TeamMember> q=session.createQuery(query);

		List<TeamMember> list=q.list();
		System.out.println(list);
		
		return list;
	}
}
