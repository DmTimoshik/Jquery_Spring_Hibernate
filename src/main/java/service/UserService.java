package service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.User;
import dao.UserDao;

@Service
public class UserService implements UserDaoService {

	@Autowired
    private UserDao userDao;
	
	public UserService(){}
	
	@Transactional
	public void create(User user) {
		userDao.create(user);
	}
	
	@Transactional
	public void update(User user) {
		userDao.update(user);
	}
	
	@Transactional
	public void remove(User user) {
		userDao.remove(user);
	}
	
	@Transactional
	public List<User> findAll() {
		return userDao.findAll();

	}
	
	@Transactional
	public User findByLogin(String login) {
		return userDao.findByLogin(login);

	}
	
	@Transactional
	public User findByEmail(String email) {
		return userDao.findByEmail(email);

	}


	
}
