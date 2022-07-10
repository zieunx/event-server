package com.triple.homework.point.dto.point;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPointDto {
	@Builder.Default
	private int page = 1;
	@Builder.Default
	private int size = 5;
	private String userId;
}
