package com.kiblerdude.achievements.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable domain object representing an Achievement awarded to a user.
 *
 * @author kiblerj
 *
 */
public final class UserAchievement {
	// Identifier for the user that earned the achievement
	private final String userId;
	// Identifier for the achievement that was awarded
	private final String achievement;
	
	@JsonCreator
	public UserAchievement(
			@JsonProperty("user_id") String userId,
			@JsonProperty("achievement") String achievement) {
		this.userId = userId;
		this.achievement = achievement;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getAchievement() {
		return achievement;
	}
}
