package com.triple.homework.point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.triple.homework.point.domain.entity.PlaceTest;
import com.triple.homework.point.domain.entity.UserTest;
import com.triple.homework.point.domain.place.PlaceRepository;
import com.triple.homework.point.domain.user.UserRepository;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PointApplicationTests {
	@LocalServerPort
	int port;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlaceRepository placeRepository;

	public void setUp() {
		RestAssured.port = port;
		userRepository.save(UserTest.USER_1);
		placeRepository.save(PlaceTest.PLACE_1);
		placeRepository.save(PlaceTest.PLACE_2);
	}
}
