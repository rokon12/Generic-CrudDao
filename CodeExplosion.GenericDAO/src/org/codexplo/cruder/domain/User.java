/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 25, 2012	
 **************/

/**
 * 
 */
package org.codexplo.cruder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Example entity for testing purpose
 * 
 * @author Rokonoid
 * 
 */

@Entity
@Table(name = "User")
public class User extends BEntity {

	private static final long serialVersionUID = -207732145432694522L;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
