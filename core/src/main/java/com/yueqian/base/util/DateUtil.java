package com.yueqian.base.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class DateUtil {

	
	/**
	 * 用来计算发送两次验证码之间的间隔 () 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static long getBetweenSecond(Date d1 ,Date d2 ){
		return  Math.abs((d1.getTime() - d2.getTime())/1000) ;
	}
	/**
	 * 得到一天的最后一秒钟
	 * 
	 */
	public static Date endOfDay(Date d) {
		return DateUtils.addSeconds(
				DateUtils.addDays(DateUtils.truncate(d, Calendar.DATE), 1), -1);
	}

	
}