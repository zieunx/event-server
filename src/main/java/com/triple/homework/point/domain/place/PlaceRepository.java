package com.triple.homework.point.domain.place;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triple.homework.point.domain.place.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, UUID> {
}
