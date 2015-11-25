package com.kiblerdude.achievements.domain;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Immutable domain object representing the metrics for an {@link Activity}.
 *
 * @author kiblerj
 *
 */
public final class ActivityMetric {

	private final String type;
	private final List<Integer> data;
	
	@JsonCreator
	public ActivityMetric(
			@JsonProperty("type") String type,
			@JsonProperty("data") List<Integer> data) {
		this.type = type;
		this.data = data;
	}
	
	public String getType() {
		return type;
	}
	
	public List<Integer> getData() {
		return Collections.unmodifiableList(data);
	}
	
	// No need for toString, equals, or hashcode yet
}
