package service;

import dao.Role;

public interface RoleDaoService {
	public void create(Role role);

	public void update(Role role);

	public void remove(Role role);

	public Role findByName(String name);
}
