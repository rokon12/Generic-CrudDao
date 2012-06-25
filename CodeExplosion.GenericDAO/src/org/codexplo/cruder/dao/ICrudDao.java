/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 25, 2012	
 **************/

/**
 * 
 */
package org.codexplo.cruder.dao;

import java.util.List;

import org.codexplo.cruder.domain.BEntity;

/**
 * interface ICrudDao
 * 
 * @author Rokonoid
 * 
 */
public interface ICrudDao<T extends BEntity> {
	public long count();

	public void delete(Long id);

	public List<T> findAll();

	public T findById(Long id);

	public T findByName(String propertyName, Object value);

	public List<T> findDeleted();

	public T save(T t);

	public T update(T t);
}
