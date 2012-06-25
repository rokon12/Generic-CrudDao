/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 25, 2012	
 **************/

/**
 * 
 */
package org.codexplo.cruder.dao;

import org.codexplo.cruder.domain.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Rokonoid
 * 
 */
@Repository("IUserDao")
@Transactional
public class UserDaoImpl extends CrudDaoImpl<User> implements IUserDao {

}
