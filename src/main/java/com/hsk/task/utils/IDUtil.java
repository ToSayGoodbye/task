package com.hsk.task.utils;

public class IDUtil {
		// 位数，默认是8位
		private final static long w = 100000000;
	 
		public synchronized static String createID() {
			long r = 0;
			r = (long) ((Math.random() + 1) * w);
			return System.currentTimeMillis() + String.valueOf(r).substring(1);
		}
}
