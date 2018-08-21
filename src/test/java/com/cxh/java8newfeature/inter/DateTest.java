package com.cxh.java8newfeature.inter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class DateTest {
	public static void main(String[] args) {
		
		formatterDate();
	}
	
	public static void DateExample(){
		//使用默认时区，查询系统时钟，返回日期对象
		LocalDate nowDate =LocalDate.now();
		System.out.println("java提醒您，现在是："+nowDate);
		
		//得到指定日期对象，ps，第三个参数是月份的某一天
		LocalDate specifyDate = LocalDate.of(2055,  Month.JULY, 1);
		System.out.println("跳跃机准备就绪，即将到达："+specifyDate);
		
		
		//得到指定时区的当前日期
		LocalDate todayChicago  = LocalDate.now(ZoneId.of("America/Chicago"));
		System.out.println("美国-芝加哥当前日期是"+todayChicago);
		
		
		//得到1970-01-01之后或者之前的日期。参数是天数，即1970-01-01过后多少天的日期。、
		//如果参数是负数，则是之前的日期
		long dateCount=365L;
		LocalDate date = LocalDate.ofEpochDay(dateCount);
		System.out.println("1970-01-01之后过了"+dateCount+"天后的日期是："+date);
		
		//从某年的01-01号开始，过了指定天数后的日期，ps，第二个参数只能是1-366
		int datecount = 322;
		int year = 2016;
		LocalDate localDate = LocalDate.ofYearDay(year, datecount);
		System.out.println("从"+year+"-01-01号开始，过了"+datecount+"天后的日期是"+localDate);
		
	}
	
	public static void timeExample(){
		//得到当前时间
		LocalTime time = LocalTime.now();
		System.out.println("java为您报时，北京时间："+time);
		
		//指定时间，获取实例,第三个参数是纳秒，取值范围为：0-999,999,999
		LocalTime  specificTime  = LocalTime.of(23, 59, 59,999999999);
		System.out.println("夜 ："+specificTime);
		
		LocalTime timeChicago = LocalTime.now(ZoneId.of("America/Chicago"));
		System.out.println("美国芝加哥为您报时，华盛顿时间："+timeChicago);
		
		//从00:00:00:000开始经过多少秒后的时间
		LocalTime specificSecondTime  = LocalTime.ofSecondOfDay(60L);
		System.out.println("零时零点零分后经过60秒的时间是"+specificSecondTime);
		
	}
	
	public static void dateAndTimeExample(){
		
		//得到当前时间和日期
		LocalDateTime nowDateTime = LocalDateTime.now();
		System.out.println("当前日期和时间为："+nowDateTime);
		
		//用现在的日期和现在的时间实例化一个时间-日期对象
		LocalDateTime specificDateTime = LocalDateTime.of(LocalDate.now(), LocalTime.now());
		System.out.println("您指定的时间为"+specificDateTime);
		
		LocalDateTime specificdateTime = LocalDateTime.of(2059, 12, 12, 23, 59, 59, 999999999);
		System.out.println("使用纯数字指定日期和时间："+specificdateTime);
		
		LocalDateTime sydneyToday = LocalDateTime.now(ZoneId.of("Australia/Sydney"));
		System.out.println("澳大利亚的悉尼报时："+sydneyToday);
		
		//自1970-01-01T00:00:000后经过x秒x纳秒后得到的日期和时间，第三个参数是与格林威治/ UTC的时区偏移，设置为UTC即就是不偏移
		LocalDateTime dateFromBase = LocalDateTime.ofEpochSecond(100000, 999999999, ZoneOffset.UTC);
		System.out.println("自1970-01-01T00:00:000后经过x秒x纳秒后得到的日期和时间:"+dateFromBase);
		
	}
	
	/**
	 * Instant产生机器可读的时间格式，它以Unix时间戳存储时间
	 * 演示ofEpochMilli，now,Duration的ofDays
	 */
	public static  void instantExample(){
		
		//产生当前的时间戳 ps:年月日能看出来，其他就乱了
		Instant timeStamp = Instant.now();
		System.out.println("当前的时间戳为 ："+timeStamp);
		
		//从1970-01-01T00:00:00Z.时期获取一个时间戳，使用毫秒
		Instant timeStampfrombase = Instant.ofEpochMilli(11);
		System.out.println("从1970-01-01T00:00:00Z.时期获取一个时间戳"+timeStampfrombase);
		
		//给它天数，它返回天数经过的小时数
		Duration du = Duration.ofDays(2L);
		System.out.println(du);
	}
	
	/**
	 * 日期/时间工具
	 */
	public static void timeAndDateUitl(){
			
		//获取当前年数，检查是不是闰年
		LocalDate date = LocalDate.now();
		System.out.println("今年是"+date.getYear()+"年，是"+(date.isLeapYear()?"闰年":"平年"));
		
		//比较两个日期，看谁在谁之前
		LocalDate afterDate = LocalDate.of(2020, 3, 13);
		System.out.println("现在日期"+(date.isBefore(afterDate)?"在":"不在")+afterDate+"之前");
		
		//组装日期和时间--得到日期时间对象LocalDateTime
		LocalTime time = LocalTime.now();
		System.out.println(date+"组装"+time+"变为"+date.atTime(time));
		
		//加减时间
		System.out.println("当前时间+1s   "+time.plusSeconds(1L));
		//PS:我觉得周围时间开始加快了
		
		System.out.println("当前时间-1s   "+time.minusSeconds(1L));
		
		System.out.println("当前日期加一周"+date.plusWeeks(1L));
		
		//TemporalAdjuster调整日期
		System.out.println("得到本月1号的日期 "+date.with(TemporalAdjusters.firstDayOfMonth()));
		
		LocalDate lastdayOfYear = date.with(TemporalAdjusters.lastDayOfYear());
		System.out.println("得到该年的最后一天 "+lastdayOfYear);
		
		//计算该日期和指定的日期之间的日期差，结果为P4M14D
		//使用Period.between（LocalDate，LocalDate）也可以达到相同的效果
		Period period = date.until(lastdayOfYear);
		System.out.println(period);
		
		
		
	}
	
	/**
	 * 解析，格式化日期/时间对象
	 */
	public static void formatterDate(){
		
		LocalDate today  = LocalDate.now();
		System.out.println("默认的日期格式为"+today);
		
		System.out.println("使用中文分割符"+today.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
		
		System.out.println("使用ISO格式"+today.format(DateTimeFormatter.BASIC_ISO_DATE));
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println("默认的日期时间格式为  "+now);
		System.out.println("使用中文分割符"+now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日  HH小时MM分钟SS秒")));
		System.out.println("使用ISO格式"+now.format(DateTimeFormatter.BASIC_ISO_DATE));
		
		Instant tiemStamp = Instant.now();
		System.out.println("默认的瞬间时间格式为  "+tiemStamp);
	}
	
}
