package com.kiblerdude.achievements.types;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.kiblerdude.achievements.domain.Activity;
import com.kiblerdude.achievements.domain.ActivityMetric;

/**
 * Unit test for the {@link ChristmasAchievement}.
 */
public class ChristmasAchievementTest 
{
	@Test
	public void test() {
		
		ActivityMetric goodMetric = new ActivityMetric("fuel", Arrays.asList(100, 100, 100, 1));
		ActivityMetric wrongType = new ActivityMetric("other", Arrays.asList(100));
		ActivityMetric wrongScore = new ActivityMetric("fuel", Arrays.asList(100, 100, 100));
		
		ChristmasAchievement instance = new ChristmasAchievement();
		
		assertTrue(instance.isAchieved(new Activity("user", "fuel", "2015-12-25T12:00:00", goodMetric)));
		assertFalse(instance.isAchieved(new Activity("user", "fuel", "2015-12-24T12:00:00", goodMetric)));
		assertFalse(instance.isAchieved(new Activity("user", "fuel", "2015-11-25T12:00:00", goodMetric)));
		assertFalse(instance.isAchieved(new Activity("user", "other", "2015-12-25T12:00:00", goodMetric)));
		assertFalse(instance.isAchieved(new Activity("user", "fuel", "2015-12-25T12:00:00", wrongType)));
		assertFalse(instance.isAchieved(new Activity("user", "fuel", "2015-12-25T12:00:00", wrongScore)));		
		assertFalse(instance.isAchieved(null));
		assertFalse(instance.isAchieved(new Activity("user", "fuel", "2015-12-25T12:00:00", null)));
		assertFalse(instance.isAchieved(new Activity("user", null, "2015-12-25T12:00:00", goodMetric)));
		assertFalse(instance.isAchieved(new Activity("user", "fuel", null, goodMetric)));
	}
}
