package com.hsk.task.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期工具类
 */
public class DateUtils {

	public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
	public static final String DATE_FORMAT_2 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_3 = "yyyyMMdd";
	public static final String DATE_FORMAT_4 = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_5 = "yyyy";
	public static final String DATE_FORMAT_6 = "yyyyMM";
	public static final String DATE_FORMAT_7 = "MM";
	public static final String DATE_FORMAT_8 = "yyyy-MM-dd 00:00:00";

	public static final int DATE_INTERVAL_DAY_7 = 7;
	public static final String[] MONTH_ARRAY = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
			"12" };
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
	public static final SimpleDateFormat DATE_FORMAT_MONTH = new SimpleDateFormat("yyyyMM");

	/**
	 * 根据一定格式的字符串，得到日期对象
	 *
	 * @param str
	 *            日期字符串
	 * @param format
	 *            格式
	 * @return 日期
	 */
	public static Date strToDate(String str, String format) {
		Date dateTime = null;
		try {
			if (!(str == null || str.equals(""))) {
				SimpleDateFormat formater = new SimpleDateFormat(format);
				dateTime = formater.parse(str);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dateTime;
	}

	/**
	 * 将日期转换成字符串
	 *
	 * @param date
	 *            日期对象
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	public static String dateToString(Date date, String format) {
		String str = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			str = formatter.format(date);
		}
		return str;
	}

	/**
	 * 按照一定格式得到当天日期
	 *
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	public static String getNowDate(String format) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(new Date());
		// int effd = -1;
		Date enddate = nowTime.getTime();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(enddate);
	}

	/**
	 * 按照一定格式，得到当月的第一天日期
	 *
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	@SuppressWarnings("static-access")
	public static String getNowMonthFirstDay(String format) {
		GregorianCalendar date = new GregorianCalendar();
		date.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat(format);
		date.set(date.get(date.YEAR), date.get(date.MONTH), date.get(date.DATE));
		return df.format(date.getTime());
	}

	/**
	 * 按照一定格式，得到前一天日期
	 *
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	public static String getYesterdayDate(String format) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(new Date());
		int effd = -1;
		nowTime.add(Calendar.DATE, effd);
		Date enddate = nowTime.getTime();

		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(enddate);
	}

	/**
	 * 按照一定格式，得到明天日期
	 *
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	public static String getTomorrowDate(String format) {
		Calendar nowTime = Calendar.getInstance();
		nowTime.setTime(new Date());
		int effd = 1;
		nowTime.add(Calendar.DATE, effd);
		Date enddate = nowTime.getTime();

		// Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		return df.format(enddate);
	}

	/**
	 * 按照一定格式，得到上个月的今天日期
	 *
	 * @param format
	 *            格式
	 * @return 日期字符串
	 */
	@SuppressWarnings("static-access")
	public static String getLastMon(String format) {
		Date dt = new Date();
		SimpleDateFormat df = new SimpleDateFormat(format);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dt);
		gc.add(2, -1);
		gc.set(gc.get(gc.YEAR), gc.get(gc.MONTH), gc.get(gc.DATE));
		return df.format(gc.getTime());
	}

	/**
	 * 日期加天数
	 *
	 * @param date
	 *            日期
	 * @param addDays
	 *            增加天数
	 * @return 日期
	 */
	public static Date dateAddDays(Date date, int addDays) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, addDays);
		return cal.getTime();
	}

	/**
	 * 日期加分钟
	 *
	 * @param date
	 *            日期
	 * @param minutes
	 *            增加分钟
	 * @return 日期
	 */
	public static Date dateAddMinutess(Date date, int minutes) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);
		return cal.getTime();
	}

	/**
	 * 日期加小时
	 * 
	 * @param date
	 *            日期
	 * @param hour
	 *            增加小时
	 * @return 日期
	 */
	public static Date dateAddHour(Date date, int hour) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hour);
		return cal.getTime();
	}

	/**
	 * 日期加月数
	 *
	 * @param date
	 *            日期
	 * @param addMonths
	 *            增加月数
	 * @return 日期
	 */
	public static Date dateAddMonths(Date date, int addMonths) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, addMonths);
		return cal.getTime();
	}

	/**
	 * 日期加星期数
	 *
	 * @param date
	 *            日期
	 * @param addWeeks
	 *            增加星期数
	 * @return 日期
	 */
	public static Date dateAddWeeks(Date date, int addWeeks) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_YEAR, addWeeks);
		return cal.getTime();
	}

	/**
	 * 日期加年数
	 *
	 * @param date
	 *            日期
	 * @param addYears
	 *            增加年数
	 * @return 日期
	 */
	public static Date dateAddYears(Date date, int addYears) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, addYears);
		return cal.getTime();
	}

	/**
	 * 日期相差天数
	 *
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 天数
	 */
	public static int getDateLength(Date beginDate, Date endDate) {
		int length = (int) ((endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24));
		length++;
		return length;
	}

	/**
	 * 日期相差秒数
	 *
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 天数
	 */
	public static int getSecondByDate(Date beginDate, Date endDate) {
		return (int) ((endDate.getTime() - beginDate.getTime()) / 1000);
	}

	/**
	 * 今天还有多少秒
	 *
	 * @return
	 */
	public static int getDaySurplusSecond() {
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int SSS = cal.get(Calendar.MILLISECOND);
		// 时分秒（毫秒数）
		int millisecond = hour * 60 * 60 + minute * 60 + second;
		int daobanye = 24 * 60 * 60;
		int tobanye = daobanye - millisecond;
		return tobanye;
	}

	/**
	 * 获取当月最后一天
	 * 
	 * @param format
	 * @return
	 */
	public static String getLastDayOfMonth(String format) {
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取上一个月最后一天
	 * 
	 * @param format
	 * @return
	 */
	public static String getLastDayOfLastMonth(String format) {
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH,-1);
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}

	public static String formaterDate(Date dateTime) {
		StringBuilder sb = new StringBuilder();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		calendar.setTime(new Date());
		int todayYear = calendar.get(Calendar.YEAR);
		int todayMonth = calendar.get(Calendar.MONTH) + 1;
		int todayDate = calendar.get(Calendar.DATE);
		if (year == todayYear && month == todayMonth && date == todayDate) {
			sb.append("今日");
		} else if (year == todayYear && month == todayMonth && date == todayDate - 1) {
			sb.append("昨日");
		} else {
			sb.append(month < 10 ? ("0" + month) : month).append("月").append(date < 10 ? ("0" + date) : date)
					.append("日");
		}
		sb.append(" ").append(hour < 10 ? ("0" + hour) : hour).append(":")
				.append(minute < 10 ? ("0" + minute) : minute);

		return sb.toString();
	}

	public static String getTimestamp() {
		return String.valueOf(System.currentTimeMillis());
	}

	/**
	 * 秒转换为分钟
	 * 
	 * @param second
	 * @return
	 * @throws Exception
	 */
	public static String secondConvertMinute(Long second) throws Exception {
		if (null == second) {
			return "";
		}
		Long minute = second / 60;
		Long minuteTemp = second % 60;
		if (minuteTemp > 30) {
			minute = minute + 1;
		}
		if (minute == 0) {
			minute = 1L;
		}
		return minute.toString();
	}

	/**
	 * 饼状图中获取6年年份字符串 如果当前月份为一月份则返回字符串中没有当前年份
	 * 
	 * @param yue
	 * @return
	 */
	public static String getSixYearForPieChart(String yue) {
		Calendar c = Calendar.getInstance();
		int nowYear = c.get(Calendar.YEAR);
		String yearString = "";
		for (int i = 6; i > 0; i--) {
			if ("01".equals(yue)) {
				if (i == 1) {
					yearString += Integer.toString(nowYear - i);
					break;
				}
				yearString += Integer.toString(nowYear - i) + ",";
			} else if (!"01".equals(yue)) {
				if (i == 1) {
					yearString += Integer.toString(nowYear + 1 - i);
					break;
				}
				yearString += Integer.toString(nowYear + 1 - i) + ",";
			}
		}
		return yearString;
	}

	/**
	 * 获取上一个月
	 * 
	 * @return
	 */
	public static String getLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -1);
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
		String lastMonth = dft.format(cal.getTime());
		return lastMonth;
	}

	/**
	 * 描述:获取下一个月.
	 * 
	 * @return
	 */
	public static String getPreMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, 1);
		SimpleDateFormat dft = new SimpleDateFormat("yyyyMM");
		String preMonth = dft.format(cal.getTime());
		return preMonth;
	}

	/**
	 * 饼状图中获取统计数据中的当前年
	 * 
	 * 如果现在时间的月份为一月份则返回当前现在年的上一年
	 * 
	 * @param yue
	 * @return
	 */
	public static String getNowYearForPieChart() {
		Calendar c = Calendar.getInstance();
		int nowYear = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		if (1 == month) {
			nowYear = nowYear - 1;
		}
		return nowYear + "";
	}

	/**
	 * 获取起始时间和结束时间 起始时间为当前年-5, 如果当前月为1月,则起始时间为当前年-6 结束时间为当前时间的上一个月
	 * 
	 * @param yue
	 * @return
	 */
	public static Map<String, String> getStartAndEndDate() {
		Map<String, String> dateMap = new HashMap<String, String>();
		String nowYearMonth = DateUtils.getNowDate(DateUtils.DATE_FORMAT_6);
		Integer currentYear = Integer.valueOf(nowYearMonth.substring(0, 4));
		String begindate = (currentYear - 5) + "01";
		String nowMonth = nowYearMonth.substring(4, 6);
		String startDate = "";
		String endDate = "";
		if ("01".equals(nowMonth)) {
			startDate = (currentYear - 6) + "01";
			endDate = (currentYear - 1) + "12";
		} else {
			startDate = begindate;
			endDate = DateUtils.getLastMonth();
		}
		dateMap.put("startDate", startDate);
		dateMap.put("endDate", endDate);
		return dateMap;
	}

	/**
	 * 获取起始时间和结束时间2 如果当前月为1月,则起始时间为当前年-7;反之,起始时间为当前年-6 结束时间为当前时间的上一个月
	 * 
	 * @param yue
	 * @return
	 */
	public static Map<String, String> getStartAndEndDate2() {
		Map<String, String> dateMap = new HashMap<String, String>();
		String nowYearMonth = DateUtils.getNowDate(DateUtils.DATE_FORMAT_6);
		Integer currentYear = Integer.valueOf(nowYearMonth.substring(0, 4));
		String begindate = (currentYear - 6) + "01";
		String nowMonth = nowYearMonth.substring(4, 6);
		String startDate = "";
		String endDate = "";
		if ("01".equals(nowMonth)) {
			startDate = (currentYear - 7) + "01";
			endDate = (currentYear - 1) + "12";
		} else {
			startDate = begindate;
			endDate = DateUtils.getLastMonth();
		}
		dateMap.put("startDate", startDate);
		dateMap.put("endDate", endDate);
		return dateMap;
	}

	/**
	 * 获取起始时间和结束时间 如果当前月为1月,起始时间为当前年的上一年的一月份;否则为起始时间为当前年的一月份
	 * 如果当前月为1月,结束时间为当前年的上一年的12月份;否则为结束时间为当前年的上月
	 * 
	 * @return
	 */
	public static Map<String, String> getStartAndEndDateForNowYear() {
		Map<String, String> dateMap = new HashMap<String, String>();
		String nowYearMonth = DateUtils.getNowDate(DateUtils.DATE_FORMAT_6);
		Integer currentYear = Integer.valueOf(nowYearMonth.substring(0, 4));
		String begindate = currentYear + "01";
		String nowMonth = nowYearMonth.substring(4, 6);
		String startDate = "";
		String endDate = "";
		if ("01".equals(nowMonth)) {
			startDate = (currentYear - 1) + "01";
			endDate = (currentYear - 1) + "12";
		} else {
			startDate = begindate;
			endDate = DateUtils.getLastMonth();
		}
		dateMap.put("startDate", startDate);
		dateMap.put("endDate", endDate);
		return dateMap;
	}

	/**
	 * 根据年份查询一整年的每一天的集合,如2017年的集合为:2017-01-01到2017-12-31
	 * 
	 * @param queryYear
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getWholeYearForDays(Integer queryYear) throws ParseException {
		String start = queryYear + "-01-01";
		String end = queryYear + "-12-31";
		Date dBegin = DATE_FORMAT.parse(start);
		Date dEnd = DATE_FORMAT.parse(end);

		List<String> dayList = findDates(dBegin, dEnd);

		return dayList;
	}

	/**
	 * 根据年份查询一整年的每一月的集合,如2017年的集合为:2017-01到2017-12
	 * 
	 * @param queryYear
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getWholeYearForMonthes(Integer queryYear) throws ParseException {
		int length = MONTH_ARRAY.length;
		List<String> monthList = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			monthList.add(queryYear + "-" + MONTH_ARRAY[i]);
		}

		return monthList;
	}

	/**
	 * 根据起止时间,获取这段时间内的所有天数,按yyyyMMdd的格式
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<String> findDates(Date dBegin, Date dEnd) {
		List<String> dateList = new ArrayList<String>();
		dateList.add(DATE_FORMAT.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			dateList.add(DATE_FORMAT.format(calBegin.getTime()));
		}
		return dateList;
	}
	
	/**
	 * 根据起止时间,获取这段时间内的所有月,按yyyy-MM的格式
	 * 
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static List<String> findDatesForMonth(Date dBegin, Date dEnd) {
		List<String> dateList = new ArrayList<String>();
		dateList.add(DATE_FORMAT_MONTH.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		calEnd.setTime(dEnd);
		// 测试此日期是否在指定日期之后
		while (dEnd.after(calBegin.getTime())) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			calBegin.add(Calendar.MONTH, 1);
			dateList.add(DATE_FORMAT_MONTH.format(calBegin.getTime()));
		}
		return dateList;
	}

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String start = "201701";
		String end = "201710";
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		List<String> list = findDatesForMonth(startDate,endDate);
		for (String string : list) {
			System.out.println(string);
		}
	}
}
