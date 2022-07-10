package com.triple.homework.point.util.type;

import org.checkerframework.checker.units.qual.C;

public enum PointState {
	ACCUMULATION,
	CANCEL;

	public static PointState findByPointAmount(long pointAmount) {
		if (pointAmount > 0) {
			return ACCUMULATION;
		}
		return CANCEL;
	}
}
