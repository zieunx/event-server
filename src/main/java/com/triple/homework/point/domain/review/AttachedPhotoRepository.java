package com.triple.homework.point.domain.review;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachedPhotoRepository extends JpaRepository<AttachedPhoto, UUID> {
}
