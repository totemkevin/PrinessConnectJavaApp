package logic;

import java.io.Serializable;
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
import entity.TeamSearchEntiy;
import service.RoleService;
import service.TeamMemberService;
import service.TeamService;
import util.HibernateUtil;

public class TeamLogic {
	public List<TeamEntity> getTeams(int start,int limit) {
		TeamService teamService = new TeamService();
		TeamMemberService teamMemberService = new TeamMemberService();
		RoleService roleService = new RoleService();
		
		List<TeamEntity> teamEntityList = new ArrayList<TeamEntity>();
		List<Team> teamList = teamService.findAll(start,limit);

		for(Team team : teamList) {
			List<TeamMember> tmList = teamMemberService.getByTeamId(team.getId());
			List<TeamMemberEntity> teamMemberEntitys = new ArrayList<TeamMemberEntity>();
			
			for(TeamMember tm : tmList) {
				TeamMemberEntity teamMemberEntity = new TeamMemberEntity();
				Role role = roleService.findById(tm.getRoleId());
				teamMemberEntity.setRole(role);
				
				teamMemberEntitys.add(teamMemberEntity);
			}
			
			TeamEntity teamEntity = new TeamEntity();
			teamEntity.setType(team.getType());
			teamEntity.setNote(team.getNote());
			teamEntity.setTeamMemberEntitys(teamMemberEntitys);
			teamEntityList.add(teamEntity);
		}
		return teamEntityList;
	}
	
	public void save(String note, String type,List<Role> roleList) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Team team = new Team();
		team.setNote(note);
		team.setType(type);
		Serializable id = session.save(team);
		
		for(Role role: roleList) {
			TeamMember teamMember = new TeamMember();
			teamMember.setTeamId((Long) id);
			teamMember.setRoleId(role.getId());
			session.save(teamMember);
		}
		session.getTransaction().commit();
	}
	
	public void search(List<Role> include, List<Role> notInclude, int start, int limit) {
		System.out.println("1");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		System.out.println("2");
		CriteriaQuery<Team> query = builder.createQuery(Team.class);
		System.out.println("3");
		Root<Team> root = query.from(Team.class);
		System.out.println("4");
		query.select(root);
		System.out.println("5");
		Query<Team> q=session.createQuery(query);
		System.out.println("6");
		List<Team> teams=q.list();
		System.out.println(teams);
//		Join<Team,TeamMember> orders = root.join(Team_.);
		
		session.getTransaction().commit();
	}
}
