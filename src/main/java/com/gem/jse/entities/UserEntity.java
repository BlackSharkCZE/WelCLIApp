package com.gem.jse.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * <strong>Created with IntelliJ IDEA</strong><br/>
 * User: BlackShark<br/>
 * Date: 21.8.15<br/>
 * Time: 12:35<br/>
 * <p>To change this template use File | Settings | File Templates.</p>
 */
@Entity
@Table(name = "user_entity")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 2L;

	@Id
	@Column(name = "id", columnDefinition = "SERIAL")
	@GeneratedValue(generator = "user_entity_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_entity_id_seq", sequenceName = "user_entity_id_seq", allocationSize = 1)
	private Long id;

	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column(name = "nick", length = 64)
	@NotNull
	private String nick;


	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				'}';
	}

	@PrePersist
	public void prePersist() {
		if (dateCreated == null) {
			dateCreated = Calendar.getInstance().getTime();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserEntity that = (UserEntity) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
}
