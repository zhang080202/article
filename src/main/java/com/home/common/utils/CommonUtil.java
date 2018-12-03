package com.home.common.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommonUtil {
	
	/**
	 * timestamp转换 LocalDateTime
	 * @param timestamp
	 * @return
	 */
	public static LocalDateTime timestamp2LocalDateTime(Timestamp timestamp) {
		long time = timestamp.getTime();
		Instant instant = Instant.ofEpochMilli(time);
	    ZoneId zone = ZoneId.systemDefault();
	    return LocalDateTime.ofInstant(instant, zone);
	}
}
