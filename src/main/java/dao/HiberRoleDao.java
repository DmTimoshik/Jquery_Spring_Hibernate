package dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class HiberRoleDao implements RoleDao,Serializable {

	public HiberRoleDao() {
	}
	
    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    
	@Transactional
	public void create(Role role) {
		currentSession().save(role);
	}
	
	
	@Transactional
	public void update(Role role) {
			currentSession().update(role);
	}
	
	@Transactional
	public void remove(Role role) {
			currentSession().delete(role);
	}
	
	@Transactional
	public Role findByName(String string) {
			return (Role) currentSession().createCriteria(Role.class)
					.add(Restrictions.eq("name", string)).uniqueResult();
	}
	
}
