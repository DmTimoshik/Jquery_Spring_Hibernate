package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.Role;
import dao.RoleDao;

@Service
public class RoleService implements RoleDaoService {

	public RoleService() {
	}
	
	@Autowired
    private RoleDao roleDao;
	
	@Transactional
	public void create(Role role) {
		roleDao.create(role);
	}
	
	@Transactional
	public void update(Role role) {
		roleDao.update(role);
	}
	
	@Transactional
	public void remove(Role role) {
		roleDao.remove(role);
	}
	
	@Transactional
	public Role findByName(String string) {
		return roleDao.findByName(string);
	}
	
}
