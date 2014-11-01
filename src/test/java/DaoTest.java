

import java.util.List;

import javax.annotation.Resource;

import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import dao.RoleDao;
import dao.UserDao;
import dao.Role;
import dao.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:config-tests.xml"}) 
@Transactional
public class DaoTest extends DBTestCase {
	    @Autowired
        private UserDao userDao;
	    
        
	    @Autowired
        private RoleDao roleDao;
        
        public UserDao getUserDao() {
            return userDao;
        }

        public void setUserDao(UserDao userDao) {
            this.userDao = userDao;
        }

        public RoleDao getRoleDao() {
            return roleDao;
        }

        public void setRoleDao(RoleDao roleDao) {
            this.roleDao = roleDao;
        }


        @Override
        protected IDataSet getDataSet() throws Exception {            
            return null;           

        }    

        @Test
        public void testfindByLoginNoUser() throws Exception {
            User res = userDao.findByLogin("User1");
            assertTrue(res == null);
        }

        @Test
        public void testfindByEmail() throws Exception {
            User user =  new User();
            user.setEmail("mail@mail");
            userDao.create(user);
            User res = userDao.findByEmail("mail@mail");
            assertNotNull(res);
            assertEquals(res.getEmail(), "mail@mail");
        }

        @Test
        public void testfindByEmailNoUser() throws Exception {
            User res = userDao.findByEmail("@");
            assertTrue(res == null);
        }

        @Test
        public void testfindAll() throws Exception {
            User user =  new User();           
            user.setLogin("User2");
            userDao.create(user);
            List <User> res = userDao.findAll();
            assertTrue(res != null);
            assertEquals(1, res.size());
        }

        @Test
        public void testCreateFindByLogin() throws Exception {
            User user =  new User();           
            user.setLogin("User2");
            userDao.create(user);
            User res = userDao.findByLogin("User2");
            assertNotNull(res);
            assertEquals(res.getLogin(), "User2");        }

        @Test
        public void testUpdate() throws Exception {
            User user =  new User();
            user.setId(4L);
            user.setLogin("User3");
            userDao.create(user);            
            user.setLogin("User4");
            userDao.update(user);
            User res = userDao.findByLogin("User4");
            assertNotNull(res);
            assertEquals("User4", res.getLogin());
        }

        @Test
        public void testRemove() throws Exception {
            User user =  new User();
            user.setId(4L);
            user.setLogin("User4");
            userDao.create(user);
            userDao.remove(user);
            User res = userDao.findByLogin("User4");
            assertTrue(res == null);
        }     
     
        @Test
        public void testfindByNameNoRole() throws Exception {
            Role res = roleDao.findByName("wrongRole");
            assertTrue(res == null);
        }

        @Test
        public void testCreateFindByNameRole() throws Exception {
            Role role = new Role();            
            role.setName("NewAdmin");
            roleDao.create(role);
            Role res = roleDao.findByName("NewAdmin");
            assertNotNull(res);
            assertEquals(res.getName(), "NewAdmin");
        }

        @Test
        public void testUpdateRole() throws Exception {
            Role role = new Role();            
            role.setName("NewAdmin");
            roleDao.create(role);
            role.setName("UpdatedAdmin");
            roleDao.update(role);
            Role res = roleDao.findByName("UpdatedAdmin");
            assertNotNull(res);
            assertEquals("UpdatedAdmin", res.getName());
        }

        @Test
        public void testRemoveRole() throws Exception {
            Role role = new Role();            
            role.setName("guest");
            roleDao.create(role);
            roleDao.remove(role);
            Role res = roleDao.findByName("guest");
            assertTrue(res == null);
        }
    
}
