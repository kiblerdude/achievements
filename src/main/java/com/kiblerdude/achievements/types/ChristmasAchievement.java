package com.kiblerdude.achievements.types;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;

import com.kiblerdude.achievements.Achievement;
import com.kiblerdude.achievements.domain.Activity;
import com.kiblerdude.achievements.domain.ActivityMetric;

/**
 * The "CHRISTMAS" achievement!
 * <p>
 * The requirements to earn this achievement are as follows:
 * <li>The activity_type and metric type must be "fuel"
 * <li>The activity must be performed on December 25th
 * <li>The total fuel for the activity must be greater than 300
 */
public class ChristmasAchievement implements Achievement {
	
	private static final Logger LOG = Logger.getLogger(ChristmasAchievement.class);

	private static final String NAME = "CHRISTMAS";
	
	private static final String REQUIRED_ACTIVITY_TYPE = "fuel";
	private static final int REQUIRED_TOTAL_FUEL = 300;
	
	private final DateTimeFormatter formatter;
	
	public ChristmasAchievement() {
		this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	}
	
	public String getName() {
		return NAME;
	}

	public boolean isAchieved(Activity activity) {
		
		if (activity == null || activity.getMetric() == null) {
			LOG.error("Activity or Metrics are null!");
			return false;
		}
		
		boolean correctType = REQUIRED_ACTIVITY_TYPE.equals(activity.getActivityType());
		boolean correctDate = checkDate(activity.getDateTime());
		boolean correctFuel = checkFuel(activity.getMetric());
		
		return (correctType && correctDate && correctFuel);
	}
	
	/**
	 * Helper method to check the date.
	 * 
	 * @param dateTime
	 * @return
	 */
	private boolean checkDate(String dateTime) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, formatter);
		LOG.debug("Date is " + localDateTime);
		boolean correctMonth = localDateTime.getMonth().equals(Month.DECEMBER);
		boolean correctDay = localDateTime.getDayOfMonth() == 25;		
		return (correctMonth && correctDay);
	}
	
	/**
	 * Helper method to check the metrics.
	 * 
	 * @param metrics
	 * @return
	 */
	private boolean checkFuel(ActivityMetric metrics) {
		// the metric type must also be fuel
		if (!REQUIRED_ACTIVITY_TYPE.equals(metrics.getType())) {
			return false;
		}
		int totalFuel = metrics.getData().stream().reduce(0, (sum, d) -> sum += d);
		LOG.debug("Total fuel is " + totalFuel);
		return totalFuel > REQUIRED_TOTAL_FUEL;
	}
}
