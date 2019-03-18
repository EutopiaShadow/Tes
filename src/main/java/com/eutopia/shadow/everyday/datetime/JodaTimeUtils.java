package com.eutopia.shadow.everyday.datetime;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/*
 * 项目开发阶段，有一个关于下单发货的需求：
 * 如果今天下午 3 点前进行下单，那么发货时间是明天，
 * 如果今天下午 3 点后进行下单，那么发货时间是后天，
 * 如果被确定的时间是周日，那么在此时间上再加 1 天为发货时间。
 */
public class JodaTimeUtils {
	
	final DateTime DISTRIBUTION_TIME_SPLIT_TIME = new DateTime().withTime(15, 0, 0, 0);
	
	public Date calculateDistributionTimeByOrderCreateTime(Date orderCreateTime) {
		DateTime orderCreateDateTime = new DateTime(orderCreateTime);
		Date tomorrow = orderCreateDateTime.plusDays(1).toDate();
		Date theDayAfterTomorrow = orderCreateDateTime.plusDays(2).toDate();
		return orderCreateDateTime.isAfter(DISTRIBUTION_TIME_SPLIT_TIME) ? wrapDistributionTime(theDayAfterTomorrow) : wrapDistributionTime(tomorrow);
	}
	
	private Date wrapDistributionTime(Date distributeTime) {
		DateTime currentDistributionDateTime = new DateTime(distributeTime);
		DateTime plusOneDay = currentDistributionDateTime.plusDays(1);
		boolean isSunday = DateTimeConstants.SUNDAY == currentDistributionDateTime.getDayOfWeek();
		return isSunday ? plusOneDay.toDate() : currentDistributionDateTime.toDate();
	}
}
