package com.triple.homework.point.domain.user;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User {
	@Id
	@Column(name = "user_id", columnDefinition = "BINARY(16)")
	private UUID id;
	private String name;

	public static User from(String name) {
		return new User(name);
	}

	private User(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User)o;

		if (!id.equals(user.id))
			return false;
		return name.equals(user.name);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		return result;
	}
}
