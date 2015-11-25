package com.kiblerdude.achievements;

import com.kiblerdude.achievements.domain.Activity;

/**
 * Defines an Achievement type that may be awarded to a user for completing an
 * activity.
 * <p>
 * Every Achievement awarded by the system must implement this interface.
 * 
 * @author kiblerj
 *
 */
public interface Achievement {

	/**
	 * Returns the name of the achievement.
	 * 
	 * @return The name of the achievement.
	 */
	String getName();

	/**
	 * Determines if the achievement was earned based on the provided
	 * {@link Activity}.
	 * 
	 * @param activity
	 *            The {@link Activity} performed by the user.
	 * @return <code>true</code> if the user earned the achievement, <code>false</code> otherwise.
	 */
	boolean isAchieved(Activity activity);
}
