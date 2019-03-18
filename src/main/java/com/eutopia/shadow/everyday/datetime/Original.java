package com.eutopia.shadow.everyday.datetime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/*
 * 项目开发阶段，有一个关于下单发货的需求：
 * 如果今天下午 3 点前进行下单，那么发货时间是明天，
 * 如果今天下午 3 点后进行下单，那么发货时间是后天，
 * 如果被确定的时间是周日，那么在此时间上再加 1 天为发货时间。
 */
public class Original {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static JodaTimeUtils jodaTimeUtils = new JodaTimeUtils();
	
	public static void main(String[] args) {
		Goods goods = new Goods();
		/*Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -2);
		send(calendar.getTime(), goods);*/
		sendByJodaTime(new Date(), goods);
	}
	
	static void send(Date time, Goods goods) {
		Date date = checkDateTime(time);
		System.out.println("send!, datetime is " + sdf.format(date));
	}
	
	static void sendByJodaTime(Date time, Goods goods) {
		Date date = jodaTimeUtils.calculateDistributionTimeByOrderCreateTime(time);
		System.out.println("send!, datetime is " + sdf.format(date));
	}
	
	static Date checkDateTime(Date sendTime) {
		Calendar sendCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		sendCalendar.setTime(sendTime);
		if (sendCalendar.get(Calendar.HOUR_OF_DAY) < 15) {
			sendCalendar.add(Calendar.DATE, 1);
		} else {
			sendCalendar.add(Calendar.DATE, 2);
		}
		if (sendCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			sendCalendar.add(Calendar.DATE, 1);
		}
		return sendCalendar.getTime();
	}
}

class Goods {
	
}




