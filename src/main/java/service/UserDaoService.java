package service;

import java.util.List;

import dao.User;

public interface UserDaoService {
	public void create(User user);

	public void update(User user);

	public void remove(User user);

	public List<User> findAll();

	public User findByLogin(String login);

	public User findByEmail(String email);
}
