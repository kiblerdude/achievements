package com.kiblerdude.achievements.domain;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ActivityTest {

	@Test
	public void testEquals() {
		ActivityMetric metric = new ActivityMetric("fuel", Arrays.asList(100, 100, 100, 1));
		
		Activity a1 = new Activity("user", "fuel", "2015-12-25T12:00:00", metric);
		Activity a2 = new Activity("user", "fuel", "2015-12-25T12:00:00", metric);
		Activity a3 = new Activity("user2", "fuel", "2015-12-25T12:00:00", metric);
		Activity a4 = new Activity("user", "running", "2015-12-25T12:00:00", metric);
		
		assertTrue(a1.equals(a1));
		assertFalse(a1.equals(null));
		assertTrue(a1.equals(a2));
		assertFalse(a1.equals(a3));
		assertFalse(a1.equals(a4));
		assertFalse(a1.equals(new Object()));
	}

}
