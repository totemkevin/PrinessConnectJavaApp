package service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import dao.Role;
import dao.TeamMember;
import entity.RoleEntity;
import util.HibernateUtil;
public class RoleService {
	
	public Role findById(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		
		CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
		Root<Role> root = criteriaQuery.from(Role.class);
		criteriaQuery.where(builder.equal(root.get("id"), id));
		List<Role> list = session.createQuery(criteriaQuery).getResultList();
		if(list.size()==1) {
			return list.get(0);
		}
		
		return null;
	}
	
	public List<Role> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		CriteriaQuery<Role> criteriaQuery = session.getCriteriaBuilder().createQuery(Role.class);
		criteriaQuery.from(Role.class);
		List<Role> list = session.createQuery(criteriaQuery).getResultList();
		session.getTransaction().commit();
		
		return list;
	}
	
	public long createRole(RoleEntity roleEntity) {
		Role role = EntityToDAO(roleEntity);
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(role);
		session.getTransaction().commit();
		
		return 0;
		
	}
	
	protected Role EntityToDAO(RoleEntity roleEntity) {
		Role role = new Role();
		role.setUuid(UUID.randomUUID().toString());
		role.setName(roleEntity.getName());
		role.setImg(roleEntity.getImg());
		role.setCreateDtae(new Date());
		role.setModifyDtae(new Date());
		
		return role;
	}
}
