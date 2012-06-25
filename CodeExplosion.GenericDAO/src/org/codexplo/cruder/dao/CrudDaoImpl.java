/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 25, 2012	
 **************/

/**
 * 
 */
package org.codexplo.cruder.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.codexplo.cruder.domain.BEntity;
import org.codexplo.cruder.domain.IDeletable;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of ICrudDao
 * 
 * 
 * @see ICrudDao
 */
@Repository("ICrudDao")
@Transactional
public abstract class CrudDaoImpl<T extends BEntity> implements ICrudDao<T> {
	private final Logger log = LoggerFactory.getLogger(CrudDaoImpl.class);

	private Class<T> persistentClass;

	@Autowired(required = true)
	private SessionFactory sessionFactory;

	private boolean checkEntityIsInstanceOfDeletable(Object a) {
		if (a instanceof IDeletable) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codexplo.cruder.dao.ICrudDao#count()
	 */
	@Override
	public long count() {
		return (long) getCriteria(getpersistentClass())
				.setProjection(Projections.rowCount()).list().get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codexplo.cruder.dao.ICrudDao#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		if (id != null) {
			T t = findById(id);
			if (t != null) {
				if (t instanceof IDeletable) {
					getCurrentSession().delete(t);
					log.debug("completely deleted..."
							+ persistentClass.toString());
				} else {
					t.setDeleted(true);
					t.setLastUpdatedDate(new Date());
					getCurrentSession().update(t);
					log.debug("soft delete..." + persistentClass.toString());
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codexplo.cruder.dao.ICrudDao#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		log.debug("return list with all object..." + persistentClass);
		Set<T> set = new HashSet<T>();
		set.addAll(getCriteria(getpersistentClass()).list());
		List<T> list = new ArrayList<T>();
		Iterator<T> it = set.iterator();
		while (it.hasNext()) {
			list.add(it.next());
		}
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codexplo.cruder.dao.ICrudDao#findById(java.lang.Long)
	 */
	@Override
	public T findById(Long id) {
		@SuppressWarnings("unchecked")
		List<T> list = getCriteria(getpersistentClass()).add(
				Restrictions.eq("id", id)).list();
		if (list.size() > 0) {
			log.debug("object found by id..." + list.get(0));
			return list.get(0);
		} else {
			log.debug("object not found... by id " + id);
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codexplo.cruder.dao.ICrudDao#findByName(java.lang.String,
	 * java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T findByName(String propertyName, Object value) {
		return (T) getCriteria(persistentClass)
				.add(Restrictions.eq(propertyName, value)).list().get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.codexplo.cruder.dao.ICrudDao#findDeleted()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findDeleted() {
		Criteria criteria = getSession().getCurrentSession().createCriteria(
				persistentClass);
		criteria.add(Restrictions.eq("deleted", true));
		return criteria.list();
	}

	/**
	 * @param persistentClass2
	 * @return
	 */
	private Criteria getCriteria(Class<T> persistentClass) {
		Criteria criteria = getSession().getCurrentSession().createCriteria(
				persistentClass);
		if (!checkEntityIsInstanceOfDeletable(persistentClass)) {
			criteria.add(Restrictions.eq("deleted", false));
		}
		return criteria;
	}

	/**
	 * @return
	 */

	public Session getCurrentSession() {
		return getSession().getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getpersistentClass() {
		if (persistentClass == null) {
			Type type = getClass().getGenericSuperclass();
			loop: while (true) {
				if (type instanceof ParameterizedType) {
					Type[] arguments = ((ParameterizedType) type)
							.getActualTypeArguments();
					for (Type argument : arguments) {
						if (argument instanceof Class
								&& BEntity.class
										.isAssignableFrom(((Class<T>) argument))) {
							persistentClass = (Class<T>) argument;
							break loop;
						}
					}
				}
				type = ((Class<T>) type).getGenericSuperclass();
				if (type == Object.class) {
					throw new RuntimeException(
							"Could not find a DatabaseObject subclass parameterized type");
				}
			}
		}
		return persistentClass;
	}

	/**
	 * @return
	 */
	private SessionFactory getSession() {
		return sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codexplo.cruder.dao.ICrudDao#save(org.codexplo.cruder.domain.BEntity)
	 */
	@Override
	public T save(T t) {
		if (t != null) {
			t.setDeleted(false);
			t.setCreationDate(new Date());
			t.setLastUpdatedDate(new Date());
			// log.debug("Saving... " + persistentClass.toString());
			getCurrentSession().save(t);
			return t;
		} else {
			log.debug(persistentClass.toString() + " is null");
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.codexplo.cruder.dao.ICrudDao#update(org.codexplo.cruder.domain.BEntity
	 * )
	 */
	@Override
	public T update(T t) {
		if (t != null) {
			t.setLastUpdatedDate(new Date());
			getCurrentSession().update(t);
			log.debug("updating ...." + t.toString());
			return findById(t.getId());
		}
		return null;
	}
}
