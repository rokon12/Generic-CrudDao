/****************
 * @author: Bazlur Rahman Rokon
 * @email: anm_brr@live.com	
 * @Dated: Jun 25, 2012	
 **************/

package org.codexplo.cruder.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

/**
 * <b>BEntity</b> means base entity. Its an abstract class. Every persistence
 * entity has to extend this base entity. there are 5 fields in this class.
 * <b>id</b> which represents identity of database entity. <b>creationDate</b>
 * holds the date when a row of a database is created for the first time. <b>
 * lastUpdatedDate</b> holds the date time of the row is updated or changed
 * <b>version</b> field is for optimistic locking. the version property is
 * automatically managed by Hibernate.and the property <b>deleted</b> is a flag
 * for soft delete. when we'll perform delete operation,basically we'll change
 * the value of deleted
 */

@MappedSuperclass
public abstract class BEntity implements Serializable {

	private static final long serialVersionUID = 2018197908223306190L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(fetch = FetchType.EAGER)
	private Long id;

	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "last_update_date")
	private Date lastUpdatedDate;
	@Column(name = "version")
	@Version
	private long version;

	@Type(type = " org.codexplo.cruder.util.MyBooleanType")
	@Column(name = "deleted", insertable = true, updatable = true)
	private boolean deleted;

	public Date getCreationDate() {
		return creationDate;
	}

	public Long getId() {
		return id;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public long getVersion() {
		return version;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
