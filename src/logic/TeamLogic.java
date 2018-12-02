package logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;

import dao.Role;
import dao.Team;
import dao.TeamMember;
import entity.RoleEntity;
import entity.TeamEntity;
import entity.TeamMemberEntity;
import util.HibernateUtil;

public class TeamLogic {
	public void getTeams() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery query = cb.createQuery(TeamEntity.class);

		Root<Team> teamTable = query.from(Team.class);
		Join<Team, TeamMember> teamMemberJoin = teamTable.join("teamMembers", JoinType.INNER);
		Root<Team> teamMemberTable = query.from(TeamMember.class);
		Join<TeamMember, Role> roleJoin = teamMemberTable.join("role", JoinType.INNER);

		query.multiselect(teamTable.get("id"),teamMemberJoin.get("id"),roleJoin.get("name"));
		
		TypedQuery<TeamEntity> typedQuery = session.createQuery(query);

		List resultList = typedQuery.getResultList();
		System.out.println(resultList);
	}
}
