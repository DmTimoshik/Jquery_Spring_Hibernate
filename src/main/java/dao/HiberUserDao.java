package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class HiberUserDao  implements UserDao,Serializable {

	public HiberUserDao() {
		
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
	public void create(User user) {
		currentSession().save(user);
	}
	
	@Transactional
	public void update(User user) {
		currentSession().update(user);
	}
	
	@Transactional
	public void remove(User user) {
		currentSession().delete(user);
	}
	
	@Transactional
	public List<User> findAll() {
		return currentSession().createCriteria(User.class).list();
	}
	
	@Transactional
	public User findByLogin(String login) {
		return(User) currentSession().createCriteria(User.class)
					.add(Restrictions.eq("login", login)).uniqueResult();
	}
	
	@Transactional
	public User findByEmail(String email) {
			return (User) currentSession().createCriteria(User.class)
					.add(Restrictions.eq("email", email)).uniqueResult();
	}

	
}
