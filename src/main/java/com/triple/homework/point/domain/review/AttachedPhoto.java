package com.triple.homework.point.domain.review;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;

@Getter
@Entity
public class AttachedPhoto {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "attached_photo_id", columnDefinition = "BINARY(16)")
	private UUID id;

	private String image;

	public AttachedPhoto() {
		this.id = UUID.randomUUID();
	}

	public static AttachedPhoto create() {
		return new AttachedPhoto();
	}

	@Override
	public String toString() {
		return "AttachedPhoto{" +
			"id=" + id +
			'}';
	}
}
