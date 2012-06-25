/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 20, 2012	
 **************/

package org.codexplo.cruder.util;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

/**
 * hibernate supports UserType. <b>MyBooleanType</b> is custom UserType to
 * convert boolean filed as bit in sql database
 * 
 * @see UserType
 * */

public class MyBooleanType implements UserType {

	private static final int[] SQL_TYPES = { Types.BIT };

	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == y) {
			return true;
		} else if (x == null || y == null) {
			return false;
		} else {
			return x.equals(y);
		}
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		assert (x != null);
		return x.hashCode();
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
			throws HibernateException, SQLException {

		return resultSet.getByte(names[0]) != 0;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index)
			throws HibernateException, SQLException {
		st.setByte(index, Boolean.TRUE.equals(value) ? (byte) 1 : (byte) 0);
	}

	@Override
	public Object replace(Object orginal, Object arg1, Object arg2)
			throws HibernateException {
		return orginal;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class returnedClass() {
		return Boolean.class;
	}

	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}
