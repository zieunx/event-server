package com.triple.homework.point.domain.place;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Place {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "place_id", columnDefinition = "BINARY(16)")
	private UUID id;

	private String name;

	public static Place from(String name) {
		return new Place(name);
	}

	private Place(String name) {
		this.id = UUID.randomUUID();
		this.name = name;
	}
}
