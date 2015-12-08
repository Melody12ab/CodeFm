package com.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 *
 */
public class DateUtil {
	
	public static final String DATE_FMT1 = "yyyy-MM-dd";
	
	public static final String DATE_FMT2 = "yyyyMMddHHmmss";
	
	public static final String DATE_FMT3 = "yyyy-MM-dd HH-mm-ss";
	
	public static final String DATE_FMT4 = "yyyy-MM-dd HH:mm:ss";
	

	/**
	 * 把时间转换成字符串
	 * @param date  时间
	 * @param formatStr  要转换的格式
	 * @return
	 */
	public static String dateToString(Date date,String formatStr){
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		sdf.format(date);
		return sdf.format(date);
	}
	
	/**
	 * 把时间字符串转换成其他字符串格式
	 * @param date  时间
	 * @param formatStr1  原格式
	 *  @param formatStr2  要转换的格式
	 * @return
	 */
	
	public static String fmtToFmt(String dateStr,String formatStr1,String formatStr2){
		return dateToString(stringToDate(dateStr,formatStr1),formatStr2);
	}
	
	public static Date stringToDate(String dateStr,String formatStr){
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);
		Date date;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	public static Date dateAddDays(Date date,int addDays){
		if(null==date){
			return null;
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, addDays);
		return cal.getTime();
	}
	
	/**
	 * 获取昨天的年、月、日 格式为2014-01-09
	 * @return
	 */
	public static String getFrontDay(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date date = calendar.getTime();
		return sdf.format(date);
		
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FMT3);
		Date date = calendar.getTime();
		return sdf.format(date);
	}
	
	/**
	 * 获取昨天的月、日格式为01月9日
	 * @return
	 */
	public static String getFrontDate(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date date = calendar.getTime();
		String dates[]=sdf.format(date).split("年");
		return dates[1];
		
	}
	
	/**
	 * 获取某一天的月、日格式为01月9日
	 * @return
	 */
	public static String getDate(Date date,String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String dates[]=sdf.format(date).split("年");
		return dates[1];
		
	}
	
	/**
	 * 获取某一天是星期几,返回字符串为:星期五
	 */
	public static String getWeekByDate(Date date){
		SimpleDateFormat sdfs = new SimpleDateFormat("EEEE");  
        String week = sdfs.format(date);
        return week;
	}
	
	
	/**
	 * 获取昨天是星期几
	 * @return
	 */
	public static String getFrontWeek(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date date = calendar.getTime();
	    SimpleDateFormat sdfs = new SimpleDateFormat("EEEE");  
        String week = sdfs.format(date);
		return week;
	}
	
	public static String dayToMin(String days){
		double day = Double.parseDouble(days);
		return (day*24*60)+"";
	}
	public static String hourToMin(String hours){
		double hour =  Double.parseDouble(hours);
		return (hour*60)+"";
	}
	public static String minToHour(String mins){
		double min = Integer.parseInt(mins);
		return (min/60)+"";
	}
	
	public static String minToDay(String mins) {
		double min = Double.parseDouble(mins);
		return (min/60/24)+"";
	}
	public static void main(String[] args) {
		System.out.println(minToHour("240"));
	}

	
	 /**
	  *
	  * @param min
	  * @return day hour min
	  */
	 public static String minConvertDayHourMin(Double min){
	  String  html="0分";
	  if(min!=null){
	   Double m=(Double) min;
	   String format;
	   Object[] array;
	   Integer days =(int) (m/(60*24));
	   Integer hours = (int) (m/(60)-days*24);
	   Integer minutes = (int) (m-hours*60-days*24*60);
	   if(days>0){
		   if(hours>0){
			   if(minutes>0){
				    format="%1$,d天%2$,d小时%3$,d分钟";
				    array=new Object[]{days,hours,minutes};
			   }else{
				   format="%1$,d天%2$,d小时";
				   array=new Object[]{days,hours,minutes};
			   }
		   }else{
			   format="%1$,d天";
			   array=new Object[]{days,hours,minutes};
		   }
	   }else if(hours>0){
		   if(minutes>0){
			    format="%1$,d小时%2$,d分钟";
			    array=new Object[]{hours,minutes};
		   } else{
		    	format="%1$,d小时";
		    	array=new Object[]{hours,minutes};
		   }
	   }else{
	    format="%1$,d分钟";
	    array=new Object[]{minutes};
	   }
	   html= String.format(format, array);
	  }
	  return html;
	 }
	 
	 
	 /**
		 *根据 tour的持续时间
		 * 把分钟转换成可读的字符串
		 */
		public static String changeTimeToRead(int minutes){
			if(minutes<0)return "";
				String  str="A"+minutes/1440+"天"+(minutes%1440)/60+"小时"+(minutes%1440)%60+"分钟";
				return str.replace("时0分钟", "时").replace("天0小时", "天").replace("A0天", "").replace("A", "");
		}
	/**
	  *
	  * @param day
	  * @param hour
	  * @param min
	  * @return min
	  */
	 public static int dayHourMinConvertMin(int day,int hour,int min){
	  int days=day*24*60;
	  int hours=hour*60;
	  return days+hours+min;
	 } 
	
}
