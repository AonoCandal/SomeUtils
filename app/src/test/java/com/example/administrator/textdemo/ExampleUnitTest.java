package com.example.administrator.textdemo;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
	@Test
	public void addition_isCorrect() throws Exception {
		assertEquals(4, 2 + 2);
	}

	@Test
	public void test() throws Exception {
		String time = "3676000";
		try {
			long timestamp = Long.parseLong(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date(timestamp));
			String hour = calendar.get(Calendar.HOUR) + ":";
			String min = calendar.get(Calendar.MINUTE) + "'";
			String second = calendar.get(Calendar.SECOND) + "''";
			time =  hour + min + second;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			time = "";
		}
		System.out.print(time);
	}

	@Test
	public void test1() throws Exception {
		String time = "3600000";
		try {
			long timestamp = Long.parseLong(time);
			long total = timestamp / 1000;
			long second = total % 60;
			long min = total / 60;
			long hour = 0;
			if (min >= 60) {
				hour = min / 60;
				min = min % 60;
			}
			String h = hour == 0 ? "" : hour + ":";
			String m = min == 0 ? "00'" : min + "'";
			String s = second == 0 ? "00''" : second + "''";
			time = h + m + s;
		} catch (NumberFormatException e) {
			e.printStackTrace();
			time = "";
		}
		System.out.print(time);
	}


}