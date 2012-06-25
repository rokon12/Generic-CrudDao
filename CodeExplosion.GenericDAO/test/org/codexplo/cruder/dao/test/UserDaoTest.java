/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 20, 2012	
 **************/

package org.codexplo.cruder.dao.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import junit.framework.Assert;

import org.codexplo.cruder.dao.IUserDao;
import org.codexplo.cruder.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends AbstractTest {
	@Autowired(required = true)
	private IUserDao userDao;

	@Test
	public void count() {
		long count = userDao.count();
		Assert.assertEquals(15, count);
	}

	// @Test
	public void CreateUser() {
		User user = new User();
		user.setName("rokon");
		user.setPassword("asdfasdf");
		userDao.save(user);

		User user1 = userDao.findById(new Long(1));
		// userDao.delete(new Long(1));

		assertNotNull(user1);
		Assert.assertEquals("rokonoid", user1.getName());
	}

	@Test
	public void delete() {
		// User user = userDao.findById(new Long(2));
		// assertNotNull(user);
		userDao.delete(new Long(4));
		List<User> users = userDao.findDeleted();

		Assert.assertEquals(1, users.size());
	}

	// @Test
	public void findByProperty() {
		User user = userDao.findByName("name", "rokonoid");
		assertNotNull(user);
		Assert.assertEquals("rokonoid", user.getName());
	}

	// @Test
	public void update() {
		User user = userDao.findById(new Long(1));
		user.setName("rokonoid");
		userDao.update(user);

		Assert.assertEquals("rokonoid", userDao.findById(new Long(1)).getName());
	}

}
